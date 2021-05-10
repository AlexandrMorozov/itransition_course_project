package com.almor.course_project.service;

import com.almor.course_project.model.Gallery;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public String[] loadImageToCloud(MultipartFile sourceFile) {

        Map<String, Object> result = null;

        String[] answer = null;

        try {

            Map<String, Object> params = ObjectUtils.asMap("resource_type", "auto");
            result = cloudinary.uploader().upload(sourceFile.getBytes(), params);

            answer = new String[] { result.get("url").toString(), result.get("public_id").toString()};

        } catch (IOException e) {

        }

        return answer;
    }

    public List<Gallery> loadImages(List<MultipartFile> files) {

        List<Gallery> imageLinks = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {

            String[] imageData = loadImageToCloud(files.get(i));

            if (imageData != null) {
                imageLinks.add(new Gallery(imageData[0], imageData[1]));
            }
        }
        return imageLinks;

    }

    public void deleteImageFromCloud(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap());
        } catch (IOException e) {

        }
    }
}
