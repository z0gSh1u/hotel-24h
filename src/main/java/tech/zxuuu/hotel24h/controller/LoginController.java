package tech.zxuuu.hotel24h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.zxuuu.hotel24h.entity.Emp;
import tech.zxuuu.hotel24h.service.LoginService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(path = "")
    public String turnToLoginPage() {return "emp/login";}

    @PostMapping(path = "/login")
    public Emp login(Emp emp) {
        return this.loginService.empLogin(emp);
    }
}
