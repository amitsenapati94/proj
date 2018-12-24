package com.proj.model;

import java.util.List;
import java.util.Map;

public class CustomerResponse {


    private List<Map<String,Object>> customerList;

    public List<Map<String, Object>> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Map<String, Object>> customerList) {
        this.customerList = customerList;
    }



}
