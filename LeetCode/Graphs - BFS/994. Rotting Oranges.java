class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Orange> queue = new LinkedList<>();
        int freshCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Orange(i, j , 0));
                } else if (grid[i][j] == 1) {
                    freshCnt++;
                }
            }
        }

        if (freshCnt == 0) {
            return 0;
        }

        int ans = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Orange now = queue.poll();
            ans = Math.max(ans, now.minute);
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    freshCnt--;
                    queue.add(new Orange(nr, nc, now.minute + 1));
                }
            }
        }
        if (freshCnt != 0) {
            return -1;
        }
        return ans;
    }
}

class Orange {
    int r, c, minute;
    public Orange (int r, int c, int minute) {
        this.r = r;
        this.c = c;
        this.minute = minute;
    }
}
