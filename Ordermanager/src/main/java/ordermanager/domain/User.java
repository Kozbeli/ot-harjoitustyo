package ordermanager.domain;

public class User {

    String username;
    String password;
    String name;
    String surname;
    String address;
    int postalCode;
    String city;

    public User(String username,
            String password,
            String name,
            String surname,
            String address,
            int postalCode,
            String city) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return username
                + ";" + password
                + ";" + name
                + ";" + surname
                + ";" + address
                + ";" + postalCode
                + ";" + city;
    }

}
