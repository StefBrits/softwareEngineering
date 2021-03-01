package be.uantwerpen.fti.se.tutorial.Service;

import be.uantwerpen.fti.se.tutorial.Model.*;
import be.uantwerpen.fti.se.tutorial.Services.SecurityService;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTests {
    @InjectMocks
    private SecurityService securityService;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    List<User> userList;

    @Before()
    public void init() {
        Privilege p1 = new Privilege("logon");
        Privilege p2 = new Privilege("secret-message");
        Role administrator = new Role("Administrator");
        Role tester = new Role("Tester");
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        tester.setPrivileges(privileges);
        privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        privileges.add(p2);
        administrator.setPrivileges(privileges);
        User u1 = new User("U1", "password1");
        u1.setRoles(Sets.newSet(administrator));
        User u2 = new User("U2", "password2");
        u2.setRoles(Sets.newSet(tester));
        userRepository.save(u1);
        userRepository.save(u2);
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void nonExistingUsernameTest() {
        when(userService.findByUsername("bla")).thenReturn(Optional.empty());
        securityService.loadUserByUsername("bla");
    }
}