package tech.zxuuu.hotel24h.entity;



public class Reserve {

    private String id;
    private String roomId;
    private String startDate;
    private String endDate;
    private String reserverName;
    private Integer reserverPhone;
    private Integer status;

    public Reserve(){};
    public Reserve(String id, String roomId, String startDate, String endDate, String reserverName,
                   Integer reserverPhone, Integer status) {
        this.id = id;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reserverName = reserverName;
        this.reserverPhone = reserverPhone;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReserverName() {
        return reserverName;
    }

    public void setReserverName(String reserverName) {
        this.reserverName = reserverName;
    }

    public Integer getReservePhone() {
        return reserverPhone;
    }

    public void setReservePhone(Integer reservePhone) {
        this.reserverPhone = reservePhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id='" + id + '\'' +
                ", roomId='" + roomId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", reserverName='" + reserverName + '\'' +
                ", reservePhone=" + reserverPhone +
                ", status=" + status +
                '}';
    }
}


