package com.app.restaurantlisting.controller;

import com.app.restaurantlisting.dto.RestaurantDto;
import com.app.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurant(){
        List<RestaurantDto> allrestaurant= restaurantService.getAllRestaurant();
        return new ResponseEntity<>(allrestaurant, HttpStatus.OK);
    }
    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody RestaurantDto restaurantDTO) {
        RestaurantDto restaurantAdded = restaurantService.addRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDto> findRestaurantById(@PathVariable Integer id) {
        return restaurantService.fetchRestaurantById(id);
    }

}
