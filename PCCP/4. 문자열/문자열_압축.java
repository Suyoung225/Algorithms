// https://school.programmers.co.kr/learn/courses/30/lessons/60057
import java.util.*;

class Solution {
    // 문자열을 주어진 길이로 나누어 리스트로 반환하는 메소드
    private List<String> splitToList(String s, int len) {
        List<String> tokens = new ArrayList<>();
        for (int startIdx = 0; startIdx < s.length(); startIdx += len) {
            int endIdx = startIdx + len;
            // 마지막 부분은 나누어진 길이보다 짧을 수 있도록 처리
            if (endIdx > s.length()) endIdx = s.length();
            tokens.add(s.substring(startIdx, endIdx));
        }
        return tokens;
    }

    // 문자열을 압축하고 압축된 길이를 반환하는 메소드
    private int compress(String s, int len) {
        StringBuilder sb = new StringBuilder();
        String last = "";
        int cnt = 0;
        for (String token : splitToList(s, len)) {
            if (token.equals(last)) {
                cnt++;
            } else {
                // 연속되는 문자열의 개수가 1보다 크면 개수를 추가
                if (cnt > 1) sb.append(cnt);
                sb.append(last);
                last = token;
                cnt = 1;
            }
        }
        // 마지막 문자열 처리
        if (cnt > 1) sb.append(cnt);
        sb.append(last);

        return sb.length();
    }

    // 모든 길이에 대해 압축 후 최소 길이를 찾아 리턴
    public int solution(String s) {
        int min = Integer.MAX_VALUE;
        for (int len = 1; len <= s.length(); len++) {
            int compressed = compress(s, len);
            min = Math.min(compressed, min);
        }
        return min;
    }
}
