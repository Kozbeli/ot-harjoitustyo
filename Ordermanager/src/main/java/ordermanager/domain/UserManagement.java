package ordermanager.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

/**
 * This class offers user management service to user interface. It keeps track
 * of users with an ArrayList during the process and saves changes into csv file
 * locating in data package.
 *
 * @author kozbeli
 */
public class UserManagement {

    public ArrayList<String> users;
    private final String file;

    /**
     * This constructor constructs initiates the user Management
     *
     * @param file path as a string
     * @throws IOException
     */
    public UserManagement(String file) throws IOException {
        users = new ArrayList<>();
        this.file = file;
        readUsersList(this.file);
    }

    /**
     * This method loads user details from csv file into ArrayList for easy
     * management.
     *
     * @param file path as a string
     * @throws IOException
     */
    public void readUsersList(String file) throws IOException {
        Scanner reader = new Scanner(new File(file));
        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(";");
            this.users.add(parts[0]);
        }
    }

    /**
     * This method creates a new user if given credentials are correct and
     * returns feedback upon success to user interface.
     *
     * @param credentials as a String array.
     * @return truth value if creation of a new user was successful or not.
     */
    public boolean createNewUser(String[] credentials) {
        if (!correct(credentials)) {
            return false;
        }

        try {
            addUser(new User(
                    credentials[0],
                    credentials[1],
                    credentials[2],
                    credentials[3],
                    credentials[4],
                    Integer.valueOf(credentials[5]),
                    credentials[6],
                    credentials[7]));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method checks if given credentials in sign up scene were correct. It
     * considers whether the username is unique, the postal code is numeric and
     * no semicolons are included in the credentials.
     *
     * @param credentials as a String array.
     * @return truth value
     */
    public boolean correct(String[] credentials) {
        if (users.contains(credentials[0])) {
            return false;
        }
        if (!StringUtils.isNumeric(credentials[5])) {
            return false;
        }
        for (int i = 0; i < credentials.length; i++) {
            if (credentials[i].contains(";") || credentials[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        int count = this.users.size();
        return "Registered users: " + count;
    }

    /**
     * This method adds an user into permanent storage.
     *
     * @param user
     * @throws IOException
     */
    public void addUser(User user) throws IOException {

        FileWriter writer = new FileWriter(file, true);
        writer.append(user.toString() + "\n");
        writer.close();
        users.add(user.getUsername());
    }

    public boolean login(String[] credentials) {
        User user = getUser(credentials);
        if (user == null) {
            return false;
        }
        if (user.getPassword().equals(credentials[1])) {
            return true;
        }
        return false;
    }

    /**
     * This method fetches an user from the database according to credentials.
     * If not found, then it returns null;
     *
     * @param credentials
     * @return user
     */
    public User getUser(String[] credentials) {
        User user = null;
        if (!users.contains(credentials[0])) {
            return user;
        }

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                if (!parts[0].equals(credentials[0])) {
                    continue;
                }

                user = new User(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4],
                        Integer.valueOf(parts[5]),
                        parts[6],
                        parts[7]);
            }
            return user;
        } catch (FileNotFoundException ex) {
            return user;
        }
    }
}
