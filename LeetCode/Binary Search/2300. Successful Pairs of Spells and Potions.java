class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = potions.length;
        int[] ans = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int s = 0;
            int e = potions.length - 1;
            while (s <= e) {
                int m = (s + e) / 2;
                long num = (long) spells[i] * potions[m];
                if (num >= success) {
                    e = m - 1;
                } else {
                    s = m + 1;
                }
            }
            ans[i] = n - s;
        }
        return ans;
    }

}
