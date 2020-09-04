package tech.zxuuu.hotel24h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.zxuuu.hotel24h.entity.Room;
import tech.zxuuu.hotel24h.service.RoomService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/listView")
    public String listRoomView() {
        return "KeFangGuanLi";
    }

    @GetMapping("/list")
    public @ResponseBody
    String listRoom(){
        List<Room> rooms = roomService.getAllRooms();
        Map map = new HashMap<String,Object>(){{
            put("status",1);
            put("data", rooms);
        }};
        return JSONUtils.buildJSON(map);
    }
}
