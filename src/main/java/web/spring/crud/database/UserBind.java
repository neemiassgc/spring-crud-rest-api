package web.spring.crud.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import web.spring.crud.database.entitymapper.UserMapper;
import web.spring.crud.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserBind implements BindAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql;

    @Override
    public void createUser(User user) {
        sql =
        "INSERT INTO users (id, username, phone, email, country) "+
        "VALUES (?, ?, ?, ?, ?);";
        this.jdbcTemplate.update(
            sql, user.getId(), user.getUsername(), user.getPhone(), user.getEmail(), user.getCountry()
        );
    }

    @Override
    public User getUser(int id) {
        sql = "SELECT * FROM users WHERE id = ?;";
        List<Map<String, Object>> mapList = this.jdbcTemplate.queryForList(sql, id);

        return UserMapper.mapperToUser(mapList.get(0));
    }

    @Override
    public void updateUser(User user) {
        sql = "UPDATE users SET id = ?, username = ?, phone = ?, email = ?, country = ? WHERE id = ?;";
        this.jdbcTemplate.update(
            sql, user.getId(), user.getUsername(), user.getPhone(), user.getEmail(), user.getCountry(), user.getId()
        );
    }

    @Override
    public void deleteUser(int id) {
        sql = "DELETE FROM users WHERE id = ?;";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAllUsers() {
        sql = "SELECT * FROM users ORDER BY id ASC;";
        List<User> listOutput = new ArrayList<>();
        List<Map<String, Object>> mapList = this.jdbcTemplate.queryForList(sql);

        for(Map<String, Object> map : mapList) {
            listOutput.add(UserMapper.mapperToUser(map));
        }

        return listOutput;
    }

    @Override
    public int getUserAmount() {
        sql = "SELECT COUNT(*) FROM users;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
