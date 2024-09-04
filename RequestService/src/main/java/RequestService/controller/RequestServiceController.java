package RequestService.controller;

import RequestService.model.RequestService;
import RequestService.repository.RequestServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RequestServiceController {

    @Autowired
    private RequestServiceRepository repo;

    // Thêm yêu cầu mới
    @PostMapping("/requests")
    public ResponseEntity<RequestService> ThemYeuCau(@RequestBody RequestService request) {
        try {
            RequestService _request = repo.save(request);
            return new ResponseEntity<>(_request, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/requests/ad/{requestId}")
    public ResponseEntity<RequestService> CapNhatYeuCauad(
        @PathVariable("requestId") String requestId, 
        @RequestParam("status") String status, 
        @RequestParam("approvedBy") String approvedBy) {
        
        Optional<RequestService> requestData = repo.findByRequestId(requestId);
        if (requestData.isPresent()) {
            RequestService _request = requestData.get();
            // Cập nhật các trường status và approvedBy từ query parameters
            _request.setStatus(status);
            _request.setApprovedBy(approvedBy);
            return new ResponseEntity<>(repo.save(_request), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Cập nhật yêu cầu
    @PutMapping("/requests/{id}")
    public ResponseEntity<RequestService> CapNhatYeuCau(@PathVariable("id") String id, @RequestBody RequestService request) {
        Optional<RequestService> requestData = repo.findById(id);
        if (requestData.isPresent()) {
            RequestService _request = requestData.get();
            _request.setRequestId(request.getRequestId());
            _request.setEmployeeId(request.getEmployeeId());
            _request.setRequestType(request.getRequestType());
            _request.setStatus(request.getStatus());
            _request.setDetails(request.getDetails());
            _request.setCreatedAt(request.getCreatedAt());
            _request.setUpdatedAt(request.getUpdatedAt());
            _request.setApprovedBy(request.getApprovedBy());
            return new ResponseEntity<>(repo.save(_request), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa yêu cầu theo ID
    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<Void> deleteRequestByRequestId(@PathVariable("requestId") String requestId) {
        Optional<RequestService> requestData = repo.findByRequestId(requestId);
        if (requestData.isPresent()) {
            repo.deleteByRequestId(requestId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content nếu xóa thành công
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found nếu không tìm thấy
        }
    }

    // Xóa tất cả yêu cầu
    @DeleteMapping("/requests")
    public ResponseEntity<HttpStatus> XoaTatCaYeuCau() {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xem danh sách tất cả yêu cầu
    @GetMapping("/requests")
    public ResponseEntity<List<RequestService>> XemDanhSachYeuCau() {
        try {
            // Trực tiếp lấy tất cả các đối tượng từ repo
            List<RequestService> requestList = repo.findAll();

            // Kiểm tra xem danh sách có rỗng không
            if (requestList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Trả về danh sách với trạng thái OK
            return new ResponseEntity<>(requestList, HttpStatus.OK);
        } catch (Exception e) {
            // In ra thông báo lỗi và trả về trạng thái lỗi
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // Xem tất cả yêu cầu của một employeeId
    @GetMapping("/requests/{employeeId}")
    public ResponseEntity<List<RequestService>> getRequestsByEmployeeId(@PathVariable String employeeId) {
        try {
            List<RequestService> requestList = repo.findByEmployeeId(employeeId);

            if (requestList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(requestList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
