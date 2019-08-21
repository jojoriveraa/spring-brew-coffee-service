package com.walmart.det.brewcoffeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.det.brewcoffeeservice.service.AppInfo;

@RestController
class ServiceInfoController {

	@Autowired
	private AppInfo version;

	@GetMapping(value = { "/version" })
	public String version() {
		return version.getBuildVersion();
	}

	@GetMapping(value = { "/name" })
	public String name() {
		return version.getApplicationName();
	}

}
