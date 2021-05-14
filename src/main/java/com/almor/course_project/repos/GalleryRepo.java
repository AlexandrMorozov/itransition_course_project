package com.almor.course_project.repos;

import com.almor.course_project.model.Gallery;
import org.springframework.data.repository.CrudRepository;

public interface GalleryRepo extends CrudRepository<Gallery, Long> {
}
