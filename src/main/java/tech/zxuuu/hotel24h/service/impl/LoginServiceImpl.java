package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Emp;
import tech.zxuuu.hotel24h.mapper.EmpMapper;
import tech.zxuuu.hotel24h.service.LoginService;

import java.util.List;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp empLogin(Emp emp) {
        List<Emp> emps = this.empMapper.selectEmpWhenLogin(emp);
        if (emps.isEmpty() || emps.size() > 1) {
            return null;
        } else {
            return emps.get(0);
        }
    }

}
