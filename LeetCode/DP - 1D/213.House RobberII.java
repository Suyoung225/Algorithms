class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(getMax(0, n - 2, nums), getMax(1, n - 1, nums));
    }
    
    int getMax(int start, int end, int[] nums) {
        int prev = 0;
        int max = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(max, prev + nums[i]);
            prev = max;
            max = cur;
        }
        return max;
    }
}
