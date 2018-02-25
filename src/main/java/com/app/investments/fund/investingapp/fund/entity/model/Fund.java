package com.app.investments.fund.investingapp.fund.entity.model;

import com.app.investments.fund.base.model.BaseEntity;
import com.app.investments.fund.investingapp.fund.type.model.FundType;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fund")
public class Fund extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fund_id", nullable = false)
	private Long fundId;

	@Column(name = "fund_name", nullable = false)
	private String fundName;

	@ManyToOne
	@JoinColumn(name = "fund_type_id", referencedColumnName = "fund_type_id")
	private FundType fundType;

	@Column(name = "fund_code", nullable = false, length = 8)
	private String fundCode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fund")
	private List<SingleInvestition> singleInvestitionList;
}
