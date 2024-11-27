package leetcode;

import leetcode.modules.ListUtils;
import org.junit.jupiter.api.Test;

public class ListUtilsTest {
  @Test
  void to_array_list() {
    Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    TestUtils.assertArrayListEquals(ListUtils.toArrayList(nums), ListUtils.fromElems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
  }
}
