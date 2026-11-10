package dev.ouhteen.lakeSide_hotel.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookedRoomTest {

    @Test
    void calculateTotalNumOfGuestsShouldAddAdultsAndChildren() {
        BookedRoom bookedRoom = new BookedRoom();

        bookedRoom.setTotalNumOfAdults(2);
        bookedRoom.setTotalNumOfChildren(3);

        assertEquals(5, bookedRoom.getTotalNumOfGuests());
    }

    @Test
    void settingAdultsShouldRecalculateTotalGuests() {
        BookedRoom bookedRoom = new BookedRoom();

        bookedRoom.setTotalNumOfChildren(2);
        bookedRoom.setTotalNumOfAdults(4);

        assertEquals(6, bookedRoom.getTotalNumOfGuests());
    }

    @Test
    void settingChildrenShouldRecalculateTotalGuests() {
        BookedRoom bookedRoom = new BookedRoom();

        bookedRoom.setTotalNumOfAdults(3);
        bookedRoom.setTotalNumOfChildren(2);

        assertEquals(5, bookedRoom.getTotalNumOfGuests());
    }

    @Test
    void calculateTotalNumOfGuestsShouldHandleZeroGuests() {
        BookedRoom bookedRoom = new BookedRoom();

        bookedRoom.setTotalNumOfAdults(0);
        bookedRoom.setTotalNumOfChildren(0);

        assertEquals(0, bookedRoom.getTotalNumOfGuests());
    }

    @Test
    void bookingConfirmationCodeShouldBeSet() {
        BookedRoom bookedRoom = new BookedRoom();

        bookedRoom.setBookingConfirmationCode("1234567890");

        assertEquals(
                "1234567890",
                bookedRoom.getBookingConfirmationCode()
        );
    }

    @Test
    void roomShouldBeAssociatedWithBooking() {
        BookedRoom bookedRoom = new BookedRoom();
        Room room = new Room();

        bookedRoom.setRoom(room);

        assertSame(room, bookedRoom.getRoom());
    }
}