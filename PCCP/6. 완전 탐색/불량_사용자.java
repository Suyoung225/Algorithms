// https://school.programmers.co.kr/learn/courses/30/lessons/64064
import java.util.*;

class Solution {
    // 전체 경우의 수 목록 예) {{frodo, abc123}, {fradi, abc123}}
    private static Set<Set<String>> banSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {

        // bans[i][j]: i번째 banned_id에 매칭되는 j번째 유저 아이디
        // 아무 문자를 나타내는 정규표현식 . 로 변환 필요
        String[][] bans = Arrays.stream(banned_id)
                .map(banned -> banned.replace('*', '.'))
                .map(banned -> Arrays.stream(user_id)
                        .filter(id -> id.matches(banned))
                        .toArray(String[]::new))
                .toArray(String[][]::new);

        count(0, bans, new HashSet<>());
        return banSet.size();
    }

    private void count(int idx, String bans[][], Set<String> banned) {
        if (idx == bans.length) {
            banSet.add(new HashSet<>(banned));
            return;
        }
      
        for (String id : bans[idx]) {
            if (banned.contains(id)) continue;
            banned.add(id);
            count(idx + 1, bans, banned);
            banned.remove(id);
        }
    }
}
