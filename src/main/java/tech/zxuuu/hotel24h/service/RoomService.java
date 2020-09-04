package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Room;

import java.util.List;

public interface RoomService {
    void addRoom(Room room);

    void updateRoom(Room room);

    List<Room> getAllRooms();

    String deleteRoom(Integer roomId);
}
