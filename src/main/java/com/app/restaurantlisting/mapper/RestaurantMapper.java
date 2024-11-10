package com.app.restaurantlisting.mapper;

import com.app.restaurantlisting.dto.RestaurantDto;
import com.app.restaurantlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {


    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDto restaurantDTO);

    RestaurantDto mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
