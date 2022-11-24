package com.webapp.javaee;


public class Employeer {
    final int countProperties = 8;
    int id;
    String name;
    String phone;
    String manager;
    String salary;
    String numberDepartament;
    String cityDepartament;
    String runk;

    public void setValueToDB(String nameProperty, Object pastValue, Object value) {
        ConnectionDB db = new ConnectionDB();
        db.setEmployeerLabel(nameProperty, pastValue, value);
    }
    public int getCountProperties() {
        return this.countProperties;
    }

    public String getNumberDepartament() {
        return this.numberDepartament;
    }

    public void setNumberDepartament(String numberDepartament) {
        this.numberDepartament = numberDepartament;
    }

    public String getRunk() {
        return this.runk;
    }

    public void setRunk(String runk) {
        this.runk = runk;
    }

    public String getCityDepartament() {
        return this.cityDepartament;
    }

    public void setCityDepartament(String cityDepartament) {
        this.cityDepartament = cityDepartament;
    }

    public String getSalary() {
        return this.salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getManager() {
        return this.manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
