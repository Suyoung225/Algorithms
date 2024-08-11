
class Solution {

    static class Task {
        int num, enqueueTime, processingTime;
        public Task (int num, int enqueueTime, int processingTime) {
            this.num = num;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
    }

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] ans = new int[n];
        PriorityQueue<Task> q = new PriorityQueue<>((x, y) -> {
            if (x.enqueueTime == y.enqueueTime) {
                return x.processingTime - y.processingTime;
            }
            return x.enqueueTime - y.enqueueTime;
        });
        for (int i = 0; i < n; i++) {
            q.offer(new Task(i, tasks[i][0], tasks[i][1]));
        }

        PriorityQueue<Task> pq = new PriorityQueue<>((x, y) -> {
            if (x.processingTime == y.processingTime) {
                return x.num - y.num;
            }
            return x.processingTime - y.processingTime;
        });

        long currTime = 0;
        int idx = 0;
        while (!q.isEmpty() || !pq.isEmpty()) {
            while (!q.isEmpty() && q.peek().enqueueTime <= currTime) {
                pq.offer(q.poll());
            }
            if (!pq.isEmpty()) {
                Task task = pq.poll();
                currTime += task.processingTime;
                ans[idx] = task.num;
                idx++;
            } else {
                currTime = q.peek().enqueueTime;
            }
        }
        return ans;
    }
}

