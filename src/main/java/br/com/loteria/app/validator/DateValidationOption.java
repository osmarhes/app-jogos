package br.com.loteria.app.validator;

public enum DateValidationOption {
	DATE("\\d{2}/\\d{2}/\\d{4}"),
	TIME("\\d{2}:\\d{2}"),
	DATETIME("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}");
	
	private final String expression;
	
	private DateValidationOption(String expression){
		this.expression = expression;
	}
	
	public String getExpression(){
		return expression;
	}
}
