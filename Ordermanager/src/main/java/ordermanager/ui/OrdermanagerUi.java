package ordermanager.ui;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ordermanager.domain.Product;
import ordermanager.domain.ProductManagement;
import ordermanager.domain.UserManagement;

public class OrdermanagerUi extends Application {

    private UserManagement userManagement;
    private ProductManagement productManagement;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {

        String userFilePath = "src/main/java/ordermanager/data/users.csv";
        String productFilePath = "src/main/java/ordermanager/data/products.csv";

        userManagement = new UserManagement(userFilePath);
        productManagement = new ProductManagement(productFilePath);
    }

    @Override
    public void start(Stage primaryStage) {

        // Start scene
        Label title = new Label("Welcome!");

        TextField usernameInput = new TextField("Username");
        TextField passwordInput = new TextField("Password");

        VBox textFields = new VBox(10);
        textFields.getChildren().addAll(title, usernameInput, passwordInput);
        textFields.setAlignment(Pos.CENTER);
        textFields.setPadding(new Insets(10));
        textFields.setSpacing(10);

        Button login = new Button("Login");
        Button createNew = new Button("Create new account");

        HBox loginButtons = new HBox(10);
        loginButtons.getChildren().addAll(login, createNew);
        loginButtons.setAlignment(Pos.CENTER);
        loginButtons.setPadding(new Insets(10));
        loginButtons.setSpacing(10);

        FlowPane loginCredentials = new FlowPane(10, 20);
        loginCredentials.getChildren().addAll(textFields, loginButtons);
        loginCredentials.setAlignment(Pos.CENTER);
        loginCredentials.setOrientation(Orientation.VERTICAL);
        loginCredentials.setVgap(10);
        loginCredentials.setHgap(10);
        loginCredentials.setPrefWrapLength(5);

        Scene start = new Scene(loginCredentials, 400, 350);
        primaryStage.setScene(start);
        primaryStage.setTitle("OrderManager");
        primaryStage.show();

        // Signup scene
        TextField nameInput = new TextField("Name");
        TextField surnameInput = new TextField("Surname");
        TextField addressInput = new TextField("Address");
        TextField postalCodeInput = new TextField("Postal Code");
        TextField cityInput = new TextField("City");
        TextField countryinput = new TextField("Country");
        TextField newUserInput = new TextField("Username");
        TextField newPasswordInput = new TextField("Password");

        VBox signupFields = new VBox(10);
        signupFields.getChildren().addAll(
                nameInput, surnameInput, addressInput, postalCodeInput,
                cityInput, countryinput, newUserInput, newPasswordInput);
        signupFields.setAlignment(Pos.CENTER);
        signupFields.setPadding(new Insets(10));
        signupFields.setSpacing(10);

        Button signupReady = new Button("Ready");
        Button signupCancel = new Button("Cancel");

        HBox signupButtons = new HBox(10);
        signupButtons.getChildren().addAll(signupReady, signupCancel);
        signupButtons.setAlignment(Pos.CENTER);
        signupButtons.setPadding(new Insets(10));
        signupButtons.setSpacing(10);

        FlowPane signupCredentials = new FlowPane(10, 20);
        signupCredentials.getChildren().addAll(signupFields, signupButtons);
        signupCredentials.setAlignment(Pos.CENTER);
        signupCredentials.setOrientation(Orientation.VERTICAL);
        signupCredentials.setVgap(10);
        signupCredentials.setHgap(10);
        signupCredentials.setPrefWrapLength(5);

        Scene signup = new Scene(signupCredentials, 400, 350);

        // Catalog scene
        TableView<Product> itemList = new TableView();

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemList.setItems(productManagement.getProducts());
        itemList.getColumns().addAll(nameColumn, priceColumn);

        Button logoutCatalog = new Button("Logout");
        Button readyCatalog = new Button("Ready");
        Button addToCart = new Button("Add");

        HBox catalogControl = new HBox(10);
        catalogControl.setPadding(new Insets(10, 10, 10, 10));
        catalogControl.setAlignment(Pos.BOTTOM_RIGHT);
        catalogControl.setSpacing(10);
        catalogControl.getChildren().addAll(addToCart, readyCatalog, logoutCatalog);

        Label nameLabel = new Label("Tuote");
        Label priceLabel = new Label("Hinta");

        HBox catalogLabel = new HBox(10);
        catalogLabel.setPadding(new Insets(10, 10, 10, 10));
        catalogLabel.setSpacing(10);
        catalogLabel.setAlignment(Pos.BOTTOM_LEFT);
        catalogLabel.getChildren().addAll(nameLabel, priceLabel, catalogControl);

        BorderPane products = new BorderPane();
        products.setCenter(itemList);
        products.setBottom(catalogLabel);

        Scene catalog = new Scene(products, 400, 350);

        // Shopping cart
        // Review
        createNew.setOnAction(e -> {
            primaryStage.setScene(signup);
            usernameInput.setText("Username");
            passwordInput.setText("Password");
        });

        signupCancel.setOnAction(e -> {
            primaryStage.setScene(start);
            newUserInput.setText("Username");
            newPasswordInput.setText("Password");
            nameInput.setText("Name");
            surnameInput.setText("Surname");
            addressInput.setText("Address");
            postalCodeInput.setText("Postal Code");
            cityInput.setText("City");
        });

        signupReady.setOnAction(e -> {
            String[] credentials = new String[7];
            credentials[0] = newUserInput.getText();
            credentials[1] = newPasswordInput.getText();
            credentials[2] = nameInput.getText();
            credentials[3] = surnameInput.getText();
            credentials[4] = addressInput.getText();
            credentials[5] = postalCodeInput.getText();
            credentials[6] = cityInput.getText();

            if (userManagement.createNewUser(credentials)) {
                primaryStage.setScene(start);
            }

        });

        login.setOnAction(e -> {
            String[] credentials = new String[2];
            credentials[0] = usernameInput.getText();
            credentials[1] = passwordInput.getText();
            if (userManagement.login(credentials)) {
                primaryStage.setScene(catalog);
            }
        });

        logoutCatalog.setOnAction(e -> {
            primaryStage.setScene(start);
        });

        addToCart.setOnAction(e -> {
            ObservableList<Product> productSelected, allProducts;
            allProducts = itemList.getItems();
            Product selectedProduct = itemList.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                nameLabel.setText(selectedProduct.getName());
                priceLabel.setText("" + selectedProduct.getPrice());
            }
        });

    }

}
