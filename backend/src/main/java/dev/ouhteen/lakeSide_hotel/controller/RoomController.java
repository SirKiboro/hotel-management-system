package dev.ouhteen.lakeSide_hotel.controller;

import dev.ouhteen.lakeSide_hotel.model.Room;
import dev.ouhteen.lakeSide_hotel.responseDto.RoomDto;
import dev.ouhteen.lakeSide_hotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final IRoomService iRoomService;

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
}
