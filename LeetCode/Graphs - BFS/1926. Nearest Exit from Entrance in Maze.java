class Coord {
    int r, c, step;
    public Coord(int r, int c, int step) {
        this.r = r;
        this.c = c;
        this.step = step;
    }
}

class Solution {
    static int[] dr = {1, 0, -1 ,0};
    static int[] dc = {0, 1, 0, -1};
    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(entrance[0], entrance[1], 0));
        while (!q.isEmpty()) {
            Coord now = q.poll();
            int r = now.r;
            int c = now.c;
            if (!visited[r][c]) {
                visited[r][c] = true;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m 
                        && maze[nr][nc] == '.') {
                        if (!visited[nr][nc] && (nr == 0 || nr == n - 1 || nc == 0 || nc == m - 1)) {
                            return now.step + 1;
                        }
                        q.add(new Coord(nr, nc, now.step + 1));
                    }
                }
            }
        }
        return -1;
    }
}
