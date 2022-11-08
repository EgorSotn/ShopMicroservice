package ru.sotn.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sotn.catalogservice.domain.Description;

@RepositoryRestResource(collectionResourceRel = "description", path = "description")
public interface DescriptionRepository extends JpaRepository<Description, Long>, DescriptionRepositoryCustom {

}
