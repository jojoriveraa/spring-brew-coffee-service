package com.walmart.det.brewcoffeeservice.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.walmart.det.brewcoffeeservice.service.AppInfo;
import com.walmart.det.brewcoffeeservice.service.AppInfoImpl;

@RunWith(MockitoJUnitRunner.class)
public class VersionBusinessImplTest {

	@Mock
	AppInfo mock;

	@Spy
	private
	AppInfoImpl spyVersion = new AppInfoImpl();

	@Test
	public void getApplicationName_value() {
		when(mock.getApplicationName()).thenReturn("AppName");
		assertThat(mock.getApplicationName()).isEqualTo("AppName");
	}

	@Test
	public void getApplicationName_happened() {
		spyVersion.getApplicationName();
		verify(spyVersion).getApplicationName();
	}

	@Test
	public void getBuildVersion_value() {
		when(mock.getBuildVersion()).thenReturn("1.0.3-SNAPSHOT");
		assertThat(mock.getBuildVersion()).isEqualTo("1.0.3-SNAPSHOT");
	}

	@Test
	public void getBuildVersion_happened() {
		spyVersion.getBuildVersion();
		verify(spyVersion).getBuildVersion();
	}

	@Test
	public void getBuildTimestamp_value() {
		when(mock.getBuildTimestamp()).thenReturn("2018-12-05T18:09:35+00:00");
		assertThat(mock.getBuildTimestamp()).isEqualTo("2018-12-05T18:09:35+00:00");
	}

	@Test
	public void getBuildTimestamp_happened() {
		spyVersion.getBuildTimestamp();
		verify(spyVersion).getBuildTimestamp();
	}

}
