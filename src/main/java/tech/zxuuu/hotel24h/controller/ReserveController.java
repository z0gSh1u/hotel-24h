package tech.zxuuu.hotel24h.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.service.ReserveService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import javax.annotation.Resource;
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
    public String addReserve(Reserve reserve) {
        this.reserveService.addReserve(reserve);
        return "Reserve/AddReserve";
    }

    @GetMapping("/preShowAll")
    public String preShowAll() {
        return "Reserve/ShowAllReserves";
    }


    @GetMapping("/showAll")
    public @ResponseBody
    String showAll() {
        List<Reserve> reserves = this.reserveService.queryAllReserves();
        Map map = new HashMap<String, Object>(){{
            put("data", reserves);
        }};
        return JSONUtils.buildJSON(map);
    }
    @PostMapping("/delete")
    public @ResponseBody String deleteReserve(@RequestParam("id") String id){
        this.reserveService.deleteReserve(id);
        return "Reserve/ShowAllReserves";
    }
}
