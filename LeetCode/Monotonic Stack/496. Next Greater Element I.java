class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n2; i++) {
            map.put(nums2[i], -1);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n2; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                int num = stack.pop();
                map.put(num, nums2[i]);
            }
            stack.push(nums2[i]);
        }

        int[] ans = new int[n1];
        for (int i = 0; i < n1; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}
