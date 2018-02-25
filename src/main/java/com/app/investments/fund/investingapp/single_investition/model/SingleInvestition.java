package com.app.investments.fund.investingapp.single_investition.model;

import com.app.investments.fund.base.model.BaseEntity;
import com.app.investments.fund.investingapp.fund.entity.model.Fund;
import com.app.investments.fund.investingapp.fund.type.model.FundType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Krzysztof Lakomy
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "single_investition")
public class SingleInvestition extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "single_investition_id", nullable = false)
	private Long investitionId;

	@ManyToOne
	@JoinColumn(name = "fund_type_id", referencedColumnName = "fund_type_id")
	private FundType fundType;

	@ManyToOne
	@JoinColumn(name = "fund_id", referencedColumnName = "fund_id")
	private Fund fund;

	@Column(name = "money_invest", nullable = false)
	private Double moneyInvestment;

	@Column(name = "invest_percent")
	private Double investmentPercentage;
}
