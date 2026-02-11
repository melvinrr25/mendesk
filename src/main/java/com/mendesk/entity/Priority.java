package com.mendesk.entity;

public enum Priority {
	LOW("Low", "secondary"),
	NORMAL("Normal", "info"),
	HIGH("High", "warning"),
	URGENT("Urgent", "danger");

	private final String displayName;
	private final String bootstrapClass;

	Priority(String displayName, String bootstrapClass) {
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
