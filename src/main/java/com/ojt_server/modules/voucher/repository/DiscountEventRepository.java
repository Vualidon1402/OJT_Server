package com.ojt_server.modules.voucher.repository;

import com.ojt_server.modules.voucher.DiscountEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountEventRepository extends JpaRepository<DiscountEvent, Long> {
}
