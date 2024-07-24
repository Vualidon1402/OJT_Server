package com.ojt_server.modules.brand.service;

import com.ojt_server.modules.brand.BrandModel;
import com.ojt_server.modules.brand.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    // View all brands
    public List<BrandModel> getAllBrands() {
        return brandRepository.findAll();
    }

    // Add a new brand
    public BrandModel addBrand(BrandModel brand) {
        return brandRepository.save(brand);
    }

    // Update a brand
    public BrandModel updateBrand(BrandModel brand) {
        return brandRepository.save(brand);
    }

    // Update category status instead of deleting
    public void deleteBrand(Long id) {
        brandRepository.findById(id).ifPresent(brand -> {
            brand.setStatus(false);
            brandRepository.save(brand);
        });
    }
}
