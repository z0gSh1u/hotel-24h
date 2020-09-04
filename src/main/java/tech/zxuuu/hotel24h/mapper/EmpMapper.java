package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.zxuuu.hotel24h.entity.Emp;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Insert("INSERT INTO tb_emp(id, password, name) VALUES(#{empId}, #{empPassword}, #{empName})")
    void insertEmp(Emp emp);

    @Select("SELECT id, name FROM tb_emp WHERE id = #{empId} AND password = #{empPassword} ")
    List<Emp> selectEmpWhenLogin(Emp emp);
}
