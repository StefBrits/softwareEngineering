package be.uantwerpen.fti.se.tutorial.Services;

import be.uantwerpen.fti.se.tutorial.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
@Profile("development")
public class DatabaseLoader {
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository; private
    final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(PrivilegeRepository privilegeRepository,
                          RoleRepository roleRepository, UserRepository userRepository) {
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Autowired
    PasswordEncoder passwordEncoder;



    @PostConstruct
    private void initDatabase() {
        Privilege p1 = new Privilege("logon");
        privilegeRepository.save(p1);
        Privilege p2 = new Privilege("secret-message"); privilegeRepository.save(p2);
        Role administrator = new Role("Administrator");
        Role tester = new Role("Tester");
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        tester.setPrivileges(privileges);
        roleRepository.save(tester);
        privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        privileges.add(p2);
        administrator.setPrivileges(privileges);
        roleRepository.save(administrator);
        User u1 = new User("Siegfried",passwordEncoder.encode("password"));
        u1.setFirstName("Siegfried");
        u1.setLastName("Mercelis");
        Set<Role> roles = new HashSet<>();
        roles.add(administrator);
        u1.setRoles(roles);
        userRepository.save(u1);
        User u2 = new User("Reinout",passwordEncoder.encode("password"));
        u2.setFirstName("Reinout");
        u2.setLastName("Eyckerman");
        roles = new HashSet<>();
        roles.add(tester);
        u2.setRoles(roles);
        userRepository.save(u2);
        User u3 = new User("Stef",passwordEncoder.encode("password"));
        u3.setFirstName("Stef");
        u3.setLastName("Brits");
        roles = new HashSet<>();
        roles.add(tester);
        u3.setRoles(roles);
        userRepository.save(u3);

    }
}
