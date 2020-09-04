
import java.util.Date;

public class Reserve {
    private String id;
    private Integer roomId;
    private Date startDate;
    private Date endDate;
    private String reserverName;
    private String reserverPhone;
    private Integer status;

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

    public String getId() {
        return id;
    }


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


    public Integer getReservePhone() {
        return reserverPhone;
    }

    public void setReservePhone(Integer reservePhone) {
        this.reserverPhone = reservePhone;

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


