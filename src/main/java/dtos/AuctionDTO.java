package dtos;

import entities.Auction;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuctionDTO {
    private long id;
    private String name;
    private String date;
    private String time;
    private String location;


    public AuctionDTO(Auction rm) {
    this.id = rm.getId();
    this.name = rm.getName();
    this.date = rm.getDate();
    this.time = rm.getTime();
    this.location = rm.getLocation();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static List<AuctionDTO> getDtos(List<Auction> rms) {
        List<AuctionDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new AuctionDTO(rm)));
        return rmdtos;
    }
}
