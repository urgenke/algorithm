import base.ListNode;
import base.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : mocun
 * @since : 2023/10/24
 */
public class Main {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode i = list1;
        ListNode j = list2;
        ListNode listNode = new ListNode(0, null);
        ListNode current = listNode;

        while (i != null && j != null) {
            if (i.val <= j.val) {
                current.next = i;
                i = i.next;
            } else {
                current.next = j;
                j = j.next;
            }
            current = current.next;
        }

        current.next = i == null ? j : i;

        return listNode.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
        }
        return list1;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode pre = null;
        ListNode next = null;
        ListNode current = head;

        while (current != null) {
            next = current.next;

            current.next = pre;

            pre = current;
            current = next;
        }
        return pre;
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode pointer = head;
        ListNode small = new ListNode(0);
        ListNode smallPointer = small;
        ListNode tall = new ListNode(0);
        ListNode tallPointer = tall;

        while (pointer != null) {
            if (pointer.val < x) {
                smallPointer.next = new ListNode(pointer.val);
                smallPointer = smallPointer.next;
            } else {
                tallPointer.next = new ListNode(pointer.val);
                tallPointer = tallPointer.next;
            }
            pointer = pointer.next;
        }

        if (tall.next != null) {
            smallPointer.next = tall.next;
        }

        return small.next;
    }


    public void deleteNode(ListNode node) {
        // 4 -> 5 -> 1 -> 9
        //       ⬇️
        // 4 -> 1 -> 9 -> null

        // 核心是将后面的node 节点的值前移，并删除最后一个节点
        if (node == null) {
            return;
        }

        ListNode pre = null;
        ListNode current = node;
        ListNode next = node.next;


        while (next != null) {
            current.val = next.val;

            pre = current;
            current = next;
            next = next.next;
        }

        pre.next = null;

    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public static final Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (map.containsKey(head)) {
            return map.get(head);
        }

        Node node = new Node(head.val);
        map.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return map.get(head);
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        if (s == null || s.length() == 0) {
            return false;
        }

        int length = s.length();
        if (length % 2 > 0) {
            return false;
        }

        boolean res = false;

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (pop.equals('(') && c == ')') {
                    res = true;
                } else if (pop.equals('{') && c == '}') {
                    res = true;
                } else if (pop.equals('[') && c == ']') {
                    res = true;
                } else {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return res;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 示例 2：
     * <p>
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * 示例 3：
     * <p>
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     * 示例 4：
     * <p>
     * 输入：s = "abc3[cd]xyz"
     * 输出："abccdcdcdxyz"
     */
    public String decodeString(String s) {
        int length = s.length();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c != ']') {
                stack.push(String.valueOf(c));
            } else {
                // ]
                stack.push(new StringBuilder(doPop(stack)).reverse().toString());
            }
        }


        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }

    public String doPop(Stack<String> stack) {
        StringBuilder repeatStr = new StringBuilder();

        while (true) {
            String pop = stack.pop();
            // 说明是之前已经组装好的字符串
            if (pop.length() > 1) {
                repeatStr.append(pop);
                continue;
            }

            char c = pop.toCharArray()[0];
            if (c == '[') {
                break;
            } else {
                repeatStr.append(c);
            }
        }

        repeatStr = repeatStr.reverse();

        StringBuilder repeatNumberStr = new StringBuilder();
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            char c = pop.toCharArray()[0];
            if (Character.isDigit(c)) {
                repeatNumberStr.append(c);
            } else {
                stack.push(pop);
                break;
            }
        }
        Integer repeatNumber = Integer.valueOf(repeatNumberStr.reverse().toString());

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < repeatNumber; i++) {
            res.append(repeatStr);
        }
        return res.toString();
    }

    Map<String, Integer> map2 = new HashMap<>();
    Map<String, Integer> map3 = new HashMap<>();

    public boolean isAnagram(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        if (length2 != length1) {
            return false;
        }
        for (int i = 0; i < length1; i++) {
            addToMap(s.charAt(i), map2);
            addToMap(t.charAt(i), map3);
        }

        return map2.keySet().stream().allMatch(key -> {
            Integer i1 = map2.get(key);
            Integer i2 = map3.getOrDefault(key, 0);
            return i1.equals(i2);
        });
    }

    public void addToMap(char c, Map<String, Integer> map) {
        String string = String.valueOf(c);
        Integer orDefault = map.getOrDefault(string, 0);
        orDefault = orDefault + 1;
        map.put(string, orDefault);
    }

    public int firstUniqChar(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String s1 = String.valueOf(s.charAt(i));
            Integer orDefault = map.getOrDefault(s1, 0);
            map.put(s1, orDefault + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            String s1 = String.valueOf(s.charAt(i));
            Integer orDefault = map.getOrDefault(s1, 0);
            if (orDefault == 1) {
                return i;
            }
        }
        return -1;

    }

    public boolean isIsomorphic(String s, String t) {

        HashMap<String, String> map = new HashMap<>();

        HashMap<String, String> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            String s1 = String.valueOf(s.charAt(i));
            if (map.containsKey(s1)) {
                String s2 = map.get(s1);
                if (String.valueOf(t.charAt(i)).equals(s2)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                if (map2.containsKey(String.valueOf(t.charAt(i))) && !map2.get(String.valueOf(t.charAt(i))).equals(s1)) {
                    return false;
                }

                map.put(s1, String.valueOf(t.charAt(i)));
                map2.put(String.valueOf(t.charAt(i)), s1);
            }
        }
        return true;

    }

    public int lengthOfLongestSubstring(String s) {
        // abcabcbb

        int i = 0;
        int j = 0;
        int max = 0;
        int next = 0;

        int tMax = 0;

        int length = s.length();
        if (length == 1) {
            return 1;
        }

        HashSet<String> set = new HashSet<>();
        boolean addMiddle = false;

        while (next < length) {

            next = i + max;
            if (next >= length) {
                break;
            }
            String s1 = String.valueOf(s.charAt(next));

            if (addMiddle) {
                // i -> next -1 全部加到 set 中，并判断是否存在重复

                addToSet(set, s.substring(i, next));
                // 有重复，i 推进到下一格
                if (set.size() != max) {
                    i = i + 1;
                    set.clear();
                    continue;
                } else {
                    if (!set.contains(s1)) {
                        set.add(s1);
                        tMax = tMax + 1;
                        max = Math.max(max, tMax);
                    } else {
                        i = i + 1;
                        addMiddle = true;
                        set.clear();
                    }
                }
            } else {
                if (!set.contains(s1)) {
                    set.add(s1);
                    tMax = tMax + 1;
                    max = Math.max(max, tMax);
                } else {
                    i = i + 1;
                    addMiddle = true;
                    set.clear();
                }
            }

        }


        return max;
    }

    public void addToSet(HashSet<String> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(String.valueOf(str.charAt(i)));
        }
    }


//    public static void main(String[] args) {
//        System.out.println(new Main().lengthOfLongestSubstring("qrsvbspk"));
//    }

    public int longestPalindrome(String s) {
        Map<Integer, Integer> count = s.chars().boxed()
                .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum));

        int sum = count.values().stream().mapToInt(i -> i - (i & 1)).sum();
        return sum < s.length() ? sum + 1 : sum;
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().longestPalindrome("aaaaabbbb"));
//    }


    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[]{i, j};
            }


        }
        return new int[]{};
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (!set.contains(p)) {
                set.add(p);
                p = p.next;
            } else {
                break;
            }
        }
        return p;
    }

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        List<String> collect = Arrays.stream(split)
                .filter(str -> str.trim().length() > 0)
                .collect(Collectors.toList());

        Collections.reverse(collect);
        return String.join(" ", collect);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<>();
        Set<NN> set = new HashSet<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            int target = -nums[k];
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum < target) {
                    i++;
                } else if (sum > target) {
                    j--;
                } else {
                    List<Integer> list = Arrays.asList(nums[k], nums[i], nums[j]);
                    NN nn = new NN(nums[k], nums[i], nums[j]);
                    if (!set.contains(nn)) {
                        res.add(list);
                        set.add(nn);
                    }
                    i++;
                }
            }
        }
        return res;
    }

    static class NN {
        int ii;
        int jj;
        int kk;

        NN(int ii, int jj, int kk) {
            this.ii = ii;
            this.jj = jj;
            this.kk = kk;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NN nn = (NN) o;
            boolean b1 = ii == nn.ii && jj == nn.jj && kk == nn.kk;
            boolean b6 = ii == nn.ii && jj == nn.kk && kk == nn.jj;
            boolean b2 = ii == nn.jj && jj == nn.ii && kk == nn.kk;
            boolean b3 = ii == nn.jj && jj == nn.kk && kk == nn.ii;
            boolean b4 = ii == nn.kk && jj == nn.jj && kk == nn.ii;
            boolean b5 = ii == nn.kk && jj == nn.ii && kk == nn.jj;
            return b1 || b2 || b3 || b4 || b5 || b6;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ii, jj, kk);
        }
    }


    public int[] maxSlidingWindow(int[] nums, int k) {

//        int i = 0;
//        int j = k - 1;
//        int max = Integer.MIN_VALUE;
//        int[] ints = new int[nums.length - 2 > 0 ? nums.length - 2 : k];
//        int intsIndex = 0;
//        while (j < nums.length) {
//            if (i < j) {
//                max = Math.max(max, nums[i]);
//                i++;
//            } else {
//                max = Math.max(max, nums[j]);
//                ints[intsIndex++] = max;
//                j++;
//                i = j;
//            }
//        }

        int i = 0;
        int max = Integer.MIN_VALUE;
        int[] ints = new int[nums.length - k + 1];
        int intsIndex = 0;
        while (i < nums.length - k + 1) {
            max = Math.max(max, nums[i]);
            if (i >= k - 1) {
                ints[intsIndex++] = max;
            }
            i++;
        }

        return ints;
    }

//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Main().maxSlidingWindow(new int[]{1, -1}, 1)));
//    }

    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int k = (i + j) / 2;

        while (i <= j) {
            if (nums[k] == target) {
                return k;
            } else if (nums[k] > target) {
                j = k - 1;
                k = (i + j) / 2;
            } else {
                i = k + 1;
                k = (i + j) / 2;
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        new Main().search(new int[]{1, 2, 3, 4, 5, 6}, 5);
//    }


    public int firstBadVersion(int n) {
        int i = 1;
        int j = n;


        while (i < j) {

            // 防溢出
            int k = i + (j - i) / 2;

            if (!isBadVersion(k)) {

                i = k + 1;

            } else {

                j = k;

            }

        }
        return i;
    }

    public boolean isBadVersion(int n) {
        return n >= 1702766719;
    }


    public int pivotIndex(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }

        int[] sums = new int[length];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sums[i] = sum + nums[i];
            sum = sum + nums[i];
        }

        int index = 0;
        while (index < length) {

            if (index == 0) {
                if (sums[length - 1] - sums[0] == 0) {
                    return index;
                }
            } else if (index == length - 1) {
                if (sums[length - 2] == 0) {
                    return index;
                }
            } else {
                if (sums[index - 1] == sums[length - 1] - sums[index]) {
                    return index;
                }
            }
            index++;
        }

        return -1;
    }

//    public static void main(String[] args) {
//        // 1,8,11,17,22,28,36
//        // 2,-2,-3
//        // 2,0,-3
//        // 1,8,11,17,22,28
//        System.out.println(new Main().pivotIndex(new int[]{1, 2, 3}));
//
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().firstBadVersion(2126753390));
//    }

    public String addStrings(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int index = 0;
        int limit = Math.min(l1, l2);
        int p1 = l1 - 1;
        int p2 = l2 - 1;
        boolean needAdd = false;

        StringBuilder res = new StringBuilder();
        while (index < limit) {
            Integer i1 = Integer.valueOf(String.valueOf(num1.charAt(p1)));
            Integer i2 = Integer.valueOf(String.valueOf(num2.charAt(p2)));
            int sum = i1 + i2;
            if (needAdd) {
                needAdd = false;
                sum = sum + 1;
            }

            if (sum < 10) {
                res.append(sum);
            } else {
                needAdd = true;
                res.append(sum % 10);
            }

            p1--;
            p2--;
            index++;
        }

        if (l1 == l2) {
            if (needAdd) {
                res.append("1");
            }
            return res.reverse().toString();
        }

        String str = l1 < l2 ? num2 : num1;
        int p3 = l1 < l2 ? p2 : p1;
        while (p3 >= 0) {
            Integer i = Integer.valueOf(String.valueOf(str.charAt(p3)));
            if (needAdd) {
                needAdd = false;
                i = i + 1;
            }

            if (i < 10) {
                res.append(i);
            } else {
                needAdd = true;
                res.append(i % 10);
            }

            p3--;
        }

        if (needAdd) {
            res.append("1");
        }
        return res.reverse().toString();
    }
//    public static void main(String[] args) {
//        System.out.println(new Main().addStrings("9", "99"));
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().rotateString("bbbacddceeb", "ceebbbbacdd"));
//    }


    //#  pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//            #  pushed = [1,2,4,5,3], popped = [4,3,5,1,2]
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0;
        int length = pushed.length;
        Stack<Integer> stack = new Stack<>();
        while (i < length && j < length) {
            int pushedValue = pushed[i];
            int poppedValue = popped[j];

            if (pushedValue != poppedValue) {
                if (!stack.isEmpty() && stack.peek() != poppedValue) {
                    stack.add(pushedValue);
                    i++;
                }

                if (!stack.isEmpty() && stack.peek() == poppedValue) {
                    stack.pop();
                    j++;
                }

                if (stack.isEmpty()) {
                    stack.add(pushedValue);
                    i++;
                }
            } else {
                i++;
                j++;
            }
        }

        if (i == j) {
            return true;
        }

        while (j < length) {
            if (!stack.isEmpty() && stack.pop() == popped[j]) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        int step = 1;
        int listIndex = 0;

        for (Character cr : s.toCharArray()) {
            list.get(listIndex).append(cr);
            listIndex = listIndex + step;

            if (listIndex >= numRows) {
                listIndex = listIndex - 2;
                step = -1;
            }

            if (listIndex < 0) {
                listIndex = listIndex + 2;
                step = 1;
            }
        }

        return list.stream().map(StringBuilder::toString).collect(Collectors.joining(""));
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().convert("PAYPALISHIRING", 3));
//    }


    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        List<Integer> res = new ArrayList<>();

        while (true) {

            // right
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            if (++top > bottom) {
                break;
            }

            // down
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }

            // left
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            if (--bottom < top) {
                break;
            }

            // up
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }

        }

        return res;
    }

//    public static void main(String[] args) {
////        System.out.println(new Main().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}}));
//        System.out.println(new Main().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
//
//    }


    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int number = 1;

        while (true) {

            // right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = number++;
            }
            if (++top > bottom) {
                break;
            }

            // down
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = number++;
            }
            if (--right < left) {
                break;
            }

            // left
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = number++;
            }
            if (--bottom < top) {
                break;
            }

            // up
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = number++;
            }
            if (++left > right) {
                break;
            }


        }

        return matrix;
    }


    public void rotate(int[][] matrix) {
        int length = matrix.length;
        if (length <= 1) {
            return;
        }

        int[][] temp = new int[length][length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, length);
        }

        // 第n行对应倒数第 n 列
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[j][length - 1 - i] = temp[i][j];
            }
        }

    }

//    public static void main(String[] args) {
//        new Main().rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
//    }


    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }

        char first = s.charAt(0);
        // 第一个不是数字或加减符号，直接返回 0
        if ((first >= 48 && first <= 57) || (first == '+' || first == '-')) {

        } else {
            return 0;
        }
        boolean hasSymbol = false;
        boolean positive = true;
        if (first == '+') {
            hasSymbol = true;
        }
        if (first == '-') {
            hasSymbol = true;
            positive = false;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = hasSymbol ? 1 : 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 48 && c <= 57) {
                sb.append(c);
            } else {
                break;
            }
        }
        if (sb.length() == 0) {
            return 0;
        }

        Long number;
        try {
            number = Long.valueOf(sb.toString());
        } catch (Throwable throwable) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (!positive) {
            number = -number;
        }
        if (positive && number >= (long) Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (!positive && number <= (long) Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return number.intValue();
    }


//    public static void main(String[] args) {
//        System.out.println(new Main().myAtoi("20000000000000000000"));
//        // 0
//        System.out.println(new Main().myAtoi("+-12"));
//        // 0
//        System.out.println(new Main().myAtoi("w"));
//        System.out.println(new Main().myAtoi("3.14159"));
//        System.out.println(new Main().myAtoi("4193 with words"));
//        // 0
//        System.out.println(new Main().myAtoi("words and 987"));
//        System.out.println(new Main().myAtoi("     -42"));
//        System.out.println(new Main().myAtoi("-91283472332"));
//    }


    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        int s = 0;
        int f = nums[slow];
        while (true) {
            s = nums[s];
            f = nums[f];
            if (s == f) {
                break;
            }
        }

        return s;
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().findDuplicate(new int[]{2, 5, 9, 6, 9, 3, 8, 9, 7, 1}));
//    }


    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }


    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> waitForAddNodes = new ArrayList<>();
        waitForAddNodes.add(root);

        while (!waitForAddNodes.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            List<Integer> tempRes = new ArrayList<>();
            waitForAddNodes.forEach(node -> {
                tempRes.add(node.val);
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            });
            res.add(tempRes);
            waitForAddNodes = temp;
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> waitForAddNodes = new ArrayList<>();
        waitForAddNodes.add(root);
        boolean fromLeftToTight = true;

        while (!waitForAddNodes.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            List<Integer> tempRes = new ArrayList<>();


            waitForAddNodes.forEach(node -> {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            });

            if (fromLeftToTight) {
                waitForAddNodes.forEach(node -> tempRes.add(node.val));
            } else {
                for (int i = waitForAddNodes.size() - 1; i >= 0; i--) {
                    tempRes.add(waitForAddNodes.get(i).val);
                }
            }

            fromLeftToTight = !fromLeftToTight;
            res.add(tempRes);
            waitForAddNodes = temp;
        }
        return res;
    }


    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        doKthSmallest(root, res, k);
        return res.get(res.size() - 1);
    }

    public void doKthSmallest(TreeNode root, ArrayList<Integer> res, int k) {
        if (res.size() == k) {
            return;
        }

        if (root == null) {
            return;
        }

        doKthSmallest(root.left, res, k);
        if (res.size() < k) {
            res.add(root.val);
        } else {
            return;
        }
        doKthSmallest(root.right, res, k);
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 利用二叉搜索树的性质
        // p、q 比 root 小，在 root 的左侧
        // p、q 比 root 大，在 root 的右侧

        if (root == null) {
            return root;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // 分布在两侧
        return root;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = maxDepth(root.left) + 1;
        int rightMax = maxDepth(root.right) + 1;
        return Math.max(leftMax, rightMax);
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().maxDepth(
//                new TreeNode(
//                        1,
//                        new TreeNode(2,
//                                new TreeNode(4),
//                                new TreeNode(5)
//                        ),
//                        new TreeNode(3,
//                                new TreeNode(6),
//                                new TreeNode(7, new TreeNode(8), null)
//                        )
//                )));
//    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }


    public static List<List<Integer>> res = new ArrayList<>();
    public static Deque<Integer> temp = new ArrayDeque<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        doPathSum(root, targetSum);
        return res;
    }

    public void doPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }

        temp.offerLast(root.val);
        targetSum = targetSum - root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            res.add(new ArrayList<>(temp));
            temp.pollLast();
            return;
        }
        doPathSum(root.left, targetSum);
        doPathSum(root.right, targetSum);
        temp.pollLast();
    }

//    public static void main(String[] args) {
////        System.out.println(new Main()
////                .pathSum(
////                        new TreeNode(5,
////                                new TreeNode(4),
////                                new TreeNode(8,
////                                        new TreeNode(11),
////                                        new TreeNode(13))),
////                        26));
//
//        System.out.println(new Main()
//                .pathSum(
//                        new TreeNode(1,
//                                new TreeNode(2),
//                                new TreeNode(3)),
//                        26));
//    }


//    public int findMin2(int[] nums) {
//
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {7, 8, 9}}));
//    }

//    public static void main(String[] args) {
//        System.out.println(new Main().validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().reverseWords("    hello world     "));
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().isIsomorphic("badc", "baba"));
//    }


//    public static void main(String[] args) {
//        System.out.println(new Main().decodeString("3[a2[bcd]]"));
//        System.out.println(new Main().decodeString("3[a]2[bc]"));
//        System.out.println(new Main().decodeString("abc3[cd]xyz"));
//    }


//    public static void main(String[] args) {
////        System.out.println(new Main().isValid("({{{{}}}))"));
////        System.out.println(new Main().isValid("]"));
////        System.out.println(new Main().isValid("){"));
////        System.out.println(new Main().isValid("[[[]"));
//
//        System.out.println(Character.isDigit('2'));
//        System.out.println(Character.isDigit('a'));
//    }


//    public static void main(String[] args) {
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        l1.next = l2;
//        l2.next = l3;
//
//        ListNode listNode = new Main().reverseList(l1);
//        System.out.println(listNode.val);
//
//    }


    // 全排列  [1,2,3]
    public List<List<Integer>> permute2(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        doPermute(nums, res, nums.length);
        return res;
    }

    public List<List<Integer>> doPermute(int[] nums, ArrayList<List<Integer>> res, int length) {
        ArrayList<List<Integer>> temp = new ArrayList<>();
        if (length == 1) {
            ArrayList<Integer> t1 = new ArrayList<>();
            t1.add(nums[0]);
            res.add(t1);
            return temp;
        }

        if (length == 2) {
            ArrayList<Integer> t1 = new ArrayList<>();
            t1.add(nums[0]);
            t1.add(nums[1]);
            ArrayList<Integer> t2 = new ArrayList<>();
            t2.add(nums[1]);
            t2.add(nums[0]);
            res.add(t1);
            res.add(t2);
            return temp;
        }

        if (nums.length == 2) {
            ArrayList<Integer> t1 = new ArrayList<>();
            t1.add(nums[0]);
            t1.add(nums[1]);
            ArrayList<Integer> t2 = new ArrayList<>();
            t2.add(nums[1]);
            t2.add(nums[0]);
            temp.add(t1);
            temp.add(t2);
            return temp;
        }

        for (int i = 0; i < nums.length; i++) {
            int firstNum = nums[i];
            int[] array = createArray(nums, i);
            List<List<Integer>> lists = doPermute(array, res, length);
            for (List<Integer> list : lists) {
                list.add(0, firstNum);
                if (length == list.size()) {
                    res.add(list);
                } else {
                    temp.add(list);
                }
            }

        }

        return temp;
    }

    public int[] createArray(int[] nums, int excludeIndex) {
        int[] res = new int[nums.length - 1];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (i != excludeIndex) {
                res[j] = nums[i];
                j++;
            }
        }
        return res;
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().permute2(new int[]{1}));
//    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

//    public static void main(String[] args) {
//        new Main().permute(new int[]{1, 2, 3});
//    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        doCombinationSum(candidates, target, new ArrayList<>(), res);
        Map<Integer, List<List<Integer>>> map = res.stream()
                .collect(Collectors.groupingBy(List::size));
        map.forEach((k, v) -> {
            if (v.size() > 1) {
                v.forEach(Collections::sort);
            }
        });

        // remove duplicate
        List<List<Integer>> res2 = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v.size() == 1) {
                res2.add(v.get(0));
            } else {
                Set<String> sets = new HashSet<>();
                v.forEach(list -> {
                    sets.add(list.stream().map(String::valueOf).collect(Collectors.joining("-")));
                });
                sets.forEach(s -> res2.add(Arrays.stream(s.split("-")).map(Integer::valueOf).collect(Collectors.toList())));
            }
        });
        return res2;
    }

    public void doCombinationSum(int[] candidates, int target, List<Integer> temp, List<List<Integer>> res) {

        for (int candidate : candidates) {
            if (candidate == target) {
                ArrayList<Integer> r = new ArrayList<>(temp);
                r.add(candidate);
                res.add(r);
            } else {
                ArrayList<Integer> temp2 = new ArrayList<>(temp);
                int newTarget = target - candidate;
                if (newTarget > 0) {
                    temp2.add(candidate);
                    doCombinationSum(candidates, target - candidate, temp2, res);
                } else {
                    // newTarget < 0  不用往下走了
                }
            }

        }

    }

//    public static void main(String[] args) {
//        System.out.println(new Main().combinationSum(new int[]{8, 7, 4, 3}, 11));
//    }


    public List<List<Integer>> combinationSumDfs(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        csdfs(candidates, target, 0, path, res);
        return res;
    }

    public void csdfs(int[] candidates, int target, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            int candidate = candidates[i];
            int newTarget = target - candidate;
            path.offerLast(candidate);
            csdfs(candidates, newTarget, i, path, res);
            path.pollLast();
        }
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().combinationSumDfs(new int[]{3, 2, 6, 7}, 7));
//    }


    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        if (min <= 0 && max >= 0) {
            return max - min;
        }
        if (max <= 0 && min <= 0) {
            return max - min;
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int sum = 0;
        int res = Integer.MIN_VALUE;


        for (int num : nums) {
            sum += num;

            res = Math.max(sum, res);
            if (sum <= 0) {
                sum = 0;
            }
        }

        return res;
    }

//    public static void main(String[] args) {
//        System.out.println(new Main().maxSubArray(new int[]{-2, 1}));
//        System.out.println(new Main().maxSubArray(new int[]{-2, -1}));
//        System.out.println(new Main().maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[candidates.length];

        Arrays.sort(candidates);
        combinationSum2Dfs(candidates, used, target, res, path);
        return res;
    }

    private void combinationSum2Dfs(int[] candidates, boolean[] used, int target, List<List<Integer>> res, Deque<Integer> path) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }


        for (int i = 0; i < candidates.length; i++) {
            if (used[i]) {
                continue;
            }
            int candidate = candidates[i];
            int newTarget = target - candidate;
            path.offerLast(candidate);
            used[i] = true;

            combinationSum2Dfs(candidates, used, newTarget, res, path);

            path.pollLast();
            used[i] = false;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Main().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

}
