package com.example.rest;

public enum ErrorLevel {
	
	WARNING("WARNING"),
	
	LOW("LOW"),
	
	MEDIUM("MEDIUM"),
	
	HIGH("HIGH");
	
	private String displayName;

	ErrorLevel(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
