package de.wirvsvirus.smart_distance_control;

import java.util.ArrayList;
import java.util.List;

public class RSSI2DISTANCE {
    private  List<RSSI> lookUpTable;
    private double upperTreshhold;
    private  double lowerTreshold;
    public  RSSI2DISTANCE(){
        this(1.5,2.5);
    }
    public  RSSI2DISTANCE(double lowerTreshold, double upperTreshhold){
        this.lowerTreshold = lowerTreshold;
        this.upperTreshhold = upperTreshhold;
        this.lookUpTable = new ArrayList<RSSI>();
        this.lookUpTable.add(new RSSI(0.5,-63.08));
        this.lookUpTable.add(new RSSI(1.0,-66.88));
        this.lookUpTable.add(new RSSI(1.5,-69.10));
        this.lookUpTable.add(new RSSI(2.0,-70.68));
        this.lookUpTable.add(new RSSI(2.5,-71.91));
        this.lookUpTable.add(new RSSI(3.0,-72.91));
        this.lookUpTable.add(new RSSI(3.5,-73.75));
        this.lookUpTable.add(new RSSI(4.0,-74.48));
        this.lookUpTable.add(new RSSI(4.5,-75.13));
        this.lookUpTable.add(new RSSI(5.0,-75.71));
    }
    public double getDistance(double rssi){
        double distance = 5.5;
        for (RSSI element : this.lookUpTable){
            if (rssi > element.getRssi()){
                return element.getDistance();
            }
        }
        return distance;
    }
}
