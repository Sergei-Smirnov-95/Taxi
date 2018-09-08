package kspt.taxi.repository;

import lombok.extern.java.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Log
public class RepositoryTest {
    private Repository repository;

    @Before
    public void setUp() throws Exception {
        repository = new Repository();
    }

    @After
    public void tearDown() throws Exception {
        repository.clear();
        repository = null;
    }

    @Test
    public void addUserTest() {
        assertTrue("Can't add user", repository.addUser("user", "user", "user", "user", "user"));
        assertNotNull("Added user not found", repository.getUser("user"));

        assertFalse("Added user second time", repository.addUser("user", "user", "user", "user", "user"));
        assertTrue("Can't add user", repository.addUser("user2", "user2", "user2", "user2", "user2"));

        assertNotNull("Added user not found", repository.getUser("user"));
        assertNotNull("Added user not found", repository.getUser("user2"));
        assertNull("Not added user was found", repository.getUser("user3"));
    }

}
