package com.max.anuncios.services;

import com.max.anuncios.dto.AdvertisementResponse;
import com.max.anuncios.dto.ClientDTO;
import com.max.anuncios.entities.Client;
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
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        ClientDTO clientDTO = new ClientDTO(client, client.getAdvertisements());
        for (AdvertisementResponse ar : clientDTO.getAdvertisementsResponse()) {
            ar.setTotalInvested(totalInvested(ar.getStartDate(), ar.getEndDate(), ar.getInvestmentPerDay()));
            ar.setVisualization((int) Calc.visualization(ar.getTotalInvested()));
            ar.setClick((int) Calc.click(ar.getTotalInvested()));
            ar.setSharing((int) Calc.sharing(ar.getTotalInvested()));
        };
        return clientDTO;
    }

    private double totalInvested(Instant startDate, Instant endDate, Double investmentPerDay) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toDays() * investmentPerDay;
    }
}
