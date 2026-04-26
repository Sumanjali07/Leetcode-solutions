class Solution {
    private static final int[][] DIRS = {{0,1},{1,0},{0,-1},{-1,0}};
    
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] seen = new boolean[m][n];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seen[i][j]) continue;
                if (dfs(grid, i, j, -1, -1, grid[i][j], seen)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, int i, int j, int prevI, int prevJ, char c, boolean[][] seen) {
        seen[i][j] = true;
        
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) continue;
            if (x == prevI && y == prevJ) continue;
            if (grid[x][y] != c) continue;
            
            if (seen[x][y]) return true;
            if (dfs(grid, x, y, i, j, c, seen)) return true;
        }
        
        return false;
    }
}
