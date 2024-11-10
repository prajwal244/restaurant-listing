package com.app.restaurantlisting.service;

import com.app.restaurantlisting.dto.RestaurantDto;
import com.app.restaurantlisting.entity.Restaurant;
import com.app.restaurantlisting.mapper.RestaurantMapper;
import com.app.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;
    public List<RestaurantDto> getAllRestaurant() {
        List<Restaurant> allrestaurant = restaurantRepo.findAll();
        List<RestaurantDto> restaurantDtoList = allrestaurant.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantDtoList;
    }

    public RestaurantDto addRestaurantInDB(RestaurantDto restaurantDTO) {
        Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDto> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant =  restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
