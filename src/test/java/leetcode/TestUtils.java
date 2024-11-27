package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TestUtils<T> {
  public static <T> void assertArrayListEquals(ArrayList<T> s1, ArrayList<T> s2) {
    assertEquals(s1.size(), s2.size());

    for (int j = 0; j < s1.size(); j += 1)
      assertEquals(s1.get(j), s2.get(j));
  }

  public static <T> void assertSetEquals(ArrayList<T> s1, ArrayList<T> s2) {
    assertEquals(s1.size(), s2.size());

    for (T e1 : s1) {
      boolean found = false;

      for (T e2 : s2) {
        if (e1 == e2)
          found = true;
      }

      assertTrue(found, "Element " + e1.toString() + " not found in list " + s2.toString());
    }
  }
}
