class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!set.contains(chars[i])) {
                set.add(chars[i]);
                ans = Math.max(ans, set.size());
            } else {
                while (set.contains(chars[i])) {
                    set.remove(chars[start]);
                    start++;
                }
                set.add(chars[i]);
            }
        }
        ans = Math.max(ans, set.size());
        return ans;
    }
}
