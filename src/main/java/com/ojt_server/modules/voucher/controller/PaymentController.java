package com.ojt_server.modules.voucher.controller;

import com.ojt_server.modules.voucher.dto.ApplyVoucherRequest;
import com.ojt_server.modules.voucher.service.PaymentService;
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

    @PostMapping("/apply-voucher")
    public ResponseEntity<Double> applyVoucher(@RequestBody ApplyVoucherRequest request) {
        double newTotalAmount = paymentService.applyVoucher(request.getVoucherCode(), request.getTotalAmount());
        return ResponseEntity.ok(newTotalAmount);
    }
}
