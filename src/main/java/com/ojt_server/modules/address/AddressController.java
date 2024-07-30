package com.ojt_server.modules.address;

import com.ojt_server.modules.address.dto.AddressUpdateRequest;
import com.ojt_server.modules.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    //câp nhật đia chỉ theo usre đăng nhâp
    @PutMapping("/update")
    public void updateAddress(@RequestBody AddressUpdateRequest request) {
        try {
            addressService.updateAddress(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



