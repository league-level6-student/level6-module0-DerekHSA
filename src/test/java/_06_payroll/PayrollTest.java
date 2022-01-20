package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double a = 5.5;
    	int b = 2;
    	double expected = 11;
        //when
    	double actual = payroll.calculatePaycheck(a, b);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int a = 1;
    	double expected = .575;
        //when
    	double actual = payroll.calculateMileageReimbursement(a);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String a = "bob";
    	double b = 17.5;
    	String expected = "Hello bob, We are pleased to offer you an hourly wage of 17.5";
        //when
    	String actual = payroll.createOfferLetter(a, b);
        //then
    	assertEquals(expected, actual);
    }

}