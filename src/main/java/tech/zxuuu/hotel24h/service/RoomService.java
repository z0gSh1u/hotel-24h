package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Room;

import java.util.List;

public interface RoomService {
  Integer addRoom(Room room);

  Integer updateRoom(Room room);

  List<Room> getAllRooms();

  Integer deleteRoom(Integer roomId);

}
