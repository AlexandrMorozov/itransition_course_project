package com.almor.course_project.controller;

import com.almor.course_project.dto.CampaignDto;
import com.almor.course_project.dto.CampaignFileDto;
import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.dto.CampaignsDto;
import com.almor.course_project.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*@CrossOrigin(origins = "*", maxAge = 3600)*/
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

    @PostMapping("/add")
    public ResponseEntity<?> addCampaign(@RequestBody CampaignDto campaignDto) {
        campaignService.addCampaign(campaignDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/addimages")
    public ResponseEntity<?> test( @RequestParam("image") List<MultipartFile> multipartFiles) {
        System.out.println("w");
        return ResponseEntity.ok("");
    }

    @GetMapping("/getrated")
    public ResponseEntity<List<CampaignRatingDto>> getMostRatedCampaigns() {
        return ResponseEntity.accepted().body(campaignService.getMostRatedCampaigns());
    }

    @GetMapping("/getupdated")
    public ResponseEntity<List<CampaignDto>> getLastUpdatedCampaigns() {
        return ResponseEntity.ok(campaignService.getLastUpdatedCampaigns());
    }

    /*@GetMapping("/isexists")
    public ResponseEntity<?> is*/
}
