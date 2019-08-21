package com.walmart.det.brewcoffeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class IndexController {
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@GetMapping("/endpoints")
	public Object showEndpointsAction() {
		return requestMappingHandlerMapping.getHandlerMethods().keySet().stream()
				.map(t -> (t.getMethodsCondition().getMethods().isEmpty() ? "GET"
						: t.getMethodsCondition().getMethods().toArray()[0]) + " "
						+ t.getPatternsCondition().getPatterns().toArray()[0])
				.toArray();
	}
}
