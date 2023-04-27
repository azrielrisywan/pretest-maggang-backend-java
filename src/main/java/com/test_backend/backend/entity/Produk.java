package com.test_backend.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produk {
    private Integer id;
    private String nama;
    private String jenis;
    private Integer berat;
    private Produsen produsen;
    private Double harga;
}
