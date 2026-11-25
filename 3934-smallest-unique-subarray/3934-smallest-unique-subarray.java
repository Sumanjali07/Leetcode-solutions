class Solution {

    int[] nums;
    long base = 19;
    long modulo = 1000000007L;
    long[] powers;
    HashMap<Long, Integer> hashValues = new HashMap<>();

    public int smallestUniqueSubarray(int[] nums) {
        this.nums = nums;

        powers = new long[nums.length + 1];

        int minPossibleLen = nums.length; // Base case.

        int minLen = 1, maxLen = nums.length; // For binary search usage.

        while (minLen <= maxLen) {
            int midLen = (minLen + maxLen) / 2;

            if (_checkUniqueness(midLen)) {
                if (midLen < minPossibleLen) {
                    minPossibleLen = midLen;
                }
                maxLen = midLen - 1;
            } else {
                minLen = midLen + 1;
            }
        }

        return minPossibleLen;
    }

    private boolean _checkUniqueness(int subarrayLen) {

        // Only need to reset leftmost power to 1 before usage.
        powers[0] = 1;

        // Because powers are calculated by bottom-up.
        for (int idx = 1; idx <= nums.length; idx++) {
            powers[idx] = (powers[idx - 1] * base) % modulo;
        }

        long currentHash = 0;

        for (int idx = 0; idx < subarrayLen; idx++) {
            currentHash *= base;
            currentHash += nums[idx];
            currentHash %= modulo;
        }

        hashValues.clear(); // Clear before usage.

        hashValues.put(currentHash, 1);

        for (int idx = 1; idx <= nums.length - subarrayLen; idx++) {

            // Window shifts: deduct leftmost num's share from hash value.
            currentHash -= powers[subarrayLen - 1] * nums[idx - 1];

            // Integrate newly added num's share into hash value.
            currentHash *= base;
            currentHash += nums[idx + subarrayLen - 1];

            currentHash %= modulo;

            if (currentHash < 0) {
                currentHash += modulo;
            }

            if (!hashValues.containsKey(currentHash)) {
                hashValues.put(currentHash, 0);
            }

            hashValues.put(currentHash, hashValues.get(currentHash) + 1);
        }

        return hashValues.containsValue(1);
    }
}