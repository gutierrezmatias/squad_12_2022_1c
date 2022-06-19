package com.aninfo.psa.Repository;

import com.aninfo.psa.modelo.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RepositoryRestResource
public interface ProyectosRepository extends PagingAndSortingRepository<Proyecto, Long> {

    @Override
    List<Proyecto> findAll();
}