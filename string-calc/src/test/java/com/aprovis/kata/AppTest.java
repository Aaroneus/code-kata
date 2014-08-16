package com.aprovis.kata;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(JUnit4.class)
public class AppTest
{
    @Resource
    Calculator calculator = new Calculator();


    @Test
    public void addMethodShouldTakeAStringOfValuesToBeCalculated() {
        String values = "";

        try {
            calculator.add("");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void addMethodShouldReturnZeroIfPassedAnEmptyString() {
        String values = "";
        Integer calculatedValue = calculator.add(values);
        assertThat(calculatedValue, is(0));
    }

    @Test
    public void addMethodShouldReturnGivenValueAsIntegerIfOneValueProvided() {
        String values = "1";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(1));
    }

    @Test
    public void addMethodShouldReturnTheSumOfTwoCommaDelimitedValuesWhenGivenAsAnInput() {
        String values = "1,2";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(3));
    }

    @Test
    public void addMethodShouldAllowForTheAdditionOfNValuesAsInputs() {
        String values = "1,2,3,4";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(10));
    }

    @Test
    public void shouldAllowForNewLineCharactersAsDelimitersForInput(){
        String values = "1\n2\n3\n4";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(10));
    }

    @Test
    public void shouldAllowForAMixtureOfNewLineCharactersAndCommasAsDelimitersForInput() {
        String values = "1\n2,3\n4";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(10));
    }

    @Test
    public void shouldAllowTheClientToSpecifyADelimiter() {
        String values = "//;\n1;2;3;4";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(10));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowThePassingOfNegativeValues() {
        String values = "-1,2,3,-4";
        Integer calculatedValue = calculator.add(values);

        assertThat(calculatedValue, is(10));
    }

    @Test
    public void shouldThrowExceptionContainingTheOffendingNegativeValues() {
        String values = "-1,2,3,-4";

        try {
            calculator.add(values);
            Assert.fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), startsWith("Negatives not allowed - [-1, -4]"));
        }
    }

    @Test
    public void numbersGreaterThanOneThousandShouldNotBeIncludedInTheCalculation() {
        String values = "1,2,3,1002";

        Integer calculatedValue = calculator.add(values);
        assertThat(calculatedValue, is(6));

    }
}










