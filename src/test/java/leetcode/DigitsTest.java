package leetcode;

import static org.junit.jupiter.api.Assertions.*;

import leetcode.modules.Digits;
import org.junit.jupiter.api.Test;

public class DigitsTest {
  @Test
  void remove_first_digit() {
    assertEquals(Digits.removeFirstDigit(5443434), 443434);
    assertEquals(Digits.removeFirstDigit(2443434), 443434);
    assertEquals(Digits.removeFirstDigit(3443434), 443434);
    assertEquals(Digits.removeFirstDigit(4443434), 443434);
    assertEquals(Digits.removeFirstDigit(1), 0);
    assertEquals(Digits.removeFirstDigit(2), 0);
    assertEquals(Digits.removeFirstDigit(2), 0);
    assertEquals(Digits.removeFirstDigit(20), 0);
    assertEquals(Digits.removeFirstDigit(235), 35);
  }

  @Test
  void get_first_digit() {
    assertEquals(Digits.getFirstDigit(5443434), 5);
    assertEquals(Digits.getFirstDigit(2443434), 2);
    assertEquals(Digits.getFirstDigit(3443434), 3);
    assertEquals(Digits.getFirstDigit(4443434), 4);
    assertEquals(Digits.getFirstDigit(1), 1);
    assertEquals(Digits.getFirstDigit(2), 2);
    assertEquals(Digits.getFirstDigit(2), 2);
    assertEquals(Digits.getFirstDigit(20), 2);
    assertEquals(Digits.getFirstDigit(235), 2);
  }

  @Test
  void fill_with_zeros() {
    assertEquals(Digits.fillWithZeros(1, 1), 1);
    assertEquals(Digits.fillWithZeros(1, 2), 1);
    assertEquals(Digits.fillWithZeros(1, 9), 1);
    assertEquals(Digits.fillWithZeros(1, 20), 10);
    assertEquals(Digits.fillWithZeros(1, 200), 100);
    assertEquals(Digits.fillWithZeros(1, 2000), 1000);
    assertEquals(Digits.fillWithZeros(2000, 2000), 2000);
    assertEquals(Digits.fillWithZeros(2000, 200000), 200000);
    assertEquals(Digits.fillWithZeros(2000, 1), 2000);
  }

  @Test
  void fill_with_nines() {
    assertEquals(Digits.fillWithNines(1, 1), 1);
    assertEquals(Digits.fillWithNines(1, 2), 1);
    assertEquals(Digits.fillWithNines(1, 9), 1);
    assertEquals(Digits.fillWithNines(1, 20), 19);
    assertEquals(Digits.fillWithNines(1, 200), 199);
    assertEquals(Digits.fillWithNines(1, 2000), 1999);
    assertEquals(Digits.fillWithNines(2000, 2000), 2000);
    assertEquals(Digits.fillWithNines(2000, 200000), 200099);
    assertEquals(Digits.fillWithNines(20, 2020), 2099);
    assertEquals(Digits.fillWithNines(2000, 1), 2000);
  }

  @Test
  void number_of_digits() {
    assertEquals(Digits.numberOfDigits(9), 1);
    assertEquals(Digits.numberOfDigits(99), 2);
    assertEquals(Digits.numberOfDigits(999), 3);
    assertEquals(Digits.numberOfDigits(9999), 4);
    assertEquals(Digits.numberOfDigits(99999), 5);
  }

  @Test
  void sum() {
    assertEquals(Digits.sum(21, 1), 211);
    assertEquals(Digits.sum(1, 21), 121);
    assertEquals(Digits.sum(11, 21), 1121);
    assertEquals(Digits.sum(12, 11), 1211);
    assertEquals(Digits.sum(1, 1), 11);
    assertEquals(Digits.sum(11, 11), 1111);
    assertEquals(Digits.sum(111, 111), 111111);
    assertEquals(Digits.sum(0, 1), 1);
    assertEquals(Digits.sum(1, 2), 12);
    assertEquals(Digits.sum(12, 3), 123);
    assertEquals(Digits.sum(123, 4), 1234);
    assertEquals(Digits.sum(1234, 5), 12345);
    assertEquals(Digits.sum(12345, 6), 123456);
    assertEquals(Digits.sum(123456, 7), 1234567);
    assertEquals(Digits.sum(1234567, 8), 12345678);
    assertEquals(Digits.sum(12345678, 9), 123456789);
  }

  @Test
  void fill() {
    assertEquals(336, Digits.fill(33, 654));
    assertEquals(365, Digits.fill(3, 654));
    assertEquals(3965, Digits.fill(3, 9654));
    assertEquals(33333, Digits.fill(33333, 3));
    assertEquals(33333, Digits.fill(33333, 33333));
  }

  @Test
  void to_string() {
    int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertEquals(Digits.toString(Digits.toIntegerArray(nums)), "123456789");
  }
}
