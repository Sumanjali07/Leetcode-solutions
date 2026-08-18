class Solution {
    public String longestCommonPrefix(String[] str) {
        int n=str.length-1;
        StringBuilder ans=new StringBuilder();
        Arrays.sort(str);
        String s1=str[0];
        String s2=str[n];
        for(int i=0;i<Math.min(s1.length(),s2.length());i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                return ans.toString();
            }
            ans.append(s1.charAt(i));
        }
        return ans.toString();
    }
}