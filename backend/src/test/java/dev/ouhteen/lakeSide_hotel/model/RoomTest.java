package dev.ouhteen.lakeSide_hotel.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {

    @Test
    void constructorShouldInitializeBookingsList() {
        Room room = new Room();

        assertNotNull(room.getBookings());
        assertTrue(room.getBookings().isEmpty());
    }

    @Test
    void addBookingShouldAssociateBookingWithRoom() {
        Room room = new Room();
        BookedRoom booking = mock(BookedRoom.class);

        room.addBooking(booking);

        assertEquals(1, room.getBookings().size());
        assertTrue(room.getBookings().contains(booking));
        assertTrue(room.getIsBooked());

        verify(booking).setRoom(room);

        verify(booking).setBookingConfirmationCode(
                argThat(code -> code != null && code.matches("\\d{10}"))
        );
    }

    @Test
    void addBookingShouldInitializeNullBookingsList() {
        Room room = new Room();
        room.setBookings(null);

        BookedRoom booking = mock(BookedRoom.class);

        room.addBooking(booking);

        assertNotNull(room.getBookings());
        assertEquals(1, room.getBookings().size());
        assertTrue(room.getBookings().contains(booking));
        assertTrue(room.getIsBooked());

        verify(booking).setRoom(room);
    }
}