package ro.ubb.homeWorkLibrary.repository.inMemoryRepo;

import ro.ubb.homeWorkLibrary.domain.BaseEntity;
import ro.ubb.homeWorkLibrary.repository.Repository;
import ro.ubb.homeWorkLibrary.validators.Validator;

import java.util.*;
import java.util.stream.Collectors;

public class InMemorySalesRepo<String, Sales extends BaseEntity<String>> implements Repository<String, Sales> {

    private Map<String, Sales> salesMap;
    private Validator<Sales> validator;

    public InMemorySalesRepo(Validator<Sales> validator) {
        this.salesMap = new HashMap<>();
        this.validator = validator;
    }

    @Override
    public Optional<Sales> save(Sales sales) {
        Sales s = sales;
        if (sales == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        validator.validate(sales);
        return Optional.ofNullable(salesMap.putIfAbsent(sales.getId(), sales));
    }

    @Override
    public Optional<Sales> delete(String str) {
        if (str == null) {
            throw new IllegalArgumentException("ID is either null or not in the list!");
        }
        return Optional.ofNullable(salesMap.remove(str));
    }

    @Override
    public Optional<Sales> findOne(String string) {
        if (string == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(salesMap.get(string));
    }

    @Override
    public Iterable<Sales> findAll() {
        Set<Sales> allPrimaryKeys = salesMap.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allPrimaryKeys;
    }

    @Override
    public Optional<Sales> update(Sales sales) {
        if (sales == null) {
            throw new IllegalArgumentException("sales must not be null");
        }
        validator.validate(sales);
        return Optional.ofNullable(salesMap.computeIfPresent(sales.getId(), (x, y) -> sales));
    }

}
