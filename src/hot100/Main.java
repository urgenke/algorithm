package hot100;

import base.ListNode;

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
            max = Math.max(max, right - left+1);
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
                res.add(left+1);
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
        ListNode pre =null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


    public static void main(String[] args) throws InterruptedException {
//        new Main().moveZeroes(new int[]{0, 1, 0, 3, 12});
//        System.out.println(new Main().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
//        System.out.println(new Main().threeSum(new int[]{0, 0, 0, 0}));
//        System.out.println(new Main().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
//        System.out.println(new Main().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(new Main().lengthOfLongestSubstring("abcdabcddee"));
        System.out.println(new Main().findAnagrams("cbaebabacd", "abc"));
    }
}
