package com.example.assessment.mapper;


import com.example.assessment.model.FoodTruck;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.*;


@Component
public class MockFoodTruckMapper {

    private List<FoodTruck> foodTrucks = new ArrayList<>();

    public List<FoodTruck> getAllFoodTrucks() {
        if (!CollectionUtils.isEmpty(foodTrucks)){
            return foodTrucks;
        }
        synchronized (MockFoodTruckMapper.class){
            if (!CollectionUtils.isEmpty(foodTrucks)){
                return foodTrucks;
            }
            try {
                initFoodTruckData();
            }catch (Exception exception){
                throw new RuntimeException(exception);
            }
        }
        return foodTrucks;
    }

    public List<FoodTruck> initFoodTruckData() {
        try {
            URL filePath = MockFoodTruckMapper.class.getClassLoader().getResource("Mobile_Food_Facility_Permit.csv");
            foodTrucks = parseFoodTrucks(filePath.getPath());
            return foodTrucks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<FoodTruck> parseFoodTrucks(String filePath) throws IOException {
        List<FoodTruck> merchants = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                FoodTruck merchant = new FoodTruck();
                merchant.setLocationId(record.get("locationid"));
                merchant.setApplicant(record.get("Applicant"));
                merchant.setFacilityType(record.get("FacilityType"));
                merchant.setCnn(record.get("cnn"));
                merchant.setLocationDescription(record.get("LocationDescription"));
                merchant.setAddress(record.get("Address"));
                merchant.setBlockLot(record.get("blocklot"));
                merchant.setBlock(record.get("block"));
                merchant.setLot(record.get("lot"));
                merchant.setPermit(record.get("permit"));
                merchant.setStatus(record.get("Status"));
                merchant.setFoodItems(record.get("FoodItems"));
                merchant.setX(record.get("X"));
                merchant.setY(record.get("Y"));
                merchant.setLatitude(record.get("Latitude"));
                merchant.setLongitude(record.get("Longitude"));
                merchant.setSchedule(record.get("Schedule"));
                merchant.setDayshours(record.get("dayshours"));
                merchant.setNOISent(record.get("NOISent"));
                merchant.setApproved(record.get("Approved"));
                merchant.setReceived(record.get("Received"));
                merchant.setPriorPermit(record.get("PriorPermit"));
                merchant.setExpirationDate(record.get("ExpirationDate"));
                merchant.setLocation(record.get("Location"));
                merchant.setFirePreventionDistricts(record.get("Fire Prevention Districts"));
                merchant.setPoliceDistricts(record.get("Police Districts"));
                merchant.setSupervisorDistricts(record.get("Supervisor Districts"));
                merchant.setZipCodes(record.get("Zip Codes"));
                merchant.setNeighborhoodsOld(record.get("Neighborhoods (old)"));

                merchants.add(merchant);
            }
        }
        return merchants;
    }

    public FoodTruck getFoodTruckByLocationId(Long locationId) {

        return getAllFoodTrucks().stream()
                .filter(foodTruck -> foodTruck.getLocationId().equals(locationId.toString()))
                .findFirst().get();
    }
}
