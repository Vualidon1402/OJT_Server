package com.ojt_server.modules.voucher.controller;

import com.ojt_server.modules.voucher.Voucher;
import com.ojt_server.modules.voucher.dto.ApplyVoucherRequest;
import com.ojt_server.modules.voucher.service.PaymentService;
import com.ojt_server.modules.voucher.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private VoucherService voucherService;

    @PostMapping("")
    public ResponseEntity<?> applyVoucher(@Valid @RequestBody ApplyVoucherRequest request) {
        System.out.println("Voucher code: " + request);
        Voucher voucher = voucherService.getVoucherByCode(request.getVoucherCode());
        if (voucher == null) {
            return ResponseEntity.badRequest().body("Voucher does not exist");
        }
        double newTotalAmount = paymentService.applyVoucher(request.getVoucherCode(), request.getTotalAmount());
        return ResponseEntity.ok(newTotalAmount);
    }
}
