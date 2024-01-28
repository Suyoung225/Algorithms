// https://school.programmers.co.kr/learn/courses/30/lessons/12926
class Solution {
    private char push(char c, int n){
        // 알파벳이 아닌 경우 그대로 리턴
        if(!Character.isAlphabetic(c)) return c;
        // position: 알파벳 c의 'A' 또는 'a'로부터의 거리
        char offset = Character.isUpperCase(c) ? 'A' : 'a';
        int position = c - offset;
        // n번 이동 후의 'A' 또는 'a'로부터의 거리
        position = (position + n) % ('Z' - 'A' + 1);
        // 'A' 또는 'a'에서 postion 만큼 거리에 있는 알파벳 리턴
        return (char) (offset + position);
    }
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(push(c, n));
        }
        return sb.toString();
    }
}
