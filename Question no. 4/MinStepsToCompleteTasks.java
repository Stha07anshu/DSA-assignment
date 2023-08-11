import java.util.*;

public class MinStepsToCompleteTasks {

    public static int minSteps(int N, int[][] prerequisites) {
        // Create a list to represent the graph, and an array to track in-degrees
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N + 1];

        // Initialize the graph as an ArrayList of ArrayLists
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph and calculate in-degrees
        for (int[] prereq : prerequisites) {
            int x = prereq[0];
            int y = prereq[1];
            graph.get(x).add(y); // Add a directed edge from x to y
            inDegree[y]++;       // Increment in-degree of y
        }

        // Create a queue to perform topological sorting using BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i); // Add tasks with no prerequisites to the queue
            }
        }

        int steps = 0; // To keep track of the number of steps

        // Perform topological sorting using BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll(); // Get a task with no prerequisites
                for (int neighbor : graph.get(current)) {
                    inDegree[neighbor]--; // Decrement in-degree of dependent tasks
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor); // If in-degree becomes 0, add to queue
                    }
                }
            }
            steps++; // Increment steps after processing tasks at this level
        }

        // Check for cycles (tasks with remaining in-degrees)
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] > 0) {
                return -1; // If there's a cycle, not all tasks can be completed
            }
        }

        return steps; // Return the minimum steps to complete all tasks
    }

    public static void main(String[] args) {
        int N = 3;
        int[][] prerequisites = {{1, 3}, {2, 3}};
        int result = minSteps(N, prerequisites);
        System.out.println("Minimum steps: " + result);//output=2
        
    }
}
