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
                if(order.length() >= i){
                    combi(order, new char[i], 0 ,0,i); // n = course
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
