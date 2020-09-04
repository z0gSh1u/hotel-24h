package tech.zxuuu.hotel24h.entity;

// 房间类
public class Room {
  private Integer id; // 房号
  private Integer type; // 房间类型，请查看对照表
  private Integer price; // 房间价格

  public Room(Integer id, Integer type, Integer price) {
    this.id = id;
    this.type = type;
    this.price = price;
  }

  public Room() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Room{" +
      "id=" + id +
      ", type='" + type + '\'' +
      ", price=" + price +
      '}';
  }
}
