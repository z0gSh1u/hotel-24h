package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Emp;
import tech.zxuuu.hotel24h.mapper.EmpMapper;
import tech.zxuuu.hotel24h.service.AdminService;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> queryEmp(Emp emp) {
        return empMapper.selectEmp(emp);
    }

    @Override
    public Boolean verifyAdmin(String adminPwd) {
        return !empMapper.selectEmpWhenLogin("admin", adminPwd).isEmpty();
    }

    @Override
    public Boolean updateEmp(Emp emp) {
        return empMapper.updateEmp(emp) > 0;
    }

    @Override
    public void insertEmp(Emp emp) {
        empMapper.insertEmp(emp);
    }

    @Override
    public Boolean deleteEmp(String empId, String empName) {
        return empMapper.deleteEmp(empId, empName) > 0;
    }

    @Override
    public void changeAdminPwd(String password) {
        empMapper.changeAdminPwd(password);
    }

}
