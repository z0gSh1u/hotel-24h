

package tech.zxuuu.hotel24h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.zxuuu.hotel24h.entity.Comment;
import tech.zxuuu.hotel24h.hadoop.HDFSReadWrite;
import tech.zxuuu.hotel24h.service.CommentService;
import tech.zxuuu.hotel24h.util.JSONUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  CommentService commentService;

  // 新增评论相关
  @GetMapping("/addView")
  public String toNewCommentView() {
    return "comment/newView";
  }

  @PostMapping("/add")
  public @ResponseBody
  String addComment(@RequestParam("orderId") String orderId, @RequestParam("comment") String comment, @RequestParam String name, @RequestParam String phone) {
    Integer retCode = commentService.insertComment(new Comment(orderId, comment), name, phone);
    Map map = new HashMap<String, Object>() {{
      put("status", retCode);
    }};
    return JSONUtils.buildJSON(map);
  }

  // 评论列表相关
  @GetMapping("/listView")
  public String toListCommentView() {
    return "/comment/listView";
  }

  @GetMapping("/list")
  public @ResponseBody
  String listComment() {
    Map map = new HashMap<String, Object>() {{
      put("status", 1);
      put("data", commentService.getAllComment());
    }};
    return JSONUtils.buildJSON(map);
  }

  // 删除评论相关
  @PostMapping("/remove")
  public @ResponseBody
  String removeComment(@RequestParam("orderId") String orderId) {
    Integer retCode = commentService.removeComment(orderId);
    Map map = new HashMap<String, Object>() {{
      put("status", retCode);
    }};
    return JSONUtils.buildJSON(map);
  }

  // 评论分析
  @GetMapping("/analyze")
  public @ResponseBody
  String analyzeComment() {
    Map<String, Object> map = commentService.analyzeComment();
    return JSONUtils.buildJSON(map);
  }

  @GetMapping("/analyzeView")
  public String analyzeCommentView() {
    return "comment/hadoop";
  }

}

