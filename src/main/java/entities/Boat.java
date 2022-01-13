package entities;

import javax.persistence.*;
import java.util.List;



@Entity
@NamedQuery(name = "Boat.deleteAllRows", query = "DELETE from Boat ")
public class Boat {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String make;
    private String name;
    private int year;
    private String image;

    @ManyToMany(mappedBy = "boatList")
    private List<Owner> ownerList;


    @ManyToOne(cascade = CascadeType.ALL)
    private Auction auction;

    public Boat() {
    }

    public Boat(long id, String brand, String make, String name, String image) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    public Boat(long id, String name, String brand, String make, int year) {

    }

    public List<Owner> getOwners() {
        return ownerList;
    }

    public Boat(String brand, String make, String name, int year) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
