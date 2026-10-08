class Solution {
    public int minimumOperations(int[] nums) {
        int n=nums.length;
        int cnt=0;
        while(true){
            //smallest  non zero number in nums
            int x=Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                if(nums[i]>0){
                    x=Math.min(x,nums[i]);
                }
            }
            //if all zeros break
            if(x==Integer.MAX_VALUE){
                break;
            }
            //subtract x from every num in nums
            //cnt++
            for(int i=0;i<n;i++){
                if(nums[i]>0){
                   nums[i]-=x;
                }
            }
            cnt++;
        }
        return cnt;
    }
}