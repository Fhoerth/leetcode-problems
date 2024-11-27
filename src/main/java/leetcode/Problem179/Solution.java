// https://leetcode.com/problems/largest-number/description/?envType=problem-list-v2&envId=greedy

package leetcode.Problem179;

import java.util.Arrays;
import leetcode.modules.Digits;

public class Solution {
  public String largestNumber(int[] nums) {
    Integer[] integerNums = Digits.toIntegerArray(nums);
    Arrays.sort(integerNums, new IntegerComparator());
    return Digits.toString(integerNums);
  }
}
