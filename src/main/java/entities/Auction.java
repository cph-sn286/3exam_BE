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
    private Date date;
    private Time time;

    @OneToMany(mappedBy = "auction")
    private List<Boat> boatList;

    public Auction() {
    }

    public Auction(long id, String name, Date date, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
