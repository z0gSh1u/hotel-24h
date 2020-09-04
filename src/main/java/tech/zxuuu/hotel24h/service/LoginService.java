package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Emp;

import java.util.List;

public interface LoginService {

    Emp empLogin(String empId, String empPassword);

    void changePwd(String empId, String newPwd);

}
