package tech.zxuuu.hotel24h.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.zxuuu.hotel24h.entity.Reserve;

public class checkProvider {
    public String createSelectAllSQL(Reserve reserve){
        return new SQL(){{
            SELECT("*");
            FROM("tb_reserve");
            if (null != reserve.getId()&& !"".equals(reserve.getReserverName())){
                WHERE("id = #{id}");
            }
            if (null != reserve.getReserverName() && !"".equals(reserve.getReserverName())){
                WHERE("reserver_name = #{reserverName}");
            }
            if (null != reserve.getReserverPhone()&& !"".equals(reserve.getReserverName())) {
                WHERE("reserver_phone = #{reserverPhone}");
            }
        }}
        .toString();
    }
}