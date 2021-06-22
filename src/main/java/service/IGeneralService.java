package service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();

    Optional<T> findOne(Long id) throws Exception;

    void save(T t);
    void delete(Long id);

}
