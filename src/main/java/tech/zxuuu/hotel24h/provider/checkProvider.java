package tech.zxuuu.hotel24h.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.zxuuu.hotel24h.entity.Reserve;

public class checkProvider {
  public String createSelectAllSQL(Reserve reserve) {
    System.out.println(reserve);
    return new SQL() {{
      SELECT("*");
      FROM("tb_reserve");
      WHERE("status < 2");
      if (null != reserve.getId() && !"".equals(reserve.getId())) {
        WHERE("id = #{id}");
      }
      if (null != reserve.getReserverName() && !"".equals(reserve.getReserverName())) {
        WHERE("reserver_name = #{reserverName}");
      }
      if (null != reserve.getReserverPhone() && !"".equals(reserve.getReserverPhone())) {
        WHERE("reserver_phone = #{reserverPhone}");
      }
    }}
      .toString();
  }
}