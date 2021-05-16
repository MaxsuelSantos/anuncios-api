package com.max.anuncios.services;

import com.max.anuncios.dto.AdvertisementDTO;
import com.max.anuncios.entities.Advertisement;
import com.max.anuncios.entities.Client;
import com.max.anuncios.exceptions.DatabaseException;
import com.max.anuncios.exceptions.ResourceNotFoundException;
import com.max.anuncios.repositories.AdvertisementRepository;
import com.max.anuncios.repositories.ClientRepository;
import com.max.anuncios.util.Calc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
            calculator(dto);
        }
        return advertisementDTOS;
    }

    @Transactional(readOnly = true)
    public AdvertisementDTO findById(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        AdvertisementDTO dto = new AdvertisementDTO(advertisement);
        calculator(dto);
        return dto;
    }

    @Transactional
    public AdvertisementDTO insert(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();
        copyDtoToEntity(advertisementDTO, advertisement);
        advertisement = advertisementRepository.save(advertisement);
        AdvertisementDTO dto = new AdvertisementDTO(advertisement);
        calculator(dto);
        return dto;
    }

    @Transactional
    public AdvertisementDTO update(Long id, AdvertisementDTO advertisementDTO) {
        try {
            Advertisement advertisement = advertisementRepository.getOne(id);
            copyDtoToEntity(advertisementDTO, advertisement);
            advertisement = advertisementRepository.save(advertisement);
            AdvertisementDTO dto = new AdvertisementDTO(advertisement);
            calculator(dto);
            return dto;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            advertisementRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(AdvertisementDTO advertisementDTO, Advertisement advertisement) {
        advertisement.setName(advertisementDTO.getName());
        advertisement.setStartDate(advertisementDTO.getStartDate());
        advertisement.setEndDate(advertisementDTO.getEndDate());
        advertisement.setInvestmentPerDay(advertisementDTO.getInvestmentPerDay());

        Client client = clientRepository.getOne(advertisementDTO.getClientDTO().getId());
        advertisement.setClient(client);

    }

    private void calculator(AdvertisementDTO dto) {
        dto.setTotalInvested(totalInvested(dto.getStartDate(), dto.getEndDate(), dto.getInvestmentPerDay()));
        dto.setVisualization((int) Calc.visualization(dto.getTotalInvested()));
        dto.setClick((int) Calc.click(dto.getTotalInvested()));
        dto.setSharing((int) Calc.sharing(dto.getTotalInvested()));
    }

    private double totalInvested(Instant startDate, Instant endDate, Double investmentPerDay) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toDays() * investmentPerDay;
    }


}
