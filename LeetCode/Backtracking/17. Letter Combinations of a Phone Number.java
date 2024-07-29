class Solution {
    static String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    static List<String> ans;
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        backtracking(digits, 0, new StringBuilder());
        return ans;
    }

    static void backtracking(String digits, int idx, StringBuilder letter) {
        if (idx == digits.length()) {
            ans.add(letter.toString());
            return;
        }
        int num = digits.charAt(idx) - '0';
        for (char c : phone[num].toCharArray()) {
            StringBuilder sb = new StringBuilder(letter);
            backtracking(digits, idx + 1, sb.append(c));
        }

    }
}
