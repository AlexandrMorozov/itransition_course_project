package com.almor.course_project.repos;

import com.almor.course_project.model.Campaign;
import org.springframework.data.repository.CrudRepository;

public interface CampaignRepo extends CrudRepository<Campaign, Long> {
}
