package repository;


import exceptions.LoginInUseExceptionEnt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryInterface<T> {


    List<T> readAll();

    T readById(UUID uuid);

    T create(T object) throws LoginInUseExceptionEnt;

    T delete(UUID uuid);

    T update(T object) throws LoginInUseExceptionEnt;

}