package com.bloomburg.resource.service.impl;

import com.bloomburg.resource.exception.MalformedExpressionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ComputationServiceImplTest {

    private ComputationServiceImpl computationService = new ComputationServiceImpl();

    @Test
    public void testComputeMultiplication() {
        String multiplicationTestInput = "2*3";
        Integer result = computationService.compute(multiplicationTestInput);
        assertEquals(6, result.intValue());
    }

    @Test
    public void testComputeAddition() {
        String additionTestInput = "4+4";
        Integer result = computationService.compute(additionTestInput);
        assertEquals(8, result.intValue());
    }

    @Test
    public void testComputeSubtraction() {
        String divisionTestInput = "20-3";
        Integer result = computationService.compute(divisionTestInput);
        assertEquals(17, result.intValue());
    }

    @Test
    public void testComputeDivision() {
        String divisionTestInput = "96/3";
        Integer result = computationService.compute(divisionTestInput);
        assertEquals(32, result.intValue());
    }

    @Test
    public void testComputeModulus() {
        String divisionTestInput = "22%5";
        Integer result = computationService.compute(divisionTestInput);
        assertEquals(2, result.intValue());
    }

    @Test(expected = NullPointerException.class)
    public void testComputeNullInput() {
        computationService.compute(null);
    }

    @Test
    public void testComputeWithValidSingleDigitInput() {
        Integer result = computationService.compute("111");
        assertEquals(111, result.intValue());
    }

    @Test(expected = MalformedExpressionException.class)
    public void testComputeWithInvalidTextInput() {
        computationService.compute("abcd");
    }
}