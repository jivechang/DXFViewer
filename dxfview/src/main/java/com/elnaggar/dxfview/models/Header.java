package com.elnaggar.dxfview.models;

import java.util.Map;

public class Header {
	private Map<String, Variable> variables;

	public Map<String, Variable> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Variable> variables) {
		this.variables = variables;
	}
}
