class Solution {

    class Node {
        long cnt, sum;

        Node(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private char[] digits;
    private Node[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n < 0) return 0;

        digits = Long.toString(n).toCharArray();
        int len = digits.length;

        memo = new Node[len + 1][3][11][11];

        return dfs(0, true, 0, 10, 10).sum;
    }

    private Node dfs(int pos, boolean tight, int state, int prev2, int prev1) {

        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight && memo[pos][state][prev2][prev1] != null) {
            return memo[pos][state][prev2][prev1];
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            int nextState;
            int nextPrev2;
            int nextPrev1;
            int add = 0;

            if (state == 0) { // no digit yet

                if (d == 0) {
                    nextState = 0;
                    nextPrev2 = 10;
                    nextPrev1 = 10;
                } else {
                    nextState = 1;
                    nextPrev2 = 10;
                    nextPrev1 = d;
                }

            } else if (state == 1) { // exactly one digit

                nextState = 2;
                nextPrev2 = prev1;
                nextPrev1 = d;

            } else { // at least two digits

                if ((prev1 > prev2 && prev1 > d) ||
                    (prev1 < prev2 && prev1 < d)) {
                    add = 1;
                }

                nextState = 2;
                nextPrev2 = prev1;
                nextPrev1 = d;
            }

            Node child = dfs(pos + 1, nextTight,
                             nextState, nextPrev2, nextPrev1);

            totalCnt += child.cnt;
            totalSum += child.sum + (long) add * child.cnt;
        }

        Node res = new Node(totalCnt, totalSum);

        if (!tight) {
            memo[pos][state][prev2][prev1] = res;
        }

        return res;
    }
}