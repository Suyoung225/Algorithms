## Sherlock and the Valid String

https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem?h_r=internal-search

```java
import java.util.*;

public static String isValid(String s) {
    // Write your code here
    HashMap<Character, Integer> hm = new HashMap<>();
    for (char c : s.toCharArray()) {
        hm.put(c, hm.getOrDefault(c, 0) + 1);
    }
    HashMap<Integer, Integer> numbersMap = new HashMap<>();
    for (Integer value : hm.values()) {
        numbersMap.put(value, numbersMap.getOrDefault(value, 0) + 1);
    }
    if(numbersMap.size() > 2) return "NO";
    if(numbersMap.size() == 1) return "YES";
    List<Integer> keys = new ArrayList<>(numbersMap.keySet());
    List<Integer> values = new ArrayList<>(numbersMap.values());
    if((keys.get(0) == 1 && values.get(0) == 1)
            || (keys.get(1) == 1 && values.get(1) == 1)) return "YES";
    if((Math.abs(keys.get(0) - keys.get(1)) != 1)) return "NO";
    if(values.get(0) == 1 || values.get(1) == 1) return "YES";
    return "NO";
}
```
