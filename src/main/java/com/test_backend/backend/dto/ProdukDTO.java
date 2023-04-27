package com.test_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.test_backend.backend.entity.Produsen;

public class ProdukDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New {
        @NotNull
        @NotEmpty
        private String nama;
        @NotNull
        @NotEmpty
        private String jenis;
        @NotNull
        private Integer berat;
        @NotNull
        private Produsen produsen;
        @NotNull
        private Double harga;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull
        private Integer id;
        @NotNull
        @NotEmpty
        private String nama;
        @NotNull
        @NotEmpty
        private String jenis;
        @NotNull
        private Integer berat;
        @NotNull
        private Produsen produsen;
        @NotNull
        private Double harga;
    }
}
