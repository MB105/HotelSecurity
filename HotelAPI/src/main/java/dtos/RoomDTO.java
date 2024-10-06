package dtos;

import entities.Hotel;
import entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data

@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private Long hotelId;
    private int number;
    private double price;


    public RoomDTO() {
    }

    public static Room toEntity(RoomDTO roomDTO, Hotel hotel) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setNumber(roomDTO.getNumber());
        room.setPrice(roomDTO.getPrice());
        room.setHotel(hotel);
        return room;
    }

    public static RoomDTO fromEntity(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setNumber(room.getNumber());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setHotelId(room.getHotel().getId());
        return roomDTO;
    }

}