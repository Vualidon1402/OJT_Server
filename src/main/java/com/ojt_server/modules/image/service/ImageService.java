package com.ojt_server.modules.image.service;

import com.ojt_server.modules.image.ImageModel;
import com.ojt_server.modules.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    //add list image
    public List<ImageModel> createImages(List<ImageModel> images) {
        return imageRepository.saveAll(images);
    }
}
