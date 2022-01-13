package entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;
@Entity
public class Auction {
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String name;
    private String date;
    private String time;
    private String location;

    @OneToMany(mappedBy = "auction")
    private List<Boat> boatList;

    public Auction() {
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

    public List<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(List<Boat> boatList) {
        this.boatList = boatList;
    }

    public Auction(long id, String name, String date, String time, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
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


}
