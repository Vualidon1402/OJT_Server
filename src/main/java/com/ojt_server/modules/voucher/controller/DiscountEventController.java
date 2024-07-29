package com.ojt_server.modules.voucher.controller;


import com.ojt_server.modules.voucher.DiscountEvent;
import com.ojt_server.modules.voucher.service.DiscountEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount-events")
public class DiscountEventController {

    @Autowired
    private DiscountEventService discountEventService;


    @GetMapping
    public ResponseEntity<List<DiscountEvent>> getAllDiscountEvents() {
       try{
           return ResponseEntity.ok(discountEventService.getAllDiscountEvents());

       }catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
       }
    }

    @PostMapping
    public ResponseEntity<DiscountEvent> createDiscountEvent(@RequestBody DiscountEvent discountEvent) {
        try{
            return ResponseEntity.ok(discountEventService.createDiscountEvent(discountEvent));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    //xóa discountEvent
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountEvent(@PathVariable Long id) {
        try{
            discountEventService.deleteDiscountEvent(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    //cập nhật discountEvent
    @PutMapping("/{id}")
    public ResponseEntity<DiscountEvent> updateDiscountEvent(@PathVariable Long id, @RequestBody DiscountEvent discountEvent) {
        try{
            DiscountEvent updatedDiscountEvent = discountEventService.saveDiscountEvent(discountEvent);
            return ResponseEntity.ok(updatedDiscountEvent);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
