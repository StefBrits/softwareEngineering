package be.uantwerpen.fti.se.tutorial.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }
    //public void save(final User user) {
    //    this.userRepository.save(user);
    //}

    public void saveSomeAttributes(User user) {
        User tempUser = null;
        if(user.getId() != null)
            tempUser = this.userRepository.findById(user.getId()).orElse(null);
        if (tempUser != null){
            tempUser.setRoles(user.getRoles());
            tempUser.setUsername(user.getUsername());
            userRepository.save(tempUser);
        } else {
            userRepository.save(user);
        }
    }
    public Optional<User> findById(Long id) {return this.userRepository.findById(id);}

    public void delete(Long id) {this.userRepository.deleteById(id);}

    public Optional<User> findByUsername(String username) { return Optional.ofNullable(this.userRepository.findByUsername(username)); }
}