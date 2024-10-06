package Controller;

import daos.HotelDAO;
import daos.RoomDAO;
import dtos.RoomDTO;
import entities.Hotel;
import entities.Room;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private final RoomDAO roomDAO;
    private final HotelDAO hotelDAO;



    public RoomController(RoomDAO roomDAO, HotelDAO hotelDAO) {
        this.roomDAO = roomDAO;
        this.hotelDAO = hotelDAO;
    }


    public void getAllRooms(Context ctx) {
        List<Room> allRooms = roomDAO.getAll();
        List<RoomDTO> roomDTOsList = new ArrayList<>();

        for (Room room : allRooms) {
            roomDTOsList.add(RoomDTO.fromEntity(room));
        }

        ctx.json(roomDTOsList);
    }


    public void getRoomById(Context ctx) {
        int roomId = Integer.parseInt(ctx.pathParam("id"));
        Room room = roomDAO.getById(roomId);

        if (room != null) {
            RoomDTO roomDTO = RoomDTO.fromEntity(room);
            ctx.json(roomDTO);
        } else {
            ctx.status(404).result("Room not found");
        }
    }


    public void createRoom(Context ctx) {
        try {
            RoomDTO roomDTO = ctx.bodyAsClass(RoomDTO.class);


            int hotelId = Integer.parseInt(ctx.pathParam("hotelId"));

            Hotel hotel = hotelDAO.getById(hotelId);

            if (hotel == null) {
                ctx.status(404).result("Hotel not found");
                return;
            }

            Room room = RoomDTO.toEntity(roomDTO,hotel);
            hotel.addRoom(room);
            roomDAO.create(room);

            ctx.json(RoomDTO.fromEntity(room)).status(201);
        } catch (IllegalStateException e) {
            ctx.status(400).result("Invalid room data");
        }
    }
}
