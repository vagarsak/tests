package ru.company.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.company.server.dao.UserDetailsDao;
import ru.company.server.model.Authorities;
import ru.company.server.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userDetailsDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        user.getAuthorities().size();
        return new MyUserPrincipal(user);
    }

    private class MyUserPrincipal implements UserDetails {

        private User user;

        public MyUserPrincipal(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Authorities auth: user.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
            }
            return authorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;

        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return user.isEnabled();
        }
    }
}

