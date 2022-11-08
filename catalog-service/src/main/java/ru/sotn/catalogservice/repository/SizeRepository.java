package ru.sotn.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sotn.catalogservice.domain.Size;

@RepositoryRestResource(collectionResourceRel = "size", path = "size")
public interface SizeRepository extends JpaRepository<Size, Long>, SizeRepositoryCustom {

}
