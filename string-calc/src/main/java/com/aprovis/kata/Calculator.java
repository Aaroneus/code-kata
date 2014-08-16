package com.aprovis.kata;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

/**
 * Created by aaron on 16/08/14.
 */
public class Calculator {

    public Calculator(){
        super();
    }

    public Integer add(String values) {
        validateInput(values);
        List<String> parsedValues = splitString(values);
        return sumValues(parsedValues);

    }

    private void validateInput(String input) {
        List<String> negatives = identifyNegativeValues(input);
        if (negatives.size() > 0){
            throw new RuntimeException("Negatives not allowed - " + Arrays.toString(negatives.toArray()));
        }
    }

    private Integer sumValues(List<String> valuesToSum) {
        Integer sumTotal = 0;

        for(String value : valuesToSum){
            Integer valueToSum = Integer.valueOf(value);
            if(valueToSum <= 1000){
                sumTotal += valueToSum;
            }
        }
        return sumTotal;
    }

    private List<String> identifyNegativeValues(String subject){

        List<String> negatives = new ArrayList<String>();
        Pattern pattern = Pattern.compile("-[0-9]");
        Matcher matcher = pattern.matcher(subject);
        while (matcher.find())
        {
            negatives.add(matcher.group());
        }

        return negatives;
    }

    private Boolean hasCustomDelimiter(String input){
        return input.startsWith("//");
    }

    private List<String> splitString(String toBeSplit) {

        String delimiter = ",\n";

        if(hasCustomDelimiter(toBeSplit)){
            int firstLineDelimiter = toBeSplit.indexOf("\n");
            delimiter += toBeSplit.substring(1,firstLineDelimiter);
            toBeSplit = toBeSplit.substring(firstLineDelimiter);
        }

        return asList(StringUtils.split(toBeSplit, delimiter));
    }


}
