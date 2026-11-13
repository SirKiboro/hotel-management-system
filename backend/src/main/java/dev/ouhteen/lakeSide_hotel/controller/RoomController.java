package dev.ouhteen.lakeSide_hotel.controller;

import dev.ouhteen.lakeSide_hotel.exception.PhotoRetrievalException;
import dev.ouhteen.lakeSide_hotel.model.BookedRoom;
import dev.ouhteen.lakeSide_hotel.model.Room;
import dev.ouhteen.lakeSide_hotel.responseDto.BookingDto;
import dev.ouhteen.lakeSide_hotel.responseDto.RoomDto;
import dev.ouhteen.lakeSide_hotel.service.BookedRoomService;
import dev.ouhteen.lakeSide_hotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final IRoomService iRoomService;
    private final BookedRoomService bookingService;

    @PostMapping("/add/new-room")
    public ResponseEntity<RoomDto> addNewRoom(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("roomType")String roomType,
            @RequestParam("roomPrice")BigDecimal roomPrice) throws SQLException, IOException {

        Room savedRoom = iRoomService.addNewRoom(photo,roomType,roomPrice);
        RoomDto response = new RoomDto(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/room/types")
    public List<String> getRoomTypes(){
        return iRoomService.getAllRoomTypes();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDto>> getAllRooms() throws SQLException {
        List<Room> rooms = iRoomService.getAllRooms();
        List<RoomDto> roomResponses = new ArrayList<>();
        for(Room room : rooms){
            byte[] photoBytes = iRoomService.getRoomPhotoByRoomId(room.getId());
            if (photoBytes != null && photoBytes.length > 0){
                String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
                RoomDto roomDto = getRoomResponse(room);
                roomDto.setPhoto(base64Photo);
                roomResponses.add(roomDto);
            }
        }
        return ResponseEntity.ok(roomResponses);
    }

    private RoomDto getRoomResponse(Room room) {
        List<BookedRoom> bookings = getAllBookingsByRoomId(room.getId());
        List<BookingDto> bookingInfo = bookings
                .stream()
                .map(booking -> new BookingDto(
                        booking.getBookingId(),
                        booking.getCheckInTime(),
                        booking.getCheckOutTime(),
                        booking.getBookingConfirmationCode()
                )).toList();
        byte[] photoBytes = null;
        Blob photoBlob = room.getPhoto();
        if (photoBlob != null){
            try {
                photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());

            } catch (SQLException e) {
                throw new PhotoRetrievalException("Error retrieving photo!");
            }
        }
        return new RoomDto(
                room.getId(),
                room.getRoomType(),
                room.getRoomPrice(),
                room.getIsBooked(), photoBytes, bookingInfo);
    }

    private List<BookedRoom> getAllBookingsByRoomId(Long roomId){
        return bookingService.getAllBookingsByRoomId(roomId);
    }
}
