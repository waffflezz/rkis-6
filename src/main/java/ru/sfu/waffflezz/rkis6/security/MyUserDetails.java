package ru.sfu.waffflezz.rkis6.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sfu.waffflezz.rkis6.models.MyUser;

public class MyUserDetails implements UserDetails {
  private final MyUser myUser;

  public MyUserDetails(MyUser myUser) {
    this.myUser = myUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(myUser.getRole()));
  }

  @Override
  public String getPassword() {
    return myUser.getPassword();
  }

  @Override
  public String getUsername() {
    return myUser.getUsername();
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
    return true;
  }

  public MyUser getUser() {
    return myUser;
  }
}
