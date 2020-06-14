package br.com.jhegner.smp.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class NumeroUtil {
	
	private static DecimalFormat decimalFormat_1 = null;
	
	static{
		
		decimalFormat_1 = new DecimalFormat(".#");
		
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		
		decimalFormat_1.setDecimalFormatSymbols(dfs);
	}
	
	public static double getDoubleFormatado(double valor) {
		return Double.parseDouble(decimalFormat_1.format(valor));
	}
	
}
