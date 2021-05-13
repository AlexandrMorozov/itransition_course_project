package com.almor.course_project.controller;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.model.Gallery;
import com.almor.course_project.service.entity_services.CampaignService;
import com.almor.course_project.service.cloud_service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CloudService cloudService;

    @GetMapping("/getcampaign")
    public ResponseEntity<?> getCampaign(String campaignName) {

        if (campaignService.isCampaignExists(campaignName)) {
            return ResponseEntity.ok(campaignService.getCampaign(campaignName));
        }

        return new ResponseEntity<>("Campaign not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCampaign(@RequestBody List<CampaignDto> campaigns) {

        for (int i = 0; i < campaigns.size(); i++) {

            List<Gallery> deletedPictures = campaignService.deleteCampaign(campaigns.get(i));
            cloudService.deleteImages(deletedPictures);
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCampaign(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                         @RequestParam("campaign") String campaign) {

        CampaignDto resultCampaign = campaignService.deserializeCampaign(campaign);

        if (files != null) {
            resultCampaign.addPictures(cloudService.loadImages(files));
        }

        if (!campaignService.isCampaignExists(resultCampaign.getName())) {
            campaignService.createCampaign(resultCampaign);
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCampaign(@RequestParam(value = "image", required = false) List<MultipartFile> files,
                                            @RequestParam("campaign") String campaign) {

        CampaignDto resultCampaign = campaignService.deserializeCampaign(campaign);

        if (files != null) {
            resultCampaign.addPictures(cloudService.loadImages(files));
        }

        campaignService.updateCampaign(resultCampaign);

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
