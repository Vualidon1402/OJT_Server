package com.ojt_server.modules.voucher.repository;


import com.ojt_server.modules.voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String voucherCode);

}
