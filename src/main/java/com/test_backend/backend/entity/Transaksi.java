package com.test_backend.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaksi {
    private Integer id;
    private Integer id_user;
    private Integer status;
    private Integer kuantitas;
    private Produk produk;
    private double totalHarga;
}
