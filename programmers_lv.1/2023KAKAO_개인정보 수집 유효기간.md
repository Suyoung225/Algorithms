## 코딩테스트 연습 2023 KAKAO BLIND RECRUITMENT 개인정보 수집 유효기간

https://school.programmers.co.kr/learn/courses/30/lessons/150370

```java
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public ArrayList<Integer> solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            termsMap.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
        }
        int idx = 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (String privacy : privacies) {
            int plusMonth = termsMap.get(privacy.split(" ")[1]);
            if(getDate(privacy.split(" ")[0]) + plusMonth * 28 <= getDate(today)){
                ans.add(idx);
            }
            idx++;
        }
        return ans;
    }
    
    int getDate(String date){
        String[] dateArr = date.split("[.]");
        return Integer.parseInt(dateArr[0]) * 12 * 28 + Integer.parseInt(dateArr[1]) * 28 + Integer.parseInt(dateArr[2]);
    }
    
}
```
