package ru.sfu.waffflezz.rkis6.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sfu.waffflezz.rkis6.models.MyUser;
import ru.sfu.waffflezz.rkis6.services.MyUserService;

@Component
public class UsernameValidator implements Validator {
  private final MyUserService myUserService;

  @Autowired
  public UsernameValidator(MyUserService myUserService) {
    this.myUserService = myUserService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return MyUser.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    MyUser myUser = (MyUser) target;
    if (myUserService.hasUsername(myUser.getUsername())) {
      errors.rejectValue("username", "", "Error");
    }
  }
}
