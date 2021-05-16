package com.max.anuncios.services;

import com.max.anuncios.dto.AdvertisementDTO;
import com.max.anuncios.entities.Advertisement;
import com.max.anuncios.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Transactional(readOnly = true)
    public Page<AdvertisementDTO> findAllPaged(PageRequest pageRequest) {
        Page<Advertisement> allAdvertisement = advertisementRepository.findAll(pageRequest);
        return allAdvertisement.map(AdvertisementDTO::new);
    }
}
