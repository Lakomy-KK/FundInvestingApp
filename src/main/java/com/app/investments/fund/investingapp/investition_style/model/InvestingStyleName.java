package com.app.investments.fund.investingapp.investition_style.model;

/**
 * @author Krzysztof Lakomy
 */
public enum InvestingStyleName {

	SAFE("Bezpieczny"),
	BALANCED("Zrównoważony"),
	AGGRESSIVE("Agresywny");

	private String styleName;


	InvestingStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

}
