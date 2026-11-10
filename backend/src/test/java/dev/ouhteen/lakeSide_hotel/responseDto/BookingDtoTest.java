package dev.ouhteen.lakeSide_hotel.responseDto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingDtoTest {

    @Test
    void noArgsConstructorShouldInitializeDefaultValues() {
        BookingDto bookingDto = new BookingDto();

        assertNull(bookingDto.getBookingId());
        assertNull(bookingDto.getCheckInTime());
        assertNull(bookingDto.getCheckOutTime());
        assertNull(bookingDto.getGuestFullName());
        assertNull(bookingDto.getGuestEmail());
        assertEquals(0, bookingDto.getTotalNumOfAdults());
        assertEquals(0, bookingDto.getTotalNumOfChildren());
        assertEquals(0, bookingDto.getTotalNumOfGuests());
        assertNull(bookingDto.getBookingConfirmationCode());
        assertNull(bookingDto.getRoom());
    }

    @Test
    void parameterizedConstructorShouldSetBookingDetails() {
        LocalDateTime checkIn = LocalDateTime.of(2026, 11, 5, 14, 0);
        LocalDateTime checkOut = LocalDateTime.of(2026, 11, 7, 11, 0);

        BookingDto bookingDto = new BookingDto(
                1L,
                checkIn,
                checkOut,
                "1234567890"
        );

        assertEquals(1L, bookingDto.getBookingId());
        assertEquals(checkIn, bookingDto.getCheckInTime());
        assertEquals(checkOut, bookingDto.getCheckOutTime());
        assertEquals("1234567890", bookingDto.getBookingConfirmationCode());
    }
}