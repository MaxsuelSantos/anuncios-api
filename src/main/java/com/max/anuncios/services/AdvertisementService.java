package com.max.anuncios.services;

import com.max.anuncios.dto.AdvertisementDTO;
import com.max.anuncios.dto.ClientDTO;
import com.max.anuncios.entities.Advertisement;
import com.max.anuncios.repositories.AdvertisementRepository;
import com.max.anuncios.repositories.ClientRepository;
import com.max.anuncios.util.Calc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<AdvertisementDTO> findAllPaged(PageRequest pageRequest) {
        Page<Advertisement> allAdvertisement = advertisementRepository.findAll(pageRequest);
        Page<AdvertisementDTO> advertisementDTOS = allAdvertisement.map(AdvertisementDTO::new);
        for (AdvertisementDTO dto : advertisementDTOS) {
            dto.setTotalInvested(totalInvested(dto.getStartDate(), dto.getEndDate(), dto.getInvestmentPerDay()));
            dto.setVisualization((int) Calc.visualization(dto.getTotalInvested()));
            dto.setClick((int) Calc.click(dto.getTotalInvested()));
            dto.setSharing((int) Calc.sharing(dto.getTotalInvested()));
        }
        return advertisementDTOS;
    }

    private double totalInvested(Instant startDate, Instant endDate, Double investmentPerDay) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toDays() * investmentPerDay;
    }


}
