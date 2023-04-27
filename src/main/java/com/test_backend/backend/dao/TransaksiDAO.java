package com.test_backend.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.test_backend.backend.dto.ProdukDTO;
import com.test_backend.backend.dto.TransaksiDTO;
import com.test_backend.backend.entity.Produk;
import com.test_backend.backend.entity.Produsen;
import com.test_backend.backend.entity.Transaksi;

import lombok.extern.java.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TransaksiDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Transaksi> findOnCart(Integer id){
        String query = "select \n" +
                "t.id as id_transaksi,\n" +
                "t.id_user as id_user,\n" +
                "t.status as status,\n" +
                "p.id as produk_id,\n" +
                "p.nama as produk_nama,\n" +
                "p.jenis as produk_jenis,\n" +
                "p.berat as produk_berat,\n" +
                "p.harga as produk_harga,\n" +
                "p.spesifikasi as produk_spesifikasi,\n" +
                "pr.nama as nama_produsen,\n" +
                "td.kuantitas as kuantitas\n" +
                "from transaksi t\n" +
                "join transaksi_detail td\n" +
                "on t.id = td.id_transaksi\n" +
                "join produk p\n" +
                "on td.id_produk = p.id\n" +
                "join produsen pr\n" +
                "on p.produsen_id = pr.id\n" +
                "where t.status = 0\n" +
                "and t.id_user = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.query(query, map, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("id_transaksi"));
                transaksi.setId_user(rs.getInt("id_user"));
                transaksi.setStatus(rs.getInt("status"));
                transaksi.setKuantitas(rs.getInt("kuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produk_id"));
                produk.setNama(rs.getString("produk_nama"));
                produk.setJenis(rs.getString("produk_jenis"));
                produk.setBerat(rs.getInt("produk_berat"));
                produk.setHarga(rs.getDouble("produk_harga"));

                transaksi.setProduk(produk);
                return transaksi;
            }
        });
    }

    public List<Transaksi> findTransaction(Integer id){
        String query = "select \n" +
                "t.id as id_transaksi,\n" +
                "t.id_user as id_user,\n" +
                "t.status as status,\n" +
                "p.id as produk_id,\n" +
                "p.nama as produk_nama,\n" +
                "p.jenis as produk_jenis,\n" +
                "p.berat as produk_berat,\n" +
                "p.harga as produk_harga,\n" +
                "p.spesifikasi as produk_spesifikasi,\n" +
                "pr.nama as nama_produsen,\n" +
                "td.kuantitas as kuantitas\n" +
                "from transaksi t\n" +
                "join transaksi_detail td\n" +
                "on t.id = td.id_transaksi\n" +
                "join produk p\n" +
                "on td.id_produk = p.id\n" +
                "join produsen pr\n" +
                "on p.produsen_id = pr.id\n" +
                "where t.status = 1\n" +
                "and t.id_user = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.query(query, map, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("id_transaksi"));
                transaksi.setId_user(rs.getInt("id_user"));
                transaksi.setStatus(rs.getInt("status"));
                transaksi.setKuantitas(rs.getInt("kuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produk_id"));
                produk.setNama(rs.getString("produk_nama"));
                produk.setJenis(rs.getString("produk_jenis"));
                produk.setBerat(rs.getInt("produk_berat"));
                produk.setHarga(rs.getDouble("produk_harga"));

                transaksi.setProduk(produk);
                return transaksi;
            }
        });
    }


    public TransaksiDTO.New save(TransaksiDTO.New transaksi){
        String queryTransaksi = "INSERT INTO transaksi\n" +
                "(id_user)\n" +
                "VALUES(:user_id);\n";
        String queryTransaksiDetail = "INSERT INTO transaksi_detail\n" +
                "(id_transaksi, id_produk, kuantitas)\n" +
                "VALUES(:id_transaksi, :id_produk, :kuantitas);";

        MapSqlParameterSource mapTransaksi = new MapSqlParameterSource();
        MapSqlParameterSource mapTransaksiDetail = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        mapTransaksi.addValue("user_id", transaksi.getUser_id());

        jdbcTemplate.update(queryTransaksi, mapTransaksi, keyHolder);
        
        int id = (int) keyHolder.getKeys().get("id");

        mapTransaksiDetail.addValue("id_transaksi", id);
        mapTransaksiDetail.addValue("id_produk", transaksi.getProduk_id());
        mapTransaksiDetail.addValue("kuantitas", transaksi.getKuantitas());

        jdbcTemplate.update(queryTransaksiDetail, mapTransaksiDetail);

        return transaksi;
    }

    public TransaksiDTO.Update update(TransaksiDTO.Update transaksi){
        String query = "UPDATE transaksi_detail\n" +
                "SET kuantitas=:kuantitas, id_produk=:produk_id\n" +
                "WHERE id_transaksi = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", transaksi.getId());
        map.addValue("kuantitas", transaksi.getKuantitas());
        map.addValue("produk_id", transaksi.getProduk_id());
        jdbcTemplate.update(query,map);
        return transaksi;
    }

    public TransaksiDTO.Checkout checkout(TransaksiDTO.Checkout transaksi){
        String query = "UPDATE transaksi\n" +
                "SET status = 1\n" +
                "WHERE id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", transaksi.getId());
        jdbcTemplate.update(query,map);
        return transaksi;
    }


    // public void delete(Integer id) {
    //     String query = "delete from transaksi where id = :id;\n" +
    //                     "delete from transaksi_detail where transaksi_id = :id;";
    //     MapSqlParameterSource map = new MapSqlParameterSource();
    //     map.addValue("id", id);
    //     jdbcTemplate.update(query, map);
    // }

    // public Optional<Transaksi> findById(Integer id) throws EmptyResultDataAccessException {
    //     String query = "select \n" +
    //                        "t.id id,\n" +
    //                        "t.id_users,\n" +
    //                        "td.id_produk,\n" +
    //                        "td.kuantitas,\n" +
    //                    "from transaksi t\n" +
    //                    "join transaksi_detail td\n" +
    //                    "on t.id = td.transaksi_id\n" +
    //                    "join produk p\n" +
    //                    "on td.id_produk = p.id\n" +
    //                    "where t.id = :id;";
    //     MapSqlParameterSource map = new MapSqlParameterSource();
    //     map.addValue("id", id);
    //     return jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Transaksi>>() {
    //         @Override
    //         public Optional<Transaksi> mapRow(ResultSet rs, int rowNum) throws SQLException {
    //             Transaksi transaksi = new Transaksi();
    //             transaksi.setId(rs.getInt("transaksi_id"));
    //             transaksi.setKuantitas(rs.getInt("kuantitas"));

    //             Produk produk = new Produk();
    //             produk.setId(rs.getInt("produk_id"));
    //             produk.setNama(rs.getString("produk_nama"));
    //             produk.setJenis(rs.getString("produk_jenis"));
    //             produk.setBerat(rs.getInt("produk_berat"));

    //             transaksi.setProduk(produk);
    //             return Optional.of(transaksi);
    //         }
    //     });
    // }
}
