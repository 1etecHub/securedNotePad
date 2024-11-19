package com.notePad.securedNotePad.security.securityServices;


import com.notePad.securedNotePad.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class AppUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String email;

    //@JsonIgnore
    private String password;
    private Boolean is2faEnabled;
    private Collection<? extends GrantedAuthority> authorities;


    /*public AppUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        //this.isEnabled = user.getIsVerified();
        //this.authorities = Stream.of(new SimpleGrantedAuthority(user.getRole().name()))
                //.collect(Collectors.toList());

    }*/

    public AppUserDetails(Long id, String username, String email, String password,
                          boolean is2faEnabled,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;
    }

    public static AppUserDetails build(User user) {
        // Map the roles or authorities from the User entity
        GrantedAuthority authority =  new SimpleGrantedAuthority(user.getRole().getRoleName().name());

        // Create and return an instance of AppUserDetails
        return new AppUserDetails(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.is2faEnabled(),
                List.of(authority)
        );
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

    /*@Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getFullName() {
        return fullName;
    }*/
}
