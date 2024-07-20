package com.ojt_server.modules.user.controller;

import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.UserRepository;
import com.ojt_server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
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

}
