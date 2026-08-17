class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder res= new StringBuilder();
        int l=0;
        for(char ch : s.toCharArray()){
            if(ch=='('){
                if(l>0) res.append(ch);
                l++;
            }
            else if(ch==')'){
                l--;
                if(l>0) res.append(ch);
            }
        }
        return res.toString();
    }
}