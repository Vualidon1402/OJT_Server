package com.ojt_server.modules.voucher.service;

import com.ojt_server.modules.voucher.Voucher;
import com.ojt_server.modules.voucher.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public Voucher createVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }
    public Voucher getVoucherByCode(String voucherCode) {
        return voucherRepository.findByCode(voucherCode);
    }

    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    //xóa voucher
    public void deleteVoucher(Long id) {
        voucherRepository.deleteById(id);
    }
}
