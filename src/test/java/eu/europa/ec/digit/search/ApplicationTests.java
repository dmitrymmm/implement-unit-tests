package eu.europa.ec.digit.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

	@InjectMocks
	public Application application;

	// write your unit tests here
	@Test
	public void testPasswordGeneration() {
		String username = application.generateUsername("12u ser 3");
		// TODO
		application.generatePassword(username);
	}

	// TODO: All Coverage
	@Test
	public void testUsernameGeneration() {
		String username = "e1o2iu ser 3";
		username = application.generateUsername(username);
		assert(username.indexOf('1')) == -1;
		assert(username.indexOf('2')) == -1;
		assert(username.indexOf('9')) == -1;
		assert(username.indexOf(' ')) == -1;
		assert(username.indexOf('%')) == -1;
		assert(username.indexOf('#')) == -1;
		assert(username.indexOf('3')) == -1;
		assert(username.indexOf('0')) > -1;
	}
}
