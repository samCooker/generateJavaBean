package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegExp {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("[A-Za-z0-9_]");
		Matcher m = p.matcher("_");
		System.out.println(m.matches());
	}
}
