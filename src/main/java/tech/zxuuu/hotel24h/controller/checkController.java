package tech.zxuuu.hotel24h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.service.CheckService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/check")
public class checkController {
    @Resource
    private CheckService checkService;

    @GetMapping(path="/preCheck")
    public String preCheck(){
        return "check/preCheck";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String reservationList(@RequestParam("id") String id,@RequestParam("reserverName") String reserverName,@RequestParam("reserverPhone") String reserverPhone){
        Reserve reserve = new Reserve();
        reserve.setId(id);
        reserve.setReserverName(reserverName);
        reserve.setReserverPhone(reserverPhone);
        List<Reserve> reserves=checkService.selectAll(reserve);
        Map map = new HashMap<String,Object>(){{
            put("status",1);
            put("data",reserves);
        }};
        return JSONUtils.buildJSON(map);
    }

    @RequestMapping(path="/checkIn")
    @ResponseBody
    public String checkIn(@RequestParam("id") String id){
        Reserve reserve=new Reserve();
        reserve.setId(id);
        System.out.println(reserve);
        String result= this.checkService.checkIn(reserve);
        return result;
    }

    @RequestMapping(path="/checkOut")
    @ResponseBody
    public String checkOut(@RequestParam("id") String id){
        Reserve reserve=new Reserve();
        reserve.setId(id);
        System.out.println(reserve);
        String result= this.checkService.checkOut(reserve);
        return result;
    }
}
