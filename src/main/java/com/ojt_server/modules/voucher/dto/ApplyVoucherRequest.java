package com.ojt_server.modules.voucher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplyVoucherRequest {

    private String voucherCode;
    private double totalAmount;

    @Override
    public String toString() {
        return "voucherCode: " + voucherCode + ", totalAmount: " + totalAmount;
    }
}
