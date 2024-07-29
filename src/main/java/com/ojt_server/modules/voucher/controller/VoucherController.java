package com.ojt_server.modules.voucher.controller;


import com.ojt_server.modules.voucher.Voucher;
import com.ojt_server.modules.voucher.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public ResponseEntity<List<Voucher>> getAllVouchers() {
       try{
              return ResponseEntity.ok(voucherService.getAllVouchers());
       }catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
       }
    }

    @PostMapping
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
        try{
            return ResponseEntity.ok(voucherService.createVoucher(voucher));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    //xóa voucher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable Long id) {
        try{
            voucherService.deleteVoucher(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    //cập nhật Voucher
    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable Long id, @RequestBody Voucher voucher) {
        try{
            Voucher updatedVoucher = voucherService.saveVoucher(voucher);
            return ResponseEntity.ok(updatedVoucher);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
