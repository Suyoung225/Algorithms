// https://school.programmers.co.kr/learn/courses/30/lessons/70129
class Solution {
    public int[] solution(String s) {
        int loop = 0;
        int removed = 0;
         while (!s.equals("1")) {
             int zeros = countZeros(s);
             removed += zeros;
             loop++;
             int ones = s.length() - zeros;
             s = Integer.toString(ones, 2);
         }
        
        return new int[] {loop, removed};
    }
    
    private static int countZeros(String s) {
        int cnt = 0;
        for(char c : s.toCharArray()) {
            if(c == '0') cnt++;
        }
        return cnt;
    }
}
