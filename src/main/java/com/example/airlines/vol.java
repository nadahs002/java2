package com.example.airlines;

import java.util.Date;

public class vol {
    //idflight
    //depdate
    //deptime
    //aerodep
    //flightdur
    //aeroarr
    private int idflight ;
    private Date depdate;
    private  String deptime;
    private String aerodep;
    private String flightdur;
    private String aeroarr;

    public vol(int idflight , Date depdate, String deptime, String aerodep, String flightdur, String aeroarr) {
        this.idflight=idflight;

        this.depdate = depdate;
        this.deptime = deptime;
        this.aerodep = aerodep;
        this.flightdur = flightdur;
        this.aeroarr = aeroarr;
    }

    public int getIdflight() {
        return idflight;
    }

    public void setIdflight(int idflight) {
        this.idflight = idflight;
    }

    public Date getDepdate() {
        return depdate;
    }

    public void setDepdate(Date depdate) {
        this.depdate = depdate;
    }

    public String getDeptime() {
        return deptime;
    }

    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    public String getAerodep() {
        return aerodep;
    }

    public void setAerodep(String aerodep) {
        this.aerodep = aerodep;
    }

    public String getFlightdur() {
        return flightdur;
    }

    public void setFlightdur(String flightdur) {
        this.flightdur = flightdur;
    }

    public String getAeroarr() {
        return aeroarr;
    }

    public void setAeroarr(String aeroarr) {
        this.aeroarr = aeroarr;
    }
}
