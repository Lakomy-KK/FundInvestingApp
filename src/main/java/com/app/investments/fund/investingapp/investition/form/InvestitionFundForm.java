package com.app.investments.fund.investingapp.investition.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestitionFundForm {

	@Pattern(regexp = "^[1-3]", message = "Wybierz styl inwestowania !")
	private String investStyle;

	@NotEmpty(message = "Brak wybranych funduszy !")
	private List<String> fundList;

	@Min(value = 1, message = "Kwota musi być większa od zera !")
	@Pattern(regexp = "^([0-9]*|\\d*\\.\\d{1}?\\d*)$", message="Podana kwota musi być liczbą !")
	private String investitionAmount;

}
