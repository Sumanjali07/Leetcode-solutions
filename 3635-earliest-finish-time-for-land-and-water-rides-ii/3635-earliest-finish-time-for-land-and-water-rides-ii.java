import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int n = landStartTime.length;
        int m = waterStartTime.length;

        int[] landEnd = new int[n];
        for (int i = 0; i < n; i++) {
            landEnd[i] = landStartTime[i] + landDuration[i];
        }

        int[] waterEnd = new int[m];
        for (int i = 0; i < m; i++) {
            waterEnd[i] = waterStartTime[i] + waterDuration[i];
        }

        long ans1 = bestFinish(landEnd, waterStartTime, waterDuration); // land -> water
        long ans2 = bestFinish(waterEnd, landStartTime, landDuration); // water -> land

        return (int) Math.min(ans1, ans2);
    }

    private long bestFinish(int[] firstRideFinish,
                            int[] nextStart,
                            int[] nextDuration) {

        int m = nextStart.length;

        int[][] rides = new int[m][2];
        for (int i = 0; i < m; i++) {
            rides[i][0] = nextStart[i];
            rides[i][1] = nextDuration[i];
        }

        Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));

        int[] starts = new int[m];
        long[] suffixMinStartPlusDur = new long[m];
        long[] prefixMinDur = new long[m];

        for (int i = 0; i < m; i++) {
            starts[i] = rides[i][0];
        }

        suffixMinStartPlusDur[m - 1] = (long) rides[m - 1][0] + rides[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            suffixMinStartPlusDur[i] = Math.min(
                suffixMinStartPlusDur[i + 1],
                (long) rides[i][0] + rides[i][1]
            );
        }

        prefixMinDur[0] = rides[0][1];
        for (int i = 1; i < m; i++) {
            prefixMinDur[i] = Math.min(prefixMinDur[i - 1], rides[i][1]);
        }

        long ans = Long.MAX_VALUE;

        for (int finishTime : firstRideFinish) {
            int idx = lowerBound(starts, finishTime);

            long best = Long.MAX_VALUE;

            // Next ride opens after (or exactly at) finishTime
            if (idx < m) {
                best = Math.min(best, suffixMinStartPlusDur[idx]);
            }

            // Next ride already open before finishTime
            if (idx > 0) {
                best = Math.min(best, (long) finishTime + prefixMinDur[idx - 1]);
            }

            ans = Math.min(ans, best);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}