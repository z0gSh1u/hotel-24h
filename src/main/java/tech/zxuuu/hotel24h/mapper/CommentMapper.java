package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.zxuuu.hotel24h.entity.Comment;

import java.util.List;

// 评论相关

@Mapper
public interface CommentMapper {

  @Insert("INSERT INTO tb_comment VALUES (#{id}, #{comment})")
  Boolean insertComment(Comment comment);

  @Select("SELECT id, comment FROM tb_comment WHERE id=#{commentId}")
  Comment selectCommentById(String commentId);

  @Select("SELECT id, comment FROM tb_comment")
  List<Comment> selectAllComment();

  @Delete("DELETE FROM tb_comment WHERE id=#{orderId}")
  Boolean removeComment(String orderId);

  @Select("SELECT comment FROM tb_comment ORDER BY id LIMIT 50")
  List<String> concatRecentComments();

}
