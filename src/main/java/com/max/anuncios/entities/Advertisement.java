package com.max.anuncios.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_advertisement")
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant startDate;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant endDate;
    private Double InvestmentPerDay;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Advertisement() {
    }

    public Advertisement(Long id, String name, Instant startDate, Instant endDate, Double investmentPerDay, Client client) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        InvestmentPerDay = investmentPerDay;
        this.client = client;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
