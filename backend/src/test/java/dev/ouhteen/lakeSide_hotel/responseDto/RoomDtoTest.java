package dev.ouhteen.lakeSide_hotel.responseDto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomDtoTest {

    @Test
    void noArgsConstructorShouldInitializeDefaultValues() {
        RoomDto roomDto = new RoomDto();

        assertFalse(roomDto.getIsBooked());
        assertNull(roomDto.getId());
        assertNull(roomDto.getRoomType());
        assertNull(roomDto.getRoomPrice());
        assertNull(roomDto.getPhoto());
        assertNull(roomDto.getBookings());
    }

    @Test
    void parameterizedConstructorShouldSetRoomDetails() {
        Long id = 1L;
        String roomType = "Deluxe";
        BigDecimal price = new BigDecimal("150.00");

        RoomDto roomDto = new RoomDto(id, roomType, price);

        assertEquals(id, roomDto.getId());
        assertEquals(roomType, roomDto.getRoomType());
        assertEquals(price, roomDto.getRoomPrice());
    }

    @Test
    void fullConstructorShouldEncodePhotoBytes() {
        byte[] photoBytes = {1, 2, 3, 4};

        RoomDto roomDto = new RoomDto(
                1L,
                "Deluxe",
                new BigDecimal("150.00"),
                true,
                photoBytes,
                List.of()
        );

        String expectedPhoto = Base64.getEncoder()
                .encodeToString(photoBytes);

        assertEquals(expectedPhoto, roomDto.getPhoto());
        assertTrue(roomDto.getIsBooked());
        assertEquals(1L, roomDto.getId());
        assertEquals("Deluxe", roomDto.getRoomType());
        assertEquals(new BigDecimal("150.00"), roomDto.getRoomPrice());
        assertNotNull(roomDto.getBookings());
    }

    @Test
    void fullConstructorShouldAllowNullPhoto() {
        RoomDto roomDto = new RoomDto(
                1L,
                "Standard",
                new BigDecimal("100.00"),
                false,
                null,
                List.of()
        );

        assertNull(roomDto.getPhoto());
        assertFalse(roomDto.getIsBooked());
    }
}