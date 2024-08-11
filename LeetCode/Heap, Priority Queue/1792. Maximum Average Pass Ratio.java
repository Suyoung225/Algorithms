class Solution {
    static class Classroom {
        int pass;
        int total;
        double change;
        public Classroom(int pass, int total) {
            this.pass = pass;
            this.total = total;
            this.change = getChange(pass, total);
        }
        static double getChange(int pass, int total) {
           return ((double) pass + 1) / (total + 1) - (double) pass / total;
        }
    }
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Classroom> pq = new PriorityQueue<>((x, y) -> Double.compare(y.change, x.change));

        for (int i = 0; i < classes.length; i++) {
            pq.offer(new Classroom(classes[i][0], classes[i][1]));
        }

        while (extraStudents --> 0) {
            Classroom classroom = pq.poll();
            classroom.pass += 1;
            classroom.total += 1;
            classroom.change = classroom.getChange(classroom.pass, classroom.total);
            pq.offer(classroom);
        }
        double ans = 0;
        while (!pq.isEmpty()) {
            Classroom classroom = pq.poll();
            ans += (double) classroom.pass / classroom.total;
        }

        return ans / classes.length;
    }
}
