package tech.zxuuu.hotel24h.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.service.ReserveService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/reserve")
public class ReserveController {
  @Resource
  private ReserveService reserveService;

  @GetMapping(path = "/preAdd")
  public String toAdd() {
    return "Reserve/AddReserve";
  }

  @PostMapping(path = "/add")
  public @ResponseBody
  String addReserve(Integer roomId, String reserverName, String reserverPhone, Long startDate, Long endDate) {
    reserveService.addReserve(new Reserve(null, roomId, new Date(startDate), new Date(endDate), reserverName, reserverPhone, null));
    Map<String, Object> map = new HashMap<>();
    map.put("status", 1);
    return JSONUtils.buildJSON(map);
  }

  @GetMapping("/preShowAll")
  public String preShowAll() {
    return "Reserve/ShowAllReserves";
  }

  @GetMapping("/showAll")
  public @ResponseBody
  String showAll() {
    List<Reserve> reserves = this.reserveService.queryAllReserves();
    Map map = new HashMap<String, Object>() {{
      put("data", reserves);
    }};
    System.out.println("xxx");
    return JSONUtils.buildJSON(map);
  }

  @PostMapping("/delete")
  public @ResponseBody
  String deleteReserve(@RequestParam("id") String id) {
    reserveService.deleteReserve(id);
    Map<String, Object> map = new HashMap<>();
    map.put("status", 1);
    return JSONUtils.buildJSON(map);
  }

  @GetMapping("/available")
  public @ResponseBody
  String getAvailableRooms(@RequestParam("startDate") Long startTS, @RequestParam("endDate") Long endTS) {
    System.out.println("in metssddsdxhdssfss");
    Date start = new Date(startTS);
    Date end = new Date(endTS);
    List<Integer> res = reserveService.getAvailableRoomIds(start, end);
    Map map = new HashMap<String, Object>() {{
      put("data", res);
    }};
    return JSONUtils.buildJSON(map);
  }
}
