package com.example.util;

import com.example.domain.DataType;
import com.example.stats.FloatStats;
import com.example.stats.IntegerStats;
import com.example.stats.Stats;
import com.example.stats.StringStats;

public class StatsSingletone {
    
    private Stats floatStats = new FloatStats();
    private Stats integerStats = new IntegerStats();
    private Stats stringStats = new StringStats();

    private static StatsSingletone instance;

    public static StatsSingletone getInstance() {
        if (instance == null) {
            instance = new StatsSingletone();
        }
        return instance;
    }

    public Stats getStats(DataType type) {
        return switch (type) {
            case INTEGER -> integerStats;
            case FLOAT -> floatStats;
            case STRING -> stringStats;
        };
    }
}
