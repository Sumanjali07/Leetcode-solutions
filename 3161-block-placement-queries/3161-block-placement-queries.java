import java.util.*;

class FenwickTree {
  private int[] vals;

  public FenwickTree(int n) {
    vals = new int[n + 1];
  }

  public void add(int i, int val) {
    while (i < vals.length) {
      vals[i] = Math.max(vals[i], val);
      i += lowbit(i);
    }
  }

  public int get(int i) {
    int res = 0;
    while (i > 0) {
      res = Math.max(res, vals[i]);
      i -= lowbit(i);
    }
    return res;
  }

  private static int lowbit(int i) {
    return i & -i;
  }
}

class Solution {
  public List<Boolean> getResults(int[][] queries) {
    final int n = Math.min(50000, queries.length * 3);
    List<Boolean> ans = new ArrayList<>();
    FenwickTree tree = new FenwickTree(n + 1);
    TreeSet<Integer> obstacles = new TreeSet<>();
    obstacles.add(0);
    obstacles.add(n); // sentinel values

    // Collect obstacles
    for (int[] query : queries) {
      if (query[0] == 1) {
        obstacles.add(query[1]);
      }
    }

    // Build initial segments
    Iterator<Integer> it = obstacles.iterator();
    int x1 = it.next();
    while (it.hasNext()) {
      int x2 = it.next();
      tree.add(x2, x2 - x1);
      x1 = x2;
    }

    // Process queries in reverse
    for (int i = queries.length - 1; i >= 0; --i) {
      int type = queries[i][0];
      int x = queries[i][1];
      if (type == 1) {
        Integer next = obstacles.higher(x);
        Integer prev = obstacles.lower(x);
        if (next != null && prev != null) {
          tree.add(next, next - prev);
        }
        obstacles.remove(x);
      } else {
        int sz = queries[i][2];
        int prev = obstacles.floor(x);
        ans.add(tree.get(prev) >= sz || x - prev >= sz);
      }
    }

    Collections.reverse(ans);
    return ans;
  }
}
