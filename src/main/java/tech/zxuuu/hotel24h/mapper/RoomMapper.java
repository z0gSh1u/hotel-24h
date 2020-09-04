package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.*;
import tech.zxuuu.hotel24h.entity.Room;

import java.util.List;

@Mapper
public interface RoomMapper {
  @Insert("INSERT INTO tb_room VALUE(#{id},#{type},#{price})")
  void insertRoom(Room room);

  @Select("SELECT * FROM tb_room WHERE id=#{id}")
  Room selectRoomById(Integer roomId);

  @Select("SELECT * FROM tb_room")
  List<Room> selectAllRooms();

  @Update("UPDATE tb_room SET type=#{type}, price=#{price} WHERE id=#{id} ")
  void updateRoom(Room room);

  @Delete("DELETE FROM tb_room WHERE id=#{id}")
  void deleteRoom(Integer roomId);
}
