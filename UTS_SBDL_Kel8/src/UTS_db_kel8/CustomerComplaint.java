package UTS_db_kel8;
import java.util.Date;

public class CustomerComplaint {
    private int complaintID;
    private Date timestamp;
    private String description;
    private String status;

    public CustomerComplaint(int complaintID, Date timestamp, String description, String status) {
        this.complaintID = complaintID;
        this.timestamp = timestamp;
        this.description = description;
        this.status = status;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
