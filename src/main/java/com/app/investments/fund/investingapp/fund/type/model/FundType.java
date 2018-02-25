package com.app.investments.fund.investingapp.fund.type.model;

import com.app.investments.fund.base.model.BaseEntity;
import com.app.investments.fund.investingapp.fund.entity.model.Fund;
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
@Table(name = "fund_type")
public class FundType extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fund_type_id", nullable = false)
	private Long fundTypeId;

	@Column(name = "type_name", nullable = false, length = 256)
	private String typeName;

	@Column(name = "type_code", nullable = false, length = 1)
	private Character typeCode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fundType")
	private List<Fund> fundList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fundType")
	private List<SingleInvestition> singleInvestitionList;
}
