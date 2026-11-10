package dev.ouhteen.lakeSide_hotel.controller;

import dev.ouhteen.lakeSide_hotel.model.Room;
import dev.ouhteen.lakeSide_hotel.service.IRoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @Mock
    private IRoomService iRoomService;

    @InjectMocks
    private RoomController roomController;

    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(roomController)
                .build();
    }

    @Test
    void addNewRoomShouldReturnRoomDto() throws Exception {

        MockMultipartFile photo = new MockMultipartFile(
                "photo",
                "room.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                new byte[]{1, 2, 3}
        );

        Room savedRoom = new Room();
        savedRoom.setId(1L);
        savedRoom.setRoomType("Deluxe");
        savedRoom.setRoomPrice(new BigDecimal("150.00"));

        when(iRoomService.addNewRoom(
                any(),
                eq("Deluxe"),
                eq(new BigDecimal("150.00"))
        )).thenReturn(savedRoom);

        mockMvc.perform(
                        multipart("/rooms/add/new-room")
                                .file(photo)
                                .param("roomType", "Deluxe")
                                .param("roomPrice", "150.00")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.roomType").value("Deluxe"))
                .andExpect(jsonPath("$.roomPrice").value(150.00));

        verify(iRoomService).addNewRoom(
                any(),
                eq("Deluxe"),
                eq(new BigDecimal("150.00"))
        );
    }

    @Test
    void getRoomTypesShouldReturnRoomTypes() throws Exception {

        List<String> roomTypes = List.of(
                "Standard",
                "Deluxe",
                "Suite"
        );

        when(iRoomService.getAllRoomTypes())
                .thenReturn(roomTypes);

        mockMvc.perform(
                        get("/rooms/room/types")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Standard"))
                .andExpect(jsonPath("$[1]").value("Deluxe"))
                .andExpect(jsonPath("$[2]").value("Suite"));

        verify(iRoomService).getAllRoomTypes();
    }
}