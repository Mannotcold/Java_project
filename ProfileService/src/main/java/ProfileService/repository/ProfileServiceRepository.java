package ProfileService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import ProfileService.model.ProfileService;

public interface ProfileServiceRepository extends MongoRepository<ProfileService, String> {
	List<ProfileService> findByCitizenId(String citizenId);
    List<ProfileService> findByPhone(String phone);
    List<ProfileService> findByName(String name);
    Optional<ProfileService> findByEmployeeId(String employeeId);

}
