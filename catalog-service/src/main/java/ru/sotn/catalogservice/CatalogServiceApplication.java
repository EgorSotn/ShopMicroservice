package ru.sotn.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import ru.sotn.catalogservice.domain.*;
import ru.sotn.catalogservice.service.ClotheService;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
public class CatalogServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CatalogServiceApplication.class, args);
//        ClotheService catalogService = context.getBean(ClotheService.class);

//        Set<Size> sizeList = Set.of(new Size("42","38","M"));
//        List<Image> images = List.of(new Image("url3"), new Image("url4"));
//        Description description = new Description("aaa", "gggg");
//        Clothe clothe = new Clothe(1L, "ab", "red",
//                7L, "1999", sizeList, images, description, Gender.MAN);
//        System.out.println(catalogService.updateClothe(clothe));
    }

}
