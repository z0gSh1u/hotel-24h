package tech.zxuuu.hotel24h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.service.CheckService;

import javax.annotation.Resource;

@Controller
@RequestMapping(path = "/check")
public class CheckController {
    @Resource
    private CheckService checkService;

    @GetMapping(path="/preCheck")
    public String PreCheck(Model model, Reserve reserve){
        model.addAttribute("reserve",reserve);
        return "CheckService";
    }

    @RequestMapping(path="/checkIn")
    @ResponseBody
    public String checkIn(@RequestParam Reserve reserve){
        System.out.println();
        String result= this.checkService.checkIn(reserve);
        return result;
    }
}
