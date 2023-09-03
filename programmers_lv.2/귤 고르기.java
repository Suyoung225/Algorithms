import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 귤의 크기를 key, 해당 크기의 귤의 개수를 value로 저장하는 map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // 귤 개수를 내림차순으로 정렬
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, Comparator.reverseOrder());

        int ans = 0; // 귤 종류 개수
        int total = 0; // 귤 총 개수 
        
        // 내림차순으로 정렬된 리스트를 순회하면서 귤을 추가하고, 귤 개수가 k 이상이 되면 종료
        for (Integer value : list) {
            ans++; // 귤 종류 개수 증가
            total += value; // 귤 총 개수 증가
            if (total >= k) {
                break;
            }
        }

        return ans; // 결과 반환
    }
}
