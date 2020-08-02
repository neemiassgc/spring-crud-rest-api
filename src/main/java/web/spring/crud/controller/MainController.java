package web.spring.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.spring.crud.database.Database;
import web.spring.crud.service.Fallback;
import web.spring.crud.entity.User;
import web.spring.crud.service.Validator;

import java.util.List;
import java.util.Map;

@Controller
//@CrossOrigin("https://sevlasnog.github.io/spring-crud")
@CrossOrigin("*")
public final class MainController implements RestAdapter {

    @Autowired
    private Database database;

    @Override
    @GetMapping(path = "/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.database.getAllUsers());
    }

    @Override
    @ResponseBody
    @PostMapping(path = "/insertUser", consumes = "application/json")
    public ResponseEntity<Fallback> insert(@RequestBody Map<String, Object> req) {
        Fallback fallback;

        try {
            if(req.size() < 5) throw new NullPointerException();

            String name = (String) req.get("name");
            String surname = (String) req.get("surname");
            String phone = (String) req.get("phone");
            String email = (String) req.get("email");
            String country = (String) req.get("country");

            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setPhone(phone);
            user.setCountry(country);
            user.setEmail(email);

            fallback = Validator.isUserValid(user);

            if(fallback.isSuccess()) this.database.createUser(user);
        }
        catch(Exception exception) {
            fallback = new Fallback();
            fallback.setSuccess(false);
            
            if(exception instanceof NullPointerException) {
                fallback.setMessage("All fields must be filled");   
            }
            else if(exception instanceof DuplicateKeyException) {
                fallback.setMessage("This data is already exist");
            }
        }

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }

    @Override
    @ResponseBody
    @PutMapping(path = "/editUser", consumes = "application/json")
    public ResponseEntity<Fallback> update(@RequestBody Map<String, Object> req) {
        Fallback fallback = new Fallback();

        if(!req.containsKey("id")) {
            fallback.setSuccess(false);
            fallback.setMessage("Missing user id");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fallback);
        }

        String name = req.containsKey("name") ? (String)req.get("name") : null;
        String surname = req.containsKey("surname") ? (String)req.get("surname") : null;
        String phone = req.containsKey("phone") ? (String)req.get("phone") : null;
        String email = req.containsKey("email") ? (String)req.get("email") : null;
        String country = req.containsKey("country") ? (String)req.get("country") : null;

        User user = new User();
        user.setId((Integer) req.get("id"));
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setCountry(country);
        user.setEmail(email);

        if((fallback = Validator.isUserValid(user)).isSuccess()) {
            try {
                this.database.updateUser(user);
            }
            catch(Exception exception) {
                fallback.setSuccess(false);
                if(exception instanceof DuplicateKeyException) {
                    fallback.setMessage("This data is already exist");
                }
            }
        }

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }

    @Override
    @ResponseBody
    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity<Fallback> delete(@RequestParam(value = "id", defaultValue = "-1") int id) {
        Fallback fallback = new Fallback();

        this.database.deleteUser(id);

        fallback.setMessage("User has been deleted");

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }

    @Override
    @DeleteMapping(path = "/deleteAllUsers")
    public ResponseEntity<Fallback> deleteAll() {
        Fallback fallback = new Fallback();
        this.database.deleteAllUsers();

        fallback.setMessage("All users have been deleted");

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }
}
