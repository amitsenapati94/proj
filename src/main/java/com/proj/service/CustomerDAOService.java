package com.proj.service;

import com.proj.model.CustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomerDAOService  {

    private static final Logger log = LoggerFactory.getLogger(CustomerDAOService.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public CustomerResponse getCustomers() {

        //createData();


        List<Map<String,Object>> customerList = jdbcTemplate.queryForList("SELECT * FROM CUSTOMERS");

       log.info(String.valueOf(customerList.size()));

        CustomerResponse response = new CustomerResponse();
        response.setCustomerList(customerList);

        return response;



    }

    public CustomerResponse searchCustomer(Integer CustomerId) {
        //createData();
        String sql = "SELECT * FROM CUSTOMERS WHERE id=:id";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("id",CustomerId);


        List<Map<String,Object>> customerList = namedParameterJdbcTemplate.queryForList(sql,paramMap);
        CustomerResponse response = new CustomerResponse();
        response.setCustomerList(customerList);
        return response;

    }

    private void createData() {
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
    }
}
