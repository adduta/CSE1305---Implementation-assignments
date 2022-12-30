package Week4;

import java.util.PriorityQueue;

public class TaskScheduling {
    /**
     * Computes how fast the given tasks can be finished by the given number of TAs.
     * @param durations Array containing the duration for each tasks.
     * @param n Number of TAs to complete the tasks.
     * @return The shortest time in which all tasks can be completed.
     */
    public static int completeTasks(int[] durations, int n) {
        int time = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Initially, all TAs can start working on a task.
        for (int i = 0; i < n; i++) {
            priorityQueue.add(i);
        }

        int m = durations.length;
        // Find the minimum duration.
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            if (min > durations[i]) min = durations[i];
        }

        // Find the maximum duration.
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            if (max < durations[i]) max = durations[i];
        }

        for (int i = 0; i < m; i++) {
            // Make as many TAs as possible unavailable.
            while (!priorityQueue.isEmpty()) {
                priorityQueue.poll();
            }
        }

        return time;
    }
}
