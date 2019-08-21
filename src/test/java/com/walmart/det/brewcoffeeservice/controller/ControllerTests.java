package com.walmart.det.brewcoffeeservice.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;

class ControllerTests {

	List<String> getTypes(MvcResult result) throws UnsupportedEncodingException {
		String jsonBody = result.getResponse().getContentAsString();
		return JsonPath.parse(jsonBody).read("$..type");
	}

}
