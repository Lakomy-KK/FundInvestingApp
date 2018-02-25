package com.app.investments.fund.investingapp.investition_style.model;

import com.app.investments.fund.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krzysztof Lakomy
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "investition_style")
public class InvestitionStyle extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "investition_style_id", nullable = false)
	private Long investitionStyleId;

	@Column(name = "style_name", nullable = false)
	private InvestingStyleName styleName;

	@Column(name = "pl_fund_investment", nullable = false)
	private Double investingFractionPL;

	@Column(name = "fr_fund_investment", nullable = false)
	private Double investingFractionFR;

	@Column(name = "cash_fund_investment", nullable = false)
	private Double investingFractionCash;

}
