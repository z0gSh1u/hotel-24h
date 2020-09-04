package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
  Integer insertComment(Comment comment);

  List<Comment> getAllComment();

  Integer removeComment(String orderId);

  Map<String, Object> analyzeComment();
}
