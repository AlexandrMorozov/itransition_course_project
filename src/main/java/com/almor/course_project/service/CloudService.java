package com.almor.course_project.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudService {

    private Cloudinary cloudinary;

    @Autowired
    public CloudService( @Value("${CLOUDINARY_CLOUD_NAME}") String name,
                         @Value("${CLOUDINARY_API_KEY}") String key,
                         @Value("${CLOUDINARY_API_SECRET}") String secret ) {

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", name,
                "api_key", key,
                "api_secret", secret));
    }

    public void loadImageToCloud(MultipartFile sourceFile) {

        Map<String, Object> result = null;

        try {

            Map<String, Object> params = ObjectUtils.asMap("resource_type", "auto");
            result = cloudinary.uploader().upload(sourceFile.getBytes(), params);

        } catch (IOException e) {

        }
    }

    public void deleteImageFromCloud(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap());
        } catch (IOException e) {

        }
    }
}
