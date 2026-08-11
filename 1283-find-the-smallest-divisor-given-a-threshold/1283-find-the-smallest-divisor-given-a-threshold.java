class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = 0;

        for (int num : nums) {
            high = Math.max(high, num);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int sum = 0;

            for (int num : nums) {
                sum += (num + mid - 1) / mid; // ceiling division
            }
            if (sum <= threshold) {
                high = mid - 1;   // divisor works → try smaller
            } else {
                low = mid + 1;    // divisor too small → increase it
            }
        }
        return low;
    }
}