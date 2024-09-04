package Request.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import Request.model.Request;

public interface RequestRepository extends MongoRepository<Request, String> {
    
}
