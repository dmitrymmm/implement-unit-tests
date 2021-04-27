package eu.europa.ec.digit.search;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String username = generateUsername("A Name To Generate Please");

		LOGGER.info("-----------------------------------------------------------------");
		String password = generatePassword(username);

		LOGGER.info("-----------------------------------------------------------------");
		LOGGER.info("Username : {} and Password : {}", username, password);
		LOGGER.info("-----------------------------------------------------------------");
	}

	public String generateUsername(String textToParse) {

		String text = textToParse.toLowerCase();

		if (text.contains(" ")) {
			text = text.replace(" ", "_");
		}

		text = text.replaceAll("[1-9]", "");

		Matcher m = Pattern.compile("_[A-z]").matcher(text);
		StringBuilder sb = new StringBuilder();

		int last = 0;

		while (m.find()) {
			sb.append(text.substring(last, m.start()));
			sb.append(m.group(0).toUpperCase());
			last = m.end();
		}

		sb.append(text.substring(last));
		text = sb.toString();

		text = text.replaceAll("[^a-zA-Z]", "");

		for (int x = 0; x < (text.length() / 2); x++) {

			int index = x * 2;
			char letter = text.charAt(index);
			letter++;

			char[] chars = text.toCharArray();
			chars[index] = letter;
			text = String.valueOf(chars);

		}

		text = text.replace("e", "3").replace("i", "1").replace("o", "O");

		return text;

	}

	public String generatePassword(String username) {

		String text = username.toLowerCase();
		LOGGER.info("password : {}", text);

		text = Base64.getEncoder().encodeToString(text.getBytes());
		LOGGER.info("password : {}", text);

		text = DigestUtils.sha1Hex(text);
		LOGGER.info("password : {}", text);

		return text;
	}
}
