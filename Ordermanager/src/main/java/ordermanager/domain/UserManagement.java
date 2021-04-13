package ordermanager.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserManagement {

    public ArrayList<String> users;
    private String file;

    public UserManagement(String file) throws IOException {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                users.add(parts[0]);
            }
        } catch (FileNotFoundException e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    public boolean createNewUser(String[] credentials) {
        if (contains(credentials[0])) return false;

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < credentials.length - 1; i++) {
            if (credentials[i].contains(";")) {
                return false;
            }
            builder.append(credentials[i]);
            builder.append(";");
        }

        builder.append(credentials[6]);
        builder.append("\n");

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(builder.toString());
            writer.close();
            users.add(credentials[0]);
        } catch (IOException e) {
            System.out.println("Unable to save credentials.");
            return false;
        }

        return true;
    }

    boolean contains(String username) {
        return this.users.contains(username);
    }
}
