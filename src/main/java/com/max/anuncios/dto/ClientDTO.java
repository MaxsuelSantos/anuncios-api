package com.max.anuncios.dto;

import com.max.anuncios.entities.Advertisement;
import com.max.anuncios.entities.Client;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO implements Serializable {

    private Long id;
    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;
    @NotEmpty
    @CPF
    private String cpf;
    @Email
    private String email;
    @NotEmpty
    private String phone;

    private List<AdvertisementResponse> advertisementsResponse = new ArrayList<>();

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, String email, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.email = client.getEmail();
        this.phone = client.getPhone();
    }

    public ClientDTO(Client client, List<Advertisement> advertisements) {
        this(client);
        advertisements.forEach(x -> this.advertisementsResponse.add(new AdvertisementResponse(x)));
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AdvertisementResponse> getAdvertisementsResponse() {
        return advertisementsResponse;
    }
}
