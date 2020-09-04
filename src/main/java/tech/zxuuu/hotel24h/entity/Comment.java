package tech.zxuuu.hotel24h.entity;

// 评论类
public class Comment {

  private String id; // 订单号
  private String comment; // 评论内容

  @Override
  public String toString() {
    return "Comment{" +
      "id='" + id + '\'' +
      ", comment='" + comment + '\'' +
      '}';
  }

  public Comment(String id, String comment) {
    this.id = id;
    this.comment = comment;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
