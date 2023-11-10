package KlajdiNdoci.U5W2D5Project.entities;

import KlajdiNdoci.U5W2D5Project.enums.DeviceState;
import KlajdiNdoci.U5W2D5Project.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DeviceType deviceType;
    private DeviceState deviceState;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

