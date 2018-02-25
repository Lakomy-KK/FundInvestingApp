package com.app.investments.fund.investingapp.investition.controller;

import com.app.investments.fund.base.exception.InvestingMoneyOnFundsException;
import com.app.investments.fund.base.exception.ResourceNotFoundException;
import com.app.investments.fund.investingapp.fund.entity.dto.FundDto;
import com.app.investments.fund.investingapp.fund.entity.service.FundService;
import com.app.investments.fund.investingapp.investition.dto.InvestitionDto;
import com.app.investments.fund.investingapp.investition.form.InvestitionFundForm;
import com.app.investments.fund.investingapp.investition.service.InvestitionService;
import com.app.investments.fund.investingapp.investition_style.dto.InvestitionStyleDto;
import com.app.investments.fund.investingapp.investition_style.service.InvestitionStyleService;
import com.app.investments.fund.investingapp.single_investition.dto.SingleInvestitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Controller
public class InvestitionController {

	public static final String MAIN_PAGE_URL = "/mainPage";
	public static final String MAIN_PAGE_VIEW = "home";
	public static final String CALCULATE_URL = "/calculate";
	public static final String CALCULATED_INVESTITIONS = "calculatedInvestitions";
	public static final String MONEY_NOT_INVESTED = "moneyNotInvested";
	public static final String MAIN_PAGE_REDIRECT = "redirect:/mainPage";
	public static final String INVESTITION_FUND_FORM = "investitionFundForm";
	public static final String ALL_FUNDS_FOR_DROPDOWN = "allFunds";
	public static final String ALL_INVESTING_STYLES_FOR_DROPDOWN = "investingStyles";
	public static final String ERROR = "error";
	public static final String DEFAULT_ERROR_VIEW = "exception";

	@Autowired
	private FundService fundService;

	@Autowired
	private InvestitionStyleService investitionStyleService;

	@Autowired
	private InvestitionService investitionService;

	//Strona główna aplikacji
	@RequestMapping(value = MAIN_PAGE_URL, method = RequestMethod.GET)
	public String getCalculationForm(Model model) {
	InvestitionFundForm investitionFundForm = new InvestitionFundForm();
	model.addAttribute(INVESTITION_FUND_FORM, investitionFundForm);
		return MAIN_PAGE_VIEW;
	}

	//URL pod którym obliczana jest kalkulacja inwestycji
	@RequestMapping(value = CALCULATE_URL, method = RequestMethod.POST)
	public String index(@Valid @ModelAttribute(INVESTITION_FUND_FORM)InvestitionFundForm investitionFundForm,
			BindingResult bindingResult,RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()){
			return MAIN_PAGE_VIEW;
		}

		InvestitionFundForm investitionForm = investitionFundForm;
		InvestitionDto calculatedInvestition = investitionService.calculateInvestitionsByStyle(investitionForm);
		List<SingleInvestitionDto> calculatedInvestitions = new ArrayList<>();
		if(!calculatedInvestition.getSingleInvestitionDtoList().isEmpty()){
			calculatedInvestitions = calculatedInvestition.getSingleInvestitionDtoList();
		}
		redirectAttributes.addFlashAttribute(CALCULATED_INVESTITIONS, calculatedInvestitions);
		redirectAttributes.addFlashAttribute(MONEY_NOT_INVESTED, calculatedInvestition.getMoneyNotInvested());
		return MAIN_PAGE_REDIRECT;
	}

	//Przechwycenie wyjątku w przypadku zbyt małej ilości pieniędzy do zainwestowania
	@ExceptionHandler({InvestingMoneyOnFundsException.class})
	public ModelAndView notEnoughMoneyToInvestError(InvestingMoneyOnFundsException ex) {
		ModelAndView errorView = new ModelAndView();
		errorView.addObject(ERROR, ex.getMessage());
		errorView.setViewName(DEFAULT_ERROR_VIEW);
		return errorView;
	}

	//Wypełnienie dropdownów dla funduszy
	@ModelAttribute(ALL_FUNDS_FOR_DROPDOWN)
	public List<FundDto> populateFundsForDropdown(){
		return fundService.getAllFunds();
	}

	//Wypełnienie drodownów dla styli inwestowania
	@ModelAttribute(ALL_INVESTING_STYLES_FOR_DROPDOWN)
	public List<InvestitionStyleDto> populateInvestingStylesForDropdown(){
		return investitionStyleService.getAllInvestitionStyles();
	}
}
