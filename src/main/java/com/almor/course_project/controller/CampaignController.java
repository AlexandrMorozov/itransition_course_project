package com.almor.course_project.controller;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.dto.mappings.CampaignMapping;
import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.Gallery;
import com.almor.course_project.service.entity_services.CampaignService;
import com.almor.course_project.service.cloud_service.CloudService;
import com.almor.course_project.service.entity_services.TagService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CloudService cloudService;

    @Autowired
    private TagService tagService;

    @GetMapping("/getcampaign")
    public CampaignDto getCampaignByName(String campaignName) {

        if (campaignService.isCampaignExists(campaignName)) {
            return Mappers.getMapper(CampaignMapping.class)
                    .entityToDto(campaignService.getCampaign(campaignName));
        }

        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity deleteCampaigns(@RequestBody List<CampaignDto> campaigns) {

        for (CampaignDto campaign : campaigns) {
            List<Gallery> deletedPictures = campaignService.deleteCampaign(campaign);
            cloudService.deleteImages(deletedPictures);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public boolean addCampaign(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                         @RequestParam("campaign") String campaign) {

        CampaignDto resultCampaign = campaignService.deserializeCampaign(campaign);

        if (!campaignService.isCampaignExists(resultCampaign.getName())) {

            if (files != null) {
                resultCampaign.addPictures(cloudService.loadImages(files));
            }

            Campaign camp = Mappers.getMapper(CampaignMapping.class).dtoToEntity(resultCampaign);
            camp.setTags(tagService.addNewTags(camp.getTags()));

            campaignService.createCampaign(camp);

            return true;
        }

        return false;
    }

    @PostMapping("/update")
    public boolean updateCampaign(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                            @RequestParam("campaign") String campaign) {

        CampaignDto resultCampaign = campaignService.deserializeCampaign(campaign);

        if (files != null) {
            resultCampaign.addPictures(cloudService.loadImages(files));
        }

        Campaign camp = Mappers.getMapper(CampaignMapping.class).dtoToEntity(resultCampaign);
        camp.setTags(tagService.addNewTags(camp.getTags()));

        campaignService.updateCampaign(camp);

        return true;
    }

    @GetMapping("/getrated")
    public List<CampaignRatingDto> getMostRatedCampaigns() {
        return campaignService.getMostRatedCampaigns();
    }

    @GetMapping("/getupdated")
    public List<CampaignRatingDto> getLastUpdatedCampaigns() {
        return campaignService.getLastUpdatedCampaigns();
    }

    @GetMapping("/donatemoney")
    public ResponseEntity donateMoney(int sumOfMoney, Long campaignId) {
        campaignService.receivePayment(campaignId, sumOfMoney);
        return ResponseEntity.ok().build();
    }

}
