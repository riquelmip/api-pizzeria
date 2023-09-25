package com.riquelmi.apipizzeria.controller;

import com.riquelmi.apipizzeria.persistence.entity.PizzaEntity;
import com.riquelmi.apipizzeria.service.PizzaService;
import com.riquelmi.apipizzeria.service.dto.UpdatePizzaPriceDto;
import com.riquelmi.apipizzeria.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public @ResponseBody ResponseObject getAll(){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getAll());
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/paginate")
    public @ResponseBody ResponseObject getAllPaginate(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int elements){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getAllPaginate(page, elements));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }
    @GetMapping("/getAvailable")
    public @ResponseBody ResponseObject getAvailable(){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getAvailable());
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getAvailable/paginate")
    public @ResponseBody ResponseObject getAvailablePaginate(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int elements,
                                                             @RequestParam(defaultValue = "price") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortDirection){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getAvailablePaginate(page, elements, sortBy, sortDirection));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getByName/{name}")
    public @ResponseBody ResponseObject getByName(@PathVariable String name){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getByName(name));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getWith/{param}")
    public @ResponseBody ResponseObject getWith(@PathVariable String param){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getWith(param));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/getWithout/{param}")
    public @ResponseBody ResponseObject getWithout(@PathVariable String param){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getWithout(param));
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @GetMapping("/get/{idPizza}")
    public @ResponseBody ResponseObject getPizza(@PathVariable Integer idPizza){
        try{
            Optional<PizzaEntity> pizza = this.pizzaService.findById(idPizza);
            if(pizza.isPresent()){
                return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, pizza);
            }else{
                return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_NOT_FOUND, null);
            }
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @PostMapping("/save")
    public @ResponseBody ResponseObject save(@RequestBody PizzaEntity pizza){
        try{
            if(pizza.getIdPizza() == 0) { //IS NEW
                PizzaEntity pizzaSaved = this.pizzaService.save(pizza);
                return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_REGISTER, pizza);
            }else{ //IS EDIT
                if(this.pizzaService.exists(pizza.getIdPizza())){ //EXISTS?
                    PizzaEntity pizzaUpdate = this.pizzaService.save(pizza);
                    return new ResponseObject(ResponseObject.CODE_SUCCESS,  ResponseObject.MSG_UPDATE, pizza);
                }else{
                    return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_NOT_FOUND, null);
                }
            }
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);
        }
    }

    @PostMapping("/delete")
    public @ResponseBody ResponseObject delete(@RequestParam Integer idPizza){
        try{
            if(idPizza != 0 && this.pizzaService.exists(idPizza)) {
                Optional<PizzaEntity> pizzaDeleted = this.pizzaService.findById(idPizza);
                this.pizzaService.deleteById(idPizza);
                return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_DELETE, pizzaDeleted);
            }else{
                return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_DELETE, null);
            }
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);
        }
    }

    @GetMapping("/getCheapest/{price}")
    public @ResponseBody ResponseObject getCheapest(@PathVariable Double price){
        try{
            return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_SUCCESS, this.pizzaService.getCheapest(price));

        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);

        }
    }

    @PostMapping("/updatePrice")
    public @ResponseBody ResponseObject updatePrice(@RequestBody UpdatePizzaPriceDto dto){
        try{
            if (this.pizzaService.exists(dto.getPizzaId())) {
                this.pizzaService.updatePrice(dto);
                return new ResponseObject(ResponseObject.CODE_SUCCESS, ResponseObject.MSG_UPDATE, null);
            }else{
                return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_DELETE, null);
            }
        }catch (Exception e){
            return new ResponseObject(ResponseObject.CODE_ERROR, ResponseObject.MSG_ERROR, null);
        }
    }

}
