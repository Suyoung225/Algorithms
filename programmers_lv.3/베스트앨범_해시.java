// https://school.programmers.co.kr/learn/courses/30/lessons/42579
import java.util.*;
class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Integer> genresCntMap = new HashMap<>(); // 장르명: 노래 개수
        Map<String, List<Music>> musicMap = new HashMap<>(); // 장르명: {노래 개수, 번호} 리스트
        int n = genres.length;
      
        for(int i = 0; i < n; i++){
            genresCntMap.put(genres[i], genresCntMap.getOrDefault(genres[i], 0) + plays[i]);
          
            if(musicMap.containsKey(genres[i])){
                musicMap.get(genres[i]).add(new Music(plays[i],i));
            }else{
                List<Music> list = new ArrayList<>();
                list.add(new Music(plays[i],i));
                musicMap.put(genres[i], list);
            }
        }
        // 장르 인기순위 정렬
        List<Map.Entry<String, Integer>> list = new ArrayList<>(genresCntMap.entrySet());
        list.sort((e1, e2) -> e2.getValue() - e1.getValue());
        
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            String genre = list.get(i).getKey();
            musicMap.get(genre).sort(null);
            for(int j = 0; j < Math.min(2, musicMap.get(genre).size()); j++){ // 각 장르당 최대 2개씩
                ans.add(musicMap.get(genre).get(j).idx);
            }
            
        }

        return ans;
    }
}

class Music implements Comparable<Music>{
    int plays, idx;
    public Music(int plays, int idx){
        this.plays = plays;
        this.idx = idx;
    }
    
    @Override
    public int compareTo(Music o){
        if(this.plays == o.plays){
            return this.idx - o.idx;
        }else{
            return o.plays - this.plays;
        }
    }
}
