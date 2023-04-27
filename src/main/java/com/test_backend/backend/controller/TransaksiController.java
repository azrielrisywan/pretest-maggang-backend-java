package com.test_backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test_backend.backend.dto.TransaksiDTO;
import com.test_backend.backend.entity.Transaksi;
import com.test_backend.backend.service.TransaksiService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService service;

    @GetMapping("/list")
    public List<Transaksi> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        Optional<Transaksi> response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid TransaksiDTO.New transaksi) {
        try {
            TransaksiDTO.New response = service.save(transaksi);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid TransaksiDTO.Update transaksi) {
        try {
            TransaksiDTO.Update response = service.update(transaksi);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
        Optional<TransaksiDTO.Detail> response = service.detail(id);
        return ResponseEntity.ok(response);
    }
}
