// package leetcode.Problem179;

// import java.util.Comparator;
// import leetcode.modules.Digits;

// public class IntegerComparator implements Comparator<Integer> {
//   public int compare(Integer a, Integer b) {
//     if (Integer.compare(a, b) == 0) {
//       return 0;
//     }

//     int firstDigit1 = Digits.getFirstDigit(a);
//     int firstDigit2 = Digits.getFirstDigit(b);

//     if (firstDigit1 == firstDigit2) {
//       int digits1 = Digits.numberOfDigits(a);
//       int digits2 = Digits.numberOfDigits(b);

//       int zeroFilledDigit1 = Digits.fillWithZeros(a, b);
//       int zeroFilledDigit2 = Digits.fillWithZeros(b, a);
//       // || Math.abs(zeroFilledDigit1 - zeroFilledDigit2) < 10
//       if (digits1 == 1 || digits2 == 1)
//         return Integer.compare(Digits.sum(b, a), Digits.sum(a, b));
//       else
//         return compare(Digits.removeFirstDigit(a), Digits.removeFirstDigit(b));

//     } else {

//       return Integer.compare(firstDigit2, firstDigit1);
//     }
//   }
// }
package leetcode.Problem179;

import java.math.BigInteger;
import java.util.Comparator;
import leetcode.modules.Digits;

public class IntegerComparator implements Comparator<Integer> {
  public int compareFilled2(Integer a, Integer b) {
    if (Integer.compare(a, b) == 0)
      return 1;

    int firstDigit1 = Digits.getFirstDigit(a);
    int firstDigit2 = Digits.getFirstDigit(b);

    if (firstDigit1 == firstDigit2)
      return compareFilled2(Digits.removeFirstDigit(a), Digits.removeFirstDigit(b));
    else
      return Integer.compare(firstDigit1, firstDigit2);
  }

  private int compareFilled(Integer a, Integer b) {
    if (Integer.compare(a, b) == 0)
      return -1;

    int firstDigit1 = Digits.getFirstDigit(a);
    int firstDigit2 = Digits.getFirstDigit(b);

    if (firstDigit1 == firstDigit2)
      return compareFilled(Digits.removeFirstDigit(a), Digits.removeFirstDigit(b));
    else
      return Integer.compare(firstDigit2, firstDigit1);
  }

  public int compare(Integer a, Integer b) {
    int digitsA = Digits.numberOfDigits(a);
    int digitsB = Digits.numberOfDigits(b);
    int filledA = Digits.fill(a, b);
    int filledB = Digits.fill(b, a);

    if (filledA == filledB) {
      return digitsA <= digitsB ? -1 : 1;
    }

    return compareFilled2(filledB, filledA);
  }

  public int compare3(Integer a, Integer b) {
    int digits1 = Digits.numberOfDigits(a);
    int digits2 = Digits.numberOfDigits(b);
    int zeroFilledDigit1 = Digits.fillWithZeros(a, b);
    int zeroFilledDigit2 = Digits.fillWithZeros(b, a);
    // int nineFilledDigit1 = Digits.fillWithNines(a, b);
    // int nineFilledDigit2 = Digits.fillWithNines(b, a);
    BigInteger superSum1 = Digits.superSum(a, b);
    BigInteger superSum2 = Digits.superSum(b, a);

    return superSum2.compareTo(superSum1);
    // return compareFilled2(zeroFilledDigit1, zeroFilledDigit2) * -1;
    // if (digits1 == digits2)
    // if (digits1 > digits2)
    // return compareFilled2(zeroFilledDigit1, zeroFilledDigit2);
    // else
    // return compareFilled2(zeroFilledDigit1, zeroFilledDigit2);
  }
}
