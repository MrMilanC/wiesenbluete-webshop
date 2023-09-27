package com.example.wiesenbluetewebshop.service;

import com.example.wiesenbluetewebshop.exception.UserException;
//import com.example.wiesenbluetewebshop.model.Cart;
import com.example.wiesenbluetewebshop.model.User;
//import com.example.wiesenbluetewebshop.repository.CartRepository;
import com.example.wiesenbluetewebshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

//    @Autowired
//    private CartRepository cartRepository;

//    @Override
//    public UserService loadUserByUsername(String username)throws UserException {
//        User user = userRepository.findByUserNameOrEmail(username, username);
//        if(user==null){
//            throw new UserException("User not exists by Username");
//        }
//
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
//        return new com.example.backendsandboxthree.model.User(username,user.getPassword(),authorities);
//    }

    public boolean doesUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository
                .findByUsername(username);
    }

    public User addUser(User user) throws UserException {
//        Cart cart = new Cart();
//        user.setCart(cart);
//        cart.setUser(user);
//        User c = userRepository.save(user);
//
//        if (c != null) {
//            return c;
//        } else {
//            throw new UserException("customer not added");
//        }
        return userRepository.save(user);
    }

    public User updateUser(User user) throws UserException {
        User c = userRepository.findById(user.getId()).orElseThrow(() -> new UserException("Customer not found"));
        if (c != null) {
            userRepository.save(user);
        }
        return c;
    }

    public User removeUser(Long userId) throws UserException {
        User userRemoved = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
        userRepository.delete(userRemoved);
        return userRemoved;
    }

//    public User removeUser(Long userId) throws UserException {
//        Optional<User> opt = userRepository.findById(userId);
//        if (opt.isPresent()) {
//            User c = opt.get();
//            userRepository.delete(c);
//            return c;
//        } else {
//            throw new UserException("Customer not found with cid - " + userId);
//        }
//    }

    public List<User> viewAllUser() throws UserException {
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            return users;
        } else {
            throw new UserException("customer not found");
        }
    }
}
