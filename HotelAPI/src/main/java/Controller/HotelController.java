package Controller;


import daos.HotelDAO;
import dtos.HotelDTO;
import entities.Hotel;

import entities.Room;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class HotelController {

    private final HotelDAO hotelDAO;

    // Constructor accepting HotelDAO
    public HotelController(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }


    public void getAllHotels(Context ctx) {
        List<Hotel> getall = hotelDAO.getAll();
        List<HotelDTO> hotelDTOsList = new ArrayList<>();
        for (Hotel hotel : getall) {
            hotelDTOsList.add(HotelDTO.fromEntity(hotel));

        }
        ctx.json(hotelDTOsList);

    }

    public void getHotelById(Context ctx) {


        Hotel hotel = hotelDAO.getById(Integer.parseInt(ctx.pathParam("id")));

        if (hotel != null) {
            HotelDTO hotelDTO = HotelDTO.fromEntity(hotel);

            ctx.json(hotelDTO);

        } else {

            ctx.status(404).result("Hotel not found");

        }
    }


    public void createHotel(Context ctx) {

        try {

            HotelDTO hotelDTO = ctx.bodyAsClass(HotelDTO.class);
            Hotel hotel = HotelDTO.toEntity(hotelDTO);
            hotelDAO.create(hotel);

            ctx.json(hotel).status(201);

        } catch (IllegalStateException e) {
            ctx.status(400).result("Invalid hotel data");
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getRoomsByHotelId(Context ctx) {
        try {

            int hotelId = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.getById(hotelId);

            if (hotel == null) {
                ctx.status(404).result("Hotel not found");
                return;
            }


            List<Room> rooms = hotelDAO.returnRooms(hotel);

            if (rooms.isEmpty()) {
                ctx.status(404).result("No rooms found for this hotel.");
            } else {
                ctx.json(rooms);
            }

        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid hotel ID");
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void update(Context ctx) {


    }


    public void delete(Context ctx) {


    }


}
