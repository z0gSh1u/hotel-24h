package tech.zxuuu.hotel24h.entity;

// 员工类
public class Emp {

  private String id; // 员工账号
  private String password; // 员工密码
  private String name; // 员工姓名

  public Emp(String id, String password, String name) {
    this.id = id;
    this.password = password;
    this.name = name;
  }

  public Emp() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
