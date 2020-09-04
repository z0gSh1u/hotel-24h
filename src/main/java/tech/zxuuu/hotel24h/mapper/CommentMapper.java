package tech.zxuuu.hotel24h.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.zxuuu.hotel24h.entity.Comment;

@Mapper
public interface CommentMapper {

  @Insert("INSERT INTO tb_comment VALUES (#{id}, #{comment})")
  Boolean insertComment(Comment comment);

  @Select("SELECT id, comment FROM tb_comment WHERE id=#{commentId}")
  Comment selectCommentById(String commentId);



}
