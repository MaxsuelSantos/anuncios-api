package com.max.anuncios.dto;

import com.max.anuncios.entities.Advertisement;
import com.max.anuncios.entities.Client;

import java.time.Instant;

public class AdvertisementDTO {

    private Long id;
    private String name;
    private Instant startDate;
    private Instant endDate;
    private Double InvestmentPerDay;
    private ClientDTO clientDTO;
    private Double totalInvested;
    private Integer visualization;
    private Integer click;
    private Integer sharing;

    public AdvertisementDTO() {
    }

    public AdvertisementDTO(Long id, String name, Instant startDate, Instant endDate, Double investmentPerDay, ClientDTO clientDTO) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.InvestmentPerDay = investmentPerDay;
        this.clientDTO = clientDTO;
    }

    public AdvertisementDTO(Advertisement advertisement) {
        this.id = advertisement.getId();
        this.name = advertisement.getName();
        this.startDate = advertisement.getStartDate();
        this.endDate = advertisement.getEndDate();
        this.InvestmentPerDay = advertisement.getInvestmentPerDay();
        this.clientDTO = new ClientDTO(advertisement.getClient());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Double getInvestmentPerDay() {
        return InvestmentPerDay;
    }

    public void setInvestmentPerDay(Double investmentPerDay) {
        InvestmentPerDay = investmentPerDay;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public Double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(Double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public Integer getVisualization() {
        return visualization;
    }

    public void setVisualization(Integer visualization) {
        this.visualization = visualization;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getSharing() {
        return sharing;
    }

    public void setSharing(Integer sharing) {
        this.sharing = sharing;
    }
}
