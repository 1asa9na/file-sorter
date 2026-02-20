package com.example.stats;

import java.util.Map;

public interface Stats {
    
    void add(String value);
    Map<String, String> get(boolean isFull);
}
