package com.ojt_server.modules.user.service;

import com.ojt_server.modules.address.Address;
import com.ojt_server.modules.address.repository.AddressRepository;
import com.ojt_server.modules.user.dto.RegisterDTO;
import com.ojt_server.modules.user.dto.UploadUserDTO;
import com.ojt_server.modules.user.model.Role;
import com.ojt_server.modules.user.model.RoleName;
import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.RoleRepository;
import com.ojt_server.modules.user.reqository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;


    public UserModel findUserByInfor(String loginId){
        return userRepository.findUserByInfor(loginId);
    }


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

    //đăng ký tài khoản
    public UserModel register(RegisterDTO registerDTO) {
        UserModel user = new UserModel();
        BeanUtils.copyProperties(registerDTO, user);
        user.setRoles(new HashSet<>());
        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER);
        if (userRole != null) {
            user.getRoles().add(userRole);
        }

        // Save the user and get the new user's id
        user = userRepository.save(user);
        // Create a new address for the new user
        Address address = new Address();
        address.setUserId(user);
        // Set other properties of the address as needed
        // Save the address to the database
        addressRepository.save(address);

        // Set createdAt to current date
        user.setCreatedAt(new Date());
        user = userRepository.save(user);
        return user;
    }


    public UserModel activate(Long userId) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setDeleted(true); // Set status to true to indicate the account is activated
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //lấy mật khẩu cũ để so sánh rồi thanh đổi mật khẩu mới
    public UserModel changePassword(Long id, String oldPassword, String newPassword) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            if (BCrypt.checkpw(oldPassword, user.getPassword())) {
                user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                return userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Sai Mật Khẩu");
            }
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }

    //chinhr sửa thông tin ueser
    public UserModel update(Long id, UploadUserDTO userDto) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();
            existingUser.setUsername(userDto.getUserName());
            existingUser.setFullName(userDto.getFullName());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setPhone(userDto.getPhone());
            // Assuming UserModel has a field named 'avatar'
            existingUser.setAvatar(userDto.getAvatar());
            existingUser.setUpdatedAt(new Date());
            return userRepository.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }

    //theo id
    public UserModel findUserById(Long id) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }






}
