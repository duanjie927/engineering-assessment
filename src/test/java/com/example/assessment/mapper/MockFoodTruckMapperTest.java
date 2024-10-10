package com.example.assessment.mapper;

import java.util.*;
import java.math.*;

import com.example.assessment.model.FoodTruck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MockFoodTruckMapperTest {

    @InjectMocks
    private MockFoodTruckMapper mockFoodTruckMapper;

    @BeforeEach
    public void setUp() {
        // Any setup can be placed here
    }

    @Test
    public void getAllFoodTrucks_WhenFoodTrucksIsEmpty_ShouldInitializeAndReturnFoodTrucks() throws IOException {
        List<FoodTruck> foodTrucks = mockFoodTruckMapper.getAllFoodTrucks();
        assertNotNull(foodTrucks, "FoodTrucks should not be null");
        assertFalse(foodTrucks.isEmpty(), "FoodTrucks should not be empty");
    }

    @Test
    public void getAllFoodTrucks_WhenFoodTrucksIsNotEmpty_ShouldReturnExistingFoodTrucks() {
        // Arrange
        List<FoodTruck> mockFoodTrucks = mock(List.class);
        System.out.println("000");
        when(mockFoodTrucks.isEmpty()).thenReturn(false);
        System.out.println("1111");

        // Act
        List<FoodTruck> foodTrucks = mockFoodTruckMapper.getAllFoodTrucks();

        // Assert
        assertNotNull(foodTrucks, "FoodTrucks should not be null");
        assertFalse(foodTrucks.isEmpty(), "FoodTrucks should not be empty");
        verify(mockFoodTrucks, never()).isEmpty(); // Ensures the initialization is not attempted
    }

    @Test
    public void getAllFoodTrucks_WhenExceptionOccurs_ShouldThrowRuntimeException()throws IOException,NoSuchFieldException, IllegalAccessException {
        // Arrange
        doThrow(new IOException()).when(mockFoodTruckMapper).initFoodTruckData();

        // Act & Assert
        assertThrows(RuntimeException.class, () -> mockFoodTruckMapper.getAllFoodTrucks());
    }
}
