package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.zxuuu.hotel24h.entity.Reserve;

@Mapper
public interface CheckMapper {
    @Select("SELECT * FROM tb_reserve WHERE id=#{id} ,reserve_name=#{reserveName} ，reserve_phone=#{reservePhone}")
    public Reserve selectReservationByINP(Reserve reserve);
    @Update("UPDATE tb_reserve SET status=#{status} WHERE id=#{id} ,reserve_name=#{reserveName} ，reserve_phone=#{reservePhone} ")
    public void updateReservationStatus(Reserve reserve);
}
