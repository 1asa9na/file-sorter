package com.example.stats;

import java.util.Map;
import java.util.Optional;

public interface Stats {
    
    void add(String value);
    Optional<Map<String, String>> get(boolean isFull);
}
