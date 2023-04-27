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

        private Integer transaksi_id;

        @NotNull
        private Integer produk_id;
        @NotNull
        private Integer kuantitas; 
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull
        private Integer id;
        
        private Integer user_id;
        @NotNull
        private Integer produk_id;
        @NotNull
        private Integer kuantitas; 
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Checkout {
        @NotNull
        private Integer id;
    }
}
