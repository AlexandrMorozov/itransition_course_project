package com.almor.course_project.controller;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignsDto;
import com.almor.course_project.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/main")
    public ResponseEntity<?> getCampaign(String campaignName) {

        if (campaignService.isCampaignExists(campaignName)) {
            return ResponseEntity.ok(new CampaignDto());
        }
        return new ResponseEntity<>("Campaign not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCampaign(@RequestBody CampaignsDto campaignsDto) {
        campaignService.deleteCampaigns(campaignsDto.getCampaigns());
        return ResponseEntity.ok("");
    }

    @PostMapping("/test1")
    public ResponseEntity<?> addCampaign(@RequestBody CampaignDto campaignDto) {
        campaignService.addCampaign(campaignDto);
        return ResponseEntity.ok("");
    }
}
