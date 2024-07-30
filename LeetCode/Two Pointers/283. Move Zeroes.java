class Solution {
    public void moveZeroes(int[] nums) {
        int zero = 0;
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] == 0 && nums[zero] != 0) {
                zero = idx;
            } else if(nums[idx] != 0 && nums[zero] == 0) {
                int temp = nums[idx];
                nums[idx] = nums[zero];
                nums[zero] = temp;
                zero++;
            }
            idx++;
        }   
    }
}
