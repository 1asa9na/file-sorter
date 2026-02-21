package com.example.stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FloatStats implements Stats {

    private final List<BigDecimal> values = new LinkedList<>();

    @Override
    public void add(String value) {
        BigDecimal floatValue = new BigDecimal(value);
        values.add(floatValue);
    }

    @Override
    public Optional<Map<String, String>> get(boolean isFull) {
        int size = values.size();
        if (size == 0) {
            return Optional.empty();
        }
        Map<String, String> map = new HashMap<>();
        if (isFull) {
            BigDecimal minValue = null;
            BigDecimal maxValue = null;
            BigDecimal sum = BigDecimal.ZERO;
            for (BigDecimal i : values) {
                minValue = (minValue == null) ? i : (minValue.compareTo(i) > 0) ? i : minValue;
                maxValue = (maxValue == null) ? i : (minValue.compareTo(i) < 0) ? i : maxValue;
                sum = sum.add(i);
            }
            map.put("Min Value", minValue.toString());
            map.put("Max Value", maxValue.toString());
            map.put("Sum of values", sum.toString());
            map.put("Average of values", String.valueOf(sum.divide(BigDecimal.valueOf(size), 10, RoundingMode.HALF_UP)));
        }
        map.put("Count of values", String.valueOf(size));
        return Optional.of(map);
    }
}
