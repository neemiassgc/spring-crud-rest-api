package web.spring.crud.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import web.spring.crud.entity.User;

import java.sql.Types;
import java.util.List;
import java.util.Vector;

@Repository
public class Database implements DatabaseAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql;

    @Override
    public void createUser(User user) {
        sql =
        "INSERT INTO users (name, surname, phone, country, email) "+
        "VALUES (?, ?, ?, ?, ?);";

        this.jdbcTemplate.update(
            sql, user.getName(), user.getSurname(), user.getPhone(), user.getCountry(), user.getEmail());
    }

    @Override
    public void updateUser(User user) {
        sql = "UPDATE users SET";
        List<String> args = new Vector<>();
        List<Object> objects = new Vector<>();
        List<Integer> types = new Vector<>();

        if(user.getName() != null){
            args.add(" name = ?");
            objects.add(user.getName());
            types.add(Types.VARCHAR);
        }
        if(user.getSurname() != null) {
            args.add(" surname = ?");
            objects.add(user.getSurname());
            types.add(Types.VARCHAR);
        }
        if(user.getPhone() != null) {
            args.add(" phone = ?");
            objects.add(user.getPhone());
            types.add(Types.VARCHAR);
        }
        if(user.getCountry() != null) {
            args.add(" country = ?");
            objects.add(user.getCountry());
            types.add(Types.VARCHAR);
        }
        if(user.getEmail() != null) {
            args.add(" email = ?");
            objects.add(user.getEmail());
            types.add(Types.VARCHAR);
        }

        objects.add(user.getId());
        types.add(Types.INTEGER);

        sql += String.join(",", args) +" WHERE id = ?;";

        this.jdbcTemplate.update(sql, objects.toArray(), types.stream().mapToInt(n -> n).toArray());
    }

    @Override
    public void deleteUser(int id) {
        sql = "DELETE FROM users WHERE id = ?;";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> getAllUsers() {
        sql = "SELECT * FROM users ORDER BY id ASC;";
        return this.jdbcTemplate.query(sql, (rs, intRow) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setPhone(rs.getString("phone"));
            user.setCountry(rs.getString("country"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    @Override
    public void deleteAllUsers() {
        sql = "TRUNCATE users;";
        this.jdbcTemplate.update(sql);
    }
}
