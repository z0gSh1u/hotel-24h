package tech.zxuuu.hotel24h.entity;
import java.util.Date;

// 房间预订记录类
public class Reserve {
    private String id; // 订单号
    private Integer roomId; // 房间号
    private Date startDate; // 开始时间
    private Date endDate; // 结束时间
    private String reserverName; // 预订人姓名
    private String reserverPhone; // 预订人电话
    private Integer status; // 状态，0：预定还没来；1：预定了在住；2：已退房未评论；3：已退房已评论

    @Override
    public String toString() {
        return "Reserve{" +
          "id='" + id + '\'' +
          ", roomId=" + roomId +
          ", startDate=" + startDate +
          ", endDate=" + endDate +
          ", reserverName='" + reserverName + '\'' +
          ", reserverPhone='" + reserverPhone + '\'' +
          ", status=" + status +
          '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReserverName() {
        return reserverName;
    }

    public void setReserverName(String reserverName) {
        this.reserverName = reserverName;
    }

    public String getReserverPhone() {
        return reserverPhone;
    }

    public void setReserverPhone(String reserverPhone) {
        this.reserverPhone = reserverPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Reserve() {
    }

    public Reserve(String id, Integer roomId, Date startDate, Date endDate, String reserverName, String reserverPhone, Integer status) {
        this.id = id;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reserverName = reserverName;
        this.reserverPhone = reserverPhone;
        this.status = status;
    }
}


