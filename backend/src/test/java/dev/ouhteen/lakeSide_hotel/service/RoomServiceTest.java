package dev.ouhteen.lakeSide_hotel.service;

import dev.ouhteen.lakeSide_hotel.model.Room;
import dev.ouhteen.lakeSide_hotel.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void addNewRoomShouldSaveRoomWithPhoto() throws Exception {
        byte[] photoBytes = {1, 2, 3, 4};

        MockMultipartFile file = new MockMultipartFile(
                "photo",
                "room.jpg",
                "image/jpeg",
                photoBytes
        );

        BigDecimal price = new BigDecimal("150.00");

        when(roomRepository.save(any(Room.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Room savedRoom = roomService.addNewRoom(
                file,
                "Deluxe",
                price
        );

        assertNotNull(savedRoom);
        assertEquals("Deluxe", savedRoom.getRoomType());
        assertEquals(price, savedRoom.getRoomPrice());
        assertNotNull(savedRoom.getPhoto());

        Blob photo = savedRoom.getPhoto();

        assertArrayEquals(
                photoBytes,
                photo.getBytes(1, (int) photo.length())
        );

        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void addNewRoomShouldSaveRoomWithoutPhoto() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "photo",
                new byte[0]
        );

        BigDecimal price = new BigDecimal("100.00");

        when(roomRepository.save(any(Room.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Room savedRoom = roomService.addNewRoom(
                file,
                "Standard",
                price
        );

        assertNotNull(savedRoom);
        assertEquals("Standard", savedRoom.getRoomType());
        assertEquals(price, savedRoom.getRoomPrice());
        assertNull(savedRoom.getPhoto());

        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void addNewRoomShouldPropagateIOException() throws Exception {
        org.springframework.web.multipart.MultipartFile file =
                mock(org.springframework.web.multipart.MultipartFile.class);

        when(file.isEmpty()).thenReturn(false);
        when(file.getBytes()).thenThrow(new IOException("File read failed"));

        assertThrows(
                IOException.class,
                () -> roomService.addNewRoom(
                        file,
                        "Deluxe",
                        new BigDecimal("150.00")
                )
        );

        verify(roomRepository, never()).save(any(Room.class));
    }

    @Test
    void getAllRoomTypesShouldReturnRepositoryResults() {
        List<String> roomTypes = List.of(
                "Standard",
                "Deluxe",
                "Suite"
        );

        when(roomRepository.findDistinctRoomTypes())
                .thenReturn(roomTypes);

        List<String> result = roomService.getAllRoomTypes();

        assertEquals(roomTypes, result);

        verify(roomRepository).findDistinctRoomTypes();
    }
}