package com.example.restaurantbusiness;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HelloController implements Initializable {
    //region СозданиеЭлементов

    @FXML
    private TableView<Obj> tbView, tbView2;
    @FXML
    private TableColumn<Obj, String> tcAdditInfo, tcName, nameTB2, typeTB2, additInfoTB2;
    @FXML
    private TableColumn<Obj, Integer> tcPrise, tcSize, idTB2, sizeTB2, priseTB2;
    @FXML
    private Button editButElemVut,delButElemVut, swhitchMenuDess, swhitchMenuSoup, swhitchMenuSouse,
            swhitchMenuDrink, swhitchMenuHot, swhitchMenuSnaks, swhitchMenuMenu;
    @FXML
    private ComboBox ComBoxType, mvcpComBox, poductComBox, mvcpComBox1, poductComBox1;
    @FXML
    private Pane createElemPane, paneMenuMenu, menuRed, createMenu, editMenu;
    @FXML
    private TextField tfName, tfSize,tfPrise, tfProductDelMenu, tfSearch, tfName1, tfSize1,tfPrise1, tfNameExelFile;
    @FXML
    private TextArea tfAdditInfo, tfAdditInfo1;
    @FXML
    private Label lableResultSearch;
    ObservableList<Obj> listDess = FXCollections.observableArrayList();
    ObservableList<Obj> listSoup = FXCollections.observableArrayList();
    ObservableList<Obj> listSouse = FXCollections.observableArrayList();
    ObservableList<Obj> listHot = FXCollections.observableArrayList();
    ObservableList<Obj> listDrink = FXCollections.observableArrayList();
    ObservableList<Obj> listSnack = FXCollections.observableArrayList();
    ObservableList<OdjMenu> listMenu = FXCollections.observableArrayList();
    //endregion

    //region Подключение к Бд
    String url = "jdbc:mysql://213.167.218.132:3306/ResBus";

    String user = "Yoghurt";
    String password = "Kaka228";

    private Connection connection;

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLException: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к базе данных успешно установлено!");
            fillComboBox();
            loadElemAll();
            fillTable(listDess, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
            printSQLException(e);
        }
    }

    public void fillComboBox(){
        mvcpComBox.getItems().addAll("Десерты", "Супы", "Соусы", "Напитки", "Горячие блюда", "Закуски");

        ComBoxType.getItems().addAll("Десерты", "Супы", "Соусы", "Напитки", "Горячие блюда", "Закуски");
        ComBoxType.getSelectionModel().select("Десерты");

        mvcpComBox1.getItems().addAll("Десерты", "Супы", "Соусы", "Напитки", "Горячие блюда", "Закуски");

    }
    //endregion

    //region ПереключательМеню

    @FXML
    protected void swhitchMenuDessStyle() {
        StyleButton(swhitchMenuDess);
    }

    @FXML
    protected void swhitchMenuSoupStyle() {
        StyleButton(swhitchMenuSoup);
    }

    @FXML
    protected void swhitchMenuSouseStyle() {
        StyleButton(swhitchMenuSouse);
    }

    @FXML
    protected void swhitchMenuHotStyle() {
        StyleButton(swhitchMenuHot);
    }
    @FXML
    protected void swhitchMenuSnaksStyle() {
        StyleButton(swhitchMenuSnaks);
    }
    @FXML
    protected void swhitchMenuDrinkStyle() {
        StyleButton(swhitchMenuDrink);
    }
    @FXML
    protected void swhitchMenuMenuStyle() {
        StyleButton(swhitchMenuMenu);
    }

    public void StyleButton(Button nameBut) {
        nameBut.setOnMouseEntered(event -> {
            nameBut.setStyle("-fx-background-color: #75E5FF");
        });
        nameBut.setOnMouseExited(event -> nameBut.setStyle("-fx-background-color: #A8F0FF"));
    }

    @FXML
    protected void switchMenuButTableViewDess(){
        createElemPane.setVisible(true);
        paneMenuMenu.setVisible(false);
        tbView.setVisible(true);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        tcSize.setText("Вес/грамм");
        loadElemAll();
        fillTable(listDess, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
    }
    @FXML
    protected void switchMenuButTableViewSoup(){
        createElemPane.setVisible(true);
        paneMenuMenu.setVisible(false);
        tbView.setVisible(true);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        tcSize.setText("Вес/грамм");
        loadElemAll();
        fillTable(listSoup, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
    }
    @FXML
    protected void switchMenuButTableViewSouse(){
        createElemPane.setVisible(true);
        paneMenuMenu.setVisible(false);
        tbView.setVisible(true);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        tcSize.setText("Вес/грамм");
        loadElemAll();
        fillTable(listSouse, tbView, null, tcName,tcPrise,tcSize,null,tcAdditInfo);
    }
    @FXML
    protected void switchMenuButTableViewDrink(){
        createElemPane.setVisible(true);
        paneMenuMenu.setVisible(false);
        tbView.setVisible(true);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        loadElemAll();
        tcSize.setText("Объем/мл");
        fillTable(listDrink, tbView, null, tcName,tcPrise,tcSize,null,tcAdditInfo);
    }
    @FXML
    protected void switchMenuButTableViewHot(){
        createElemPane.setVisible(true);
        paneMenuMenu.setVisible(false);
        tbView.setVisible(true);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        tcSize.setText("Вес/грамм");
        loadElemAll();
        fillTable(listHot, tbView, null, tcName,tcPrise,tcSize,null,tcAdditInfo);
    }
    @FXML
    protected void switchMenuButTableViewSnack(){
        createElemPane.setVisible(true);
        paneMenuMenu.setVisible(false);
        tbView.setVisible(true);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        tcSize.setText("Вес/грамм");
        loadElemAll();
        fillTable(listSnack, tbView, null, tcName,tcPrise,tcSize,null,tcAdditInfo);
    }
    @FXML
    protected void switchMenuButTableViewMenu(){
        createElemPane.setVisible(false);
        paneMenuMenu.setVisible(true);
        tbView.setVisible(false);
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        listMenu.clear();
        loadElemAll();
        fillTable(listMenu, tbView2, idTB2, nameTB2,priseTB2,sizeTB2,typeTB2,additInfoTB2);
    }

    @FXML
    protected void switchMenuButCreate(){
        createMenu.setVisible(true);
        menuRed.setVisible(false);
        editMenu.setVisible(false);
    }
    @FXML
    protected void switchMenuButBack(){
        createMenu.setVisible(false);
        menuRed.setVisible(true);
        editMenu.setVisible(false);
    }
    @FXML
    protected void switchMenuButEdit(){
        createMenu.setVisible(false);
        menuRed.setVisible(false);
        editMenu.setVisible(true);
        editButElemVut.setVisible(true);
        delButElemVut.setVisible(false);
        tfName1.setEditable(true);
        tfPrise1.setEditable(true);
        tfSize1.setEditable(true);
        tfAdditInfo1.setEditable(true);
    }

    @FXML
    protected void switchMenuDutDel(){
        createMenu.setVisible(false);
        menuRed.setVisible(false);
        editMenu.setVisible(true);
        editButElemVut.setVisible(false);
        delButElemVut.setVisible(true);
        tfName1.setEditable(false);
        tfPrise1.setEditable(false);
        tfSize1.setEditable(false);
        tfAdditInfo1.setEditable(false);
    }
    //endregion
    @FXML
    protected void addElemBut(){
        String i = ComBoxType.getValue().toString();
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        if (i.equals("Десерты")){
            sendInsertQuary("Desserts");
            loadElemAll();
            fillTable(listDess, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Супы")) {
            sendInsertQuary("Soup");
            loadElemAll();
            fillTable(listSoup, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Соусы")) {
            sendInsertQuary("Souse");
            loadElemAll();
            fillTable(listSouse, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Напитки")) {
            sendInsertQuary("Drinks");
            loadElemAll();
            fillTable(listDrink, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        }else if(i.equals("Горячие блюда")){
            sendInsertQuary("HotDishes");
            loadElemAll();
            fillTable(listHot, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Закуски")) {
            sendInsertQuary("Snaks");
            loadElemAll();
            fillTable(listSnack, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        }
    }
    @FXML
    protected void editElemBut(){
        String i = mvcpComBox1.getValue().toString();
        listDess.clear();
        listSoup.clear();
        listSouse.clear();
        listDrink.clear();
        listHot.clear();
        listSnack.clear();
        if (i.equals("Десерты")){
            sendEditQuary();
            loadElemAll();
            fillTable(listDess, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Супы")) {
            sendEditQuary();
            loadElemAll();
            fillTable(listSoup, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Соусы")) {
            sendEditQuary();
            loadElemAll();
            fillTable(listSouse, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Напитки")) {
            sendEditQuary();
            loadElemAll();
            fillTable(listDrink, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        }else if(i.equals("Горячие блюда")){
            sendEditQuary();
            loadElemAll();
            fillTable(listHot, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        } else if (i.equals("Закуски")) {
            sendEditQuary();
            loadElemAll();
            fillTable(listSnack, tbView,null, tcName,tcPrise,tcSize,null,tcAdditInfo);
        }
    }
    @FXML
    protected void fillProductComboBox(){
        if(paneMenuMenu.isVisible()){
            fillProductComboBoxUp(mvcpComBox, poductComBox);
        }else {
            fillProductComboBoxUp(mvcpComBox1, poductComBox1);
        }
    }
    @FXML
    protected void fillTF(){
        try {
            String table = mvcpComBox1.getValue().toString();
            String poduct;
            if (table.equals("Десерты")) {
                for (int i = 0; i < listDess.size(); i++) {
                    poduct = listDess.get(i).name;
                    if (poduct.equals(poductComBox1.getValue().toString())) {
                        tfName1.setText(poduct);
                        tfSize1.setText(String.valueOf(listDess.get(i).size));
                        tfPrise1.setText(String.valueOf(listDess.get(i).prise));
                        tfAdditInfo1.setText(listDess.get(i).AdditInfo);
                        break;
                    }
                }
            } else if (table.equals("Супы")) {
                for (int i = 0; i < listSoup.size(); i++) {
                    poduct = listSoup.get(i).name;
                    if (poduct.equals(poductComBox1.getValue().toString())) {
                        tfName1.setText(poduct);
                        tfSize1.setText(String.valueOf(listSoup.get(i).size));
                        tfPrise1.setText(String.valueOf(listSoup.get(i).prise));
                        tfAdditInfo1.setText(listSoup.get(i).AdditInfo);
                        break;
                    }
                }
            } else if (table.equals("Соусы")) {
                for (int i = 0; i < listSouse.size(); i++) {
                    poduct = listSouse.get(i).name;
                    if (poduct.equals(poductComBox1.getValue().toString())) {
                        tfName1.setText(poduct);
                        tfSize1.setText(String.valueOf(listSouse.get(i).size));
                        tfPrise1.setText(String.valueOf(listSouse.get(i).prise));
                        tfAdditInfo1.setText(listSouse.get(i).AdditInfo);
                        break;
                    }
                }
            } else if (table.equals("Напитки")) {
                for (int i = 0; i < listDrink.size(); i++) {
                    poduct = listDrink.get(i).name;
                    if (poduct.equals(poductComBox1.getValue().toString())) {
                        tfName1.setText(poduct);
                        tfSize1.setText(String.valueOf(listDrink.get(i).size));
                        tfPrise1.setText(String.valueOf(listDrink.get(i).prise));
                        tfAdditInfo1.setText(listDrink.get(i).AdditInfo);
                        break;
                    }
                }
            } else if (table.equals("Горячие блюда")) {
                for (int i = 0; i < listHot.size(); i++) {
                    poduct = listHot.get(i).name;
                    if (poduct.equals(poductComBox1.getValue().toString())) {
                        tfName1.setText(poduct);
                        tfSize1.setText(String.valueOf(listHot.get(i).size));
                        tfPrise1.setText(String.valueOf(listHot.get(i).prise));
                        tfAdditInfo1.setText(listHot.get(i).AdditInfo);
                        break;
                    }
                }
            } else if (table.equals("Закуски")) {
                for (int i = 0; i < listSnack.size(); i++) {
                    poduct = listSnack.get(i).name;
                    if (poduct.equals(poductComBox1.getValue().toString())) {
                        tfName1.setText(poduct);
                        tfSize1.setText(String.valueOf(listSnack.get(i).size));
                        tfPrise1.setText(String.valueOf(listSnack.get(i).prise));
                        tfAdditInfo1.setText(listSnack.get(i).AdditInfo);
                        break;
                    }
                }
            }
        } catch (Exception e) { }
    }
    public void fillProductComboBoxUp(ComboBox cb, ComboBox prodComBox){
        String table = cb.getValue().toString();
        String poduct;
        if (table.equals("Десерты")) {
            prodComBox.getItems().clear();
            for (int i = 0; i < listDess.size(); i++) {
                poduct = listDess.get(i).name;
                prodComBox.getItems().add(poduct);
            }
        }else if (table.equals("Супы")) {
            prodComBox.getItems().clear();
            for (int i = 0; i < listSoup.size(); i++) {
                poduct = listSoup.get(i).name;
                prodComBox.getItems().add(poduct);
            }
        }else if (table.equals("Соусы")) {
            prodComBox.getItems().clear();
            for (int i = 0; i < listSouse.size(); i++) {
                poduct = listSouse.get(i).name;
                prodComBox.getItems().add(poduct);
            }
        }else if (table.equals("Напитки")) {
            prodComBox.getItems().clear();
            for (int i = 0; i < listDrink.size(); i++) {
                poduct = listDrink.get(i).name;
                prodComBox.getItems().add(poduct);
            }
        }else if (table.equals("Горячие блюда")) {
            prodComBox.getItems().clear();
            for (int i = 0; i < listHot.size(); i++) {
                poduct = listHot.get(i).name;
                prodComBox.getItems().add(poduct);
            }
        }else if (table.equals("Закуски")) {
            prodComBox.getItems().clear();
            for (int i = 0; i < listSnack.size(); i++) {
                poduct = listSnack.get(i).name;
                prodComBox.getItems().add(poduct);
            }
        }
    }
    public void sendInsertQuary(String tableName){
        String name = null;
        int size = 0;
        int prise = 0;
        String type = tableName;
        String additInfo = null;

        try {
            name = tfName.getText();
            size = Integer.parseInt(tfSize.getText());
            prise = Integer.parseInt(tfPrise.getText());
            additInfo = tfAdditInfo.getText();
        } catch (Exception e) {
            System.out.println("Ошибка в записи");
        }

        try {
            String quary = "insert " + tableName + " values (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, size);
            preparedStatement.setInt(3, prise);
            preparedStatement.setString(4, additInfo);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно");
        } catch (SQLException e) {
            System.out.println("Неуспешно");
            printSQLException(e);
        }
    }
    public void fillTable(ObservableList obslist,TableView tableView, TableColumn tableColumnId,TableColumn tableColumnName, TableColumn tableColumnPrise,TableColumn tableColumnSize,TableColumn tableColumnType,TableColumn tableColumnInfo){
        try {

            try { tableColumnId.setCellValueFactory(new PropertyValueFactory<Obj, String>("Id")); }
            catch (Exception e) { }

            tableColumnName.setCellValueFactory(new PropertyValueFactory<Obj, String>("Name"));
            tableColumnPrise.setCellValueFactory(new PropertyValueFactory<Obj, Integer>("Prise"));
            tableColumnSize.setCellValueFactory(new PropertyValueFactory<Obj, Integer>("Size"));

            try { tableColumnType.setCellValueFactory(new PropertyValueFactory<Obj, String>("Type")); }
            catch (Exception e) { }

            tableColumnInfo.setCellValueFactory(new PropertyValueFactory<Obj, String>("AdditInfo"));

            tableView.setItems(obslist);

            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        } catch (Exception e) {
            System.out.println("Ошибка");
        }

    }
    public void loadElemAll(){

        loadElem("Snaks", listSnack);
        loadElem("Soup", listSoup);
        loadElem("Souse", listSouse);
        loadElem("Desserts", listDess);
        loadElem("Drinks", listDrink);
        loadElem("HotDishes", listHot);
        String query = "SELECT * FROM Menu";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                listMenu.add(new OdjMenu(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6)));
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public void loadElem(String tableName, List list){
        String query = "SELECT * FROM "+ tableName;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Obj(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getString(4)));
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    @FXML
    protected void addToMenuFiltrBut(){
        //region addToListMenuElements
        String table = mvcpComBox.getValue().toString();
        String poduct;
        int num = listMenu.size() + 1;
        if (table.equals("Десерты")) {
            for (int i = 0; i < listDess.size(); i++) {
                poduct = listDess.get(i).name;
                if(poduct.equals(poductComBox.getValue().toString())){
                    listMenu.add(new OdjMenu(num, poduct, listDess.get(i).size,listDess.get(i).prise,"Десерты",listDess.get(i).AdditInfo));
                    break;
                }
            }
        }else if (table.equals("Супы")) {
            for (int i = 0; i < listSoup.size(); i++) {
                poduct = listSoup.get(i).name;
                if(poduct.equals(poductComBox.getValue().toString())){
                    listMenu.add(new OdjMenu(num, poduct, listSoup.get(i).size,listSoup.get(i).prise,"Супы",listSoup.get(i).AdditInfo));
                    break;
                }
            }
        }else if (table.equals("Соусы")) {
            for (int i = 0; i < listSouse.size(); i++) {
                poduct = listSouse.get(i).name;
                if(poduct.equals(poductComBox.getValue().toString())){
                    listMenu.add(new OdjMenu(num, poduct, listSouse.get(i).size,listSouse.get(i).prise,"Соусы",listSouse.get(i).AdditInfo));
                    break;
                }
            }
        }else if (table.equals("Напитки")) {
            for (int i = 0; i < listDrink.size(); i++) {
                poduct = listDrink.get(i).name;
                if(poduct.equals(poductComBox.getValue().toString())){
                    listMenu.add(new OdjMenu(num, poduct, listDrink.get(i).size,listDrink.get(i).prise,"Напитки",listDrink.get(i).AdditInfo));
                    break;
                }
            }
        }else if (table.equals("Горячие блюда")) {
            for (int i = 0; i < listHot.size(); i++) {
                poduct = listHot.get(i).name;
                if(poduct.equals(poductComBox.getValue().toString())){
                    listMenu.add(new OdjMenu(num, poduct, listHot.get(i).size,listHot.get(i).prise,"Горячие блюда",listHot.get(i).AdditInfo));
                    break;
                }
            }
        }else if (table.equals("Закуски")) {
            for (int i = 0; i < listSnack.size(); i++) {
                poduct = listSnack.get(i).name;
                if(poduct.equals(poductComBox.getValue().toString())){
                    listMenu.add(new OdjMenu(num, poduct, listSnack.get(i).size,listSnack.get(i).prise,"Закуски",listSnack.get(i).AdditInfo));
                    break;
                }
            }
        }
        //endregion

        fillTable(listMenu, tbView2, idTB2, nameTB2,priseTB2,sizeTB2,typeTB2,additInfoTB2);

    }
    @FXML
    protected void delToMenuBut(){

        int idTime = Integer.parseInt(tfProductDelMenu.getText());
        listMenu.remove(idTime - 1);
        while (idTime < listMenu.size() + 1){
            int idNew = idTime - 1;
            listMenu.set(idTime - 1, new OdjMenu(idTime, listMenu.get(idNew).name, listMenu.get(idNew).size,listMenu.get(idNew).prise,listMenu.get(idNew).type,listMenu.get(idNew).AdditInfo));
            idTime++;
        }

        fillTable(listMenu, tbView2, idTB2, nameTB2,priseTB2,sizeTB2,typeTB2,additInfoTB2);

    }
    @FXML
    protected void clearToMenuBut(){

        listMenu.clear();
        fillTable(listMenu, tbView2, idTB2, nameTB2,priseTB2,sizeTB2,typeTB2,additInfoTB2);

    }
    @FXML
    protected void searchToTable(){
        String find = tfSearch.getText();
        String name = null;
        try {
            String quary = "SELECT nameSoup FROM Soup WHERE nameSoup LIKE \""+ find +"%\";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()){
                name = resultSet.getString(1);
            }
            lableResultSearch.setText(name);
        } catch (SQLException e) { }
        try {
            String quary = "SELECT nameSnaks FROM Snaks WHERE nameSnaks LIKE \""+ find +"%\";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()){
                name = resultSet.getString(1);
            }
            lableResultSearch.setText(name);
        } catch (SQLException e) { }

        try {
            String quary = "SELECT nameSouse FROM Souse WHERE nameSouse LIKE \""+ find +"%\";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()){
                name = resultSet.getString(1);
            }
            lableResultSearch.setText(name);
        } catch (SQLException e) { }

        try {
            String quary = "SELECT nameDesserts FROM Desserts WHERE nameDesserts LIKE \""+ find +"%\";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()){
                name = resultSet.getString(1);
            }
            lableResultSearch.setText(name);
        } catch (SQLException e) { }

        try {
            String quary = "SELECT nameDrinks FROM Drinks WHERE nameDrinks LIKE \""+ find +"%\";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()){
                name = resultSet.getString(1);
            }
            lableResultSearch.setText(name);
        } catch (SQLException e) { }

        try {
            String quary = "SELECT nameHotDishes FROM HotDishes WHERE nameHotDishes LIKE \""+ find +"%\";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(quary);
            while(resultSet.next()){
                name = resultSet.getString(1);
            }
            lableResultSearch.setText(name);
        } catch (SQLException e) { }

    }
    @FXML
    protected void FillsearchToTable(){
        String findRes = lableResultSearch.getText();
        String name = null;
        int num = listMenu.size() + 1;

        try {
            for (int i = 0; i < listDess.size(); i++) {
                name = listDess.get(i).name;
                if (name.equals(findRes)) {
                    listMenu.add(new OdjMenu(num, findRes, listDess.get(i).size, listDess.get(i).prise, "Десерты", listDess.get(i).AdditInfo));
                    break;
                }
            }
        } catch (Exception e) { }

        try {
            for (int i = 0; i < listSoup.size(); i++) {
                name = listSoup.get(i).name;
                if (name.equals(findRes)) {
                    listMenu.add(new OdjMenu(num, findRes, listSoup.get(i).size, listSoup.get(i).prise, "Супы", listSoup.get(i).AdditInfo));
                    break;
                }
            }
        } catch (Exception e) { }

        try {
            for (int i = 0; i < listSouse.size(); i++) {
                name = listSouse.get(i).name;
                if (name.equals(findRes)) {
                    listMenu.add(new OdjMenu(num, findRes, listSouse.get(i).size, listSouse.get(i).prise, "Соусы", listSouse.get(i).AdditInfo));
                    break;
                }
            }
        } catch (Exception e) { }

        try {
            for (int i = 0; i < listDrink.size(); i++) {
                name = listDrink.get(i).name;
                if (name.equals(findRes)) {
                    listMenu.add(new OdjMenu(num, findRes, listDrink.get(i).size, listDrink.get(i).prise, "Напитки", listDrink.get(i).AdditInfo));
                    break;
                }
            }
        } catch (Exception e) { }

        try {
            for (int i = 0; i < listHot.size(); i++) {
                name = listHot.get(i).name;
                if (name.equals(findRes)) {
                    listMenu.add(new OdjMenu(num, findRes, listHot.get(i).size, listHot.get(i).prise, "Горячие блюда", listHot.get(i).AdditInfo));
                    break;
                }
            }
        } catch (Exception e) { }

        try {
            for (int i = 0; i < listSnack.size(); i++) {
                name = listSnack.get(i).name;
                if (name.equals(findRes)) {
                    listMenu.add(new OdjMenu(num, findRes, listSnack.get(i).size, listSnack.get(i).prise, "Закуски", listSnack.get(i).AdditInfo));
                    break;
                }
            }
        } catch (Exception e) { }

        fillTable(listMenu, tbView2, idTB2, nameTB2,priseTB2,sizeTB2,typeTB2,additInfoTB2);

    }
    public void sendEditQuary()     {
        String name = null;
        int size = 0;
        int prise = 0;
        String type = mvcpComBox1.getValue().toString();
        String additInfo = null;
        String lastName = poductComBox1.getValue().toString();

        try {
            name = tfName1.getText();
            size = Integer.parseInt(tfSize1.getText());
            prise = Integer.parseInt(tfPrise1.getText());
            additInfo = tfAdditInfo1.getText();
        } catch (Exception e) {
            System.out.println("Ошибка в записи");
        }
        String quary = "";
        try {

            if (type.equals("Десерты")) {
                quary = "UPDATE Desserts SET nameDesserts = ?, sizeDesserts = ?, priseDesserts = ?, AddiInfDesserts = ? WHERE nameDesserts = '" + lastName + "'";
            } else if (type.equals("Супы")){
                quary = "UPDATE Soup SET nameSoup = ?, sizeSoup = ?, priseSoup = ?, AddiInfSoup = ? WHERE nameSoup = '" + lastName + "'";
            }else if (type.equals("Соусы")){
                quary = "UPDATE Souse SET nameSouse = ?, sizeSouse = ?, priseSouse = ?, AddiInfSouse = ? WHERE nameSouse = '" + lastName + "'";
            }else if (type.equals("Напитки")){
                quary = "UPDATE Soup SET nameDrinks = ?, sizeDrinks = ?, priseDrinks = ?, AddiInfDrinks = ? WHERE nameDrinks = '" + lastName + "'";
            }else if (type.equals("Горячие блюда")){
                quary = "UPDATE HotDishes SET nameHotDishes = ?, sizeHotDishes = ?, priseHotDishes = ?, AddiInfHotDishes = ? WHERE nameHotDishes = '" + lastName + "'";
            }else if (type.equals("Закуски")){
                quary = "UPDATE Snaks SET nameSnaks = ?, sizeSnaks = ?, priseSnaks = ?, AddiInfSnacs = ? WHERE nameSnaks = '" + lastName + "'";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, size);
            preparedStatement.setInt(3, prise);
            preparedStatement.setString(4, additInfo);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно");
        } catch (SQLException e) {
            System.out.println("Неуспешно");
            printSQLException(e);
        }
    }
    @FXML
    protected void  fillMenuTableBut(){
        try {
            String quary = "DELETE FROM Menu;";
            PreparedStatement preparedStatement = connection.prepareStatement(quary);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно");
        } catch (SQLException e) {
            System.out.println("Неуспешно");
            printSQLException(e);
        }

        int id = 0;
        String name = null;
        int size = 0;
        int prise = 0;
        String type = null;
        String additInfo = null;

        for (int i = 0; i < listMenu.size(); i ++) {
            try {
                id = listMenu.get(i).id;
                name = listMenu.get(i).name;
                size = listMenu.get(i).prise;
                prise = listMenu.get(i).size;
                type = listMenu.get(i).type;
                additInfo = listMenu.get(i).AdditInfo;
            } catch (Exception e) {
                System.out.println("Ошибка в записи");
            }

            try {
                String quary = "insert Menu values (?,?,?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(quary);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, size);
                preparedStatement.setInt(4, prise);
                preparedStatement.setString(5, type);
                preparedStatement.setString(6, additInfo);

                int rows = preparedStatement.executeUpdate();
                System.out.println("Успешно");
            } catch (SQLException e) {
                System.out.println("Неуспешно");
                printSQLException(e);
            }
        }

        fillExeclFile();

    }
    public void fillExeclFile(){
        String nameMenu = tfNameExelFile.getText();
        if (nameMenu.equals(""))
        {
            nameMenu = "Меню";
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        String direct = directoryChooser.showDialog(null).toString();
        String path = direct + "\\" + nameMenu + ".xls";


        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(new FileInputStream("../RestaurantBusiness/src/main/resources/prim.xls"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = wb.getSheet("Лист1");
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();

        font.setFontName("Candara");
        font.setColor(IndexedColors.SKY_BLUE.getIndex());
        font.setFontHeightInPoints((short) 28);
        style.setFont(font);

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(3);
        cell.setCellStyle(style);

        cell.setCellValue("Меню");


        CellStyle styleType = wb.createCellStyle();
        Font fontType = wb.createFont();
        fontType.setFontName("Candara");
        fontType.setColor(IndexedColors.SKY_BLUE.getIndex());
        fontType.setFontHeightInPoints((short) 20);
        styleType.setFont(fontType);

        CellStyle styleName = wb.createCellStyle();
        Font fontName = wb.createFont();
        fontName.setFontName("Candara");
        fontName.setColor(IndexedColors.SKY_BLUE.getIndex());
        fontName.setFontHeightInPoints((short) 14);
        styleName.setFont(fontName);



        int rowExcelType = 1;
        int cellExcelType = 1;

        int rowExcelName = 2;
        int cellExcelName = 1;

        int rowExcelPrise =2;
        int cellExcelPrise = 7;



        for(int i = 0; i<listMenu.size();i++){

            if(listMenu.get(i).type.equals("Десерты")){
                Row row1 = sheet.createRow(rowExcelType);
                Cell cell1 = row1.createCell(cellExcelType);
                cell1.setCellValue(listMenu.get(i).type);
                cell1.setCellStyle(styleType);
                while (i < listMenu.size())
                {
                    if (!listMenu.get(i).type.equals("Десерты"))
                    {
                        i--;
                        break;
                    }
                    Row row2 = sheet.createRow(rowExcelName);
                    Cell cell2 = row2.createCell(cellExcelName);
                    cell2.setCellValue(listMenu.get(i).name + "| " +listMenu.get(i).size + "гр ");
                    cell2.setCellStyle(styleName);


                    Cell cell3 = row2.createCell(cellExcelPrise);
                    cell3.setCellStyle(styleName);
                    cell3.setCellValue(listMenu.get(i).prise+"р");
                    rowExcelPrise++;
                    rowExcelName++;
                    rowExcelType++;
                    i++;
                }
                rowExcelPrise++;
                rowExcelName++;
                rowExcelType++;
            }else if(listMenu.get(i).type.equals("Супы")){
                Row row1 = sheet.createRow(rowExcelType);
                Cell cell1 = row1.createCell(cellExcelType);
                cell1.setCellValue(listMenu.get(i).type);
                cell1.setCellStyle(styleType);
                while (i < listMenu.size())
                {
                    if (!listMenu.get(i).type.equals("Супы"))
                    {
                        i--;
                        break;
                    }
                    Row row2 = sheet.createRow(rowExcelName);
                    Cell cell2 = row2.createCell(cellExcelName);
                    cell2.setCellValue(listMenu.get(i).name + "| " +listMenu.get(i).size + "гр ");
                    cell2.setCellStyle(styleName);


                    Cell cell3 = row2.createCell(cellExcelPrise);
                    cell3.setCellStyle(styleName);
                    cell3.setCellValue(listMenu.get(i).prise+"р");
                    rowExcelPrise++;
                    rowExcelName++;
                    rowExcelType++;
                    i++;
                }
                rowExcelPrise++;
                rowExcelName++;
                rowExcelType++;
            }else if(listMenu.get(i).type.equals("Соусы")){
                Row row1 = sheet.createRow(rowExcelType);
                Cell cell1 = row1.createCell(cellExcelType);
                cell1.setCellValue(listMenu.get(i).type);
                cell1.setCellStyle(styleType);
                while (i < listMenu.size())
                {
                    if (!listMenu.get(i).type.equals("Соусы"))
                    {
                        i--;
                        break;
                    }
                    Row row2 = sheet.createRow(rowExcelName);
                    Cell cell2 = row2.createCell(cellExcelName);
                    cell2.setCellValue(listMenu.get(i).name + "| " +listMenu.get(i).size + "гр ");
                    cell2.setCellStyle(styleName);


                    Cell cell3 = row2.createCell(cellExcelPrise);
                    cell3.setCellStyle(styleName);
                    cell3.setCellValue(listMenu.get(i).prise+"р");
                    rowExcelPrise++;
                    rowExcelName++;
                    rowExcelType++;
                    i++;
                }
                rowExcelPrise++;
                rowExcelName++;
                rowExcelType++;
            }else if(listMenu.get(i).type.equals("Напитки")){
                Row row1 = sheet.createRow(rowExcelType);
                Cell cell1 = row1.createCell(cellExcelType);
                cell1.setCellValue(listMenu.get(i).type);
                cell1.setCellStyle(styleType);
                while (i < listMenu.size())
                {
                    if (!listMenu.get(i).type.equals("Напитки"))
                    {
                        i--;
                        break;
                    }
                    Row row2 = sheet.createRow(rowExcelName);
                    Cell cell2 = row2.createCell(cellExcelName);
                    cell2.setCellValue(listMenu.get(i).name + "| " +listMenu.get(i).size + "гр ");
                    cell2.setCellStyle(styleName);


                    Cell cell3 = row2.createCell(cellExcelPrise);
                    cell3.setCellStyle(styleName);
                    cell3.setCellValue(listMenu.get(i).prise+"р");
                    rowExcelPrise++;
                    rowExcelName++;
                    rowExcelType++;
                    i++;
                }
                rowExcelPrise++;
                rowExcelName++;
                rowExcelType++;
            }else if(listMenu.get(i).type.equals("Горячие блюда")){
                Row row1 = sheet.createRow(rowExcelType);
                Cell cell1 = row1.createCell(cellExcelType);
                cell1.setCellValue(listMenu.get(i).type);
                cell1.setCellStyle(styleType);
                while (i < listMenu.size())
                {
                    if (!listMenu.get(i).type.equals("Горячие блюда"))
                    {
                        i--;
                        break;
                    }
                    Row row2 = sheet.createRow(rowExcelName);
                    Cell cell2 = row2.createCell(cellExcelName);
                    cell2.setCellValue(listMenu.get(i).name + "| " +listMenu.get(i).size + "гр ");
                    cell2.setCellStyle(styleName);


                    Cell cell3 = row2.createCell(cellExcelPrise);
                    cell3.setCellStyle(styleName);
                    cell3.setCellValue(listMenu.get(i).prise+"р");
                    rowExcelPrise++;
                    rowExcelName++;
                    rowExcelType++;
                    i++;
                }
                rowExcelPrise++;
                rowExcelName++;
                rowExcelType++;
            }else if(listMenu.get(i).type.equals("Закуски")){
                Row row1 = sheet.createRow(rowExcelType);
                Cell cell1 = row1.createCell(cellExcelType);
                cell1.setCellValue(listMenu.get(i).type);
                cell1.setCellStyle(styleType);
                while (i < listMenu.size())
                {
                    if (!listMenu.get(i).type.equals("Закуски"))
                    {
                        i--;
                        break;
                    }
                    Row row2 = sheet.createRow(rowExcelName);
                    Cell cell2 = row2.createCell(cellExcelName);
                    cell2.setCellValue(listMenu.get(i).name + "| " +listMenu.get(i).size + "гр ");
                    cell2.setCellStyle(styleName);


                    Cell cell3 = row2.createCell(cellExcelPrise);
                    cell3.setCellStyle(styleName);
                    cell3.setCellValue(listMenu.get(i).prise+"р");
                    rowExcelPrise++;
                    rowExcelName++;
                    rowExcelType++;
                    i++;
                }
                rowExcelPrise++;
                rowExcelName++;
                rowExcelType++;
            }

        }



        try {
            FileOutputStream fos = new FileOutputStream(path);
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) { }
        catch (IOException e) { }
    }
    @FXML
    protected void delElemBut(){
        String lastName = poductComBox1.getValue().toString();

        PreparedStatement preparedStatement = null;

        try {
            String quary;
            if (mvcpComBox1.getValue().equals("Десерты")) {
                quary = "DELETE FROM Desserts WHERE nameDesserts = '" + lastName + "'";
                preparedStatement = connection.prepareStatement(quary);
            } else if (mvcpComBox1.getValue().equals("Супы")){
                quary = "DELETE FROM Soup WHERE nameSoup = '" + lastName + "'";
                preparedStatement = connection.prepareStatement(quary);
            }else if (mvcpComBox1.getValue().equals("Соусы")){
                quary = "DELETE FROM Souse WHERE nameSouse = '" + lastName + "'";
                preparedStatement = connection.prepareStatement(quary);
            }else if (mvcpComBox1.getValue().equals("Напитки")){
                quary = "DELETE FROM Drinks WHERE nameDrinks = '" + lastName + "'";
                preparedStatement = connection.prepareStatement(quary);
            }else if (mvcpComBox1.getValue().equals("Горячие блюда")){
                quary = "DELETE FROM Snaks WHERE nameHotDishes = '" + lastName + "'";
                preparedStatement = connection.prepareStatement(quary);
            }else if (mvcpComBox1.getValue().equals("Закуски")){
                quary = "DELETE FROM HotDishes WHERE nameSnaks = '" + lastName + "'";
                preparedStatement = connection.prepareStatement(quary);
            }


            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно");
        } catch (SQLException e) {
            System.out.println("Неуспешно");
            printSQLException(e);
        }

    }
}