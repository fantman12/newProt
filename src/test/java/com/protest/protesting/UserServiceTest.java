package com.protest.protesting;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Iterator;

import com.protest.protesting.entity.User;
import com.protest.protesting.mapper.UserMapper;
import com.protest.protesting.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private User user1;

    @Before
    public void setup() {
        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");
        user1.setAccountNonExpired(true);
        user1.setAccountNonLocked(true);
        user1.setName("USER1");
        user1.setCredentialsNonExpired(true);
        user1.setEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("USER"));
    }

    @Test
    public void createUserTest() {

//        userService.deleteUser(user1.getUsername());
//        userService.createUser(user1);
//        User user = userService.readUser(user1.getUsername());
//        assertThat(user.getUsername(), is(user1.getUsername()));
//
//        PasswordEncoder passwordEncoder = userService.passwordEncoder();
//        assertThat(passwordEncoder.matches("pass1", user.getPassword()), is(true));
//
//        System.out.println("123123");
//
//        Collection<? extends GrantedAuthority> authorities1 = user1.getAuthorities();
//        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
//        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
//        while (it.hasNext()) {
//            GrantedAuthority authority = it.next();
//            assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
//        }
    }
}