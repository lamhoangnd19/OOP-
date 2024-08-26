package vn.viettuts.qlsv.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateTimeFormater {
	public static String toString(LocalDate localDate) {
		if (Objects.isNull(localDate))
			return null;
		return to2(localDate.getDayOfMonth()) + "-" + 
			to2(localDate.getMonthValue()) + "-" + 
			localDate.getYear();
	}
	public static String toString(LocalDateTime localDate) {
		if (Objects.isNull(localDate))
			return null;
		return to2(localDate.getHour()) + ":" + 
			to2(localDate.getMinute()) + ":" + 
			to2(localDate.getSecond()) + " " + 
			to2(localDate.getDayOfMonth()) + "-" + 
			to2(localDate.getMonthValue()) + "-" + 
			localDate.getYear();
	}	
	public LocalDate toLocalDate(String date) {
		try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        LocalDate localDate = LocalDate.parse(date, formatter);
	        
	        return localDate;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public LocalDateTime toLocalDateTime(String dateTime) {
		try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
	        
	        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

	        return localDateTime;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	private static String to2(int number) {
		if (number < 0)
			return null;
		String vl = String.valueOf(number);
		if (vl.length() >= 2)
			return vl;
		return "0" + vl;
	}
	public static LocalDate toLocalDate(Date date) {
		if (Objects.isNull(date)) return null;
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public static Date toDate(LocalDate date) {
		if (Objects.isNull(date)) return null;
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
