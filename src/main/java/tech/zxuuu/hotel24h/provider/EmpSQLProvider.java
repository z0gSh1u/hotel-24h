package tech.zxuuu.hotel24h.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.zxuuu.hotel24h.entity.Emp;

public class EmpSQLProvider {

    public String selectEmp(Emp emp) {
        return new SQL(){{
            SELECT("id, name");
            FROM("tb_emp");
            if (emp.getId() != "") {
                WHERE("id = #{id}");
            }
            if (emp.getName() != "") {
                WHERE("name = #{name}");
            }
        }}.toString();
    }

    public String updateEmp(Emp emp) {
        return new SQL(){{
            UPDATE("tb_emp");
            if (emp.getName() != "") {
                SET("name = #{name}");
            }
            WHERE("id = #{id} AND id <> 'admin'");
        }}.toString();
    }

}
