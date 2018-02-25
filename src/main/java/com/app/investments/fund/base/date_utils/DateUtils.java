package com.app.investments.fund.base.date_utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author Krzysztof Lakomy
 */
public class DateUtils {

	private static final String DATE_PATTERN = "ddMMyyyy";
	private static final String DATE_FORMATTER = "dd-MM-yyyy";

	public static LocalDate convertString2LocalDate(String stringDate){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		if(stringDate != null && !stringDate.isEmpty()){
			return LocalDate.parse(stringDate, formatter);
		}
		else{
			return null;
		}
	}

	public static String convertLocalDate2String(LocalDate date){
		return Optional.ofNullable(date).map(d -> d.format(DateTimeFormatter.ofPattern(DATE_FORMATTER))).orElse(null);
	}

	public static LocalDate formatLocalDate(LocalDate date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
		if(date != null ){
		String dateFormat = date.format(formatter);
			return LocalDate.parse(dateFormat, formatter);
		}else{
			return null;
		}
	}

	private DateUtils(){
	}
}
