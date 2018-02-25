package com.app.investments.fund.investingapp.investition.service.impl;

import com.app.investments.fund.base.date_utils.DateUtils;
import com.app.investments.fund.base.exception.InvestingMoneyOnFundsException;
import com.app.investments.fund.base.exception.ResourceNotFoundException;
import com.app.investments.fund.investingapp.fund.entity.dto.FundDto;
import com.app.investments.fund.investingapp.fund.entity.service.FundService;
import com.app.investments.fund.investingapp.fund.type.service.FundTypeService;
import com.app.investments.fund.investingapp.investition.dto.InvestitionDto;
import com.app.investments.fund.investingapp.investition.executor.InvestitionExecutor;
import com.app.investments.fund.investingapp.investition.form.InvestitionFundForm;
import com.app.investments.fund.investingapp.investition.model.Investition;
import com.app.investments.fund.investingapp.investition.service.InvestitionService;
import com.app.investments.fund.investingapp.investition_style.dto.InvestitionMoneyDto;
import com.app.investments.fund.investingapp.investition_style.model.InvestitionStyle;
import com.app.investments.fund.investingapp.investition_style.service.InvestitionStyleService;
import com.app.investments.fund.investingapp.single_investition.model.SingleInvestition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Krzysztof Lakomy
 */
@Service
public class InvestitionServiceImpl implements InvestitionService {

	private static final Character PL_FUND_CODE = '1';
	private static final Character FR_FUND_CODE = '2';
	private static final Character CASH_FUND_CODE = '3';
	private static final int ZERO = 0;
	private static int MONEY_NOT_INVESTED = ZERO;

	@Autowired
	private FundService fundService;

	@Autowired
	private FundTypeService fundTypeService;

	@Autowired
	private InvestitionStyleService investitionStyleService;

	@Autowired
	private InvestitionExecutor investitionExecutor;

	//Główna metoda kalkulacji inwestycji
	@Override
	public InvestitionDto calculateInvestitionsByStyle(InvestitionFundForm investitionFundForm) {

		checkInvestitionForm(investitionFundForm);
		InvestitionStyle investitionStyle = getInvestitionStyleConfiguration(investitionFundForm.getInvestStyle());

		int investmentAmount;
		int wholeMoneyInvested;
		Investition calculatedInvestition = new Investition();
		InvestitionMoneyDto investitionMoneyDto;

		List<FundDto> fundList = getFundListDtoByFundIds(investitionFundForm.getFundList());
		//Kwota inwestycji podana w formularzu
		investmentAmount = Integer.valueOf(investitionFundForm.getInvestitionAmount());

		investitionMoneyDto = setAmountsInTermsOfInvestingStyle(investitionStyle, investmentAmount);
		MONEY_NOT_INVESTED = investitionMoneyDto.getNotInvestedMoney();

		List<SingleInvestition> singleInvestitionList = new ArrayList<>();

		List<FundDto> plFundList = sortFundsByType(fundList, PL_FUND_CODE);
		List<FundDto> frFundList = sortFundsByType(fundList, FR_FUND_CODE);
		List<FundDto> cashFundList = sortFundsByType(fundList, CASH_FUND_CODE);

		investMoneyOnFundBasedOnType(singleInvestitionList, plFundList, investitionMoneyDto.getPlInvestingMoney());
		investMoneyOnFundBasedOnType(singleInvestitionList, frFundList, investitionMoneyDto.getFrInvestingMoney());
		investMoneyOnFundBasedOnType(singleInvestitionList, cashFundList, investitionMoneyDto.getCashInvestingMoney());

		wholeMoneyInvested = investmentAmount - MONEY_NOT_INVESTED;
		countPercentageForEachInvestition(singleInvestitionList, wholeMoneyInvested);

		calculatedInvestition.setInvestitionStyle(investitionStyle);
		calculatedInvestition.setSingleInvestitions(singleInvestitionList);
		calculatedInvestition.setMoneyNotInvested((double) MONEY_NOT_INVESTED);
		calculatedInvestition.setInvestitionDate(DateUtils.formatLocalDate(LocalDate.now()));

		InvestitionDto calculatedInvestitionDto = saveInvestition(calculatedInvestition);
		formatDataToRepresentInvestition(calculatedInvestitionDto);
		setDefaultValuesOfInvestment();

		return calculatedInvestitionDto;
	}

	//Zapis obliczonej inwestycji do bazy danych
	@Override
	public InvestitionDto saveInvestition(Investition investition) {
		return investitionExecutor.saveInvestition(investition);
	}

	//Walidacja poprawności formularza
	private void checkInvestitionForm(InvestitionFundForm investitionFundForm){
		if(investitionFundForm == null){
			throw new ResourceNotFoundException("No data send to calculate !");
		}
		if(investitionFundForm.getFundList().isEmpty()){
			throw new ResourceNotFoundException("No Funds chosen to invest !");
		}
		if(investitionFundForm.getInvestitionAmount() == null || investitionFundForm.getInvestitionAmount().isEmpty()){
			throw new ResourceNotFoundException("No amount given to invest !");
		}
	}

	// Sortowanie funduszy ze względu na rodzaj
	private List<FundDto> sortFundsByType(List<FundDto> notSortedFundList, Character fundType){
		List<FundDto> sortedFundList = new ArrayList<>();
		for (FundDto fund : notSortedFundList) {
			if (fund.getFundTypeCode().equals(fundType)) {
				sortedFundList.add(fund);
			}
		}
			return sortedFundList;
	}

	//Kalkulacja porcentu inwestycji z poszczególnych rodzajów funduszy
	private void countPercentageForEachInvestition(List<SingleInvestition> singleInvestitionList, int wholeMoneyInvested){
		for (SingleInvestition investition : singleInvestitionList){
			Double percentage =
					new BigDecimal(100*investition.getMoneyInvestment()/wholeMoneyInvested).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
			investition.setInvestmentPercentage(percentage);
		}
	}

	//Pobranie funduszy z bazy wybranych przez użytkownika z dropdowna
	private List<FundDto> getFundListDtoByFundIds(List<String> fundList){
		List<Long> fundIds = fundList.stream().map(fundId -> Long.valueOf(fundId)).
				collect(Collectors.toList());

		return fundIds.stream().map(id -> fundService.findByFundId(id))
				.collect(Collectors.toList());
	}

	//Pobranie z bazy stylu inwestowania
	private InvestitionStyle getInvestitionStyleConfiguration(@RequestParam String investStyle){
		return investitionStyleService.findInvestitionStyleById(investStyle);
	}

	//Przeznaczenie odpowiedniej kwoty inwestycji na fundusze danego rodzaju
	private void investMoneyOnFundBasedOnType(List<SingleInvestition> singleInvestitionList,
			List<FundDto> sortedFundList, int moneyToInvest){

		int numberOfFunds = sortedFundList.size();
		if(numberOfFunds == ZERO){
			MONEY_NOT_INVESTED += moneyToInvest;
		}
		else {
			//Sprawdzenie czy kwota całkowita inwestycji wystarczy na wszystkie fundusze
			if (moneyToInvest > numberOfFunds) {
				int eachFundMoneyInvestition = moneyToInvest / numberOfFunds;
				MONEY_NOT_INVESTED += moneyToInvest % numberOfFunds;
				for (FundDto fund : sortedFundList) {
					singleInvestitionList.add(SingleInvestition.builder()
							.fundType(fundTypeService.findFundTypeByFundTypeCode(fund.getFundTypeCode()))
							.fund(fundService.findByFundName(fund.getFundName()))
							.moneyInvestment((double) eachFundMoneyInvestition)
							.build());
				}
			} else {
				throw new InvestingMoneyOnFundsException("Not enough money to invest !");
			}
		}
	}

	//Przypisanie kwot inwestycji dla rodzajów funduszy w zależności od stylu inwestowania
	private InvestitionMoneyDto setAmountsInTermsOfInvestingStyle(InvestitionStyle investitionStyle, int generalAmount){
		InvestitionMoneyDto investitionMoneyDto = new InvestitionMoneyDto();
		investitionMoneyDto.setPlInvestingMoney((int)Math.floor(investitionStyle.getInvestingFractionPL()*generalAmount));
		investitionMoneyDto.setFrInvestingMoney((int)Math.floor(investitionStyle.getInvestingFractionFR()*generalAmount));
		investitionMoneyDto.setCashInvestingMoney((int)Math.floor(investitionStyle.getInvestingFractionCash()*generalAmount));

		Integer potentialOddMoney = checkIfAllMoneyInvested(investitionMoneyDto,generalAmount);
		if(potentialOddMoney != ZERO){
			investitionMoneyDto.setNotInvestedMoney(potentialOddMoney);
		}
		return investitionMoneyDto;
	}

	//Sprawdzenie czy całość kwoty inwestycji została rozdysponowana
	private Integer checkIfAllMoneyInvested(InvestitionMoneyDto moneyDto, int generalAmount){
		return (generalAmount -(moneyDto.getPlInvestingMoney() + moneyDto.getFrInvestingMoney() + moneyDto.getCashInvestingMoney()));
	}

	//Metoda foramtująca warstwę prezentacji przekazywaną do kontrolera
	private void formatDataToRepresentInvestition(InvestitionDto investitionDto){
		if(investitionDto!= null){
			investitionDto.getSingleInvestitionDtoList().stream()
			.forEach(singleInvestitionDto -> {
				StringBuilder sb = new StringBuilder(singleInvestitionDto.getPercentage()).append(" %");
				singleInvestitionDto.setPercentage(sb.toString());
				singleInvestitionDto.setMoneyAmount(singleInvestitionDto.getMoneyAmount() + " PLN");
			});
			investitionDto.setMoneyNotInvested(investitionDto.getMoneyNotInvested() + " PLN");
		}
	}

	/*Zerowanie zmiennej statycznej na potrzeby kolejnej kalkulacji
	 w przyszłości można by nie zerować kwoty jeśli wzielibyśmy pod uwagę np.
	 sesję użytkownika i kwota niezainwestowana przechodziła by na kolejną kalkulacje
	 */
	private void setDefaultValuesOfInvestment(){
		MONEY_NOT_INVESTED = ZERO;
	}

}
