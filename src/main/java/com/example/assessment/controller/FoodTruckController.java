package com.example.assessment.controller;


import com.example.assessment.controller.request.QueryCondition;
import com.example.assessment.controller.view.FoodTruckView;
import com.example.assessment.model.FoodTruck;
import com.example.assessment.service.FoodTruckService;
import com.example.assessment.utils.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foodTruck")
public class FoodTruckController {


    @Autowired
    private FoodTruckService foodTruckService;

    @RequestMapping("ping")
    public String ping(){
        return "pong";
    }


    @RequestMapping("/locationId/{locationId}")
    public FoodTruckView getFoodTruckByLocationId(@PathVariable Long locationId){
        return BeanUtil.copy(foodTruckService.getFoodTruckByLocationId(locationId), FoodTruckView.class);
    }


    @PostMapping("list")
    public List<FoodTruckView> getFoodTrucksrByCondition(@RequestBody QueryCondition queryCondition){
        return BeanUtil.batchCopy(foodTruckService.getFoodTrucksOrderByDistance(queryCondition.getLatitude(), queryCondition.getLongitude(), queryCondition.getLimit()),FoodTruckView.class);
    }



}
