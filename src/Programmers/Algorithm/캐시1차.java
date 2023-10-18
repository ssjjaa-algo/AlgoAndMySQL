package Programmers.Algorithm;

import java.util.*;
class 캐시1차 {
    public int solution(int cacheSize, String[] cities) {
        Map<String,Integer> cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cacheSize;
            }
        };


        return calculate(cache, cacheSize, cities);
    }

    public int calculate(Map<String,Integer> cache, int cacheSize, String[] cities) {

        int time = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();

            if (cache.get(city) == null) {
                time += 5;
                cache.put(city,0);
            }
            else time += 1;
        }

        return time;
    }

}
