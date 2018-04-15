package com.orange.shop;

public class Line {

    private double longitude;
    private double latitude;
    private String shopDescription;
    private int sunusng;
    private int ipom;
    private int weiwei;

    public Line() {
        super();
    }

    public Line(double longitude, double latitude, String shopDescription, int sunusng, int ipom, int weiwei) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
        this.shopDescription = shopDescription;
        this.sunusng = sunusng;
        this.ipom = ipom;
        this.weiwei = weiwei;
    }

    public Line(String... fields) {
        this.longitude = Double.parseDouble(fields[0]);
        this.latitude = Double.parseDouble(fields[1]);
        this.shopDescription = fields[2];
        this.sunusng = Integer.parseInt(fields[3]);
        this.ipom = Integer.parseInt(fields[4]);
        this.weiwei = Integer.parseInt(fields[5]);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public int getSunusng() {
        return sunusng;
    }

    public void setSunusng(int sunusng) {
        this.sunusng = sunusng;
    }

    public int getIpom() {
        return ipom;
    }

    public void setIpom(int ipom) {
        this.ipom = ipom;
    }

    public int getWeiwei() {
        return weiwei;
    }

    public void setWeiwei(int weiwei) {
        this.weiwei = weiwei;
    }

}
