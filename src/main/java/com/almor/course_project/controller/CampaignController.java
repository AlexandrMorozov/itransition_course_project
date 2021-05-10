package com.almor.course_project.controller;

import com.almor.course_project.dto.*;
import com.almor.course_project.dto.requests.CampaignIndexRequest;
import com.almor.course_project.model.Gallery;
import com.almor.course_project.service.CampaignService;
import com.almor.course_project.service.CloudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/*@CrossOrigin(origins = "*", maxAge = 3600)*/
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
    public ResponseEntity<?> deleteCampaign(@RequestBody CampaignsDto campaignsDto) {
        campaignService.deleteCampaigns(campaignsDto.getCampaigns());
        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    /*@PostMapping( value = "/add")*/
    public ResponseEntity<?> addCampaign(@RequestBody CampaignDto campaignDto) {
        //String f = "";
        if (!campaignService.isCampaignExists(campaignDto.getName())) {

            Long f = campaignService.createCampaign(campaignDto);

            CampaignIndexRequest request = new CampaignIndexRequest(f);
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.ok(new CampaignIndexRequest(null));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCampaign(@RequestBody CampaignDto campaignDto) {

        campaignService.updateCampaign(campaignDto);

        return ResponseEntity.ok("");
    }

    @PostMapping("/addimages")
    public ResponseEntity<?> loadCampaignImages(@RequestParam("image") List<MultipartFile> files,
                                                @RequestParam("id") String id) {
        System.out.println("qqqqqqq");

        List<Gallery> imageLinks = cloudService.loadImages(files);

        //campaignService.attachImages(imageLinks, userId);

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

}
