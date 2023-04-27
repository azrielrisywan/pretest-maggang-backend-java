package com.test_backend.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test_backend.backend.dto.ProdukDTO;
import com.test_backend.backend.dto.TransaksiDTO;
import com.test_backend.backend.entity.Produk;
import com.test_backend.backend.entity.Produsen;
import com.test_backend.backend.entity.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TransaksiDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Transaksi> findAll(){
        String query = "select \n" +
                "t.id as transaksi_id,\n" +
                "p.id as produk_id,\n" +
                "p.nama as produk_nama,\n" +
                "p.jenis as produk_jenis,\n" +
                "p.berat as produk_berat,\n" +
                "t.kuantitas as kuantitas\n" +
                "from transaksi t\n" +
                "left join produk p\n" +
                "on t.produk_id = p.id ";
        return jdbcTemplate.query(query, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("transaksi_id"));
                transaksi.setKuantitas(rs.getDouble("kuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produk_id"));
                produk.setNama(rs.getString("produk_nama"));
                produk.setJenis(rs.getString("produk_jenis"));
                produk.setBerat(rs.getInt("produk_berat"));

                transaksi.setProduk(produk);
                return transaksi;
            }
        });
    }


    public TransaksiDTO.New save(TransaksiDTO.New transaksi){
        String query = "INSERT INTO transaksi\n" +
                "(kuantitas, produk_id)\n" +
                "VALUES(:kuantitas, :produk_id);\n";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kuantitas", transaksi.getKuantitas());
        map.addValue("produk_id", transaksi.getProduk_id());
        jdbcTemplate.update(query,map);
        return transaksi;
    }



    public TransaksiDTO.Update update(TransaksiDTO.Update transaksi){
        String query = "UPDATE transaksi\n" +
                "SET kuantitas=:kuantitas, produk_id=:produk_id\n" +
                "WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", transaksi.getId());
        map.addValue("kuantitas", transaksi.getKuantitas());
        map.addValue("produk_id", transaksi.getProduk_id());
        jdbcTemplate.update(query,map);
        return transaksi;
    }


    public void delete(Integer id) {
        String query = "delete from transaksi where id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        jdbcTemplate.update(query, map);
    }

    public Optional<Transaksi> findById(Integer id) throws EmptyResultDataAccessException {
        String query = "select \n" +
                "t.id as transaksi_id,\n" +
                "p.id as produk_id,\n" +
                "p.nama as produk_nama,\n" +
                "p.jenis as produk_jenis,\n" +
                "p.berat as produk_berat,\n" +
                "t.kuantitas as kuantitas\n" +
                "from transaksi t\n" +
                "left join produk p\n" +
                "on t.produk_id = p.id " +
                "where t.id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Transaksi>>() {
            @Override
            public Optional<Transaksi> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("transaksi_id"));
                transaksi.setKuantitas(rs.getDouble("kuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produk_id"));
                produk.setNama(rs.getString("produk_nama"));
                produk.setJenis(rs.getString("produk_jenis"));
                produk.setBerat(rs.getInt("produk_berat"));

                transaksi.setProduk(produk);
                return Optional.of(transaksi);
            }
        });
    }

    public Optional<TransaksiDTO.Detail> detail(Integer id) throws EmptyResultDataAccessException {
        String query = "select\n" +
                "t.id as id,\n" +
                "p.nama as produk,\n" +
                "p2.nama as produsen,\n" +
                "p.harga as harga,\n" +
                "t.kuantitas as kuantitas,\n" +
                "sum(harga * kuantitas) as totalHarga\n" +
                "from transaksi t\n" +
                "left join produk p on t.produk_id = p.id\n" +
                "left join produsen p2 on p.produsen_id = p2.id\n" +
                "where t.id = :id\n" +
                "group by p.nama, t.id, p2.nama, p.harga, t.kuantitas";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<TransaksiDTO.Detail>>() {
            @Override
            public Optional<TransaksiDTO.Detail> mapRow(ResultSet rs, int rowNum) throws SQLException {
                TransaksiDTO.Detail transaksi = new TransaksiDTO.Detail();
                transaksi.setId(rs.getInt("id"));
                transaksi.setProduk(rs.getString("produk"));
                transaksi.setProdusen(rs.getString("produsen"));
                transaksi.setHarga(rs.getDouble("harga"));
                transaksi.setKuantitas(rs.getInt("kuantitas"));
                transaksi.setTotalHarga(rs.getDouble("totalHarga"));

                return Optional.of(transaksi);
            }
        });
    }
}
