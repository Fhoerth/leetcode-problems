package leetcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import leetcode.Problem179.*;
import leetcode.modules.Digits;
import leetcode.modules.ListUtils;
import org.junit.jupiter.api.Test;

public class LargestNumber179Test {
  @Test
  void compare_filled() {
    IntegerComparator comparator = new IntegerComparator();
    Integer[][] nums = new Integer[][]{new Integer[]{20, 10}, new Integer[]{202, 200}, new Integer[]{2022, 2000},
        new Integer[]{1001, 1000}, new Integer[]{23000000, 22000000},};

    assertEquals(comparator.compareFilled2(10, 10), 1);
    assertEquals(comparator.compareFilled2(20, 20), 1);

    for (int j = 0; j < nums.length; j += 1) {
      Integer a = nums[j][0];
      Integer b = nums[j][1];

      assertEquals(comparator.compareFilled2(a, b), 1);
      assertEquals(comparator.compareFilled2(b, a), -1);
    }
  }

  @Test
  void compare_digits() {
    IntegerComparator comparator = new IntegerComparator();

    // spotless:off
    Integer[][] nums = new Integer[][]{
      new Integer[]{95, 55},
      new Integer[]{3, 2},
      new Integer[]{30, 20},
      new Integer[]{3, 30},
      new Integer[]{34, 3},
      new Integer[]{34, 30},
      new Integer[]{32, 311},
      new Integer[]{32, 300},
      new Integer[]{346, 32},
      new Integer[]{1, 0},
      new Integer[]{2, 1},
      new Integer[]{9, 10},
      new Integer[]{9, 20},
      new Integer[]{9, 80},
      new Integer[]{9, 90},
      new Integer[]{23, 2},
      new Integer[]{2, 21},
      new Integer[]{1113, 111311},
      new Integer[]{999999998, 999999997},
      new Integer[]{999999999, 999999997},
      new Integer[]{3432, 34323},
      new Integer[]{43243, 432},
    };
    // spotless:on

    for (int j = 0; j < nums.length; j += 1) {
      Integer a = nums[j][0];
      Integer b = nums[j][1];

      assertEquals(comparator.compare(a, b), -1);
      assertEquals(comparator.compare(b, a), 1);
    }
  }

  @Test
  void sort_test_1() {
    int[] nums = {3, 30, 34, 5, 9};

    IntegerComparator comparator = new IntegerComparator();

    Integer[] expectedNums = {9, 5, 34, 3, 30};
    Integer[] integerNums = Digits.toIntegerArray(nums);
    Arrays.sort(integerNums, comparator);

    TestUtils.assertArrayListEquals(ListUtils.toArrayList(integerNums), ListUtils.toArrayList(expectedNums));
  }

  @Test
  void sort_test_3() {
    int[] nums = {999999998, 999999997, 999999999};

    IntegerComparator comparator = new IntegerComparator();

    Integer[] expectedNums = {999999999, 999999998, 999999997};
    Integer[] integerNums = Digits.toIntegerArray(nums);
    Arrays.sort(integerNums, comparator);

    TestUtils.assertArrayListEquals(ListUtils.toArrayList(integerNums),
    ListUtils.toArrayList(expectedNums));
  }

  @Test
  void sort_test_2() {
    int[] nums = {10, 2};

    IntegerComparator comparator = new IntegerComparator();

    Integer[] expectedNums = {2, 10};
    Integer[] integerNums = Digits.toIntegerArray(nums);
    Arrays.sort(integerNums, comparator);

    TestUtils.assertArrayListEquals(ListUtils.toArrayList(integerNums), ListUtils.toArrayList(expectedNums));
  }

  @Test
  void test_1() {
    Solution solution = new Solution();
    assertEquals(solution.largestNumber(new int[]{10, 2}), "210");
  }

  @Test
  void test_2() {
    Solution solution = new Solution();
    assertEquals(solution.largestNumber(new int[]{3, 30, 34, 5, 9}), "9534330");
  }

  @Test
  void test_3() {
    Solution solution = new Solution();
    assertEquals(solution.largestNumber(new int[]{34323, 3432}), "343234323");
  }

  @Test
  void test_4() {
    Solution solution = new Solution();
    assertEquals(solution.largestNumber(new int[]{111311, 1113}), "1113111311");
  }
}
