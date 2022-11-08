package ru.sotn.catalogservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sotn.catalogservice.domain.Clothe;
import ru.sotn.catalogservice.domain.Image;
import ru.sotn.catalogservice.domain.Size;
import ru.sotn.catalogservice.repository.ClotheRepository;
import ru.sotn.catalogservice.repository.DescriptionRepository;
import ru.sotn.catalogservice.repository.ImageRepository;
import ru.sotn.catalogservice.repository.SizeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClotheServiceImpl implements ClotheService{
    private final ClotheRepository clotheRepository;
    private final SizeRepository sizeRepository;
    private final DescriptionRepository descriptionRepository;
    private final ImageRepository imageRepository;
    @Transactional
    public Clothe createClothe(Clothe clothe){

        Set<Size> findSizeSet = new HashSet<>();
        if(!clothe.getSizes().isEmpty()){
            clothe.getSizes().stream().forEach(s -> {
                findSizeSet.add(sizeRepository.getBySizeOrCreate(s).get());
            });
        }
        clothe.setSizes(findSizeSet);
        if(clothe.getDescription() != null){
            descriptionRepository.save(clothe.getDescription());
        }

        return  clotheRepository.save(clothe);
    }


    @Transactional
    @Override
    public Clothe updateClothe(Clothe updateClothe) {
       Clothe findClothe = clotheRepository.findById(updateClothe.getId()).orElseThrow(()->new NoSuchElementException());

       findClothe.setNameCloth(updateClothe.getNameCloth());
       findClothe.setColorCloth(updateClothe.getColorCloth());
       findClothe.setGender(updateClothe.getGender());
       findClothe.setPrice(updateClothe.getPrice());
       findClothe.setQuantity(updateClothe.getQuantity());
       if(updateClothe.getSizes() !=null && !updateClothe.getSizes().isEmpty()){
           findClothe.setSizes(updateClothe.getSizes().stream().map(size ->
                   sizeRepository.getBySizeOrCreate(size)).map(Optional::get).collect(Collectors.toSet()));
       }

       if(updateClothe.getImages() != null && !updateClothe.getImages().isEmpty()){
           findClothe.setImages(updateClothe.getImages().stream()
                   .map(imageRepository::getByUrlOrCreate).map(Optional::get).collect(Collectors.toList()));
       }
       for(Image i: findClothe.getImages()){
           i.setClothe(findClothe);
       }
       if(updateClothe.getDescription() != null){
           findClothe.setDescription(descriptionRepository.getByTextileOrCreate(updateClothe.getDescription()).get());
       }

       return clotheRepository.save(findClothe);
    }

//    public void addClothe(Clothe clothe, Integer quantity) {
//        Optional<Clothe> optionalSock = clotheRepository.findClotheByNameClothAndColorCloth(clothe.getNameCloth(),
//                clothe.getColorCloth());
//
//        optionalSock.ifPresentOrElse(
//            //is present
//            cl -> {
//                cl.setQuantity(cl.getQuantity() + quantity);
//                clotheRepository.save(cl);
//            },
//            //is optional.empty
//            () -> clotheRepository.save(new Clothe(clothe.getNameCloth(), clothe.getColorCloth(), quantity,
//                    clothe.getPrice(),))
//        );
//    }
}
