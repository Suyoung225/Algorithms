import java.util.*;
class Solution {
    public String solution(String input_string) {
        char[] chars = input_string.toCharArray();
        char prev = chars[0];
        Set<Character> set = new HashSet<>();
        List<Character> list = new ArrayList<>();
        for (int i = 1; i < chars.length; i++) {
            if (prev == chars[i]) continue;
            
            // 이미 앞에서 나온 알파벳의 경우 -> 외톨이 알파벳 
            if (set.contains(prev) && !list.contains(prev)) {
                list.add(prev);
            } else { // 아직 안 나온 경우 -> set에 추가
                set.add(prev);
            }
            prev = chars[i];
        }
        
        // 마지막 알파벳
        if (set.contains(prev) && !list.contains(prev)) {
            list.add(prev);
        }
  
        StringBuilder sb = (list.size() == 0) ? new StringBuilder("N") : new StringBuilder();
        list.sort(null);
        for (Character c : list) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}
