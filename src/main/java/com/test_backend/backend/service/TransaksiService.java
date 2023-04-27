package com.test_backend.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test_backend.backend.dao.ProdukDAO;
import com.test_backend.backend.dao.TransaksiDAO;
import com.test_backend.backend.dto.ProdukDTO;
import com.test_backend.backend.dto.TransaksiDTO;
import com.test_backend.backend.entity.Produk;
import com.test_backend.backend.entity.Transaksi;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiDAO dao;

    public List<Transaksi> findOnCart(Integer id){
        return dao.findOnCart(id);
    }

    public List<Transaksi> findTransaction(Integer id){
        return dao.findTransaction(id);
    }

    // public Optional<Transaksi> findById(Integer id){
    //     return dao.findById(id);
    // }

    public TransaksiDTO.New save(TransaksiDTO.New transaksi) {
        return dao.save(transaksi);
    }

    public TransaksiDTO.Update update(TransaksiDTO.Update transaksi) {
        return dao.update(transaksi);
    }

    public TransaksiDTO.Checkout checkout(TransaksiDTO.Checkout transaksi) {
        return dao.checkout(transaksi);
    }

    // public void delete(Integer id) {
    //     dao.delete(id);
    // }
}
