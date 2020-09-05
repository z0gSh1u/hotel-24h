package tech.zxuuu.hotel24h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.zxuuu.hotel24h.entity.Emp;
import tech.zxuuu.hotel24h.service.AdminService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Controller
public class AdminController {

  @Autowired
  private AdminService adminService;

  @GetMapping("/loginPage")
  public String turnToLoginPage() {
    return "emp/login";
  }

  @PostMapping("/admin/select")
  public @ResponseBody
  String selectEmp(@RequestParam String empId, @RequestParam String empName) {
    Map map = new Hashtable<String, Object>() {{
      put("list", adminService.queryEmp(new Emp(empId, "", empName)));
    }};
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/admin/update")
  public @ResponseBody
  String updateEmp(@RequestParam String empId, @RequestParam String empName) {
    Map map = new HashMap<String, Boolean>();
    if (adminService.updateEmp(new Emp(empId, "", empName))) {
      map.put("isOk", true);
    } else {
      map.put("isOk", false);
    }
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/admin/insert")
  public @ResponseBody
  String insertEmp(@RequestParam String empId, @RequestParam String empPwd, @RequestParam String empName) {
    Map map = new HashMap<String, Boolean>();
    if (!adminService.queryEmp(new Emp(empId, "", "")).isEmpty()) {
      map.put("isOk", false);
    } else {
      adminService.insertEmp(new Emp(empId, empPwd, empName));
      map.put("isOk", true);
    }
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/admin/delete")
  public @ResponseBody
  String deleteEmp(@RequestParam String empId, @RequestParam String empName, @RequestParam String adminPwd) {
    Map map = new HashMap<String, Boolean>();
    if (!adminService.verifyAdmin(adminPwd)) {
      map.put("isOk", false);
      map.put("isVerified", false);
    } else if (adminService.deleteEmp(empId, empName)) {
      map.put("isOk", true);
      map.put("isVerified", true);
    } else {
      map.put("isOk", false);
      map.put("isVerified", true);
    }
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/admin/changePwd")
  public @ResponseBody
  String changeAdminPwd(@RequestParam String oldAdminPwd, @RequestParam String newAdminPwd) {
    Map map = new HashMap<String, Boolean>();
    if (!adminService.verifyAdmin(oldAdminPwd)) {
      map.put("isOk", false);
    } else {
      adminService.changeAdminPwd(newAdminPwd);
      map.put("isOk", true);
    }
    return JSONUtils.buildJSON(map);
  }

}
