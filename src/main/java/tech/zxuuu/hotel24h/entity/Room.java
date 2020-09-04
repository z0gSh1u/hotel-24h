package tech.zxuuu.hotel24h.entity;

import javax.annotation.sql.DataSourceDefinition;

public class Room {
    private Integer id;

    private String type;

    private Integer price;

    public Room(Integer id, String type, Integer price) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
