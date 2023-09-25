package com.riquelmi.apipizzeria.controller;

import com.riquelmi.apipizzeria.service.CustomerService;
import com.riquelmi.apipizzeria.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getByPhone/{phone}")
    public @ResponseBody ResponseObject getByPhone(@PathVariable String phone){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.customerService.findByPhone(phone));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }
}
