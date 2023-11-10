package KlajdiNdoci.U5W2D5Project.repositories;

import KlajdiNdoci.U5W2D5Project.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> { Optional<Device> findByState(String state);
}
