package ProfileService.controller;

import ProfileService.model.ProfileService;
import ProfileService.repository.ProfileServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ProfileServiceController {
	
	@Autowired
	ProfileServiceRepository repo;
	
	// Th��m nh��n vi��n m���i
    @PostMapping("/profiles")
    public ResponseEntity<ProfileService> ThemNhanVien(@RequestBody ProfileService profile) {
        try {
        	ProfileService _profile = repo.save(profile);
            return new ResponseEntity<>(_profile, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // Cập nhật thông tin nhân viên
    @PostMapping("/profiles/{id}")
    public ResponseEntity<ProfileService> CapNhatNhanVien(@PathVariable("id") String id, @RequestBody ProfileService profile) {
        Optional<ProfileService> profileData = repo.findById(id);

        if (profileData.isPresent()) {
            ProfileService _profile = profileData.get();
            _profile.setName(profile.getName());
            _profile.setDob(profile.getDob());
            _profile.setGender(profile.getGender());
            _profile.setCitizenId(profile.getCitizenId());
            _profile.setTaxCode(profile.getTaxCode());
            _profile.setAddress(profile.getAddress());
            _profile.setPhone(profile.getPhone());
            _profile.setEmail(profile.getEmail());
            _profile.setBankAccount(profile.getBankAccount());
            return new ResponseEntity<>(repo.save(_profile), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    // X��a nh��n vi��n theo ID
    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<HttpStatus> XoaMotNhanVien(@PathVariable("id") String id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Xem danh s��ch t���t c��� nh��n vi��n
    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileService>> XemDanhSachNhanVien() {
        try {
            List<ProfileService> profileList = new ArrayList<>();
            repo.findAll().forEach(profileList::add);
            if (profileList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(profileList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/profiles/{id}")
    public ResponseEntity<ProfileService> XemProfileById(@PathVariable("id") String id) {
        try {
            Optional<ProfileService> profile = repo.findById(id);
            if (profile.isPresent()) {
                return new ResponseEntity<>(profile.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    
    @GetMapping("/profilesid/{employeeId}")
    public ResponseEntity<ProfileService> getProfileByEmployeeId(@PathVariable("employeeId") String employeeId) {
        try {
            // Find the profile by employeeId
            Optional<ProfileService> profile = repo.findByEmployeeId(employeeId);

            if (profile.isPresent()) {
                // If the profile is found, return it with HTTP status OK
                return new ResponseEntity<>(profile.get(), HttpStatus.OK);
            } else {
                // If the profile is not found, return HTTP status NOT FOUND
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // In case of any error, return HTTP status INTERNAL SERVER ERROR
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    //lay ds dưa tren citizenId hoặc phone hoặc name
    @GetMapping("/profilesby")
    public ResponseEntity<List<ProfileService>> XemProfileByCriteria(
        @RequestParam(value = "citizenId", required = false) String citizenId,
        @RequestParam(value = "phone", required = false) String phone,
        @RequestParam(value = "name", required = false) String name) {
        try {
            List<ProfileService> profiles;

            if (citizenId != null) {
                profiles = repo.findByCitizenId(citizenId);
            } else if (phone != null) {
                profiles = repo.findByPhone(phone);
            } else if (name != null) {
                profiles = repo.findByName(name);
            } else {
                profiles = repo.findAll(); // Trả về tất cả các profile nếu không có tiêu chí tìm kiếm nào
            }

            if (!profiles.isEmpty()) {
                return new ResponseEntity<>(profiles, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
