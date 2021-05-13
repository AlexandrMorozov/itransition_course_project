package com.almor.course_project.controller;

import com.almor.course_project.model.Campaign;
import com.almor.course_project.model.User;
import com.almor.course_project.repos.CampaignRepo;
import com.almor.course_project.repos.UserRepo;
import com.almor.course_project.service.entity_services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BonusController {

    @Autowired
    RatingService ratingService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CampaignRepo campaignRepo;

    @GetMapping("/testbase")
    public void addBonus() {

        User user = userRepo.findById(new Long(5)).get();
        Campaign campaign = campaignRepo.findById(new Long(108)).get();

        ratingService.rateCampaign(user, campaign, 4);

    }
}
