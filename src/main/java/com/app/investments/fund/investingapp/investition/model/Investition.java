package com.app.investments.fund.investingapp.investition.model;

import com.app.investments.fund.base.model.BaseEntity;
import com.app.investments.fund.investingapp.investition_style.model.InvestitionStyle;
import com.app.investments.fund.investingapp.single_investition.model.SingleInvestition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "investition")
public class Investition extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "investition_id", nullable = false)
	private Long investitionId;

	@OneToOne
	@JoinColumn(name = "investition_style_id", referencedColumnName = "investition_style_id", nullable = false)
	private InvestitionStyle investitionStyle;

	@Column(name = "investition_date")
	LocalDate investitionDate;

	@Column(name = "money_not_invested")
	private Double moneyNotInvested;

	@OneToMany(cascade = CascadeType.ALL)
	List<SingleInvestition> singleInvestitions;

}
