package com.notePad.securedNotePad.security.securityServices;


import com.notePad.securedNotePad.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
public class AppUserDetails implements UserDetails {
    private String email;
    private String fullName;

    //@JsonIgnore
    private String password;
    private Boolean isEnabled;
    private Boolean is2faEnabled;
    //private List<GrantedAuthority> authorities;
    private Collection<? extends GrantedAuthority> authorities;


    /*public AppUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        //this.isEnabled = user.getIsVerified();
        //this.authorities = Stream.of(new SimpleGrantedAuthority(user.getRole().name()))
                //.collect(Collectors.toList());

    }*/

    public AppUserDetails(String email, String fullName, String password,
                          Boolean isEnabled, Boolean is2faEnabled,
                          Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return isEnabled;
    }

    public String getFullName() {
        return fullName;
    }
}
