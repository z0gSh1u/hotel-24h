package tech.zxuuu.hotel24h.entity;

public class Comment {

  private String id;
  private String comment;

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
