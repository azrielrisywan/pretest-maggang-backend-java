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

    @GetMapping("/list-cart/{id}")
    public ResponseEntity<?> findOnCart(@PathVariable("id") Integer id){
        List<Transaksi> response = service.findOnCart(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-transaction/{id}")
    public ResponseEntity<?> findTransaction(@PathVariable("id") Integer id){
        List<Transaksi> response = service.findTransaction(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        Optional<Transaksi> response = service.findById(id);
        return ResponseEntity.ok(response);
    }


    // Menambah Transaksi Baru (Tambah ke Cart)
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid TransaksiDTO.New transaksi) {
        try {
            TransaksiDTO.New response = service.save(transaksi);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Mengubah Detail Transaksi
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid TransaksiDTO.Update transaksi) {
        try {
            TransaksiDTO.Update response = service.update(transaksi);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Checkout Transaksi
    @PutMapping("/checkout")
    public ResponseEntity<?> update(@RequestBody @Valid TransaksiDTO.Checkout transaksi) {
        try {
            TransaksiDTO.Checkout response = service.checkout(transaksi);
            return ResponseEntity.ok(response);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(dae.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    //     service.delete(id);
    //     return ResponseEntity.ok().build();
    // }
}
