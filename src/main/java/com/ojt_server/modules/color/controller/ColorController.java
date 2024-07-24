package com.ojt_server.modules.color.controller;

import com.ojt_server.modules.color.ColorModel;
import com.ojt_server.modules.color.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    // Get all colors
    @RequestMapping("/findAll")
    public List<ColorModel> getAllColor() {
        return colorService.findAllColor();
    }

    // Add new color
    @PostMapping("/add")
    public ColorModel addColor(@RequestBody ColorModel colorModel) {
        return colorService.addColorModel(colorModel);
    }

    // Update color
    @PutMapping("/update")
    public ColorModel updateColor (@RequestBody ColorModel color){
        return colorService.updateColorModel(color);
    }

    // Update color status instead of deleting
    @DeleteMapping("/delete/{id}")
    public void deleteColor(@PathVariable Long id){
        colorService.deleteColorModel(id);
    }
}
