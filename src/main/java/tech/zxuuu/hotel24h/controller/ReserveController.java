package tech.zxuuu.hotel24h.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.service.ReserveService;

import javax.annotation.Resource;

@Controller
@RequestMapping(path="/reserve")
public class ReserveController {
    @Resource
    private ReserveService reserveService;

    @GetMapping(path="/preAdd")
    public String toAdd() {
        return "Reserve/AddReserve";
    }
    @PostMapping(path="/add")
    public String addReserve(Reserve reserve){
        this.reserveService.addReserve(reserve);
        return "forward/reserve/preAdd";
    }




}
