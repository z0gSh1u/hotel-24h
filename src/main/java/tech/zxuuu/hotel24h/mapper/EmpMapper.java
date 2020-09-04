package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.*;
import tech.zxuuu.hotel24h.entity.Emp;
import tech.zxuuu.hotel24h.provider.EmpSQLProvider;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Insert("INSERT INTO tb_emp(id, password, name) VALUES(#{id}, #{password}, #{name})")
    void insertEmp(Emp emp);

    @SelectProvider(type = EmpSQLProvider.class, method = "selectEmp")
    List<Emp> selectEmp(Emp emp);

    @UpdateProvider(type = EmpSQLProvider.class, method = "updateEmp")
    Integer updateEmp(Emp emp);

    @Select("SELECT id, name FROM tb_emp WHERE id = #{empId} AND password = #{empPassword} ")
    List<Emp> selectEmpWhenLogin(String empId, String empPassword);

    @Delete("DELETE FROM tb_emp WHERE id = #{empId} AND name = #{empName} AND id <> 'admin' ")
    Integer deleteEmp(String empId, String empName);
}
