package de.wirvsvirus.smart_distance_control;

public class RSSI {
    private double distance;
    private double rssi;
    public RSSI(){
        this(0.0,0.0);
    }
    public RSSI(double distance, double rssi){
        this.distance = distance;
        this.rssi = rssi;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getRssi() {
        return rssi;
    }

    public void setRssi(double rssi) {
        this.rssi = rssi;
    }
}
