class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        while (top < bottom && left < right) {
            List<Integer> layerElements = new ArrayList<>();

            // Extract top row
            for (int c = left; c < right; c++) {
                layerElements.add(grid[top][c]);
            }
            // Extract right column
            for (int r = top; r < bottom; r++) {
                layerElements.add(grid[r][right]);
            }
            // Extract bottom row
            for (int c = right; c > left; c--) {
                layerElements.add(grid[bottom][c]);
            }
            // Extract left column
            for (int r = bottom; r > top; r--) {
                layerElements.add(grid[r][left]);
            }

            int numElements = layerElements.size();
            int effectiveK = k % numElements;

            List<Integer> rotatedLayer = new ArrayList<>();
            for(int i = effectiveK; i < numElements; i++){
                rotatedLayer.add(layerElements.get(i));
            }
            for(int i = 0; i < effectiveK; i++){
                rotatedLayer.add(layerElements.get(i));
            }

            int idx = 0;
            // Place back top row
            for (int c = left; c < right; c++) {
                grid[top][c] = rotatedLayer.get(idx);
                idx++;
            }
            // Place back right column
            for (int r = top; r < bottom; r++) {
                grid[r][right] = rotatedLayer.get(idx);
                idx++;
            }
            // Place back bottom row
            for (int c = right; c > left; c--) {
                grid[bottom][c] = rotatedLayer.get(idx);
                idx++;
            }
            // Place back left column
            for (int r = bottom; r > top; r--) {
                grid[r][left] = rotatedLayer.get(idx);
                idx++;
            }

            top++;
            bottom--;
            left++;
            right--;
        }

        return grid;
    }
}