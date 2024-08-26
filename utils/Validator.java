package vn.viettuts.qlsv.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean isPhoneNumber(String pn) {
		String regex = "((^(\\+84|84|0|0084){1})(3|5|7|8|9))+([0-9]{8})$";
		return pn.matches(regex);
	}
}
