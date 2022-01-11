package dtos;

import entities.Boat;
import entities.Harbour;

import java.util.ArrayList;
import java.util.List;

public class HarbourDTO {
    private long id;
    private String name;
    private String address;
    private int capacity;


    public HarbourDTO(Harbour rm) {
    this.id = rm.getId();
    this.name = rm.getName();
    this.address = rm.getAddress();
    this.capacity = rm.getCapacity();
    }

    public static List<HarbourDTO> getDtos(List<Harbour> rms) {
        List<HarbourDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new HarbourDTO(rm)));
        return rmdtos;
    }
}
