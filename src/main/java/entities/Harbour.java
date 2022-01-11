package entities;

import javax.persistence.*;
import java.util.List;
@Entity
public class Harbour {
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String name;
    private String address;
    private int capacity;

    @OneToMany(mappedBy = "harbour")
    private List<Boat> boatList;

    public Harbour() {
    }

    public Harbour(long id, String name, String address, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
