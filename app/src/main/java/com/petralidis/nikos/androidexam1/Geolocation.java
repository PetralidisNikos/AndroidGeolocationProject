package com.petralidis.nikos.androidexam1;

public class Geolocation {

    // fields
    private int id;
    private String Userid;
    private float Longitude;
    private float Latitude;
    private String dt;

    // constructor
    public Geolocation()  {}
    public Geolocation(int id, String Userid, float Longitude, float Latitude, String dt) {

        this.id = id;
        this.Userid = Userid;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.dt = dt;
    }

    //getters and setters
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }

    public String getUserid() {
        return Userid;
    }
    public void setUserid(String userid) {
        Userid = userid;
    }

    public float getLongitude() {
        return Longitude;
    }
    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public float getLatitude() {
        return Latitude;
    }
    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public String getdt() {
        return dt;
    }
    public void setdt(String dt) {
        this.dt = dt;
    }
}
