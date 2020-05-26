package com.amit.service.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amit.service.beans.Response;
import com.amit.service.beans.v1.Bean;
import com.amit.service.controller.AbstractController;
import com.amit.service.utils.web.MimeTypes;
import com.amit.service.validator.Create;
import com.amit.service.validator.Update;

@RestController("/ws")
public class WSControllerV1 extends AbstractController {

	private Logger LOG = LoggerFactory.getLogger(WSControllerV1.class);
	
	@GetMapping(produces = MimeTypes.SVC_V1_JSON)
	public Response<Bean> getAllBeans() throws Throwable{
		try {
			return super.prepareSuccessResponse(new Bean());
		} catch (Exception e) {
			super.handleException(e);
		}
		return null;
	}
	
	@PostMapping(consumes = MimeTypes.SVC_V1_JSON)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createBean(@RequestBody @Validated(Create.class) Bean bean) throws Throwable{
		try {
			LOG.info("Creating beans");
		} catch (Exception e) {
			super.handleException(e);
		}
	}
	
	@PutMapping(consumes = MimeTypes.SVC_V1_JSON)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateBean(@RequestBody @Validated(Update.class) Bean bean) throws Throwable{
		try {
			LOG.info("Updating beans");
		} catch (Exception e) {
			super.handleException(e);
		}
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteBean() throws Throwable{
		try {
			LOG.info("Deleting beans");
		} catch (Exception e) {
			super.handleException(e);
		}
	}
	
	
}
