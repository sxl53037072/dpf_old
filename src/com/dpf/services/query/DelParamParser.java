package com.dpf.services.query;

import java.util.regex.Pattern;

public class DelParamParser {
	private static final Pattern REG = Pattern.compile(
			"(?:^|\\s+(?:and|or)\\s+)(.*?)(?=\\s+(?:and|or)\\s+|$)",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public static String parse(String str) {
		DelParamParserReplace replaceStr = new DelParamParserReplace();
		return ParserUtils.replace(REG, str, replaceStr);
	}
}
