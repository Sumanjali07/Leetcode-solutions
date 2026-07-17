class Solution {
    public void sortColors(int[] nums) {
        int n=nums.length;
        int i=0,j=0,k=0;
        for(int num:nums){
            if(num==0) i++;
            else if(num==1) j++;
            else k++;
        }
        int index=0;
        while(i-->0){ nums[index++]=0;}
        while(j-->0){ nums[index++]=1;}
        while(k-->0){ nums[index++]=2;}
    }
}