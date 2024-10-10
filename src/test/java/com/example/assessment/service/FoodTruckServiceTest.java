package com.example.assessment.service;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.math.*;

import com.example.assessment.mapper.MockFoodTruckMapper;
import com.example.assessment.model.FoodTruck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoodTruckServiceTest {


    @Mock
    private MockFoodTruckMapper mockFoodTruckMapper;

    @InjectMocks
    private FoodTruckService foodTruckService;

    private List<FoodTruck> foodTrucks;

    @BeforeEach
    public void setUp() {
        /*foodTrucks = new ArrayList<>();
        FoodTruck foodTruck1 = new FoodTruck();
        foodTruck1.setLatitude("37.7749");
        foodTruck1.setLongitude("-122.4194");
        foodTrucks.add(foodTruck1);

        FoodTruck foodTruck2 = new FoodTruck();
        foodTruck2.setLatitude("37.7750");
        foodTruck2.setLongitude("-122.4195");
        foodTrucks.add(foodTruck2);

        when(mockFoodTruckMapper.getAllFoodTrucks()).thenReturn(foodTrucks);*/
    }

    @Test
    public void getFoodTrucksOrderByDistance_ValidInput_ShouldReturnClosestFoodTrucks() {
        List<FoodTruck> result = foodTruckService.getFoodTrucksOrderByDistance(37.7749, -122.4194, 1);

        assertEquals(1, result.size());
        assertEquals("37.7749", result.get(0).getLatitude());
        assertEquals("-122.4194", result.get(0).getLongitude());
    }

    @Test
    public void getFoodTrucksOrderByDistance_LimitExceedsSize_ShouldReturnAllFoodTrucks() {
        List<FoodTruck> result = foodTruckService.getFoodTrucksOrderByDistance(37.7749, -122.4194, 3);

        assertEquals(2, result.size());
        assertEquals("37.7749", result.get(0).getLatitude());
        assertEquals("-122.4194", result.get(0).getLongitude());
        assertEquals("37.7750", result.get(1).getLatitude());
        assertEquals("-122.4195", result.get(1).getLongitude());
    }

    @Test
    public void getFoodTrucksOrderByDistance_EmptyList_ShouldReturnEmptyList() {
        when(mockFoodTruckMapper.getAllFoodTrucks()).thenReturn(new ArrayList<>());
        List<FoodTruck> result = foodTruckService.getFoodTrucksOrderByDistance(37.7749, -122.4194, 1);

        assertEquals(0, result.size());
    }


    @Test
    public void getFoodTruckByLocationId_ValidLocationId_ShouldReturnFoodTruck() {
        Long locationId = 1577176L;
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setLocationId("1577176");

        when(mockFoodTruckMapper.getFoodTruckByLocationId(locationId)).thenReturn(foodTruck);

        FoodTruck result = foodTruckService.getFoodTruckByLocationId(locationId);

        assertEquals(foodTruck, result);
    }

    @Test
    public void getFoodTruckByLocationId_InvalidLocationId_ShouldReturnNull() {
        Long locationId = 1L;

        when(mockFoodTruckMapper.getFoodTruckByLocationId(locationId)).thenReturn(null);

        FoodTruck result = foodTruckService.getFoodTruckByLocationId(locationId);

        assertNull(result);
    }
}
