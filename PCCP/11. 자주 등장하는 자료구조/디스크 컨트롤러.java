// https://school.programmers.co.kr/learn/courses/30/lessons/42627

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {

        // 시작 시간-> 작업 시간 기준 오름차순으로 정렬된 작업 리스트
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            jobList.add(new Job(jobs[i][0], jobs[i][1]));
        }
        Collections.sort(jobList);
        // 작업 리스트 큐에 담기
        Queue<Job> q = new LinkedList<>(jobList);
        // 대기 중인 작업 담을 pq, 작업 시간 기준 오름차순
        PriorityQueue<Job> pq = new PriorityQueue<>(
            Comparator.comparingInt(job -> job.duration));
        
        int exec = 0;
        int time = 0;
        while (!q.isEmpty() || !pq.isEmpty()) {
            
            while(!q.isEmpty() && q.peek().start <= time) {
                pq.add(q.poll());
            }
            // 작업이 이뤄지지 않는 텀이 존재하는 경우
            if (pq.isEmpty()) {
                time = q.peek().start;
                continue;
            }
            
            Job job = pq.poll();
            exec += time + job.duration - job.start;  
            time += job.duration;
        }
        return exec / jobs.length;
    }
}

class Job implements Comparable<Job> {
    int start;
    int duration;
    
    public Job (int start, int duration) {
        this.start = start;
        this.duration = duration;
    }
    
    @Override
    public int compareTo(Job o) {
        if (o.start == this.start) {
            return this.duration - o.duration;
        }
        return this.start - o.start;
    }
}
