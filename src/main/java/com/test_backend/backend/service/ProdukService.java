package com.test_backend.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test_backend.backend.dao.ProdukDAO;
import com.test_backend.backend.dto.ProdukDTO;
import com.test_backend.backend.entity.Produk;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProdukService {

    @Autowired
    private ProdukDAO dao;

    public List<Produk> findAll(){
        return dao.findAll();
    }

    public Optional<Produk> findById(Integer id){
        return dao.findById(id);
    }

    public List<Produk> findByProdusenId(Integer id){
        return dao.findByProdusenId(id);
    }

    public ProdukDTO.New save(ProdukDTO.New produk) {
        return dao.save(produk);
    }

    public ProdukDTO.Update update(ProdukDTO.Update produk) {
        return dao.update(produk);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }
}
