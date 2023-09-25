package com.riquelmi.apipizzeria.service;

import com.riquelmi.apipizzeria.persistence.entity.PizzaEntity;
import com.riquelmi.apipizzeria.persistence.repository.PizzaPagSortRepository;
import com.riquelmi.apipizzeria.persistence.repository.PizzaRepository;
import com.riquelmi.apipizzeria.service.dto.UpdatePizzaPriceDto;

import com.riquelmi.apipizzeria.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    /* USANDO JDBC
    * private final JdbcTemplate jdbcTemplate;
    *

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   */

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public List<PizzaEntity> getAll(){
        /* USANDO JDBC
         * return jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
         * */

        return pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAllPaginate(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public Page<PizzaEntity>  getAvailablePaginate(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public Optional<PizzaEntity> getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }
    public Optional<PizzaEntity> findById(Integer idPizza){
        return pizzaRepository.findById(idPizza);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public void deleteById(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaRepository.updatePrice(dto);
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }
    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

}
