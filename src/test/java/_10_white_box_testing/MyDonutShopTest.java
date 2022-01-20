package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock
    PaymentService paymentService;
    
    @Mock
    DeliveryService deliveryService;
    
    @Mock
    BakeryService bakeryService;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order("Mike", "why do you wanna know", 0, 0, "this is a joke", true);
    	when(paymentService.charge(order)).thenReturn(true);
    	when(bakeryService.getDonutsRemaining()).thenReturn(12);
        //when
    	myDonutShop.openForTheDay();
    	myDonutShop.takeOrder(order);
        //then
    	verify(deliveryService, times(1)).scheduleDelivery(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	Order order = new Order("Mike", "why do you wanna know", 12, 0, "this is a joke", true);
    	when(paymentService.charge(order)).thenReturn(true);
    	when(bakeryService.getDonutsRemaining()).thenReturn(11);
        //when
    	myDonutShop.openForTheDay();
    	
        //then
    	Throwable exceptionThrown = assertThrows(IllegalArgumentException.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
    	Order order = new Order("Mike", "why do you wanna know", 12, 0, "this is a joke", true);
    	myDonutShop.closeForTheDay();
        //then
    	Throwable exceptionThrown = assertThrows(IllegalStateException.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(), "Sorry we're currently closed");
    }

}