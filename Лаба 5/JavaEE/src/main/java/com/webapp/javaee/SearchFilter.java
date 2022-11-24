package com.webapp.javaee;

import java.util.ArrayList;

public class SearchFilter {
    public ArrayList<Employeer> getResult(ArrayList<Employeer> employeers, String dataFilter, String data) {
        ArrayList<Employeer> searchedEmployeers = new ArrayList<Employeer>();
        try {
            if ((dataFilter != null && data != null)){
                switch (dataFilter) {
                    case "number":
                        for (int i = 0; i < employeers.size(); i++) {
                            if (employeers.get(i).getPhone().equalsIgnoreCase(data)) {
                                searchedEmployeers.add(employeers.get(i));
                            }
                        }
                        break;
                    case "name":
                        for (int i = 0; i < employeers.size(); i++) {
                            if (employeers.get(i).getName().equalsIgnoreCase(data)) {
                                searchedEmployeers.add(employeers.get(i));
                            }
                        }
                        break;
                    case "information":
                        searchedEmployeers.addAll(employeers);
                        break;
                }
            }
        } catch (Exception ex) {
            searchedEmployeers.clear();
        }
        return searchedEmployeers;
    }
}
