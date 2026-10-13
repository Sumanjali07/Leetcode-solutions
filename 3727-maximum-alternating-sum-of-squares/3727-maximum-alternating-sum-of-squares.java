class Solution {
    public long maxAlternatingSum(int[] nums) {
        int length = nums.length;

        // Square each element in the array
        for (int i = 0; i < length; ++i) {
            nums[i] *= nums[i];
        }

        // Sort the array in ascending order,
        // so smaller squared values come first
        Arrays.sort(nums);

        long answer = 0;
        int half = length / 2;

        // Subtract the smaller half of the squared values
        for (int i = 0; i < half; ++i) {
            answer -= nums[i];
        }

        // Add the larger half of the squared values
        for (int i = half; i < length; ++i) {
            answer += nums[i];
        }

        return answer;
    }
}
