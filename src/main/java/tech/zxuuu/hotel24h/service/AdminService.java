package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Emp;

import java.util.List;

public interface AdminService {

    List<Emp> queryEmp(Emp emp);

    Boolean verifyAdmin(String adminPwd);

    Boolean updateEmp(Emp emp);

    void insertEmp(Emp emp);

    Boolean deleteEmp(String empId, String empName);

    void changeAdminPwd(String password);

}
