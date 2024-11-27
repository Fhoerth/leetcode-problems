package leetcode.modules;

import java.math.BigInteger;

public class Digits {
  public static Integer[] toIntegerArray(int[] nums) {
    Integer[] result = new Integer[nums.length];

    for (int i = 0; i < nums.length; i++)
      result[i] = nums[i];

    return result;
  }

  public static Integer fillWithZeros(Integer a, Integer b) {
    int digitsInA = numberOfDigits(a);
    int digitsInB = numberOfDigits(b);

    if (digitsInA >= digitsInB) {
      return a;
    }

    int zerosToAdd = digitsInB - digitsInA;

    return a * (int) Math.pow(10, zerosToAdd);
  }

  public static Integer fillWithNines(Integer a, Integer b) {
    int digitsInA = numberOfDigits(a);
    int digitsInB = numberOfDigits(b);

    if (digitsInA >= digitsInB) {
      return a;
    }

    int ninesToAdd = digitsInB - digitsInA;
    int multiplier = (int) Math.pow(10, ninesToAdd);
    return a * multiplier + (multiplier - 1);
  }

  public static Integer numberOfDigits(Integer num) {
    num = Math.abs(num);

    if (num < 10)
      return 1;

    return (int) Math.floor(Math.log10(num)) + 1;
  }

  public static Integer sum(Integer a, Integer b) {
    int digitsInB = numberOfDigits(b);
    int multiplier = (int) Math.pow(10, digitsInB); // 10^zerosToAdd
    return a * multiplier + b;
  }

  public static Integer getFirstNDigits(Integer num, int n) {
    num = Math.abs(num);
    int totalDigits = (int) Math.floor(Math.log10(num)) + 1;

    if (n >= totalDigits)
      return num;

    return (int) (num / Math.pow(10, totalDigits - n));
  }

  public static Integer fill(Integer a, Integer b) {
    int digitsA = numberOfDigits(a);
    int digitsB = numberOfDigits(b);

    if (digitsB <= digitsA)
      return a;

    int n = digitsB - digitsA;
    int firstBDigits = getFirstNDigits(b, n);

    return sum(a, firstBDigits);
  }

  public static Integer superNumberOfDigits(BigInteger num) {
    num = num.abs(); // Obtiene el valor absoluto

    if (num.compareTo(BigInteger.TEN) < 0) { // num < 10
      return 1;
    }

    return num.toString().length(); // Cuenta la cantidad de dígitos convirtiendo a String
  }

  public static BigInteger superSuperSum(BigInteger bigA, BigInteger bigB) {
    int digitsInB = superNumberOfDigits(bigB);
    BigInteger multiplier = BigInteger.TEN.pow(digitsInB); // 10^digitsInB

    return bigA.multiply(multiplier).add(bigB); // a * 10^digitsInB + b
  }

  public static BigInteger superSum(Integer a, Integer b) {
    int digitsInB = numberOfDigits(b);
    BigInteger multiplier = BigInteger.TEN.pow(digitsInB); // 10^digitsInB
    BigInteger bigA = BigInteger.valueOf(a);
    BigInteger bigB = BigInteger.valueOf(b);

    return bigA.multiply(multiplier).add(bigB); // a * 10^digitsInB + b
  }

  public static Integer getFirstDigit(Integer num) {
    num = Math.abs(num);
    return (int) (num / Math.pow(10, Math.floor(Math.log10(num))));
  }

  public static Integer removeFirstDigit(Integer num) {
    num = Math.abs(num);
    return (int) (num % Math.pow(10, Math.floor(Math.log10(num))));
  }

  public static String toString(Integer[] nums) {
    BigInteger acc = BigInteger.valueOf(0);

    for (int j = 0; j < nums.length; j += 1)
      acc = superSuperSum(acc, BigInteger.valueOf(nums[j]));

    return acc.toString();
  }
}
