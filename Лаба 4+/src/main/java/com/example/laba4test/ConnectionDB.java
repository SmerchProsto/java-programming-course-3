package com.example.laba4test;

import javafx.scene.Node;
import javafx.scene.control.TextField;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ConnectionDB {
    Connection con;
    // Statement statement = con.createStatement();

    Employeer viewOfEmployeer = new Employeer();
    int countColumns = viewOfEmployeer.getCountProperties();


    public boolean open() {
        try{
            establishConnection();
            System.out.println("Connected!!");
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public Connection establishConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Games and Program Files\\SQLite\\stuff.db");
            return con;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Employeer> select() {
        ArrayList<Employeer> employeers = new ArrayList<>();
        int i = 0;
        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM stuff ORDER BY id";
            employeers.addAll(setEmployeers(i, statement, query));

            //System.out.println(employeers);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return employeers;

    }
    public void close() throws SQLException {
        con.close();
    }

    public void addEmployeer(ArrayList<Node> formData) {
        try {
            con = establishConnection();
            Statement statement = con.createStatement();
            String query = "SELECT id FROM stuff";

            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd;



            int lastId = 0;
            while (rs.next()) {
                lastId = rs.getInt("id");
            }
            lastId++;

            query = "SELECT * FROM stuff ORDER BY id";
            rs = statement.executeQuery(query);
            rsmd = rs.getMetaData();

            List<String> nameColumns = new ArrayList<String>();

            for (int i = 1, j = 0; i < countColumns + 1; i++, j++) {
                nameColumns.add(rsmd.getColumnName(i));
                //System.out.println(nameColumns.get(j));
            }

            String labelText = null;

            int indexColumn = 0;

            for (int i = 0, j = 0; i < nameColumns.size(); i++, j++) {
                labelText = ((TextField) formData.get(j)).getText();


                if (i == 0 && j == 0)
                {
                    query = "INSERT INTO stuff (" + nameColumns.get(i) + ") " +
                            "VALUES ('" + lastId + "')";
                    statement.executeUpdate(query);
                    indexColumn = lastId;
                    lastId++;
                    j=-1;

                } else {
                    /*query = "INSERT INTO stuff (" + nameColumns.get(i) + ") " +
                            "VALUES ('" + labelText + "') " +
                            "FROM stuff " +
                            "WHERE id = " + lastId;*/
                    query = "update stuff set " +  nameColumns.get(i) + " = " + "'" + labelText + "'" + " where id = " + indexColumn + " and " + nameColumns.get(i) +  " is null";
                    statement.executeUpdate(query);
                }


//                ((TextField) formData.get(i)).getText()
            }



        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployeer(int idEmployeer) {
        try {
            Connection con = establishConnection();
            Statement statement = con.createStatement();
            String query = "DELETE FROM stuff WHERE id = " + idEmployeer;
            statement.executeUpdate(query);
            //updateIDS(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Employeer> search(String searchType, String searchValue) {
        ArrayList<Employeer> employeers = new ArrayList<>();
        int i = 0;
        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM stuff WHERE " + searchType + " = " + "'" + searchValue + "'" + " ORDER BY id";
            employeers.addAll(setEmployeers(i, statement, query));

            //System.out.println(employeers);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return employeers;
    }

    private ArrayList<Employeer> setEmployeers(int i, Statement statement, String query) throws SQLException {
        ArrayList<Employeer> employeers = new ArrayList<>();
        ResultSet rs = statement.executeQuery(query);


        while (rs.next()) {
            employeers.add(new Employeer());
            employeers.get(i).setId(Integer.parseInt(rs.getString("id")));
            employeers.get(i).setName(rs.getString("name"));
            employeers.get(i).setPhone(rs.getString("phone"));
            employeers.get(i).setManager(rs.getString("manager"));
            employeers.get(i).setSalary(rs.getString("salary"));
            employeers.get(i).setNumberDepartament(rs.getString("numberDepartament"));
            employeers.get(i).setCityDepartament(rs.getString("cityDepartament"));
            employeers.get(i).setRunk(rs.getString("runk"));
            i++;
        }
        return employeers;
    }

    public void setEmployeerLabel(String nameProperty, Object pastValue, Object newValue) {


        try {
            con = establishConnection();
            Statement statement = con.createStatement();
            String query = "update stuff set " +  nameProperty + " = " + "'" + newValue + "'" + " where " + nameProperty + " = " + "'" + pastValue + "'";
            statement.executeUpdate(query);

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("not");
        }


    }

    /*private void updateIDS(Connection con) {
        try {
            Statement statement = con.createStatement();

            String query = "SELECT * FROM stuff";
            ResultSet rs = statement.executeQuery(query);

            int i = 1;
            int failIndexDB = 0;
            //List<Integer> failIndicesDB = new ArrayList<Integer>();
            while (rs.next()) {
                failIndexDB = rs.getInt("id");
                //System.out.println(rs.getString("id"));
                if (failIndexDB != i)
                {
                    System.out.println("Не равно");

                    query = "update stuff set id " + " = " + "'" + i + "'" + " where id = " + failIndexDB;
                    statement.executeUpdate(query);

                }
                i++;
            }


            *//*int i = 1;
            while (rs.next()) {
                System.out.println(rs.getString(i));
                *//**//*if (rs.getInt(i) != i) {
                    query = "update stuff set id = " + i + " where id = " + rs.getInt(i);
                    statement.executeUpdate(query);
                }*//**//*
                i++;
            }*//*
            //query = "update stuff set id = " + i + " where id = " + i;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
