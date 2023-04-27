package com.test_backend.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test_backend.backend.dto.ProdukDTO;
import com.test_backend.backend.entity.Produk;
import com.test_backend.backend.entity.Produsen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdukDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Produk> findAll(){
        String query = "select \n" +
                "p.id as produkId,\n" +
                "p.nama as produkNama,\n" +
                "p.jenis as produkJenis,\n" +
                "p.berat as produkBerat," +
                "p.harga as produkHarga,\n" +
                "p.spesifikasi as produkSpesifikasi,\n" +
                "p2.id as produsenId,\n" +
                "p2.nama as produsenNama,\n" +
                "p2.kode as produsenkode,\n" +
                "p2.alamat as produsenAlamat\n" +
                "from produk p left join produsen p2 \n" +
                "on p.produsen_id = p2.id ";
        return jdbcTemplate.query(query, new RowMapper<Produk>() {
            @Override
            public Produk mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));
                produk.setHarga(rs.getDouble("produkHarga"));
                produk.setSpesifikasi(rs.getString("produkSpesifikasi"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenkode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                produk.setProdusen(produsen);
                return produk;
            }
        });
    }


    public ProdukDTO.New save(ProdukDTO.New produk){
        String query = "INSERT INTO produk\n" +
                "(nama, jenis, berat, produsen_id, harga)\n" +
                "VALUES(:nama, :jenis, :berat, :produsenId, :harga);\n";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        map.addValue("produsenId", produk.getProdusen().getId());
        map.addValue("harga", produk.getHarga());
        jdbcTemplate.update(query,map);
        return produk;
    }



    public ProdukDTO.Update update(ProdukDTO.Update produk){
        String query = "UPDATE produk\n" +
                "SET nama=:nama, jenis=:jenis, berat=:berat, produsen_id=:produsenId, harga=:harga\n" +
                "WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", produk.getId());
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        map.addValue("produsenId", produk.getProdusen().getId());
        map.addValue("harga", produk.getHarga());
        jdbcTemplate.update(query,map);
        return produk;
    }


    public void delete(Integer id) {
        String query = "delete from produk where id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        jdbcTemplate.update(query, map);
    }

    public Optional<Produk> findById(Integer id) throws EmptyResultDataAccessException {
        String query = "select \n" +
                "p.id as produkId,\n" +
                "p.nama as produkNama,\n" +
                "p.jenis as produkJenis,\n" +
                "p.berat as produkBerat," +
                "p.harga as produkHarga,\n" +
                "p.spesifikasi as produkSpesifikasi,\n" +
                "p2.id as produsenId,\n" +
                "p2.nama as produsenNama,\n" +
                "p2.kode as produsenkode,\n" +
                "p2.alamat as produsenAlamat\n" +
                "from produk p left join produsen p2 \n" +
                "on p.produsen_id = p2.id where p.id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Produk>>() {
            @Override
            public Optional<Produk> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));
                produk.setHarga(rs.getDouble("produkHarga"));
                produk.setSpesifikasi(rs.getString("produkSpesifikasi"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenkode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                produk.setProdusen(produsen);
                return Optional.of(produk);
            }
        });
    }

    public List<Produk> findByProdusenId(Integer id) throws EmptyResultDataAccessException {
        String query = "select \n" +
                "p.id as produkId,\n" +
                "p.nama as produkNama,\n" +
                "p.jenis as produkJenis,\n" +
                "p.berat as produkBerat," +
                "p.harga as produkHarga,\n" +
                "p2.id as produsenId,\n" +
                "p2.nama as produsenNama,\n" +
                "p2.kode as produsenkode,\n" +
                "p2.alamat as produsenAlamat\n" +
                "from produk p left join produsen p2 \n" +
                "on p.produsen_id = p2.id where p.produsen_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.query(query, map, new RowMapper<Produk>() {
            @Override
            public Produk mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("produkId"));
                produk.setNama(rs.getString("produkNama"));
                produk.setJenis(rs.getString("produkJenis"));
                produk.setBerat(rs.getInt("produkBerat"));
                produk.setHarga(rs.getDouble("produkHarga"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsenId"));
                produsen.setNama(rs.getString("produsenNama"));
                produsen.setKode(rs.getString("produsenkode"));
                produsen.setAlamat(rs.getString("produsenAlamat"));

                produk.setProdusen(produsen);
                return produk;
            }
        });
    }


}
