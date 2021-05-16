package com.almor.course_project.service.service_interfaces;

import com.almor.course_project.model.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CloudService {

    List<Gallery> loadImages(List<MultipartFile> files);

    void deleteImages(List<Gallery> pictureLinks);
}
