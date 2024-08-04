// https://school.programmers.co.kr/learn/courses/30/lessons/49189
import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 인접 리스트 초기화
        List<Integer>[] adjacency = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjacency[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            int e1 = edge[i][0];
            int e2 = edge[i][1];
            adjacency[e1].add(e2);
            adjacency[e2].add(e1);
        }
        
        int max = 0; // 가장 먼 노드까지의 거리
        int[] step = new int[n + 1]; 
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        step[1] = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            max = Math.max(max, step[now]);
            for (Integer next : adjacency[now]) {
                if (step[next] == 0) {
                    queue.add(next);
                    step[next] = step[now] + 1;
                }
            }
        }
        
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            if (max == step[i]) {
                ans++;
            }
        }
        return ans;
    }
}
