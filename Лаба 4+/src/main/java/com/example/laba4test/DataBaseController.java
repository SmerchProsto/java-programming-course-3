package com.example.laba4test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;


public class DataBaseController {

    @FXML
    private ChoiceBox<String> filterLabel;
    @FXML
    private Button searchButton;
    @FXML
    private MenuItem addButton;

    @FXML
    private MenuItem deleteButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Employeer, String> NameColumn;

    @FXML
    private MenuItem bdButton;




    @FXML
    private TableColumn<Employeer, String> cityDepartamentColumn;

    @FXML
    private TableColumn<Employeer, Integer> idColumn;

    @FXML
    private TableColumn<Employeer, String> managerColumn;

    @FXML
    private Menu markEdit;

    @FXML
    private MenuBar markFile;

    @FXML
    private TableColumn<Employeer, String> numSubdivisionColumn;

    @FXML
    private TableColumn<Employeer, String> phoneColumn;

    @FXML
    private TableColumn<Employeer, String> rankColumn;

    @FXML
    private TableColumn<Employeer, String> salaryColumn;

    @FXML
    private TableView<Employeer> stuffTable;

    @FXML
    private TextField searchLabel;


    @FXML
    void initialize() {
        /*Stage stage = new Stage();
        stage.setTitle("File Chooser Sample");
        final FileChooser fileChooser = new FileChooser();*/

        /*bdButton.setOnAction(actionEvent -> {
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            *//*if (fileChooser != null) {
                openFile(file);
            }*//*
        });*/

        ArrayList<Employeer> employeers = new ArrayList<>();
        ControlInterface controlInterface = new ControlInterface();

        createSearchFilter();

        bdButton.setOnAction(actionEvent -> {
            stuffTable.setEditable(true);
            drawTable(employeers);
        });

        addButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            controlInterface.openAddEmployerWindow(loader);
        });

        deleteButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            controlInterface.openDeleteEmployerWindow(loader);
        });

        searchButton.setOnAction(actionEvent -> {
            switch (filterLabel.getValue()) {
                case "id": drawResult("id" , searchLabel.getText());
                    break;
                case "Имя":
                    drawResult("name", searchLabel.getText());
                    break;
                case "Телефон":
                    drawResult("phone", searchLabel.getText());
                    break;
                case "Менеджер":
                    drawResult("manager", searchLabel.getText());
                    break;
                case "Зарплата":
                    drawResult("salary", searchLabel.getText());
                    break;
                case "Номер подразделения":
                    drawResult("numberDepartament", searchLabel.getText());
                    break;
                case "Город нахождения отдела":
                    drawResult("cityDepartament", searchLabel.getText());
                    break;
                case "Разряд":
                    drawResult("runk", searchLabel.getText());
                    break;
            }
            //System.out.println(filterLabel.getValue());
        });



        /*addButton.setOnAction(actionEvent -> {
            if (stuffTable.getItems().size() != 0) {
                loaderAddWindow.setLocation(DataBaseController.class.getResource("add-function-window.fxml"));
                try {
                    showAddWindow(loaderAddWindow);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });*/

    }

    private void createSearchFilter() {
        ObservableList<String> filterData = FXCollections.observableArrayList("id", "Имя", "Телефон", "Менеджер", "Зарплата", "Номер подразделения", "Город нахождения отдела", "Разряд");
        filterLabel.setValue("id");
        filterLabel.getItems().addAll(filterData);

    }

    private void drawResult(String searchType, String searchValue) {
        ConnectionDB db = new ConnectionDB();
        ArrayList<Employeer> employeers = new ArrayList<>();

        if (db.open()) {
            employeers.addAll(db.search(searchType, searchValue));
            stuffTable.getItems().clear();
            stuffTable.refresh();
            setValueToTable(employeers);
        }
    }

    private void setValueToTable(ArrayList<Employeer> employeers) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Employeer, Integer>("id"));

        NameColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("name"));
        NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        NameColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue()));
        NameColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("name",e.getTableView().getItems().get(e.getTablePosition().getRow()).getName() , e.getNewValue()));

        phoneColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("phone"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhone(e.getNewValue()));
        phoneColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("phone",e.getTableView().getItems().get(e.getTablePosition().getRow()).getPhone() , e.getNewValue()));


        managerColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("manager"));
        managerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        managerColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setManager(e.getNewValue()));
        managerColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("manager",e.getTableView().getItems().get(e.getTablePosition().getRow()).getManager() , e.getNewValue()));


        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("salary"));
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        salaryColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setSalary(e.getNewValue()));
        salaryColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("salary",e.getTableView().getItems().get(e.getTablePosition().getRow()).getSalary() , e.getNewValue()));


        numSubdivisionColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("numberDepartament"));
        numSubdivisionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numSubdivisionColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setNumberDepartament(e.getNewValue()));
        numSubdivisionColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("numberDepartament",e.getTableView().getItems().get(e.getTablePosition().getRow()).getNumberDepartament() , e.getNewValue()));


        cityDepartamentColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("cityDepartament"));
        cityDepartamentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cityDepartamentColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setCityDepartament(e.getNewValue()));
        cityDepartamentColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("cityDepartament",e.getTableView().getItems().get(e.getTablePosition().getRow()).getCityDepartament() , e.getNewValue()));


        rankColumn.setCellValueFactory(new PropertyValueFactory<Employeer, String>("runk"));
        rankColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rankColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setRunk(e.getNewValue()));
        rankColumn.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValueToDB("runk",e.getTableView().getItems().get(e.getTablePosition().getRow()).getRunk() , e.getNewValue()));


        stuffTable.getItems().addAll(employeers);
    }

    void drawTable(ArrayList<Employeer> employeers) {
        ConnectionDB db = new ConnectionDB();

        if(db.open()) {
            employeers.clear();
            stuffTable.getItems().clear();
            stuffTable.refresh();
            employeers.addAll(db.select());
            setValueToTable(employeers);

        }
    }

    /*void showAddWindow(FXMLLoader loader) throws IOException {
        Scene scene;
        scene = new Scene(loader.load(), 600, 400);
        Stage stage = new Stage();
        Parent root  = loader.getRoot();
        stage.setTitle("Окно добавления");
        *//*scene = new Scene(root);*//*
        stage.setScene(scene);
        stage.show();
    }*/

    /*private void fillTable(int rows) {

    }*/

    /*private int countRows(ArrayList table) {
        int rows = 0;
        for(int i = 0; i< table.size(); i++) {
            try {
               if (Integer.parseInt((String) table.get(i)) < 1000000) {
                   rows++;
               }
            }
            catch (Exception e) {
                System.out.println();
            }
            System.out.println(table.get(i));
        }

        return rows;
    }*/

    /*private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {

        }
    }*/

   /* private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Просмотр Баз данных");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All DB", "*.*"),
                new FileChooser.ExtensionFilter("DB", "*.db")
        );
    }*/


}