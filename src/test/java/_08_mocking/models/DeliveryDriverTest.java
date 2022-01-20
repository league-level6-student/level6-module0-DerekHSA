package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    @Mock
    CellPhone cellphone;
    
    @Mock
    Car car;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver("bob", car, cellphone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expectedWasteTime = true;
    	when(deliveryDriver.wasteTime()).thenReturn(true);
        //when
    	boolean actualWasteTime = deliveryDriver.wasteTime();
        //then
    	assertEquals(expectedWasteTime, actualWasteTime);
    }

    @Test
    void itShouldRefuel() {
        //given
    	int octane = 85;
    	boolean expectedRefuel = true;
    	when(deliveryDriver.refuel(octane)).thenReturn(true);
        //when
    	boolean actualRefuel = deliveryDriver.refuel(octane);
        //then
    	assertEquals(expectedRefuel,actualRefuel);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expectedContactCustomer = true;
    	when(deliveryDriver.contactCustomer(null)).thenReturn(true);
        //when
    	boolean actualContactCustomer = deliveryDriver.contactCustomer(null);
        //then
    	assertEquals(expectedContactCustomer,actualContactCustomer);
    }

}