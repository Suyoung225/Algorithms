import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        // 각 섬의 초기 루트 설정
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // cost가 적은 간선부터 선택하도록
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int[] c : costs) {
            pq.offer(c);
        }
      
        // 최소 비용 저장 변수
        int ans = 0;
      
        while(!pq.isEmpty()) {
            // 비용이 가장 적은 다리를 선택
            int[] cur = pq.poll();
            // 선택한 다리의 두 섬이 아직 연결되지 않았다면
            if (find(cur[0]) != find(cur[1])) {
                 // 두 섬을 연결하고, 비용을 더해준다
                union(cur[0], cur[1]);
                ans += cur[2];
            }
        }

        return ans;
    }
     // 주어진 섬의 부모를 찾는 함수 (경로 압축 기법 사용)
    int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]); // 루트를 찾아 재귀적으로 반환
    }
    // 두 섬을 하나의 집합으로 묶는 함수
    void union(int a, int b) {
        parent[find(b)] = parent[a]; // b의 루트를 a의 루트로 설정
    }
}
