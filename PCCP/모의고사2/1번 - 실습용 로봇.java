class Solution {
    public int[] solution(String command) {
        int[] dy = {1, 0, -1, 0}; // 북, 동, 남, 서
        int[] dx = {0, 1, 0, -1};
        // 현재 위치
        int x = 0;
        int y = 0;
        int d = 0; // 방향 : 북
        for(char c : command.toCharArray()) {
            switch(c) {
                    case 'R' -> d = (d + 1) % 4;
                    case 'L' -> d = (d + 3) % 4;
                    case 'G' -> {
                        x += dx[d];
                        y += dy[d];
                    }
                    case 'B' -> {
                        x -= dx[d];
                        y -= dy[d];
                    }
            }
        }
        
        return new int[] {x, y};
    }
}
