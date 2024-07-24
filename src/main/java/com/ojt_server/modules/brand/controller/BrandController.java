package com.ojt_server.modules.brand.controller;

import com.ojt_server.modules.brand.BrandModel;
import com.ojt_server.modules.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    // View all brands
    @GetMapping("/findAll")
    public List<BrandModel> getAllBrand() {
        return brandService.getAllBrands();
    }

    // Add a new brand
    @PostMapping("/add")
    public BrandModel addBrand(@RequestBody BrandModel brand){
        return brandService.addBrand(brand);
    }

    // Update a brand
    @PutMapping("/update")
    public BrandModel updateBrand (@RequestBody BrandModel brand){
        return brandService.updateBrand(brand);
    }

    // Update brand status instead of deleting
    @DeleteMapping("/delete/{id}")
    public void deleteBrand(@PathVariable Long id){
        brandService.deleteBrand(id);
    }
}
