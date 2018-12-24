package com.proj.app;


import com.proj.model.CustomerResponse;
import com.proj.service.CustomerDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proj")
public class ProjController {

    @Autowired
    CustomerDAOService customerDAOService;


    @GetMapping("/customers")
    public @ResponseBody ResponseEntity<CustomerResponse> getCustomers() {


    CustomerResponse response = customerDAOService.getCustomers();

    return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @GetMapping("/customers/{id}")
    public @ResponseBody ResponseEntity<CustomerResponse> searchCustomer(@PathVariable int id) {

        CustomerResponse response = customerDAOService.searchCustomer(id);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}
