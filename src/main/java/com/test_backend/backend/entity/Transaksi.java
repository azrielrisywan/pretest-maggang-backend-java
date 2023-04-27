package com.test_backend.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaksi {
    private Integer id;
    private Double kuantitas;
    private Produk produk;
}
