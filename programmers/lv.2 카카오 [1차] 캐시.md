## Coding Test Practice 2018 KAKAO BLIND RECRUITMENT - [1차] 캐시

https://school.programmers.co.kr/learn/courses/30/lessons/17680

**조건** <br>
캐시 교체 알고리즘은 LRU(Least Recently Used)를 사용한다. <br>
cache hit일 경우 실행시간은 1이다.  <br>
cache miss일 경우 실행시간은 5이다. <br>

|캐시크기(cacheSize)|	도시이름(cities)|	실행시간|
|:---|:---|:---|
|3	|["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"]|	50|
|3|	["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]|	21|
|2	|["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"]|	60|
|5|	["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"]	|52||
|2|	["Jeju", "Pangyo", "NewYork", "newyork"]|16|
|0|	["Jeju", "Pangyo", "Seoul", "NewYork", "LA"]	|25|  

&nbsp;

**LRU(Least Recently Used)** 는 가장 오랫동안 참조되지 않은 페이지를 교체하는 방식입니다.
LRU 는 사용된지 가장 오래된 페이지는 앞으로도 사용될 확률이 낮다는 가설에 의해 만들어진 알고리즘

Cache Hit : CPU 가 참조하고자 하는 메모리가 캐시에 존재하고 있을 경우 <br>
Cache Miss : CPU 가 참조하고자 하는 메모리가 캐시에 존재하지 않을 경우 <br>

참고: https://dailylifeofdeveloper.tistory.com/355

&nbsp;

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> queue = new LinkedList<>();
        int cnt = 0;

        for (String city : cities) {
            if(queue.contains(city.toUpperCase())){
                cnt += 1;
                queue.remove(city.toUpperCase());
                queue.add(city.toUpperCase());
            }else{
                cnt += 5;
                if(cacheSize==0){}
                else if (queue.size() < cacheSize) {
                    queue.add(city.toUpperCase());
                }else{
                    queue.poll();
                    queue.add(city.toUpperCase());
                }
            }
        }
        return cnt;
    }
}
```
10점
