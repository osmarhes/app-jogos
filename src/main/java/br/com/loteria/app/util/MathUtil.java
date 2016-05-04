package br.com.loteria.app.util;

import java.math.BigDecimal;

import com.google.common.math.BigIntegerMath;

public class MathUtil {
	public static BigDecimal probalidade(Integer n, Integer p) {
		double cn = BigIntegerMath.factorial(n)
				.divide(BigIntegerMath.factorial(p).multiply(BigIntegerMath.factorial(n - p))).doubleValue();

		return new BigDecimal(cn);
	}

}
