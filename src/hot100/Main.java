package hot100;

import base.ListNode;
import base.TreeNode;

import java.util.*;

/**
 * @author : mocun
 * @since : 2023/12/11
 */
public class Main {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return new int[]{};
    }


    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }

        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if (k < nums.length - 1 && nums[k] == nums[k + 1]) {
                    k--;
                    continue;
                }

                if (nums[i] + nums[j] + nums[j + 1] > 0) {
                    break;
                }

                if (nums[i] + nums[k] + nums[k - 1] < 0) {
                    break;
                }

                int v = nums[i] + nums[j] + nums[k];
                if (v == 0) {
                    res.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                } else if (v < 0) {
                    j++;
                } else {
                    k--;
                }
            }

        }

        return res;
    }

    public int trap(int[] height) {
        int maxHeight = height[0];
        int curMax = height[0];
        int s1 = 0;
        for (int i = 0; i < height.length; i++) {
            maxHeight = Math.max(maxHeight, height[i]);
            curMax = Math.max(curMax, height[i]);
            s1 = s1 + curMax;
        }

        int rectangle = height.length * maxHeight;
        int s2 = 0;
        curMax = 0;
        int wallArea = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            curMax = Math.max(curMax, height[i]);
            s2 = s2 + curMax;
            wallArea = wallArea + height[i];
        }

        return s1 + s2 - wallArea - rectangle;
    }

    public int trap2(int[] height) {
        int length = height.length;
        int[] before = new int[length];
        before[0] = height[0];
        int[] after = new int[length];
        after[length - 1] = height[length - 1];

        for (int i = 1; i < length; i++) {
            before[i] = Math.max(height[i], before[i - 1]);
        }

        for (int i = length - 2; i >= 0; i--) {
            after[i] = Math.max(height[i], after[i + 1]);
        }

        int res = 0;
        for (int i = 0; i < length; i++) {
            int h = Math.min(before[i], after[i]);
            int t = h - height[i];
            if (t > 0) {
                res += t;
            }
        }
        return res;
    }


    public int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < charArray.length; right++) {
            char c = charArray[right];
            if (map.containsKey(c)) {
                Integer count = map.get(c);
                map.put(c, count + 1);
                while (map.get(c) > 1) {
                    Integer count2 = map.get(charArray[left]);
                    map.put(charArray[left], count2 - 1);
                    left++;
                }

            } else {
                map.put(c, 1);
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if (sLen < pLen) {
            return Collections.emptyList();
        }

        ArrayList<Integer> res = new ArrayList<>();
        char[] pC = new char[26];
        char[] sC = new char[26];
        for (int i = 0; i < pLen; i++) {
            pC[p.charAt(i) - 'a']++;
            sC[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(pC, sC)) {
            res.add(0);
        }

        int left = 0;
        int right = pLen;
        while (right < sLen) {
            sC[s.charAt(left) - 'a']--;
            sC[s.charAt(right) - 'a']++;

            if (Arrays.equals(pC, sC)) {
                res.add(left + 1);
            }

            left++;
            right++;
        }
        return res;
    }


    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int ans = 0;
        int[] ints = new int[len];
        ints[0] = nums[0];
        for (int i = 1; i < len; i++) {
            ints[i] = ints[i - 1] + nums[i];
        }

        // 1,2,3 k=3
        // 1,3,6
        for (int i = 0; i < len; i++) {
            if (ints[i] == k) {
                ans++;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (ints[i] - ints[j] == k) {
                    ans++;
                }
            }
        }

        return ans;
    }


    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    list.add(1);
                    continue;
                }
                if (j == i) {
                    list.add(1);
                    continue;
                }
                List<Integer> integers = res.get(i - 1);
                list.add(integers.get(j - 1) + integers.get(j));
            }
            res.add(list);
        }
        return res;
    }

    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }


    public int numSquares(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        return numSquaresDfs(n, map);
    }

    public int numSquaresDfs(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 1) {
            map.put(1, 1);
            return 1;
        }
        int a = (int) Math.sqrt(n);
        int min = Integer.MAX_VALUE;
        for (int i = a; i >= 1; i--) {
            if (a * a == n) {
                min = 1;
                break;
            }
            min = Math.min(numSquaresDfs(n - (i * i), map) + 1, min);
        }
        map.put(n, min);
        return min;
    }

    // 递推
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - (j * j)] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 如果有交点，headA 走到头后从 headB 开始走，headB 同理
        // 最后指针会相遇在交点

        // 如果没有交点
        // 1->2->3
        // 4->5
        // 最后都为 null

        ListNode curA = headA;
        ListNode curB = headB;

        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }


    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        cur = head;
        // 121
        // 1221
        LinkedList<Integer> stack = new LinkedList<>();
        if (count % 2 == 0) {
            while (cur != null && stack.size() < count / 2) {
                stack.addLast(cur.val);
                cur = cur.next;
            }
        } else {
            while (cur != null && stack.size() < (count + 1) / 2) {
                stack.addLast(cur.val);
                cur = cur.next;
            }
            stack.removeLast();
        }

        while (cur != null) {
            if (stack.size() > 0 && stack.pollLast() == cur.val) {
                cur = cur.next;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return false;
        }
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;

        StringBuilder slowStr = new StringBuilder();
        StringBuilder fastStr = new StringBuilder();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;

        while (slow != null) {
            slowStr.append(slow.val);
            fastStr.append(fast.val);
            slow = slow.next;
            fast = fast.next;
        }
        return fastStr.toString().contentEquals(slowStr.reverse());
    }

    public boolean isPalindrome3(ListNode head) {
        if (head == null) {
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        // 1221
        // 121
        for (int i = 0, j = list.size() - 1; i < list.size() / 2; i++, j--) {
            if (list.get(i).equals(list.get(j))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length;
        int j = matrix[0].length;

        for (int m = 0, n = j - 1; m < i && n >= 0; ) {
            if (matrix[m][n] == target) {
                return true;
            } else if (matrix[m][n] < target) {
                m++;
            } else {
                n--;
            }
        }
        return false;
    }


    public void setZeroes(int[][] matrix) {
        // 行
        Set<Integer> rows = new HashSet<>();
        // 列
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rows.contains(i)) {
                    matrix[i][j] = 0;
                }
                if (cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    public void setZeroes2(int[][] matrix) {
        // 空间复杂度为 o1
        int iL = matrix.length;
        int jL = matrix[0].length;

        boolean row = false;
        boolean col = false;

        for (int i = 0; i < iL; i++) {
            for (int j = 0; j < jL; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        row = true;
                    }
                    if (j == 0) {
                        col = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < iL; i++) {
            for (int j = 1; j < jL; j++) {
                if (matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
                if (matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row) {
            for (int j = 0; j < jL; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col) {
            for (int i = 0; i < iL; i++) {
                matrix[i][0] = 0;
            }
        }

    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        int len = nums.length;
        int resLen = len - k;
        int[] res = new int[resLen + 1];
        // 最大值的次数
        int max = Integer.MIN_VALUE;
        int maxCount = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxCount = 1;
            } else if (nums[i] == max) {
                maxCount++;
            }
        }
        res[0] = max;


        for (int i = 1; i + k - 1 <= len - 1; i++) {
            int preKey = nums[i - 1];
            int afterKey = nums[i + k - 1];
            if (preKey == afterKey) {
                res[i] = max;
                continue;
            }

            if (afterKey > max) {
                max = afterKey;
                maxCount = 1;
            } else if (afterKey == max) {
                maxCount++;
            } else {
                if (preKey >= max) {
                    maxCount--;
                    if (maxCount == 0) {
                        max = Integer.MIN_VALUE;
                        for (int j = i; j <= i + k - 1; j++) {
                            if (nums[j] > max) {
                                max = nums[j];
                                maxCount = 1;
                            } else if (nums[j] == max) {
                                maxCount++;
                            }
                        }
                    }
                }
            }

            res[i] = max;
        }

        return res;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        midView(root, list);
        return list;
    }

    public void midView(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        midView(root.left, list);
        list.add(root.val);
        midView(root.right, list);
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        invertTree(left);
        invertTree(right);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }


    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxDepth2(root);
        return max;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }


    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < intervals.length; i++) {
            if (i + 1 < intervals.length) {
                // 当前和后面的相交
                // 当前数组和后面数组合并
                // 当前数组置为空
                if (intervals[i][0] <= intervals[i + 1][0] && intervals[i][1] >= intervals[i + 1][0]) {
                    intervals[i + 1][0] = intervals[i][0];
                    intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
                    intervals[i] = new int[0];
                }
            }
        }

        return Arrays.stream(intervals).filter(a -> a.length != 0).toArray(int[][]::new);
    }

    public int maximalSquare(char[][] matrix) {
        int i = matrix.length;
        int j = matrix[0].length;
        // 以 x,y 作为正方形右下角的最大变长
        int[][] dp = new int[i][j];
        int maxSide = 0;
        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                if (matrix[m][n] == '1') {
                    int top = 0;
                    if (m - 1 >= 0) {
                        top = dp[m - 1][n];
                    }
                    int left = 0;
                    if (n - 1 >= 0) {
                        left = dp[m][n - 1];
                    }
                    // 对角
                    int diagonal = 0;
                    if (m - 1 >= 0 && n - 1 >= 0) {
                        diagonal = dp[m - 1][n - 1];
                    }

                    dp[m][n] = Math.min(Math.min(top, left), diagonal) + 1;
                    maxSide = Math.max(maxSide, dp[m][n]);
                } else {
                    dp[m][n] = 0;
                }
            }
        }
        return maxSide * maxSide;
    }


    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return;
        }
        if (k % len == 0) {
            return;
        }
        k = k % len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.pollFirst();
                TreeNode left = treeNode.left;
                if (left != null) {
                    queue.addLast(left);
                }
                TreeNode right = treeNode.right;
                if (right != null) {
                    queue.addLast(right);
                }
                list.add(treeNode.val);
            }
            res.add(list);
        }

        return res;
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int maxProduct(int[] nums) {
        int len = nums.length;
        // 以 i 结尾的最大或最小值（负数），0代表负数，1代表正数
        int[][] dp = new int[len][2];
        int num = nums[0];
        dp[0][1] = Math.max(num, 0);
        dp[0][0] = Math.min(num, 0);
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            int v = nums[i];
            if (v == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (v > 0) {
                dp[i][1] = Math.max(dp[i - 1][1] * v, v);
                dp[i][0] = dp[i - 1][0] * v;
            } else {
                dp[i][1] = dp[i - 1][0] * v;
                dp[i][0] = Math.min(dp[i - 1][1] * v, v);
            }
            max = Math.max(max, dp[i][1]);
        }

        return max;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 10000);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return amount == 0 ? 0 : dp[amount] == 10000 ? -1 : dp[amount];
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static void main(String[] args) throws InterruptedException {
//        new Main().moveZeroes(new int[]{0, 1, 0, 3, 12});
//        System.out.println(new Main().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
//        System.out.println(new Main().threeSum(new int[]{0, 0, 0, 0}));
//        System.out.println(new Main().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
//        System.out.println(new Main().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(new Main().lengthOfLongestSubstring("abcdabcddee"));
//        System.out.println(new Main().findAnagrams("cbaebabacd", "abc"));
//        System.out.println(new Main().subarraySum(new int[]{1,-1,0}, 0));
//        System.out.println(new Main().generate(5));
//        System.out.println(new Main().rob(new int[]{2, 1, 1, 2}));
//        System.out.println(new Main().numSquares2(12));
//        System.out.println(Arrays.toString(new Main().maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3)));
//        System.out.println(Arrays.toString(new Main().maxSlidingWindow(new int[]{1, -1}, 1)));
//        System.out.println(new Main().numSquares2(12));
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(1);
////        ListNode l4 = new ListNode(1);
//        l1.next = l2;
//        l2.next = l3;
////        l3.next = l4;
//
//        System.out.println(new Main().isPalindrome3(l1));

//        System.out.println(new Main().searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
//        new Main().setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
//        new Main().merge(new int[][]{{2, 3}, {1, 6}, {8, 10}, {15, 18}});
//        new Main().rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
//        new Main().merge(new int[][]{{2, 3}, {1, 6}, {8, 10}, {15, 18}});
//        System.out.println(new Main().maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
//        new Main().rotate(new int[]{-1, -100, 3, 99}, 2);
        System.out.println(new Main().maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(new Main().maxProduct(new int[]{2, 3, -2, 4, -100}));
        System.out.println(new Main().maxProduct(new int[]{0, 2}));
//        System.out.println(new Main().coinChange(new int[]{2}, 3));
//        System.out.println(new Main().coinChange(new int[]{2}, 4));
//        System.out.println(new Main().coinChange(new int[]{1,2,5}, 11));
//        System.out.println(new Main().coinChange(new int[]{2}, 1));
        System.out.println(new Main().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
