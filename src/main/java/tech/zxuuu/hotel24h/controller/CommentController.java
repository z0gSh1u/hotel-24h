package tech.zxuuu.hotel24h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.zxuuu.hotel24h.entity.Comment;
import tech.zxuuu.hotel24h.service.CommentService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

  @Autowired
  CommentService commentService;

  @GetMapping("/comment")
  public String toNewCommentView() {
    return "comment/newComment";
  }

  @PostMapping("/comment/add")
  public @ResponseBody String addComment(@RequestParam("orderId") String orderId, @RequestParam("comment") String comment) {
    System.out.println(orderId);
    System.out.println(comment);
    Integer retCode = commentService.insertComment(new Comment(orderId, comment));
    Map map = new HashMap<String, Object>(){{
      put("status", retCode);
    }};
    return JSONUtils.buildJSON(map);
  }

}
