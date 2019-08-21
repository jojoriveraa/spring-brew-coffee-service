package com.walmart.det.brewcoffeeservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.walmart.det.brewcoffeeservice.service.AppInfoImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceInfoController.class)
public class AppInfoTest {
	

	private final static String VERSION_URI 	= "/version";
	private final static String NAME_URI 		= "/name";

	private final RequestBuilder reqToGetVersion= MockMvcRequestBuilders.get(VERSION_URI);
	private final RequestBuilder reqToGetName	= MockMvcRequestBuilders.get(NAME_URI);

	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private AppInfoImpl appInfo;

	@Test
	public void version_basic() throws Exception {
		
		String mockVersion = "1.0.0";
		
		doReturn(mockVersion).when(appInfo).getBuildVersion();
		
		MvcResult result = mockMvc.perform(reqToGetVersion)
				.andExpect(status().isOk())
				.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).isEqualTo(mockVersion);

	}
	
	@Test
	public void name_basic() throws Exception {
		
		String dummyAppName = "1.0.0";

		doReturn(dummyAppName).when(appInfo).getApplicationName();

		when(appInfo.getApplicationName()).thenReturn(dummyAppName);
		
		MvcResult result = mockMvc.perform(reqToGetName)
				.andExpect(status().isOk())
				.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).isEqualTo(dummyAppName);

	}

}
