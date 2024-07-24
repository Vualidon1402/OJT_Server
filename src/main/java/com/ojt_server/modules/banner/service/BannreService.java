package com.ojt_server.modules.banner.service;

import com.ojt_server.modules.banner.BannreModel;
import com.ojt_server.modules.banner.repository.BannreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BannreService {
    @Autowired
    private BannreRepository bannreRepository;

    //thêm hình ảnh banner
    public BannreModel addBanner(BannreModel image){
        return bannreRepository.save(image);
    }
    //hiển thị danh sách banner
    public List<BannreModel> getAllBanner(){
        return bannreRepository.findAll();
    }
    //xóa banner
    public void deleteBanner(Long id){
        if (bannreRepository.existsById(id)) {
            bannreRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Banner not found with id: " + id);
        }
    }



    // Update banner
    public BannreModel updateBanner(BannreModel image){
        if (bannreRepository.existsById(image.getId())) {
            return bannreRepository.save(image);
        } else {
            throw new NoSuchElementException("Banner not found with id: " + image.getId());
        }
    }
    public BannreModel findBannerById(Long id){
        return bannreRepository.findById(id).orElse(null);
    }

}
