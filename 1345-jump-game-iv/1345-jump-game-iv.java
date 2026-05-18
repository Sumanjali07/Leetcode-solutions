import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        if (n == 1) {
            return 0;
        }

        // value -> list of indices
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int s = 0; s < size; s++) {

                int curr = queue.poll();

                // reached last index
                if (curr == n - 1) {
                    return steps;
                }

                // i + 1
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }

                // i - 1
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }

                // same value jumps
                List<Integer> sameValue = map.get(arr[curr]);

                for (int index : sameValue) {
                    if (!visited[index]) {
                        visited[index] = true;
                        queue.offer(index);
                    }
                }

                // clear to avoid repeated processing
                sameValue.clear();
            }

            steps++;
        }

        return -1;
    }
}