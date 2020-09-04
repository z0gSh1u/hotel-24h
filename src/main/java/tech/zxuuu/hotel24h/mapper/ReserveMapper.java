package tech.zxuuu.hotel24h.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import tech.zxuuu.hotel24h.entity.Reserve;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ReserveMapper {

  @Select("SELECT * FROM tb_reserve WHERE id=#{id}")
  Reserve selectReserveById(String id);

  @Insert("INSERT INTO tb_reserve " +
    "VALUES(#{id},#{roomId},#{startDate},#{endDate},#{reserverName},#{reserverPhone},#{status} )")
  void insertReserve(Reserve reserve);

  @Select("SELECT MAX(id) FROM tb_reserve WHERE id LIKE #{pattern}")
  String selectMaxReserveByDate(String pattern);

  @Select("SELECT * FROM tb_reserve")
  List<Reserve> showAllReserves();

  @Delete("DELETE FROM tb_reserve WHERE id=#{Id}")
  void deleteReserve(String Id);

  @Select("SELECT id FROM tb_room WHERE id NOT IN (" +
    "SELECT room_id FROM tb_reserve" +
    "  WHERE (start_date >= #{start} AND start_date <= #{end}) OR" +
    "        (start_date <= #{start} AND end_date >= #{end}) OR" +
    "        (end_date >= #{start} AND end_date <= #{end})" +
    ") ORDER BY id ASC;\n")
  List<Integer> getAvailableRoomIds(Date start, Date end);

}
