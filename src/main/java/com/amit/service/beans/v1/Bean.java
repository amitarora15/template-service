package com.amit.service.beans.v1;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.amit.service.validator.Create;
import com.amit.service.validator.Update;

public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = Update.class, message = "invalid.data")
	private Integer id;

	@NotBlank(groups = Create.class)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
