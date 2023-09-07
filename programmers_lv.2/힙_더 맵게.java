import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : scoville){
            pq.add(i);
        }
        int k = pq.peek(); // 가장 작은 스코빌 지수
        int cnt = 0; // 최소 횟수
        while(k < K){
            if(pq.size() < 2){
                cnt = -1;
                break;
            }
            int smallest = pq.poll();
            int secondSmallest = pq.poll();
            int newFood = smallest + (secondSmallest * 2);
            pq.add(newFood);
            cnt++;
            k = pq.peek();
        }
        
        return cnt;
    }
}
