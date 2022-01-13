package dtos;

import entities.Owner;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private long id;
    private String name;
    private String email;
    private String phone;


    public OwnerDTO(Owner rm) {
    this.id = rm.getId();
    this.name = rm.getName();
    this.email = rm.getEmail();
    this.phone = rm.getPhone();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static List<OwnerDTO> getDtos(List<Owner> rms) {
        List<OwnerDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new OwnerDTO(rm)));
        return rmdtos;
    }



}
