package Request.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.Map;

@Document(collection = "requests")
public class Request {
    @Id
    private String id;  // MongoDB sẽ tự động tạo ObjectId
    private String requestId;
    private String employeeId;
    private String requestType;
    private String status;
    private Map<String, String> details;  // Sử dụng Map để lưu trữ thông tin chi tiết
    private Date createdAt;
    private Date updatedAt;
    private String approvedBy;

    // Constructors
    public Request() {}

    public Request(String requestId, String employeeId, String requestType, String status,
                   Map<String, String> details, Date createdAt, Date updatedAt, String approvedBy) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.status = status;
        this.details = details;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.approvedBy = approvedBy;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
