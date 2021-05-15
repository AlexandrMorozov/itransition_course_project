package com.almor.course_project.repos;

import com.almor.course_project.dto.CampaignRatingDto;
import com.almor.course_project.model.Campaign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepo extends CrudRepository<Campaign, Long> {

    Optional<Campaign> findByName(String name);

    @Query("SELECT new com.almor.course_project.dto.CampaignRatingDto(" +
            "c.id, c.name, c.description, AVG(ur.rating), c.lastUpdateDate, c.user)" +
            " FROM Campaign c " +
            "INNER JOIN UsersRatings ur ON c.id = ur.id.campaignid " +
            "GROUP BY c.name, c.id ORDER BY AVG(ur.rating) DESC")
    List<CampaignRatingDto> findMostRatedCampaigns(Pageable pageable);


    @Query("SELECT new com.almor.course_project.dto.CampaignRatingDto(" +
            "c.id, c.name, c.description, AVG(ur.rating), c.lastUpdateDate,c.user)" +
            " FROM Campaign c " +
            "LEFT JOIN UsersRatings ur ON c.id = ur.id.campaignid " +
            "GROUP BY c.name, c.id ORDER BY c.lastUpdateDate DESC")
    List<CampaignRatingDto> findRecentlyUpdatedCampaigns(Pageable pageable);

}
