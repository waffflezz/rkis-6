package ru.sfu.waffflezz.rkis6.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sfu.waffflezz.rkis6.models.MyUser;
import ru.sfu.waffflezz.rkis6.repositories.MyUsersRepository;
import ru.sfu.waffflezz.rkis6.security.MyUserDetails;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
  private final MyUsersRepository myUsersRepository;

  @Autowired
  public MyUserDetailsService(MyUsersRepository myUsersRepository) {
    this.myUsersRepository = myUsersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MyUser> user = myUsersRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not exist");
    }
    return new MyUserDetails(user.get());
  }
}
