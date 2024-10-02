import java.util.*;

class Solution {
    static int cnt, n, m;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static List<Integer> oilList = new ArrayList<>();
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        // group 2번부터 넘버링해서 석유량 저장
        oilList.add(0); oilList.add(0);
        int group = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 석유 땅이면서 아직 방문하지 않은 경우
                if (land[i][j] == 1) {
                    cnt = 0; // 그룹별 석유량 측정
                    dfs(i, j, group, land);
                    oilList.add(cnt);
                    group++;
                }
            }
        }
        
        int ans = 0;
        for (int j = 0; j < m; j++) {
            // 열별로 서로 다른 석유 그룹
            Set<Integer> set = new HashSet<>();
            // 열별 총 석유량
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (land[i][j] > 1 && set.add(land[i][j])) {
                    sum += oilList.get(land[i][j]);
                } 
            }
            ans = Math.max(ans, sum);
        
        }
    
        return ans;
    }
    
    void dfs(int i, int j, int group, int[][] land) {
        cnt++; // 석유량
        land[i][j] = group; // 석유 그룹 표시
        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && land[nr][nc] == 1) {
                land[nr][nc] = group;
                dfs(nr, nc, group, land);
            }
        }
    }
}
