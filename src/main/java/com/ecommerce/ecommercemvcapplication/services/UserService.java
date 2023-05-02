package com.ecommerce.ecommercemvcapplication.services;

import com.ecommerce.ecommercemvcapplication.model.UsersModel;
import com.ecommerce.ecommercemvcapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UsersModel registerUser(String name, String email, String mobileNumber, String username, String password){
        if(name == null || email == null || mobileNumber == null || username == null || password == null){
            return null;
        } else {
            if(userRepository.findFirstByUsername(username).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel =new UsersModel();
            usersModel.setName(name);
            usersModel.setEmail(email);
            usersModel.setMobileNumber(mobileNumber);
            usersModel.setUsername(username);
            usersModel.setPassword(password);
            return userRepository.save(usersModel);
        }
    }
    public UsersModel authentication(String email, String password){
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
