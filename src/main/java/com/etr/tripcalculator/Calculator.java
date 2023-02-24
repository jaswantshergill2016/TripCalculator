package com.etr.tripcalculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Component
public class Calculator {
    public static final Double TOLL_RATE_PER_KM = 0.25;

    public static Double calculateCostOfTrip(Double distance) {

        return TOLL_RATE_PER_KM * distance;
    }

    public static Double calculateDistance(String source, String destination, Map<String,LocationDesc> locationsMap) {
        String sourceId ="0";
        String  destinationId ="0";

        for (Map.Entry<String, LocationDesc> entry : locationsMap.entrySet()) {
            //System.out.println(entry.getKey() + "/" + entry.getValue());

            LocationDesc locationDesc = entry.getValue();

            if(locationDesc.getName().equalsIgnoreCase(source)){
                sourceId = entry.getKey();
            }

            if(locationDesc.getName().equalsIgnoreCase(destination)){
                destinationId = entry.getKey();
            }
        }

        System.out.println("source  ==>  "+ source+" ID ==> "+ sourceId);
        System.out.println("destination  ==>  "+  destination+" ID ==> "+destinationId);
        int sourceID = Integer.parseInt(sourceId);
        int destinationID = Integer.parseInt(destinationId);
        Double distance = 0.0;
        if(sourceID >0 && destinationID > 0 && sourceID < destinationID && sourceID != destinationID){
            int toID = sourceID;
            while( toID < destinationID){
                Route route = locationsMap.get(String.valueOf(toID)).getRoutes().get(0);
                toID = route.getToId();
                distance+= route.getDistance();
                //System.out.println(route.getDistance());
            }
        }else if (sourceID >0 && destinationID > 0 && sourceID > destinationID && sourceID != destinationID){
            int toID = sourceID;
            while( toID > destinationID){
                Route route = locationsMap.get(String.valueOf(toID)).getRoutes().get(1);
                toID = route.getToId();
                distance+= route.getDistance();
                //System.out.println(route.getDistance());
            }
        }
        return distance;
    }

    public static Map<String, LocationDesc> readInterChangesJSONFile() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        File file = ResourceUtils.getFile("classpath:interchanges.json");
        String content = new String(Files.readAllBytes(file.toPath()));
        Interchanges interchanges = gson.fromJson(content, Interchanges.class);
        //jsonString = gson.toJson(interchanges);
        //System.out.println(jsonString);

        return interchanges.getMap();

    }
}
