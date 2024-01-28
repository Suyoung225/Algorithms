// https://school.programmers.co.kr/learn/courses/30/lessons/68645
class Solution {
    private static final int[] dx = {0, 1, -1};
    private static final int[] dy = {1, 0, -1};
    public int[] solution(int n) {
        // 삼각형 배열 초기화
        int[][] triangle = new int[n][n];
        // 숫자 초기값
        int num = 1;
        // 현재 위치 (x, y)
        int x = 0;
        int y = 0;
        // 방향 변수 (0: 아래, 1: 오른쪽, 2: 왼쪽 위로 이동)
        int d = 0;
        
        // 무한 루프
        while(true){
            // 현재 위치에 숫자 할당
            triangle[y][x] = num++;
            // 다음 위치 계산
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            // 다음 위치가 배열 범위를 벗어나거나 이미 값이 할당된 경우
            if(nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
                // 방향 전환
                d = (d + 1) % 3;
                // 방향 전환 후 다시 다음 위치 계산
                nx = x + dx[d];
                ny = y + dy[d];
                
                // 방향 전환 후 다음 위치도 범위를 벗어나거나 값이 할당된 경우 종료
                if(nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) break;
            }
            
            // 다음 위치로 이동
            x = nx;
            y = ny;
        }
        
        // 결과 배열 초기화
        int[] ans = new int[num - 1];
        // 결과 배열에 값 할당
        int idx = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                ans[idx++] = triangle[i][j];
            }
        }
        
        // 결과 반환
        return ans;
    }
}
