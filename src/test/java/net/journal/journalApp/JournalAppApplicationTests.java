package net.journal.journalApp;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.services.UserService;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017/journal_test"
})
class JournalAppApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		UserEntity user = userService.getUser("jhon_doe");
		assertNotEquals(user, null);

	}

}
