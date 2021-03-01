package be.uantwerpen.fti.se.tutorial.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Iterable<Role> findAll() {return this.roleRepository.findAll();}
    public void save(final Role role) {this.roleRepository.save(role);}
    //public void add(final Role role) {this.roleRepository.add(role)}
}
