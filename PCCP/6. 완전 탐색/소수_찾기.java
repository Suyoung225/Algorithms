// https://school.programmers.co.kr/learn/courses/30/lessons/42839
import java.util.*;

class Solution {
    private Set<Integer> primes = new HashSet<>();
    
    private boolean isPrime(int num) {
        if(num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    
    private void getPrimes(int num, int[] nums, boolean[] isVisited) {
        if(isPrime(num)) primes.add(num);
        for (int i = 0; i < nums.length; i++) {
            if(isVisited[i]) continue;
            
            int nextNum = num * 10 + nums[i];
            isVisited[i] = true;
            getPrimes(nextNum, nums, isVisited);
            isVisited[i] = false;
        }
    }
    
    public int solution(String numbers) {
        int[] nums = numbers.chars().map(c -> c - '0').toArray();
        getPrimes(0, nums, new boolean[nums.length]);
        return primes.size();
    }
}
