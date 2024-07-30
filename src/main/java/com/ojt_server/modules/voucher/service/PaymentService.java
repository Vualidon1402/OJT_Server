package com.ojt_server.modules.voucher.service;

import com.ojt_server.modules.voucher.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {
    @Autowired
    private VoucherService voucherService;

    public double applyVoucher(String voucherCode, double totalAmount) {
        Voucher voucher = voucherService.getVoucherByCode(voucherCode);
        if (voucher == null) {
            throw new RuntimeException("Voucher does not exist");
        }

        if (voucher != null && !voucher.isUsed() && LocalDate.now().isBefore(voucher.getExpirationDate())) {
            totalAmount -= voucher.getDiscountAmount();
            voucher.setUsed(true);
            voucherService.saveVoucher(voucher);
        }

        return totalAmount;
    }


}
