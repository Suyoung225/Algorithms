// https://school.programmers.co.kr/learn/courses/30/lessons/68645
class Solution {
    private static final int[] dx = {0, 1, -1};
    private static final int[] dy = {1, 0, -1};
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int num = 1;
        int x = 0;
        int y = 0;
        int d = 0; // 0: 아래, 1: 오른쪽, 2: 왼쪽 위로 이동
        while(true){
            triangle[y][x] = num++;
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
                d = (d + 1) % 3;
                nx = x + dx[d];
                ny = y + dy[d];
                if(nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) break;
            }
            x = nx;
            y = ny;
        }
        int[] ans = new int[num - 1];
        int idx = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                ans[idx++] = triangle[i][j];
            }
        }
        
        return ans;
    }
}
