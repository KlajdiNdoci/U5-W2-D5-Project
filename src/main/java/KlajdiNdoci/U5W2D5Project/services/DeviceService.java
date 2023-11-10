package KlajdiNdoci.U5W2D5Project.services;

import KlajdiNdoci.U5W2D5Project.entities.Device;
import KlajdiNdoci.U5W2D5Project.entities.User;
import KlajdiNdoci.U5W2D5Project.enums.DeviceState;
import KlajdiNdoci.U5W2D5Project.exceptions.NotFoundException;
import KlajdiNdoci.U5W2D5Project.payloads.NewDeviceDTO;
import KlajdiNdoci.U5W2D5Project.repositories.DeviceRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinary;


    public Page<Device> getDevices(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return deviceRepository.findAll(pageable);
    }

    public Device save(NewDeviceDTO body) throws IOException {
        User foundUser = userService.findById(body.deviceId());
        Device newDevice = new Device();
        newDevice.setDeviceState(DeviceState.ASSIGNED);
        newDevice.setDeviceType(body.deviceType());
        newDevice.setUser(foundUser);
        return deviceRepository.save(newDevice);
    }

    public Device findById(long id) throws NotFoundException {
        Device found = null;
        for (Device device : deviceRepository.findAll()) {
            if (device.getId() == id) {
                found = device;
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return found;
        }
    }

    public void findByIdAndDelete(int id) throws NotFoundException {
        Device found = this.findById(id);
        deviceRepository.delete(found);
    }

    public Device findByIdAndUpdate(int id, Device body) throws NotFoundException {
        Device found = this.findById(id);
        found.setUser(body.getUser());
        found.setDeviceState(body.getDeviceState());
        return deviceRepository.save(found);
    }
}
