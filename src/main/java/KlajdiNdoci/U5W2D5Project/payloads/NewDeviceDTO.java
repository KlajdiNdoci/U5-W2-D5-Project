package KlajdiNdoci.U5W2D5Project.payloads;

import KlajdiNdoci.U5W2D5Project.enums.DeviceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewDeviceDTO (
        @NotNull(message = "You have to enter a type for the device!")
        @Size(min = 3, max = 20, message = "The type must have between 3 and 20 characters")
        String deviceType,
        @NotNull(message = "You have to enter a valid user id")
        long userId
) {

}
