package com.walmart.det.brewcoffeeservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppInfoImpl implements AppInfo {

	@Value("${application.name}")
	private String applicationName;

	@Value("${build.version}")
	private String buildVersion;

	@Value("${build.timestamp}")
	private String buildTimestamp;

	@Override
	public String getApplicationName() {
		return applicationName;
	}

	@Override
	public String getBuildVersion() {
		return buildVersion;
	}

	@Override
	public String getBuildTimestamp() {
		return buildTimestamp;
	}

}
