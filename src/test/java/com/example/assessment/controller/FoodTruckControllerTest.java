package com.example.assessment.controller;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import java.math.*;
import com.example.assessment.controller.FoodTruckController;
import com.example.assessment.controller.request.QueryCondition;
import com.example.assessment.controller.view.FoodTruckView;
import com.example.assessment.model.FoodTruck;
import com.example.assessment.service.FoodTruckService;
import com.example.assessment.utils.BeanUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class FoodTruckControllerTest {


    private MockMvc mockMvc;

    @Mock
    private FoodTruckService foodTruckService;

    @InjectMocks
    private FoodTruckController foodTruckController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(foodTruckController).build();
    }

    @Test
    public void getFoodTruckByLocationId_ValidLocationId_ReturnsFoodTruckView() throws Exception {
        // Arrange
        Long locationId = 1L;
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setApplicant("Test Applicant");
        foodTruck.setLatitude("37.7749");
        foodTruck.setLongitude("-122.4194");
        foodTruck.setLocationId("1");

        when(foodTruckService.getFoodTruckByLocationId(anyLong())).thenReturn(foodTruck);

        // Act & Assert
        mockMvc.perform(get("/foodTruck/locationId/{locationId}", locationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.applicant").value("Test Applicant"))
                .andExpect(jsonPath("$.latitude").value("37.7749"))
                .andExpect(jsonPath("$.longitude").value("-122.4194"))
                .andExpect(jsonPath("$.locationId").value("1"));
    }

    @Test
    public void getFoodTrucksrByCondition_ValidInput_ReturnsListOfFoodTruckView() throws Exception {
        // Arrange
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setLatitude(37.7749);
        queryCondition.setLongitude(-122.4194);
        queryCondition.setLimit(5);

        List<FoodTruck> foodTrucks = new ArrayList<>();
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setApplicant("Test Applicant");
        foodTruck.setLatitude("37.7749");
        foodTruck.setLongitude("-122.4194");
        foodTrucks.add(foodTruck);

        when(foodTruckService.getFoodTrucksOrderByDistance(anyDouble(), anyDouble(), anyInt())).thenReturn(foodTrucks);

        // Act & Assert
        mockMvc.perform(post("/foodTruck/list")
                        .contentType("application/json")
                        .content("{\"latitude\":37.7749,\"longitude\":-122.4194,\"limit\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].applicant").value("Test Applicant"))
                .andExpect(jsonPath("$[0].latitude").value("37.7749"))
                .andExpect(jsonPath("$[0].longitude").value("-122.4194"));
    }
}
