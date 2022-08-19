// Time Complexity : O(pw * pwCn)
// Space Complexity : O(pw)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class Main {
    private static int minDist;

    public static int optimalPlacement(int h, int w, int n) {
        // null case
        if (w == 0 || h == 0)
            return 0;
        // if number of spaces == number of building return 0
        if (w * h == n)
            return 0;
        minDist = Integer.MAX_VALUE;
        // find all the possible placement of the building and count maximum
        // distance and return minimum
        // intially create a matrix of w by h
        int[][] grid = new int[h][w];
        // fill grid with -1
        for (int[] g : grid) {
            Arrays.fill(g, -1);
        }
        // System.out.println(Arrays.deepToString(grid));
        // using dfs to place building
        dfs(grid, 0, 0, n, h, w);
        return minDist;
    }

    // after placing building find maximum distance
    private static void maxDistance(int[][] grid, int h, int w) {
        // add all the 0s inside the queue
        // queue for BFS traversal
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        // direction array
        int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        // bfs traversal and find minimam distance
        while (!q.isEmpty()) {
            // level
            int size = q.size();
            // level traversal
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                // go in all direction and get minimum distance
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    // check if it is already visited or not and bound check
                    if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc]) {
                        q.add(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                }
            }
            // increase the level by one
            dist++;
        }
        minDist = Math.min(dist - 1, minDist);
    }

    private static void dfs(int[][] grid, int r, int c, int n, int h, int w) {
        // base case
        if (n == 0) {
            maxDistance(grid, h, w);
            return;
        }
        // main logic
        // if c is at the end
        // reset c to 0 increase row
        // by one
        if (c == w) {
            c = 0;
            r++;
        }
        for (int i = r; i < h; i++) {
            for (int j = c; j < w; j++) {
                // action
                // put building on i , j+1
                grid[i][j] = 0;
                // recurse
                dfs(grid, i, j + 1, n - 1, h, w);
                // backtrack
                grid[i][j] = -1;
            }
            c = 0;
        }
    }

    public static void main(String[] args) {
        int h = 4;
        int w = 4;
        int n = 1;
        System.out.println(optimalPlacement(h, w, n));
    }
}