// https://school.programmers.co.kr/learn/courses/30/lessons/42583

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        // 다리 길이만큼 0 추가
        for (int i = 0; i < bridge_length; i++) {
            q.add(0);
        }
        // 경과한 시간
        int time = 0;
        int idx = 0;
        int bridgeWeight = 0;
        while(true) {
            bridgeWeight -= q.poll();
            if (idx == truck_weights.length) {
                time += bridge_length;
                break;
            }
            if (truck_weights[idx] + bridgeWeight <= weight) {
                q.add(truck_weights[idx]);
                bridgeWeight += truck_weights[idx];
                idx++;
            } else {
                q.add(0);
            }
            time++;
        }

        return time;
    }
}
