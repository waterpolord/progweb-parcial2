package oppucmm.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Form implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String sector;

    @Column(length = 30)
    private String academicLevel;
/*
    @OneToOne()
    private Location location;*/

    public Form() {
    }

    public Form(String name,String sector,String academicLevel){
        this.name = name;
        this.sector = sector;
        this.academicLevel = academicLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }
}
