class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<Integer, int[]> rMap = new HashMap<>();
        Map<Integer, int[]> cMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rMap.put(i, grid[i]);

            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                temp[j] = grid[j][i];
            }
            cMap.put(i, temp);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Arrays.equals(rMap.get(i), (cMap.get(j)))) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
