package tech.zxuuu.hotel24h.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.provider.ReserveSQLProvider;
import org.apache.ibatis.annotations.*;
@Mapper
public interface ReserveMapper {


    @Insert("INSERT INTO tb_reserve(id,room_id, start_date,end_date,reserver_name,reserver_phone,status) "+
            "VALUES(#{id},#{roomId},#{startDate},#{endDate},#{reserverName},#{reserverPhone},#{status} )")
    void insertReserve(Reserve reserve);

}
