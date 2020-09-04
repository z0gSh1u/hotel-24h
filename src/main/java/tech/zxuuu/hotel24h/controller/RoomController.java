package tech.zxuuu.hotel24h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    return "room/roomManage";
  }

  @GetMapping("/list")
  public @ResponseBody
  String listRoom() {
    List<Room> rooms = roomService.getAllRooms();
    Map map = new HashMap<String, Object>() {{
      put("data", rooms);
    }};
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/remove")
  public @ResponseBody
  String removeRoom(@RequestParam("roomId") Integer roomId) {
    Integer retCode = roomService.deleteRoom(roomId);
    Map map = new HashMap<String, Object>() {{
      put("status", retCode);
    }};
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/add")
  public @ResponseBody
  String removeRoom(@RequestParam("roomId") Integer roomId, @RequestParam("roomType") Integer roomType, @RequestParam("roomPrice") Integer roomPrice) {
    Room room = new Room(roomId, roomType, roomPrice);
    Integer retCode = roomService.addRoom(room);
    Map map = new HashMap<String, Object>() {{
      put("status", retCode);
    }};
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/modify")
  public @ResponseBody
  String modifyRoom(@RequestParam("roomId") Integer roomId, @RequestParam("roomType") Integer roomType, @RequestParam("roomPrice") Integer roomPrice) {
    Room room = new Room(roomId, roomType, roomPrice);
    System.out.println("okdone");
    Integer retCode = roomService.updateRoom(room);
    Map map = new HashMap<String, Object>() {{
      put("status", retCode);
    }};
    return JSONUtils.buildJSON(map);
  }


}
