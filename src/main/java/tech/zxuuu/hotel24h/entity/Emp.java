package tech.zxuuu.hotel24h.entity;

import java.util.Date;

public class Emp {

    private String empId;

    private String empName;

    private String empPassword;

    public Emp(String empId, String empName, String empPassword) {
        this.empId = empId;
        this.empName = empName;
        this.empPassword = empPassword;
    }

    public Emp() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }
}
