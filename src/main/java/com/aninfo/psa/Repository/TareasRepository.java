package com.aninfo.psa.Repository;

import com.aninfo.psa.modelo.Tarea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;


@RepositoryRestResource
public interface TareasRepository extends PagingAndSortingRepository<Tarea, Long>{

    @Override
    public List<Tarea> findAll();

}
