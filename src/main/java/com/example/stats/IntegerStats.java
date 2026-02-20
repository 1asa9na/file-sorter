package com.example.stats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IntegerStats implements Stats {

    private final List<BigInteger> values = new LinkedList<>();

    @Override
    public void add(String value) {
        BigInteger intValue = new BigInteger(value);
        values.add(intValue);
    }

    @Override
    public Map<String, String> get(boolean isFull) {
        int size = values.size();
        if (size == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        if (isFull) {
            BigInteger minValue = null;
            BigInteger maxValue = null;
            BigInteger sum = BigInteger.ZERO;
            for (BigInteger i : values) {
                minValue = (minValue == null) ? i : (minValue.compareTo(i) > 0) ? i : minValue;
                maxValue = (maxValue == null) ? i : (maxValue.compareTo(i) < 0) ? i : maxValue;
                sum = sum.add(i);
            }
            map.put("Min Value", minValue.toString());
            map.put("Max Value", maxValue.toString());
            map.put("Sum of values", sum.toString());
            BigDecimal sumBigDecimal = new BigDecimal(sum);
            map.put("Average of values", String.valueOf(sumBigDecimal.divide(BigDecimal.valueOf(size), 10, RoundingMode.HALF_UP)));
        }
        map.put("Count of values", String.valueOf(size));
        return map;
    }
    
}
