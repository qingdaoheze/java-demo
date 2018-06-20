package lfg.demo.utils;

import org.junit.Assert;
import org.junit.Test;


public class ArithsTest {
	@Test
	public void add() {
		System.out.println("================================= Add ===================================");
		printAdd(2.16, 3.18, 5.34);
		printAdd(0.1, 0.1, 0.2);
		printAdd(0.1, 0.2, 0.3);
		printAdd(0.1, 0.3, 0.4);
		printAdd(0.1, 0.4, 0.5);
		printAdd(0.1, 0.5, 0.6);
		printAdd(0.1, 0.6, 0.7);
		printAdd(0.1, 0.7, 0.8);
		printAdd(0.1, 0.8, 0.9);
		printAdd(0.1, 0.9, 1);
	}
	@Test
	public void minus() {
		System.out.println("================================= Minus ===================================");
		Assert.assertEquals(0, Double.compare(1.02, Ariths.subtract(3.18, 2.16)));
		printMinus(2.16, 3.18, -1.02);
		printMinus(0.1, 0.8, -0.7);
		printMinus(1.0, 0.1, 0.9);
		printMinus(1.0, 0.2, 0.8);
		printMinus(1.0, 0.3, 0.7);
		printMinus(1.0, 0.4, 0.6);
		printMinus(1.0, 0.5, 0.5);
		printMinus(1.0, 0.6, 0.4);
		printMinus(1.0, 0.7, 0.3);
		printMinus(1.0, 0.8, 0.2);
		printMinus(1.0, 0.9, 0.1);
	}
	
	@Test
	public void divide() {
		// 0.333333……
		Assert.assertEquals(0.333, Ariths.divide(1, 3, 3), 18);
		Assert.assertEquals(-0.333, Ariths.divide(-1, 3, 3), 18);

		// 0.666666……
		Assert.assertEquals(0.667, Ariths.divide(2, 3, 3), 18);
		Assert.assertEquals(-0.667, Ariths.divide(-2, 3, 3), 18);

		// 0.0555
		Assert.assertEquals(0.056, Ariths.divide(0.111, 2, 3), 18);
		Assert.assertEquals(-0.056, Ariths.divide(-0.111, 2, 3), 18);
	}
	
	@Test
	public void divideUp() {
		// 0.333333……
		Assert.assertEquals(0.334, Ariths.divideUp(1, 3, 3), 18);
		Assert.assertEquals(-0.334, Ariths.divideUp(-1, 3, 3), 18);

		// 0.666666……
		Assert.assertEquals(0.667, Ariths.divideUp(2, 3, 3), 18);
		Assert.assertEquals(-0.667, Ariths.divideUp(-2, 3, 3), 18);

		// 0.0555
		Assert.assertEquals(0.056, Ariths.divideUp(0.111, 2, 3), 18);
		Assert.assertEquals(-0.056, Ariths.divideUp(-0.111, 2, 3), 18);
	}
	
	@Test
	public void divideDown() {
		// 0.333333……
		Assert.assertEquals(0.333, Ariths.divideDown(1, 3, 3), 18);
		Assert.assertEquals(-0.333, Ariths.divideDown(-1, 3, 3), 18);

		// 0.666666……
		Assert.assertEquals(0.666, Ariths.divideDown(2, 3, 3), 18);
		Assert.assertEquals(-0.666, Ariths.divideDown(-2, 3, 3), 18);

		// 0.0555
		Assert.assertEquals(0.055, Ariths.divideDown(0.111, 2, 3), 18);
		Assert.assertEquals(-0.055, Ariths.divideDown(-0.111, 2, 3), 18);
	}
	
	@Test
	public void divideCeiling() {
		// 0.333333……
		Assert.assertEquals(0.334, Ariths.divideCeiling(1, 3, 3), 18);
		Assert.assertEquals(-0.333, Ariths.divideCeiling(-1, 3, 3), 18);

		// 0.666666……
		Assert.assertEquals(0.667, Ariths.divideCeiling(2, 3, 3), 18);
		Assert.assertEquals(-0.666, Ariths.divideCeiling(-2, 3, 3), 18);

		// 0.0555
		Assert.assertEquals(0.056, Ariths.divideCeiling(0.111, 2, 3), 18);
		Assert.assertEquals(-0.055, Ariths.divideCeiling(-0.111, 2, 3), 18);
	}
	
	@Test
	public void divideFloor() {
		// 0.333333……
		Assert.assertEquals(0.333, Ariths.divideFloor(1, 3, 3), 18);
		Assert.assertEquals(-0.334, Ariths.divideFloor(-1, 3, 3), 18);

		// 0.666666……
		Assert.assertEquals(0.666, Ariths.divideFloor(2, 3, 3), 18);
		Assert.assertEquals(-0.667, Ariths.divideFloor(-2, 3, 3), 18);

		// 0.0555
		Assert.assertEquals(0.055, Ariths.divideFloor(0.111, 2, 3), 18);
		Assert.assertEquals(-0.056, Ariths.divideFloor(-0.111, 2, 3), 18);
	}

	public static final void printAdd(double d1, double d2, double expect) {
		double direct = d1 + d2, actual = Ariths.add(d1, d2);
		String format = "d1: %-20s | d2: %-20s | d1 * d2: %-20s | NumberUtils.add(d1, d2): %-20s | ";
		formatPrintAndAssert(format, expect, direct, actual, d1, d2);
	}
	

	public static final void printMinus(double d1, double d2, double expect) {
		double direct = d1 - d2, actual = Ariths.subtract(d1, d2);
		String format = "d1: %-20s | d2: %-20s | d1 - d2: %-20s | NumberUtils.add(d1, d2): %-20s | ";
		formatPrintAndAssert(format, expect, direct, actual, d1, d2);
	}

	public static final void formatPrintAndAssert(String format, double expect, double direct, double actual, double oper1, double oper2) {
		if (Double.compare(direct, actual) != 0) {
			System.err.println(String.format(format, oper1, oper2, direct, actual));
		} else {
			System.out.println(String.format(format, oper1, oper2, direct, actual));
		}
		Assert.assertEquals(0, Double.compare(expect, actual));
	}
}
