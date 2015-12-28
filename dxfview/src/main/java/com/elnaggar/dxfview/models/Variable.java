package com.elnaggar.dxfview.models;

import java.util.Map;

public class Variable {
	private String name;
	private Map<String, Double> value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Double> getValue() {
		return value;
	}
	public void setValue(Map<String, Double> value) {
		this.value = value;
	}
}
