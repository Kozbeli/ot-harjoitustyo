package ordermanager.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class UserManagement {

    public ArrayList<String> users;
    private final String file;

    public UserManagement(String file) throws IOException {
        users = new ArrayList<>();
        this.file = file;
        readUsersList(this.file);
    }

    public void readUsersList(String file) throws IOException {
        Scanner reader = new Scanner(new File(file));
        while (reader.hasNextLine()) {
            String[] parts = reader.nextLine().split(";");
            users.add(parts[0]);
        }
    }

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
                    credentials[6]));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean correct(String[] c) {
        if (users.contains(c[0])) {
            return false;
        }
        if (!StringUtils.isNumeric(c[5])) {
            return false;
        }
        for (int i = 0; i < c.length; i++) {
            if (c[i].contains(";") || c[i].isEmpty()) {
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
                        parts[6]);
            }
            return user;
        } catch (FileNotFoundException ex) {
            return user;
        }
    }
}
