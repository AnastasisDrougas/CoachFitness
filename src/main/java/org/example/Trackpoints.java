package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Trackpoints {
    private LocalDateTime timestamp;
    private double longitude;
    private double latitude;
    private double altitude;
    private int heartRate;
    private int cadence;

    public Trackpoints(Node node) {
        Element trackpointElement = (Element) node;
        initiator(trackpointElement);
        System.out.println("Trackpoint");
        System.out.println(longitude);
        //cadence = Integer.parseInt(TrackpointElement.getAttribute(""));
    }

    private void initiator(Element element) {

        //Timestamp
        timestamp = null;
        NodeList timeList = element.getElementsByTagName("Time");
        if (timeList.getLength() > 0){
            String timeStr = timeList.item(0).getTextContent();
            if( !timeStr.isEmpty()){ timestamp = LocalDateTime.ofInstant(Instant.parse(timeStr), ZoneId.systemDefault()); }
        }

        //Position -> longitude ,latitude values.
        NodeList positionList = element.getElementsByTagName("Position");
        if (positionList.getLength() > 0) {
            Element position = (Element) positionList.item(0);

            String lonStr = position.getElementsByTagName("LongitudeDegrees").item(0).getTextContent();
            String latStr = position.getElementsByTagName("LatitudeDegrees").item(0).getTextContent();

            if ((lonStr != null && !lonStr.isEmpty())){
                longitude = Double.parseDouble(lonStr);
            }else{ longitude = 0; }

            if ((latStr != null && !latStr.isEmpty())){
                latitude = Double.parseDouble(latStr);
            }else{ latitude = 0; }

        }else {longitude = 0; latitude = 0;}

        // -> Altitude.
        NodeList altList = element.getElementsByTagName("AltitudeMeters");
        String altStr = altList.item(0).getTextContent();
        if((altStr != null && !altStr.isEmpty())){
            altitude = Double.parseDouble(altStr);
        }else{ altitude = 0; }

        //Heart Rate -> heartRate value.
        NodeList heartrateList = element.getElementsByTagName("HeartRateBpm");
        if (heartrateList.getLength() > 0) {
            Element hrElement = (Element) heartrateList.item(0);
            String hrStr = hrElement.getElementsByTagName("Value").item(0).getTextContent();


            if((hrStr != null) && (!hrStr.isEmpty())){
                heartRate = Integer.parseInt(hrStr);
            }else{ heartRate = 0; }
        }else { heartRate = 0; }

        // Cadence (optional, safe default)
        cadence = 0;
    }
}
