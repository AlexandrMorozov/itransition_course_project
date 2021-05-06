package com.almor.course_project.repos;

import com.almor.course_project.model.Bonus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BonusRepo extends CrudRepository<Bonus, Long> {

    //List<Bonus> findAllByUser
}
