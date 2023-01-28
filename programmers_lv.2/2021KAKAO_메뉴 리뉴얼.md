## 코딩테스트 연습 2021 KAKAO BLIND RECRUITMENT 메뉴 리뉴얼

https://school.programmers.co.kr/learn/courses/30/lessons/72411

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    static HashMap<String, Integer> hm;
    
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> menu = new ArrayList<>();
        for (int i : course) {
            hm = new HashMap<>();
            for (String order : orders) {
                if(order.length() >= i){ // 주문한 단품 메뉴 개수가 코스 메뉴 개수보다 같거나 많을 때만 계산
                    combi(order, new char[i], 0, 0, i); 
                }
            }
            if(!hm.isEmpty()){ 
                int max = Collections.max(hm.values());
                for (String s : hm.keySet()) {
                    if(hm.get(s) == max && max > 1) menu.add(s);
                }
            }
        }
        Collections.sort(menu);
        return menu.toArray(new String[0]);
    }
    
    // order: 한 손님의 주문 목록, arr: 조합 배열, depth: 현재 조합 배열 길이, l: 조합에 최근에 저장된 문자열 인덱스, r: 메뉴 구성 개수 
    void combi(String order, char[] arr, int depth, int l, int r){ 
        if(depth == r){
            char[] a = arr.clone();
            Arrays.sort(a);
            String s = String.valueOf(a); // 메뉴
            hm.put(s, hm.getOrDefault(s, 0) + 1);
        }else{
            for (int i = 0; i < order.length(); i++) {
                if(i+l < order.length()){
                    arr[depth] = order.charAt(i+l);
                    combi(order,arr, depth+1, i+l+1, r);
                }
            }
        }
    }
}
```
