package com.riquelmi.apipizzeria.controller;

import com.riquelmi.apipizzeria.service.OrderService;
import com.riquelmi.apipizzeria.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public @ResponseBody ResponseObject getAll(){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.orderService.getAll());
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getTodayOrders")
    public @ResponseBody ResponseObject getTodayOrders(){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.orderService.getTodayOrders());
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getOutsideOrders")
    public @ResponseBody ResponseObject getOutsideOrders(){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.orderService.getOutsideOrders());
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getCustomerOrders/{id}")
    public @ResponseBody ResponseObject getCustomerOrders(@PathVariable String id){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.orderService.getCustomerOrders(id));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getSummary/{id}")
    public @ResponseBody ResponseObject getSummary(@PathVariable int id){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.orderService.getSummary(id));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

}
