class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans=new ArrayList<>();
        int n=nums.length;
        for(int num:nums){
            if(ans.contains(num)){
                continue;
            }
            int cnt=0;
            for(int i=0;i<n;i++){
                if(nums[i]==num){
                    cnt++;
                }
            }
            if(cnt>(n/3)){
                ans.add(num);
            }
            if(ans.size()==2){
                break;
            }
        }
        return ans;
    }
}