## Waiter

https://www.hackerrank.com/challenges/waiter/problem

```java
public static List<Integer> waiter(List<Integer> number, int q) {
  
    List<Integer> ans = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    stack.addAll(number);
  
    List<Integer> primes = getPrimes(q);
  
    for (Integer prime : primes) {
        Stack<Integer> aStack = new Stack<>();
        Stack<Integer> bStack = new Stack<>();
        while (!stack.isEmpty()) {
            int n = stack.pop();
            if(n % prime == 0) bStack.push(n);
            else aStack.push(n);
        }
        stack.addAll(aStack);
        while(!bStack.isEmpty()) ans.add(bStack.pop());
    }
  
    while(!stack.isEmpty()) ans.add(stack.pop());
  
    return ans;
}

static List<Integer> getPrimes(int q){
    List<Integer> primes = new ArrayList<>();
    int num = 2;
    while(primes.size() < q) {
        boolean isPrime = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0){
                isPrime = false;
                break;
            }
        }
        if(isPrime) primes.add(num);
        num += 1;
    }
    return primes;
}
```
