package com.ojt_server.modules.user.controller;

import com.ojt_server.modules.user.dto.OtpVerificationDTO;
import com.ojt_server.modules.user.dto.RegisterDTO;
import com.ojt_server.modules.user.email.CreateRespone;
import com.ojt_server.modules.user.email.EmailService;
import com.ojt_server.modules.user.email.OtpService;
import com.ojt_server.modules.user.model.Role;
import com.ojt_server.modules.user.model.RoleName;
import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.RoleRepository;
import com.ojt_server.modules.user.reqository.UserRepository;
import com.ojt_server.modules.user.service.UserService;


import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private RoleRepository roleRepository;
    //hiện thị tất cả users
    @GetMapping("/findAll")

    public ResponseEntity<Object> getUsers(){
        try{
            return new ResponseEntity<>(userService.findUsers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //thay đổi trạng thái staus
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id){
        try{
            UserModel user = userService.update(id);
            if(user != null){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    //tìm kiếm user theo userName
    @GetMapping("/findByUserName/{userName}")
    public ResponseEntity<Object> findByUserName(@PathVariable String userName){
        try{
            List<UserModel> users = userService.findByUserName(userName);
            if(!users.isEmpty()){
                return new ResponseEntity<>(users, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No users found with userName containing: " + userName, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    //sắp xếp user
    @GetMapping("/sort")
    public ResponseEntity<Object> sortUser(@RequestParam(defaultValue = "asc") String sortType) {
        try {
            return new ResponseEntity<>(userService.sortUser(sortType), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    // phân trang user
    @GetMapping("/find")
    public ResponseEntity<Object> findUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        try {
            Page<UserModel> usersPage = userService.findUsers(page, size);
            return new ResponseEntity<>(usersPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //thanh đổi trạng thái role của users
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody List<Long> roleIds) {
        System.out.println("roleIds: " + roleIds);
        try {
            return new ResponseEntity<>(userService.updateRole(id, roleIds), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    //đăng ký tài khoản
@PostMapping("/register")
public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO registerDTO){
    System.out.println("registerDTO: " + registerDTO);
    try{
        String hashedPassword = BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt());
        registerDTO.setPassword(hashedPassword);
        UserModel user = userService.register(registerDTO);
        System.out.println("user1: " + user);
        if(user != null) {
            CreateRespone createRespone = new CreateRespone();
            createRespone.setData(user);
            createRespone.setMessage("Đăng ký tài khoản thành công");
            String userEmail = createRespone.getData().getEmail();
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            try {
                emailService.sendOtpToUser(userEmail, otp);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending OTP");
            }
            otpService.storeOtp(createRespone.getData().getId(), otp);
            return ResponseEntity.ok("Đăng ký thành công");
        }
        return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
    }catch (Exception e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}

    //nhạp otp
    @PostMapping("/activate")
    public ResponseEntity<Object> activateAccount(@RequestBody OtpVerificationDTO otpVerificationDTO) {
        try {
            boolean isOtpValid = otpService.verifyOtp(otpVerificationDTO.getUserId(), otpVerificationDTO.getOtp());
            if (isOtpValid) {
                UserModel user = userService.activate(otpVerificationDTO.getUserId());
                if (user != null) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User not found with id: " + otpVerificationDTO.getUserId(), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
