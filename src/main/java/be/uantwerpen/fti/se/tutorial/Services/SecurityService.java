package be.uantwerpen.fti.se.tutorial.Services;

import be.uantwerpen.fti.se.tutorial.Model.User;
import be.uantwerpen.fti.se.tutorial.Model.UserRepository;
import be.uantwerpen.fti.se.tutorial.Model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            userRepository.save(user); // Update timestamp by saving
        } else throw new UsernameNotFoundException("No user with username '" + username + "' found!");
        return user;
    }
}