package com.sts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.sts.model.User;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    // Hapus @Autowired: Tidak diperlukan jika hanya ada satu constructor
    public UserDao(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- RowMapper untuk Mapping Object User ---\r\n
    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        // Hapus @NonNull dari int rowNum
        public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException { 
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password")); 
            user.setName(rs.getString("name"));
            user.setPhoneNumber(rs.getString("phoneNumber")); 
            return user;
        }
    }
    // -----------------------------------------------------------

    // C - Create/Save
    public void save(User user) {
        // GANTI NAMA TABEL KE 'Users'
        String sql = "INSERT INTO Users (username, password, name, phoneNumber) VALUES (?, ?, ?, ?)"; 
        jdbcTemplate.update(
            sql, 
            user.getUsername(), 
            user.getPassword(), 
            user.getName(), 
            user.getPhoneNumber()
        );
    }

    // R - Read All 
    public List<User> findAll() {
        // GANTI NAMA TABEL KE 'Users'
        String sql = "SELECT id, username, password, name, phoneNumber FROM Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    // R - Find By Username and Password
    public User findByUsernameAndPassword(String username, String password) {
        // GANTI NAMA TABEL KE 'Users'
        String sql = "SELECT id, username, password, name, phoneNumber FROM Users WHERE username = ? AND password = ?";
        
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username, password);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    // U - Update 
    public void update(long id, User userDetails) {
        // GANTI NAMA TABEL KE 'Users'
        String sql = "UPDATE Users SET username = ?, password = ?, name = ?, phoneNumber = ? WHERE id = ?";
        jdbcTemplate.update(
            sql, 
            userDetails.getUsername(), 
            userDetails.getPassword(), 
            userDetails.getName(), 
            userDetails.getPhoneNumber(), 
            id
        );
    }
    
    // R - Find By ID
    public User findById(long id) {
        // GANTI NAMA TABEL KE 'Users'
        String sql = "SELECT id, username, password, name, phoneNumber FROM Users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        } // <--- Penambahan brace penutup yang hilang
    } // <--- Penambahan brace penutup method yang hilang
}