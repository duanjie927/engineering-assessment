package com.example.assessment;

import com.example.assessment.mapper.MockFoodTruckMapper;
import com.example.assessment.model.FoodTruck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;


@SpringBootTest
class AssessmentApplicationTests {

	@Autowired
	private MockFoodTruckMapper mockFoodTruckMapper;
	@Test
	void contextLoads() {
		List<FoodTruck> allFoodTrucks = mockFoodTruckMapper.getAllFoodTrucks();
//		assertThat(allFoodTrucks).isEqualTo(expectedValue);
//		assertThat(allFoodTrucks).isNotEmpty();
	}

}
