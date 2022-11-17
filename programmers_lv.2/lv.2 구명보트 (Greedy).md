## Coding Test Practice 탐욕법(Greedy) 구명보트

https://school.programmers.co.kr/learn/courses/30/lessons/42885

Description<br>
- 무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.

- 예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.

- 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.

- 사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.

제한사항 <br>
- 무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
- 각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.

입출력 예

|people|	limit|	return|
|:--|:--|:--|
|[70, 50, 80, 50]	|100	|3|
|[70, 80, 50]|	100|	3|


```java
import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.sort(people);
        for (int person : people) {
            deque.add(person);
        }
        int result = 0;
        while(!deque.isEmpty()){
            if(deque.getLast() + deque.getFirst() <= limit){
                int sum = deque.getFirst()+deque.getLast();
                deque.pollFirst();
                deque.pollLast();
                result++;

            }else{
                deque.removeLast();
                result++;
            }
        }
        return result;
    }
}
```

### remove와 poll의 차이점 
remove는 deque가 비어있으면 Exception, poll을 null을 리턴

<br>


문제 설명에 한 보트에 '최대 2명'인 것을 보지 못한 풀이
```java
import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.sort(people);
        for (int person : people) {
            deque.add(person);
        }
        int result = 0;
        while(!deque.isEmpty()){
            if(deque.getLast() + deque.getFirst() <= limit){
                int sum = deque.getFirst()+deque.getLast();
                deque.pollFirst();
                deque.pollLast();
                result++;
                
                if(deque.isEmpty()) continue;

                sum += deque.getFirst();
                while(sum <= limit){
                    deque.removeFirst();
                    sum += deque.getFirst();
                    result++;
                }

            }else{
                deque.removeLast();
                result++;
            }
        }
        return result;
    }
}
```
