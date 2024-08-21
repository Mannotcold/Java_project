package DemoWebAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import DemoWebAPI.model.NhanVien;

public interface NhanVienRepository extends MongoRepository<NhanVien, String> {
    // CRUD operations are inherited from MongoRepository
}