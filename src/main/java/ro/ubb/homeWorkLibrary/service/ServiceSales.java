package ro.ubb.homeWorkLibrary.service;

import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.repository.Repository;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ServiceSales {

    private Repository<String, Sales> repository;

    public ServiceSales(Repository<String, Sales> repository) {
        this.repository = repository;
    }

    public void addSales(Sales sales){
        repository.save(sales);
    }

    public Set<Sales> getAllSales(){
        Iterable<Sales> sales = repository.findAll();
        return StreamSupport.stream(sales.spliterator(), false).collect(Collectors.toSet());
    }

    public boolean checkSale(String sale){
        Optional sales =  repository.findOne(sale);

        return sales.isPresent();
    }

    public void removeSale(String bookClientsPK){
        repository.delete(bookClientsPK);
    }


    public void updateSaleService(Sales sale){
        repository.update(sale);
    }

//    public Long findMostCommon() {

//        Long mostCommon = null;
//        Long last = null;
//        int mostCount = 0;
//        int lastCount = 0;
//        for (Sales x : repository.findAll()) {
//            if (x.getBookIdSale().equals(last)) {
//                lastCount++;
//            } else if (lastCount > mostCount) {
//                mostCount = lastCount;
//                mostCommon = last;
//            }
//            last = x.getBookIdSale();
//        }
//        return mostCommon;
//    }

    public Long findMostCommon() {
        Map<Long, Sales> map = new HashMap<>();

        for (Sales t : repository.findAll()) {
//            Sales val = repository.findAll().iterator().next();
            map.put(t.getBookIdSale() == null ? 1 : t.getBookIdSale() + 1, t);
        }

        Map.Entry<Long, Sales> max = null;

        for (Map.Entry<Long, Sales> e : map.entrySet()) {
            if (max == null || e.getValue().getBookIdSale() > max.getValue().getBookIdSale())
                max = e;
        }

        return max.getValue().getBookIdSale();
    }


}
