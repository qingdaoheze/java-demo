package lfg.demo.utils;

import java.math.BigDecimal;

/**
 * double类型的加、减、乘、除
 * 
 * @author lifenggang
 *
 */
public class Ariths {
	/**
	 * 功能：BigDecimal类型的加法运算。
	 * 
	 * @param d1
	 *            被加数
	 * @param d2
	 *            加数
	 * @return 两个参数的和
	 */
	public static final double add(double d1, double d2) {
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.add(oper2).doubleValue();
	}

	/**
	 * 多个数相加
	 * 
	 * @param ds
	 * @return
	 */
	public static final double add(double... ds) {
		BigDecimal total = BigDecimal.ZERO;
		for (double d0 : ds) {
			total.add(new BigDecimal(Double.toString(d0)));
		}
		return total.doubleValue();
	}

	/**
	 * BigDecimal类型的减法运算。
	 * 
	 * @param d1
	 *            被减数
	 * @param d2
	 *            减数
	 * @return 两个参数的差
	 */
	public static final double subtract(double d1, double d2) {
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.subtract(oper2).doubleValue();
	}

	/**
	 * 两个数的乘法
	 * 
	 * @param d1
	 *            被乘数
	 * @param d2
	 *            乘数
	 * @return 乘积
	 */
	public static final double multiply(double d1, double d2) {
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.multiply(oper2).doubleValue();
	}

	/**
	 * 两个数的除法运算，当除不尽的时候，由scale指定精度，做四舍五入。
	 * 
	 * @param d1
	 *            被除数
	 * @param d2
	 *            除数
	 * @param scale
	 *            结果的精度，即保留几位小数
	 * @return 商
	 */
	public static final double divide(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度不能小于0");
		}
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.divide(oper2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 两个数的除法运算，当除不尽的时候，由scale指定精度，向远离0的方向舍入。
	 * 
	 * @param d1
	 *            被除数
	 * @param d2
	 *            除数
	 * @param scale
	 *            结果的精度，即保留几位小数
	 * @return 商
	 */
	public static final double divideUp(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度不能小于0");
		}
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.divide(oper2, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 两个数的除法运算，当除不尽的时候，由scale指定精度，向接近0的方向舍入。
	 * 
	 * @param d1
	 *            被除数
	 * @param d2
	 *            除数
	 * @param scale
	 *            结果的精度，即保留几位小数
	 * @return 商
	 */
	public static final double divideDown(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度不能小于0");
		}
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.divide(oper2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 两个数的除法运算，当除不尽的时候，由scale指定精度，向正无穷的方向舍入。
	 * 
	 * @param d1
	 *            被除数
	 * @param d2
	 *            除数
	 * @param scale
	 *            结果的精度，即保留几位小数
	 * @return 商
	 */
	public static final double divideCeiling(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度不能小于0");
		}
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.divide(oper2, scale, BigDecimal.ROUND_CEILING).doubleValue();
	}

	/**
	 * 两个数的除法运算，当除不尽的时候，由scale指定精度，向负无穷的方向舍入。
	 * 
	 * @param d1
	 *            被除数
	 * @param d2
	 *            除数
	 * @param scale
	 *            结果的精度，即保留几位小数
	 * @return 商
	 */
	public static final double divideFloor(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度不能小于0");
		}
		BigDecimal oper1 = new BigDecimal(Double.toString(d1));
		BigDecimal oper2 = new BigDecimal(Double.toString(d2));
		return oper1.divide(oper2, scale, BigDecimal.ROUND_FLOOR).doubleValue();
	}

}
