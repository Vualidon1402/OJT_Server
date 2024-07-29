package com.ojt_server.modules.voucher.service;

import com.ojt_server.modules.voucher.DiscountEvent;
import com.ojt_server.modules.voucher.repository.DiscountEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountEventService {
    @Autowired
    private DiscountEventRepository discountEventRepository;

    public List<DiscountEvent> getAllDiscountEvents() {
        return discountEventRepository.findAll();
    }

    public DiscountEvent createDiscountEvent(DiscountEvent discountEvent) {
        return discountEventRepository.save(discountEvent);
    }
    //xóa discountEvent
    public void deleteDiscountEvent(Long id) {
        discountEventRepository.deleteById(id);
    }

    //cập nhật discountEvent
    public DiscountEvent saveDiscountEvent(DiscountEvent discountEvent) {
        return discountEventRepository.save(discountEvent);
    }
}
