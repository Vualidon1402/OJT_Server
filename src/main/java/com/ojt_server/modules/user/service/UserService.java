package com.ojt_server.modules.user.service;

import com.ojt_server.modules.user.model.Role;
import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.RoleRepository;
import com.ojt_server.modules.user.reqository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;



    //hiển thị danh sách user
    public List<UserModel> findUsers(){
        return userRepository.findAll();
    }
    //thay đổi trạng thái status
    public UserModel update(Long id){
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setStatus(!user.isStatus()); // Flip the status
            return userRepository.save(user);
        } else {
            System.out.println("User not found with id: " + id);
            return null;
        }
    }
    //tìm kiếm user theo useName
    public List<UserModel> findByUserName(String userName){
        return userRepository.findByUsernameContains(userName);
    }

    //sắp xếp user
    public List<UserModel> sortUser(String sortType) {
        Sort sort;
        switch (sortType.toLowerCase()) {
            case "desc":
            case "zyx":
                sort = Sort.by(Sort.Direction.DESC, "username");
                break;
            case "abc":
            case "asc":
            default:
                sort = Sort.by(Sort.Direction.ASC, "username");
        }
        return userRepository.findAll(sort);
    }

    // phân trang user
    public Page<UserModel> findUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    //thanh đổi trạng thái role của users
    public UserModel updateRole(Long id, List<Long> roleIds) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            Set<Role> roles = new HashSet<>();
            for (Long roleId : roleIds) {
                Optional<Role> optionalRole = roleRepository.findById(roleId);
                optionalRole.ifPresent(roles::add);
            }
            user.setRoles(roles);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

}
