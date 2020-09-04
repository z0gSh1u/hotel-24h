package tech.zxuuu.hotel24h.provider;

import org.apache.ibatis.jdbc.SQL;

public class ReserveSQLProvider {

    public String selectAllSQl(){
        return new SQL(){{
            SELECT("id,room_id,name,start_date,end_date," +
                            "reserver_name,reserver_phone,status");
            FROM("tb_reserve");

        }}.toString();
    }

}


