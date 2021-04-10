package oppucmm.models;

import oppucmm.controllers.Controller;

public class FormAux {

    private int id;
    private String fullName;
    private String sector;
    private String academicLevel;
    private String latitude;
    private String longitude;
    private String user;

    /*Empty constructor*/
    public FormAux() {
    }
    /*Principal Constructor*/
    public FormAux(String fullName, String sector, String academicLevel, String latitude, String longitude, String user) {
        this.id = id;
        this.fullName = fullName;
        this.sector = sector;
        this.academicLevel = academicLevel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }
    /*Getters and Setters*/
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSector() { return sector; }

    public void setSector(String sector) { this.sector = sector; }

    public String getAcademicLevel() { return academicLevel; }

    public String getLatitude() { return latitude; }


    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

}

