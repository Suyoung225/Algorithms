## Coding Test Practice - 2022 KAKAO TECH INTERNSHIP - 성격 유형 검사하기

https://school.programmers.co.kr/learn/courses/30/lessons/118666

검사 결과는 모든 질문의 성격 유형 점수를 더하여 각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형이라고 판단합니다. 단, 하나의 지표에서 각 성격 유형 점수가 같으면, 두 성격 유형 중 사전 순으로 빠른 성격 유형을 검사자의 성격 유형이라고 판단합니다.
<br>
질문마다 판단하는 지표를 담은 1차원 문자열 배열 survey와 검사자가 각 질문마다 선택한 선택지를 담은 1차원 정수 배열 choices가 매개변수로 주어집니다. 이때, 검사자의 성격 유형 검사 결과를 지표 번호 순서대로 return 하도록 solution 함수를 완성해주세요.
<br>

제한사항<br>
1 ≤ survey의 길이 ( = n) ≤ 1,000 <br>
survey의 원소는 "RT", "TR", "FC", "CF", "MJ", "JM", "AN", "NA" 중 하나입니다. <br>
survey[i]의 첫 번째 캐릭터는 i+1번 질문의 비동의 관련 선택지를 선택하면 받는 성격 유형을 의미합니다. <br>
survey[i]의 두 번째 캐릭터는 i+1번 질문의 동의 관련 선택지를 선택하면 받는  <br>성격 유형을 의미합니다. <br>
choices의 길이 = survey의 길이 <br>

choices[i]는 검사자가 선택한 i+1번째 질문의 선택지를 의미합니다. <br>
1 ≤ choices의 원소 ≤ 7 <br>
choices	뜻 <br>
1	매우 비동의 <br>
2	비동의 <br>
3	약간 비동의 <br>
4	모르겠음 <br>
5	약간 동의 <br>
6	동의 <br>
7	매우 동의 <br>

입출력 예 <br>

survey|	choices|result|
|:---|:---|:---|
|["AN", "CF", "MJ", "RT", "NA"]	|[5, 3, 2, 7, 5]	|"TCMA"|
|["TR", "RT", "TR"]|	[7, 1, 3]	|"RCJA"|



```java
import java.util.HashMap;

public class Solution {
    public String solution(String[] survey, int[] choices) {

        int[] scores = {0,3,2,1,0,1,2,3};

        char[] chars = {'R','T','C','F','J','M','A','N'};
        HashMap<Character,Integer> hm = new HashMap<>();
        for (char aChar : chars) hm.put(aChar,0);

        for (int i = 0; i < survey.length; i++) {
            if(choices[i]<4) {
                hm.put(survey[i].charAt(0),hm.getOrDefault(survey[i].charAt(0),0)+scores[choices[i]]);
            }else {
                hm.put(survey[i].charAt(1),hm.getOrDefault(survey[i].charAt(1),0)+scores[choices[i]]);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append((hm.get('R')>=hm.get('T')) ? 'R' : 'T');
        sb.append((hm.get('C')>=hm.get('F')) ? 'C' : 'F');
        sb.append((hm.get('J')>=hm.get('M')) ? 'J' : 'M');
        sb.append((hm.get('A')>=hm.get('N')) ? 'A' : 'N');

        return sb.toString();
    }
}

```
