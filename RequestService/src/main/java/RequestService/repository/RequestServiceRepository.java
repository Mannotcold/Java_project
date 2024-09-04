package RequestService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import RequestService.model.RequestService;

public interface RequestServiceRepository extends MongoRepository<RequestService, String> {
	Optional<RequestService> findByRequestId(String requestId);
    void deleteByRequestId(String requestId);
    
    List<RequestService> findByEmployeeId(String employeeId);
}
