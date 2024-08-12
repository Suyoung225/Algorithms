import java.util.*;

class Solution {
    public int solution(int[] ability, int number) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(ability).forEach(pq::offer);
        while (number-- > 0) {
            int employee1 = pq.poll();
            int employee2 = pq.poll();
            pq.offer(employee1 + employee2);
            pq.offer(employee1 + employee2);
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }

}
