package ordermanager.domain;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    User user;

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        user = new User("username",
                "password",
                "name",
                "surname",
                "address",
                1234,
                "City");
    }

    @Test
    public void userCanBeCreated() {
        assertTrue(user != null);
    }
    
    @Test
    public void credentialsAreCorrect() {
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("name", user.getName());
        assertEquals("surname", user.getSurname());
        assertEquals("address", user.getAddress());
        assertEquals(1234, user.getPostalCode());
        assertEquals("City", user.getCity());
        assertEquals("username;password;name;surname;address;1234;City", user.toString());
    }

}
