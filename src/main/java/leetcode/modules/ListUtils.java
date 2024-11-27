package leetcode.modules;

import java.util.ArrayList;
import java.util.Arrays;

public class ListUtils<T> {
  public static <T> ArrayList<T> toArrayList(T[] elems) {
    ArrayList<T> result = new ArrayList<>(elems.length);

    for (int j = 0; j < elems.length; j += 1)
      result.add(j, elems[j]);

    return result;
  }

  @SafeVarargs
  public static <T> ArrayList<T> fromElems(T... elems) {
    return new ArrayList<>(Arrays.asList(elems));
  }
}
