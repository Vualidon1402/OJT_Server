package com.ojt_server.modules.color.service;

import com.ojt_server.modules.color.ColorModel;
import com.ojt_server.modules.color.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<ColorModel> findAllColor() {
        return colorRepository.findAll();
    }

    //Add new color
    public ColorModel addColorModel(ColorModel colorModel) {
        return colorRepository.save(colorModel);
    }

    //Update color
    public ColorModel updateColorModel(ColorModel colorModel) {
        return colorRepository.save(colorModel);
    }

    // Update category status instead of deleting
    public void deleteColorModel(Long id) {
        colorRepository.findById(id).ifPresent(colorModel -> {
            colorModel.setStatus(false);
            colorRepository.save(colorModel);
        });
    }
}
