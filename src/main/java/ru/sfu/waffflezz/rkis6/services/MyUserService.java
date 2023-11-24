package ru.sfu.waffflezz.rkis6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sfu.waffflezz.rkis6.models.MyUser;
import ru.sfu.waffflezz.rkis6.repositories.MyUsersRepository;

@Service
@Transactional(readOnly = true)
public class MyUserService {
  private final MyUsersRepository myUsersRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public MyUserService(MyUsersRepository myUsersRepository, PasswordEncoder passwordEncoder) {
    this.myUsersRepository = myUsersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean hasUsername(String username) {
    return myUsersRepository.findByUsername(username).isPresent();
  }

  @Transactional
  public void register(MyUser myUser) {
    myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
    if (myUser.getUsername().equals("admin")) {
      myUser.setRole("ROLE_ADMIN");
    } else {
      myUser.setRole("ROLE_USER");
    }
    myUsersRepository.save(myUser);
  }
}
