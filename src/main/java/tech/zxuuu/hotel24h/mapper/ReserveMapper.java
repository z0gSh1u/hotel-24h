package tech.zxuuu.hotel24h.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.provider.ReserveSQLProvider;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReserveMapper {


    @Insert("INSERT INTO tb_reserve(id,room_id, start_date,end_date,reserver_name,reserver_phone,status) "+
            "VALUES(#{id},#{roomId},#{startDate},#{endDate},#{reserverName},#{reserverPhone},#{status} )")
    void insertReserve(Reserve reserve);
    @Select("SELECT MAX(id) FROM tb_reserve WHERE id LIKE #{pattern}")
    String selectMaxReserveByDate(String pattern);
    @Select("SELECT id,room_id, start_date,end_date,reserver_name,reserver_phone,status FROM tb_reserve")
    List<Reserve> showAllReserves();
    @Delete("DELETE FROM tb_reserve WHERE id=#{Id}")
    void deleteReserve(String Id);



}
