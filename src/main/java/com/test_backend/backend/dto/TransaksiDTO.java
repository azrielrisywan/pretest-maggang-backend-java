package com.test_backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class TransaksiDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class New {
        @NotNull
        private Integer user_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull
        private Integer id;
        @NotNull
        private Integer user_id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        @NotNull
        private Integer id;
        @NotNull
        private String produk;
        @NotNull
        private String produsen;
        @NotNull
        private Double harga;
        @NotNull
        private Integer kuantitas;
        @NotNull
        private Double totalHarga;
    }
}
