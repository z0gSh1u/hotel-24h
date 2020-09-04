package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Comment;
import tech.zxuuu.hotel24h.mapper.CommentMapper;
import tech.zxuuu.hotel24h.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentMapper commentMapper;

  @Override
  /**
   * 状态码：0：评论成功；1：重复评论；2：错误信息
   */
  public Integer insertComment(Comment comment) {
    Comment commentCheck = commentMapper.selectCommentById(comment.getId());
    // TODO: 检查信息错误
    if (commentCheck != null) {
      return 1;
    }
    commentMapper.insertComment(comment);
    return 0;
  }

  @Override
  public List<Comment> getAllComment() {
    return commentMapper.selectAllComment();
  }


  /**
   * 状态码：0：成功；1：失败
   */
  public Integer removeComment(String orderId) {
    return commentMapper.removeComment(orderId) ? 0 : 1;
  }

}
