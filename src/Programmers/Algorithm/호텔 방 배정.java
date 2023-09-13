package Programmers.Algorithm;

import java.util.*;

class 호텔방배정 {

    Map<Long,Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {

        int size = room_number.length;
        long[] answer = new long[size];

        for (int i = 0; i < size; i++) {
            answer[i] = room(room_number[i]);
        }

        return answer;
    }

    public long room(long roomNumber) {
        // 1. 해당 방에 들어갈 수 있는 경우
        if (!map.containsKey(roomNumber)) {
            map.put(roomNumber,roomNumber + 1);
            return roomNumber;
        }

        long findEnterRoom = map.get(roomNumber);
        long findEmptyRoom = room(findEnterRoom);
        map.put(roomNumber,findEmptyRoom);
        return findEmptyRoom;
    }
}
