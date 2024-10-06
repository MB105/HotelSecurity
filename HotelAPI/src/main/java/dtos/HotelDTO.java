package dtos;

import entities.Hotel;
import entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;
import java.util.stream.Collectors;

@Data

@AllArgsConstructor
public class HotelDTO {
    private Long id;
    private String name;
    private String address;
    private List<RoomDTO> rooms;


    public HotelDTO() {
    }

    public static Hotel toEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());

        if (hotelDTO.getRooms() != null) {
            List<Room> rooms = hotelDTO.getRooms().stream()
                    .map(roomDTO -> RoomDTO.toEntity(roomDTO, hotel))
                    .collect(Collectors.toList());
            hotel.setRooms(rooms);
        }

        return hotel;
    }


    public static HotelDTO fromEntity(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());


        if (hotel.getRooms() != null) {
            List<RoomDTO> roomDTOs = hotel.getRooms().stream()
                    .map(RoomDTO::fromEntity)
                    .collect(Collectors.toList());
            hotelDTO.setRooms(roomDTOs);
        }

        return hotelDTO;
    }

}

