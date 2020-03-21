package de.wirvsvirus.smart_distance_control;

import java.util.ArrayList;
import java.util.List;

public class RSSI2DISTANCE {
    private  List<RSSI> lookUpTable;
    private double upperTreshhold;
    private  double lowerTreshold;
   enum Farbe{
       rot,gelb,gruen;
   }
    public  RSSI2DISTANCE(){
        this(150,250);
    }
    public  RSSI2DISTANCE(double lowerTreshold, double upperTreshhold){
        this.lowerTreshold = lowerTreshold;
        this.upperTreshhold = upperTreshhold;
        this.lookUpTable = new ArrayList<RSSI>();
        this.lookUpTable.add(new RSSI(50,-63.08));
        this.lookUpTable.add(new RSSI(100,-66.88));
        this.lookUpTable.add(new RSSI(150,-69.10));
        this.lookUpTable.add(new RSSI(200,-70.68));
        this.lookUpTable.add(new RSSI(250,-71.91));
        this.lookUpTable.add(new RSSI(300,-72.91));
        this.lookUpTable.add(new RSSI(350,-73.75));
        this.lookUpTable.add(new RSSI(400,-74.48));
        this.lookUpTable.add(new RSSI(450,-75.13));
        this.lookUpTable.add(new RSSI(500,-75.71));
    }
    public double getDistance(double rssi){
        double distance = 550;
        for (RSSI element : this.lookUpTable){
            if (rssi > element.getRssi()){
                return element.getDistance();
            }
        }
        return distance;
    }
    public Farbe getDistanceAsColor(double rssi){
       double distance = this.getDistance(rssi);
       if (distance < this.lowerTreshold ){
           return Farbe.rot;
       }
       else if ((distance > this.lowerTreshold) & (distance < this.upperTreshhold)){
            return Farbe.gelb;
           }
       else {
           return  Farbe.gruen;
        }
    }

}
