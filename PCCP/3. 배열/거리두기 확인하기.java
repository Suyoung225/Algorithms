// https://school.programmers.co.kr/learn/courses/30/lessons/81302
class Solution {
    private static final int dx[] = {0, -1, 1, 0}; // 상, 좌, 우, 하
    private static final int dy[] = {1, 0, 0, -1};
    private static char[][] room = new char[5][5];
    public int[] solution(String[][] places) {
        int[] ans = new int[5];
        for(int i = 0; i < 5; i++) {
            room = new char[5][5];
            for(int j = 0; j < 5; j++) {
                room[j] = places[i][j].toCharArray();
            }
            ans[i] = (isDistanced()) ? 1:  0;
        }
        
        return ans;
    }
    
    private boolean isDistanced() {
        for(int y = 0; y < 5; y++) {
            for(int x = 0; x < 5; x++) {
                if(room[y][x] != 'P') continue;
                if(!isDistanced(y, x)) return false;
            }
        }
        return true;
    }
    
    private boolean isDistanced(int y, int x) {
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
            switch (room[ny][nx]) {
                case 'P': return false;
                case 'O':
                    if(!isDistanced(ny, nx, 3-d)) return false;
                    break;
            }
        }
        return true;
    }
    
    private boolean isDistanced(int y, int x, int excluded) {
         for (int d = 0; d < 4; d++) {
             if(d == excluded) continue;
             int ny = y + dy[d];
             int nx = x + dx[d];
             if(ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
             if(room[ny][nx] == 'P') return false;
        }
        return true;
    }
        
}
