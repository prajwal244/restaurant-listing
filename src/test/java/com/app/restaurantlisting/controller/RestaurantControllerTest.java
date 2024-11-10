package com.app.restaurantlisting.controller;

import com.app.restaurantlisting.dto.RestaurantDto;
import com.app.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;
    @Mock
    RestaurantService restaurantService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllRestaurants(){
        List<RestaurantDto> mockRestaurants = Arrays.asList(
                new RestaurantDto(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new RestaurantDto(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantService.getAllRestaurant()).thenReturn(mockRestaurants);
        // Call the controller method
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getAllRestaurant();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).getAllRestaurant();
    }

    @Test
    public void testSaveRestaurant(){
        RestaurantDto mockrestaurant = new RestaurantDto(1, "resto 1", "addrress 1", "city1", "desc 1");
        when(restaurantService.addRestaurantInDB(mockrestaurant)).thenReturn(mockrestaurant);
        ResponseEntity<RestaurantDto> response = restaurantController.saveRestaurant(mockrestaurant);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(mockrestaurant,response.getBody());
        verify(restaurantService,times(1)).addRestaurantInDB(mockrestaurant);
    }
    @Test
    public void testFindRestaurantById(){
        Integer mockerRestaurantId=1;
        RestaurantDto mockRestaurant = new RestaurantDto(1, "resto 1", "addrress 1", "city1", "desc 1");
        when(restaurantService.fetchRestaurantById(mockerRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant,HttpStatus.OK));
        ResponseEntity<RestaurantDto> response = restaurantController.findRestaurantById(mockerRestaurantId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurant,response.getBody());
        verify(restaurantService,times(1)).fetchRestaurantById(mockerRestaurantId);
    }
}
