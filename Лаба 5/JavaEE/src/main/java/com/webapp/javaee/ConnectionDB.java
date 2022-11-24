package com.webapp.javaee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
* Server works by Tomcat technology
* Database will be work if file stuff.db will be moved to homedirectoryTomcat\DB\stuff.db
* Tom reads all files with datasource him directory
* */

public class ConnectionDB {
    Connection con;

    Employeer viewOfEmployeer = new Employeer();
    int countColumns = viewOfEmployeer.getCountProperties();

    public Connection establishConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:DB\\stuff.db"); // path reads from directory TomCat
            System.out.println("connected to stuff.db");
            return con;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Employeer> select() {
        ArrayList<Employeer> employeers = new ArrayList<>();
        con = establishConnection();
        int i = 0;
        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM stuff ORDER BY id";
            employeers.addAll(setEmployeers(i, statement, query));
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
            //ex.PrintStackTrace();
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



            i = 1;
            while (rs.next()) {
                System.out.println(rs.getString(i));


                if (rs.getInt(i) != i) {
                    query = "update stuff set id = " + i + " where id = " + rs.getInt(i);
                    statement.executeUpdate(query);
                }

                i++;
            }
            //query = "update stuff set id = " + i + " where id = " + i;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

}
