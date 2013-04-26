package com.dpf.services.query;

import java.util.regex.Matcher;

public abstract class ReplaceStr {
	private boolean isFind;

	protected abstract String replace(Matcher m);

	final void onBeforeReplace() {
		this.isFind = false;
	}

	final String replaceStr(Matcher m) {
		this.isFind = true;
		return this.replace(m);
	}

	final boolean isFind() {
		return this.isFind;
	}

}
