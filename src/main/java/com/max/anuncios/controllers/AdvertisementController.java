package com.max.anuncios.controllers;

import com.max.anuncios.dto.AdvertisementDTO;
import com.max.anuncios.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping
    public ResponseEntity<Page<AdvertisementDTO>> findAllPaged(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<AdvertisementDTO> advertisementDTOS = advertisementService.findAllPaged(pageRequest);
        return ResponseEntity.ok(advertisementDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDTO> findById(@PathVariable Long id) {
        AdvertisementDTO advertisementDTO = advertisementService.findById(id);
        return ResponseEntity.ok(advertisementDTO);
    }

    @PostMapping
    public ResponseEntity<AdvertisementDTO> insert(@RequestBody AdvertisementDTO advertisementDTO) {
        advertisementDTO = advertisementService.insert(advertisementDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(advertisementDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(advertisementDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDTO> update(@PathVariable Long id, @RequestBody AdvertisementDTO advertisementDTO) {
        advertisementDTO = advertisementService.update(id, advertisementDTO);
        return ResponseEntity.ok(advertisementDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id) {
        advertisementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
