package com.etr.tripcalculator.service;

import com.etr.tripcalculator.domain.Interchanges;
import com.etr.tripcalculator.domain.LocationDesc;
import com.etr.tripcalculator.domain.Route;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
@Slf4j
public class Calculator {
    public static final Double TOLL_RATE_PER_KM = 0.25;

    public Double calculateCostOfTrip(Double distance) {

        return TOLL_RATE_PER_KM * distance;
    }

    public Double calculateDistance(String source, String destination, Interchanges interchanges) {
        String sourceId ="0";
        String  destinationId ="0";
        Map<String, LocationDesc> locationsMap = interchanges.getMap();
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
        log.info("source  ==>  "+ source+" ID ==> "+ sourceId);
        log.info("destination  ==>  "+  destination+" ID ==> "+destinationId);


        int sourceID = Integer.parseInt(sourceId);
        int destinationID = Integer.parseInt(destinationId);
        Double distance = 0.0;
        if(sourceID >0 && destinationID > 0 && sourceID < destinationID && sourceID != destinationID){
            int toID = sourceID;
            while( toID < destinationID){
                Route route = locationsMap.get(String.valueOf(toID)).getRoutes().get(0);
                toID = route.getToId();
                distance+= route.getDistance();
            }
        }else if (sourceID >0 && destinationID > 0 && sourceID > destinationID && sourceID != destinationID){
            int toID = sourceID;
            while( toID > destinationID){
                Route route = locationsMap.get(String.valueOf(toID)).getRoutes().get(1);
                toID = route.getToId();
                distance+= route.getDistance();
            }
        }
        return distance;
    }

    public Interchanges readInterChangesJSONFile() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        //builder.setPrettyPrinting();

        Gson gson = builder.create();

        File file = ResourceUtils.getFile("classpath:interchanges.json");
        String content = new String(Files.readAllBytes(file.toPath()));
        Interchanges interchanges = gson.fromJson(content, Interchanges.class);
        String jsonString = gson.toJson(interchanges);
        //log.info(jsonString);

        return interchanges;

    }
}
