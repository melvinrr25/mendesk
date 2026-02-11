package com.mendesk.entity;

public enum TicketStatus {
	NEW("New", "info"),
	OPEN("Open", "warning"),
	PENDING("Pending", "secondary"),
	ON_HOLD("On Hold", "dark"),
	SOLVED("Solved", "success"),
	CLOSED("Closed", "secondary");

	private final String displayName;
	private final String bootstrapClass;

	TicketStatus(String displayName, String bootstrapClass) {
		this.displayName = displayName;
		this.bootstrapClass = bootstrapClass;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getBootstrapClass() {
		return bootstrapClass;
	}
}
