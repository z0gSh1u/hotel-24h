package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Room;
import tech.zxuuu.hotel24h.mapper.RoomMapper;
import tech.zxuuu.hotel24h.service.RoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public void addRoom(Room room) {
        this.roomMapper.insertRoom(room);
    }

    @Override
    public void updateRoom(Room room) {
        this.roomMapper.updateRoom(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return this.roomMapper.selectAllRooms();
    }

    @Override
    public String deleteRoom(Integer roomId) {
        if(this.roomMapper.selectRoomById(roomId) == null){
            return "此房间号不存在！";
        }
        else{
            this.roomMapper.deleteRoom(roomId);
            return "删除成功！";
        }
    }
}
