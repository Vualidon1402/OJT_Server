package com.ojt_server.modules.address.service;

import com.ojt_server.modules.address.Address;
import com.ojt_server.modules.address.dto.AddressUpdateRequest;
import com.ojt_server.modules.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    //câp nhật đia chỉ theo usre đăng nhâp
    public void updateAddress(AddressUpdateRequest request) {
        Address address = addressRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Address not found"));
        address.setReceiveName(request.getReceiveName());
        address.setStreetAddress(request.getStreetAddress());
        address.setDistrict(request.getDistrict());
        address.setProvince(request.getProvince());
        address.setWard(request.getWard());
        address.setPhone(request.getPhone());
        address.setPriority(request.isPriority());
        addressRepository.save(address);
    }



}
