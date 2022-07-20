package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.RoleRepository;
import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userModel = userRepository.findByUserName(username)
                .orElseThrow(() ->new UsernameNotFoundException("User Not Found with name: " + username));
        User user = new User(userModel.getUsername(),
                             userModel.getPassword(),
                      true,true, true, true,
                              userModel.getAuthorities());
        return user;
    }
    public UserModel saveUser(UserModel userModel){
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        UserModel userSaved = userRepository.save(userModel);
        return userSaved;
    }
}
