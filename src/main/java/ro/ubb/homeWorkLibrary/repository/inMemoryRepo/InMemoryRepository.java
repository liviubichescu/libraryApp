package ro.ubb.homeWorkLibrary.repository.inMemoryRepo;

import ro.ubb.homeWorkLibrary.domain.BaseEntity;
import ro.ubb.homeWorkLibrary.repository.Repository;
import ro.ubb.homeWorkLibrary.validators.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Liviu.
 */
public class InMemoryRepository<ID, Entities extends BaseEntity<ID>> implements Repository<ID, Entities> {

    private Map<ID, Entities> entities;
    private Validator<Entities> validator;

    public InMemoryRepository(Validator<Entities> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<Entities> findOne(ID id){
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<Entities> findAll(){
        Set<Entities> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }


    @Override
    public Optional<Entities> save(Entities entity)  {
        if (entity == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<Entities> delete(ID id)  {
        if (id == null) {
            throw new IllegalArgumentException("ID is either null or not in the list!");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<Entities> update(Entities entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
