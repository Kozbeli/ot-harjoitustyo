package ordermanager.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
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
    public void userManagementCanBeConstructed() {
        assertTrue(userManagement != null);
    }

    @Test
    public void userCredentialsCanBeSaved() {
        String[] testDummie = {"test", "test", "test", "test", "test", "test", "test"};
        userManagement.createNewUser(testDummie);
        assertTrue(userManagement.contains("test"));
    }
}
