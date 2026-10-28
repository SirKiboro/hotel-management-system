package dev.ouhteen.lakeSide_hotel.responseDto;

import dev.ouhteen.lakeSide_hotel.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long bookingId;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String guestFullName;

    private String guestEmail;

    private int totalNumOfAdults;

    private int totalNumOfChildren;

    private int totalNumOfGuests;

    private String bookingConfirmationCode;

    private RoomDto room;

    public BookingDto(Long bookingId, LocalDateTime checkInTime,
                      LocalDateTime checkOutTime, String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
