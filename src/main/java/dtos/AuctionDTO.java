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

    public static List<AuctionDTO> getDtos(List<Auction> rms) {
        List<AuctionDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new AuctionDTO(rm)));
        return rmdtos;
    }
}
