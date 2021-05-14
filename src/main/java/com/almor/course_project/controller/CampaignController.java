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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*@Controller*/
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
    public ResponseEntity<?> getCampaign(String campaignName) {

        if (campaignService.isCampaignExists(campaignName)) {
            return ResponseEntity.ok(campaignService.getCampaign(campaignName));
        }

        return new ResponseEntity<>("Campaign not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCampaign(@RequestBody List<CampaignDto> campaigns) {

        for (CampaignDto campaign : campaigns) {

            List<Gallery> deletedPictures = campaignService.deleteCampaign(campaign);
            cloudService.deleteImages(deletedPictures);
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCampaign(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                         @RequestParam("campaign") String campaign) {

        CampaignDto resultCampaign = campaignService.deserializeCampaign(campaign);
        Campaign camp = Mappers.getMapper(CampaignMapping.class).dtoToEntity(resultCampaign);

        if (!campaignService.isCampaignExists(resultCampaign.getName())) {

            if (files != null) {
                resultCampaign.addPictures(cloudService.loadImages(files));
            }

            camp.setTags(tagService.addNewTags(camp.getTags()));

            campaignService.createCampaign(camp);
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCampaign(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                            @RequestParam("campaign") String campaign) {

        CampaignDto resultCampaign = campaignService.deserializeCampaign(campaign);
        Campaign camp = Mappers.getMapper(CampaignMapping.class).dtoToEntity(resultCampaign);

        if (files != null) {
            resultCampaign.addPictures(cloudService.loadImages(files));
        }

        camp.setTags(tagService.addNewTags(camp.getTags()));

        campaignService.updateCampaign(camp);

        return ResponseEntity.ok("");
    }

    @GetMapping("/getrated")
    public ResponseEntity<List<CampaignRatingDto>> getMostRatedCampaigns() {
        return ResponseEntity.accepted().body(campaignService.getMostRatedCampaigns());
    }

    @GetMapping("/getupdated")
    public ResponseEntity<List<CampaignRatingDto>> getLastUpdatedCampaigns() {
        return ResponseEntity.accepted().body(campaignService.getLastUpdatedCampaigns());
    }

    @GetMapping("/donate")
    public void donateMoney() {

    }

}
