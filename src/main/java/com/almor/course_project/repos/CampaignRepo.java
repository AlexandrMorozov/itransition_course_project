package com.almor.course_project.repos;

import com.almor.course_project.model.Campaign;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepo extends CrudRepository<Campaign, Long> {

    Optional<Campaign> findByName(String name);
}
