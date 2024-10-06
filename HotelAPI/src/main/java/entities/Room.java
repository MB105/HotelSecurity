package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private int number;
    private double price;


    public Room() {}


    public Room(int number, double price, Hotel hotel) {
        this.number = number;
        this.price = price;
        this.hotel = hotel;
    }



}
