package dev.ouhteen.lakeSide_hotel.responseDto;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomDto {

    private Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private Boolean isBooked = false;
    private String photo;

    private List<BookingDto> bookings;

    public RoomDto(Long id, String roomType, BigDecimal roomPrice) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public RoomDto(Long id, String roomType, BigDecimal roomPrice,
                   Boolean isBooked, byte[] photoBytes, List<BookingDto> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.photo = photoBytes != null
                ? Base64.getEncoder().encodeToString(photoBytes)
                : null;
        this.bookings = bookings;
    }
}
