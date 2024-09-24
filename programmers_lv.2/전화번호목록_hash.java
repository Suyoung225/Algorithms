import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 길이 역순으로 정렬
        Arrays.sort(phone_book, (a, b) -> b.length() - a.length());
        // 접두사를 저장할 set 선언
        Set<String> set = new HashSet<>();
        
        // 접두사 set에 해당 번호가 존재하면 false 리턴
        for(String pb: phone_book) {
            if (set.contains(pb)) {
                return false;
            }
            // 접두사를 잘라서 접두사 저장 set에 저장
            for (int i = 1; i <= pb.length(); i++) {
                set.add(pb.substring(0, i));
            }
        }
        
        return true;
    }
}
