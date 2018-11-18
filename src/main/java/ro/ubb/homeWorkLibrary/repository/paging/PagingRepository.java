package ro.ubb.homeWorkLibrary.repository.paging;

import ro.ubb.homeWorkLibrary.domain.BaseEntity;
import ro.ubb.homeWorkLibrary.repository.Repository;
import java.io.Serializable;

public interface PagingRepository<ID extends Serializable,T extends BaseEntity<ID>>
        extends Repository<ID, T> {

    Page<T> findAll(Pageable pageable);

}
