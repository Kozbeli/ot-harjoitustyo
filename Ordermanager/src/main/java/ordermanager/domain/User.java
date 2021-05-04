package ordermanager.domain;

public class User {

    String username;
    String password;
    String name;
    String surname;
    String address;
    int postalCode;
    String city;
    String country;

    public User(String username,
            String password,
            String name,
            String surname,
            String address,
            int postalCode,
            String city,
            String country) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    /**
     * Method returns the username of the user.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method returns the password of the user.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method returns the name of the user.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method returns the surname of the user
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method returns the address of the user
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method returns the postal Code of the user
     *
     * @return postal code
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Method returns the city of the user
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Method returns the country of the user
     *
     * @return country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Method returns all the user details
     *
     * @return user details
     */
    @Override
    public String toString() {
        return username
                + ";" + password
                + ";" + name
                + ";" + surname
                + ";" + address
                + ";" + postalCode
                + ";" + city
                + ";" + country;
    }

}
