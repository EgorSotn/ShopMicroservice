package ru.sotn.catalogservice.service;

import ru.sotn.catalogservice.controller.dto.ClotheDto;
import ru.sotn.catalogservice.controller.dto.DescriptionDto;
import ru.sotn.catalogservice.controller.dto.ImageDto;
import ru.sotn.catalogservice.controller.dto.SizeDto;
import ru.sotn.catalogservice.domain.Clothe;
import ru.sotn.catalogservice.domain.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapClotheToDtoImpl implements MapClotheToDto{
    @Override
    public ClotheDto mapToProductResponse(Clothe clothe) {
        Set<SizeDto> sizeDtos = new HashSet<>();
        clothe.getSizes().stream().forEach(s->{
            SizeDto sizeDto = new SizeDto(s.getIdSize(),s.getEurSize(),s.getRuSize(),s.getWorldSize());
            sizeDtos.add(sizeDto);
        });
        List<ImageDto> imageDtoList = new ArrayList<>();
        clothe.getImages().stream().forEach(i->{
            ImageDto imageDto = new ImageDto(i.getIdImage(), i.getUrl());
            imageDtoList.add(imageDto);
        });
        return ClotheDto.builder().id(clothe.getId())
                .colorCloth(clothe.getColorCloth())
                .nameCloth(clothe.getNameCloth())
                .description(DescriptionDto.builder()
                        .idDescription(clothe.getDescription().getIdDescription())
                        .aboutCloth(clothe.getDescription().getAboutCloth())
                        .textile(clothe.getDescription().getTextile()).build())
                .sizes(sizeDtos)
                .images(imageDtoList).build();
    }
}
