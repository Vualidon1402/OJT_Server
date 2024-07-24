package com.ojt_server.modules.banner.controller;


import com.ojt_server.modules.banner.BannreModel;
import com.ojt_server.modules.banner.service.BannreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BannreController {

    @Autowired
    private BannreService bannreService;

// Add banner image
   @PostMapping("/addBanner")
   public ResponseEntity<Object> addBanner(@RequestBody BannreModel image){
    try{
        BannreModel savedBanner = bannreService.addBanner(image);
        return ResponseEntity.ok(savedBanner);
    }catch (Exception e){
        return ResponseEntity.badRequest().body(e);
    }
  }
    //hiển thị danh sách banner
    @GetMapping("/getAllBanner")
    public ResponseEntity<Object> getAllBanner(){
        try{
            return ResponseEntity.ok(bannreService.getAllBanner());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    //xóa banner
    @DeleteMapping("/deleteBanner/{id}")
    public ResponseEntity<Object> deleteBanner(@PathVariable Long id){
        try{
            bannreService.deleteBanner(id);
            return ResponseEntity.ok("Deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    // Update banner
    @PutMapping("/updateBanner/{id}")
    public ResponseEntity<Object> updateBanner(@PathVariable Long id, @RequestBody BannreModel image){
        try{
            BannreModel existingBanner = bannreService.findBannerById(id);
            if (existingBanner != null) {
                existingBanner.setImage(image.getImage());
                existingBanner.setTitle(image.getTitle());
                BannreModel updatedBanner = bannreService.updateBanner(existingBanner);
                return ResponseEntity.ok(updatedBanner);
            } else {
                return ResponseEntity.badRequest().body("Banner not found");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }



}
