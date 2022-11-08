package ru.sotn.catalogservice.service;

import ru.sotn.catalogservice.domain.Clothe;

public interface ClotheService {
    Clothe createClothe(Clothe clothe);
    Clothe updateClothe(Clothe clothe);
}
