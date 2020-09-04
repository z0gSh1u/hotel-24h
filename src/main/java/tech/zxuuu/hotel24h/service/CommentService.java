package tech.zxuuu.hotel24h.service;

import tech.zxuuu.hotel24h.entity.Comment;

import java.util.List;

public interface CommentService {
  Integer insertComment(Comment comment);

  List<Comment> getAllComment();

  Integer removeComment(String orderId);
}
