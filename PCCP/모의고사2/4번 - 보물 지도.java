import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Coord {
        int r;
        int c;
        int isJumped;
        int step = 0;
        Coord(int r, int c, int isJumped, int step) {
            this.r = r;
            this.c = c;
            this.isJumped = isJumped; // 1: jumped
            this.step = step;
        }
    }
    public int solution(int n, int m, int[][] hole) {
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        boolean[][] map = new boolean[n][m]; // true: hole
        for (int[] h : hole) {
            map[h[0] - 1][h[1] - 1] = true;
        }
        int minStep = Integer.MAX_VALUE;
        boolean[][][] isVisited = new boolean[n][m][2];
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(0, 0, 0, 0));
        while (!queue.isEmpty()) {
            Coord now = queue.poll();
            if (now.r == n - 1 && now.c == m - 1) {
                minStep = Math.min(minStep, now.step);
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !map[nr][nc] && !isVisited[nr][nc][now.isJumped]) {
                    isVisited[nr][nc][now.isJumped] = true;
                    queue.add(new Coord(nr, nc, now.isJumped, now.step + 1));
                }
            }
            if (now.isJumped == 1) continue;

            for (int d = 0; d < 4; d++) {
                int nr = now.r + 2 * dr[d];
                int nc = now.c + 2 * dc[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !map[nr][nc] && !isVisited[nr][nc][1]) {
                    isVisited[nr][nc][1] = true;
                    queue.add(new Coord(nr, nc, 1, now.step + 1));
                }
            }
        }

        return (minStep == Integer.MAX_VALUE) ? -1 : minStep;
    }

}
