package com.almor.course_project.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudService {

    private Cloudinary cloudinary = new Cloudinary();

    public void f(File file) {

        Map params = ObjectUtils.asMap(
                "public_id", "myfolder/mysubfolder/my_dog",
                "overwrite", true,
                "resource_type", "image");

        try {
            Map uploadResult = cloudinary.uploader().upload(file, params);
        } catch (IOException e) {

        }

    }




}
