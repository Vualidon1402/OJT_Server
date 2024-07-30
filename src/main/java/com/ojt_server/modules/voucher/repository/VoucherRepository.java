package com.ojt_server.modules.voucher.repository;


import com.ojt_server.modules.voucher.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findFirstByCode(String voucherCode);
    List<Voucher> findByCode(String code);

}
