class Solution {
    static boolean[] visited;
    static int n;
    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        visited = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, isConnected);
                cnt++;
            }
        }
        return cnt;
    }

    static void dfs(int city, int[][] isConnected) {
        if (visited[city]) {
            return;
        }
        visited[city] = true;
        for (int i = 0; i < n; i++) {
            if (i == city) continue;
            if (isConnected[city][i] == 1) {
                dfs(i, isConnected);
            } 
        }
    }
}
