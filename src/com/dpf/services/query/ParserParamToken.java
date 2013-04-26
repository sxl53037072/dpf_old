package com.dpf.services.query;

public class ParserParamToken implements ParamToken {
	static final String TOKEN = "#PARSER_TOKEN_PARAM";

	private int index;

	public ParserParamToken(int index) {
		this.index = index;
	}

	public String getToken() {
		StringBuffer sbuf = new StringBuffer(TOKEN);
		sbuf.append(index);
		index++;
		return sbuf.toString();
	}

}
