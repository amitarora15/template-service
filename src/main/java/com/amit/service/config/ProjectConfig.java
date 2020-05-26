package com.amit.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

	private Message message;

	public static class Message {

		private String errorFile;

		public String getErrorFile() {
			return errorFile;
		}

		public void setErrorFile(String errorFile) {
			this.errorFile = errorFile;
		}

	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
