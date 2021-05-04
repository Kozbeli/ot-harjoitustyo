package ordermanager.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserManagementTest {

    String filePath;
    UserManagement userManagement;

    @Before
    public void setUp() {
        filePath = "src/test/java/ordermanager/data/fakeUsers.csv";

        try {
            FileWriter writer = new FileWriter(new File(filePath));
        } catch (Exception e) {
            System.out.println("Couldn't intitialize fakeUsers.");
        }

        try {
            userManagement = new UserManagement(filePath);
        } catch (IOException e) {
            System.out.println("userManagement cannot be created.");
        }
    }

    @Test
    public void usresDatabaseCanBeRestored() {
        try {
            userManagement.readUsersList(this.filePath);
            assertEquals("Registered users: " + userManagement.users.size(),
                    userManagement.toString());
        } catch (IOException ex) {
            System.out.println("Could not restore the user database");
        }
    }

    @Test
    public void userManagementCanBeConstructed() {
        assertTrue(userManagement != null);
    }

    @Test
    public void userCredentialsCanBeSaved() throws IOException {
        String[] testDummie = {"test", "test", "test", "test", "test", "123", "test", "test"};
        userManagement.createNewUser(testDummie);
        userManagement.users.clear();
        userManagement.readUsersList(filePath);
        assertTrue(userManagement.users.contains("test"));
    }

    @Test
    public void userCredentialsAreEvaluatedCorrectly() throws IOException {
        String[] testDummie1 = {"test1", "test", "test", "test", "test", "123", "test", "test"};
        assertTrue(userManagement.correct(testDummie1));
        userManagement.addUser(new User("test1", "test", "test", "test", "test", 123, "test", "test"));
        assertFalse(userManagement.correct(testDummie1));
        String[] testDummie2 = {"test2", "test", "test", "test", "test", "testi", "test", "test"};
        assertFalse(userManagement.correct(testDummie2));
        String[] testDummie3 = {"test2", "test", "test", "test", "", "123", "test", "test"};
        assertFalse(userManagement.correct(testDummie3));
        String[] testDummie4 = {"test2", ";", "test", "test", "test", "testi", "test", "test"};
        assertFalse(userManagement.correct(testDummie4));
    }

    @Test
    public void credentialsCantIncludeBadCharacters() {
        String[] badCharacters = {";", ";", ";", ";", ";", "123", ";"};
        assertFalse(userManagement.createNewUser(badCharacters));
    }

    @Test
    public void usersCanBeAddedToDatabase() {
        User user = new User("test3", "test", "test", "test", "test", 123, "test", "test");
        try {
            userManagement.addUser(user);
            userManagement.users.clear();
            userManagement.readUsersList(filePath);
            assertTrue(userManagement.users.contains(user.getUsername()));
        } catch (Exception e) {
            System.out.println("Unable");
        }
    }
    
}
