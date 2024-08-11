import java.util.*;

class Solution {
    public long[] solution(int[][] program) {
        long[] ans = new long[11];
        Arrays.sort(program, (x, y) -> {
            if (x[1] == y[1]) { // 호출 시간 같으면 점수 오름차순
                return x[0] - y[0]; 
            }
            return x[1] - y[1]; // 호출 시간 오름차순
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] == y[0]) { // 점수 같으면 호출 시간 오름차순
                return x[1] - y[1];
            }
            return x[0] - y[0]; // 점수 오름차순
        }); 
        int i = 0;
        long currTime = 0;
        while (i < program.length || !pq.isEmpty()) {
            while (i < program.length && program[i][1] <= currTime) {
                pq.offer(program[i]);
                i++;
            } 
            if (!pq.isEmpty()) {
                int[] now = pq.poll();
                ans[now[0]] += (currTime - now[1]); // 대기 시간 추가
                currTime += now[2];
            } else if (i < program.length) {
                currTime = program[i][1];
            }
            
        }
        
        ans[0] = currTime;
        return ans;
    }
}
