package ru.sotn.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sotn.catalogservice.domain.Clothe;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "clothe", path = "clothe")
public interface ClotheRepository extends JpaRepository<Clothe, Long> {

    @RestResource(path = "name", rel = "findByName")
    List<Clothe> findByNameCloth(@Param("name") String nameCloth);

    @RestResource(path = "name/color", rel = "findClotheByNameClothAndColorCloth")
    Optional<Clothe> findClotheByNameClothAndColorCloth(@Param("name") String nameCloth, @Param("color") String colorCloth);
}
