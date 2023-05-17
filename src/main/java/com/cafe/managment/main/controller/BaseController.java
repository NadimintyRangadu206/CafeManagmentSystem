package com.cafe.managment.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.cafe.managment.main.model.EntityAudit;
import com.cafe.managment.main.response.ResponseObject;


public class BaseController {



	public ResponseObject getResponse(List<? extends EntityAudit> entities) {
		if (entities == null || entities.isEmpty()) {
			return new ResponseObject(entities, null, HttpStatus.ACCEPTED);
		}

		return new ResponseObject(entities, null, HttpStatus.OK);
	}

	
	
}
