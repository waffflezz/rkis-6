package ru.sfu.waffflezz.rkis6.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sfu.waffflezz.rkis6.models.MyUser;
import ru.sfu.waffflezz.rkis6.services.MyUserService;
import ru.sfu.waffflezz.rkis6.utils.UsernameValidator;

@RequestMapping("/authorize")
@Controller
public class AuthorizeController {
  private final UsernameValidator usernameValidator;

  private final MyUserService myUserService;

  @Autowired
  public AuthorizeController(UsernameValidator usernameValidator, MyUserService myUserService) {
    this.usernameValidator = usernameValidator;
    this.myUserService = myUserService;
  }

  @GetMapping("/login")
  public String loginPage() {
    return "authorize/login";
  }

  @GetMapping("/register")
  public String registrationPage(@ModelAttribute("myUser") MyUser myUser) {
    return "authorize/registration";
  }

  @PostMapping("/register_process")
  public String doReg(@ModelAttribute("myUser") @Valid MyUser myUser, BindingResult bindingResult) {
    usernameValidator.validate(myUser, bindingResult);
    if (bindingResult.hasErrors()) {
      return "authorize/registration";
    } else {
      myUserService.register(myUser);
      return "redirect:/authorize/login";
    }
  }
}
