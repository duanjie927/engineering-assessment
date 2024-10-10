package com.example.assessment.service;


import com.example.assessment.mapper.FoodTruckMapper;
import com.example.assessment.mapper.MockFoodTruckMapper;
import com.example.assessment.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTruckService {

    //@Autowired
    //private FoodTruckMapper foodTruckMapper;

    @Autowired
    private MockFoodTruckMapper mockFoodTruckMapper;


    public List<FoodTruck> getFoodTrucksOrderByDistance(double latitude, double longitude, int limit) {

        List<FoodTruck> foodTrucks = mockFoodTruckMapper.getAllFoodTrucks();

        return findNearestFoodTrucks(foodTrucks, latitude, longitude, limit);
    }



    public FoodTruck getFoodTruckByLocationId(Long LocationId){
        return mockFoodTruckMapper.getFoodTruckByLocationId(LocationId);
    }

    private double calculateStraightLineDistance(double lat1, double lon1, double lat2, double lon2) {
        // 计算纬度和经度的差值
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // 使用直线距离公式计算距离
        return Math.sqrt(deltaLat * deltaLat + deltaLon * deltaLon);
    }


    private List<FoodTruck> findNearestFoodTrucks(List<FoodTruck> foodTrucks, double targetLatitude, double targetLongitude, int limit) {
        foodTrucks.sort((m1, m2) -> {
            double distance1 = calculateStraightLineDistance(targetLatitude, targetLongitude, Double.parseDouble(m1.getLatitude()), Double.parseDouble(m1.getLongitude()));
            double distance2 = calculateStraightLineDistance(targetLatitude, targetLongitude, Double.parseDouble(m2.getLatitude()), Double.parseDouble(m2.getLongitude()));
            return Double.compare(distance1, distance2);
        });

        return foodTrucks.subList(0, Math.min(limit, foodTrucks.size()));
    }





}
