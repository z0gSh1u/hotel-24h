package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.*;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.provider.checkProvider;

import java.util.List;

// 入住、退房相关

@Mapper
public interface CheckMapper {
  @SelectProvider(type = checkProvider.class, method = "createSelectAllSQL")
  List<Reserve> selectAllReservation(Reserve reserve);

  @Update("UPDATE tb_reserve SET status=#{status} WHERE id=#{id} AND reserver_name=#{reserverName} AND reserver_phone=#{reserverPhone}")
  public void updateReservationStatus(Reserve reserve);

  @Delete("DELETE FROM tb_reserve WHERE id=#{id} AND reserver_name=#{reserverName} AND reserver_phone=#{reserverPhone}")
  public void deleteReservation(Reserve reserve);

  @Select("SELECT * FROM tb_reserve WHERE id=#{id} ")
  public Reserve selectReservationById(Reserve reserve);
}
