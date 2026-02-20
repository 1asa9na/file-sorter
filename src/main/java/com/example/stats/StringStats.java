package com.example.stats;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringStats implements Stats {

    private final List<String> values = new LinkedList<>();

    @Override
    public void add(String value) {
        values.add(value);
    }

    @Override
    public Map<String, String> get(boolean isFull) {
        int size = values.size();
        if (size == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        if (isFull) {
            Integer minLength = null;
            Integer maxLength = null;
            for (String s : values) {
                int length = s.length();
                minLength = (minLength == null) ? length : (minLength > length) ? length : minLength;
                maxLength = (maxLength == null) ? length : (maxLength < length) ? length : maxLength;
            }
            map.put("Min Length", minLength.toString());
            map.put("Max Length", maxLength.toString());
        }
        map.put("Count of values", String.valueOf(size));
        return map;
    }
    
}
