package be.uantwerpen.fti.se.tutorial.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;
    public Iterable<Privilege> findAll() {
        return this.privilegeRepository.findAll();
    }
    public void save(final Privilege privilege) {
        this.privilegeRepository.save(privilege);
    }
}
