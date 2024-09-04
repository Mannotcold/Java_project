package Request.controller;

import Request.model.Request;
import Request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private RequestRepository repo;

    // Thêm yêu cầu mới
    @PostMapping("/requests")
    public ResponseEntity<Request> ThemYeuCau(@RequestBody Request request) {
        try {
            Request _request = repo.save(request);
            return new ResponseEntity<>(_request, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật yêu cầu
    @PutMapping("/requests/{id}")
    public ResponseEntity<Request> CapNhatYeuCau(@PathVariable("id") String id, @RequestBody Request request) {
        Optional<Request> requestData = repo.findById(id);
        if (requestData.isPresent()) {
            Request _request = requestData.get();
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
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<HttpStatus> XoaMotYeuCau(@PathVariable("id") String id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<List<Request>> XemDanhSachYeuCau() {
        try {
            List<Request> requestList = new ArrayList<>();
            repo.findAll().forEach(requestList::add);
            if (requestList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(requestList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
