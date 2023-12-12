package hot100;

import java.util.HashMap;

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

    }


    public static void main(String[] args) {
        new Main().moveZeroes(new int[]{0, 1, 0, 3, 12});
    }
}
