// https://school.programmers.co.kr/learn/courses/30/lessons/68935
class Solution {
    public int solution(int num) {
        // 3진법으로 변환
        String str = Integer.toString(num, 3);
        // 앞뒤로 뒤집기
        String reversed = new StringBuilder(str).reverse().toString();
        // 3진법 수를 10진법 수로 변환
        return Integer.valueOf(reversed, 3);
    }
}
