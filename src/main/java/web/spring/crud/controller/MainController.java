package web.spring.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.spring.crud.database.DataBind;
import web.spring.crud.entity.Fallback;
import web.spring.crud.entity.User;

import java.util.List;
import java.util.Map;

@Controller
public final class MainController implements RestAdapter {

    @Autowired
    private DataBind dataBind;

    @Override
    @GetMapping(path = "/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity
            .ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "*")
            .header("Access-Control-Allow-Headers", "*")
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.dataBind.getAllUsers());
    }

    @Override
    @ResponseBody
    @PostMapping(path = "/insertUser", consumes = "application/json")
    public ResponseEntity<Fallback> insert(@RequestBody Map<String, Object> req) {
        Fallback fallback = new Fallback();

        try {
            String name = (String) req.get("name");
            String surname = (String) req.get("surname");
            String phone = (String) req.get("phone");
            String email = (String) req.get("email");
            String country = (String) req.get("country");

            if(name.length() > 50 || name.length() < 3 || surname.length() > 50 ||
                surname.length() < 3 || phone.length() < 12 || phone.length() > 20 ||
                email.length() > 100 || email.length() < 10 || country.length() > 30 ||
                country.length() < 3) {
                fallback.setSuccess(false);
                fallback.setMessage("User doesn't create");

            }
            else {
                User user = new User();
                user.setName(name);
                user.setSurname(surname);
                user.setPhone(phone);
                user.setCountry(country);
                user.setEmail(email);

                this.dataBind.createUser(user);
                fallback.setMessage("User has been created");
            }
        }
        catch(Exception exception) {
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
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "*")
            .header("Access-Control-Allow-Headers", "*")
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

        if(name != null) {
            if(name.length() > 50 || name.length() < 3) {
                fallback.setSuccess(false);
                fallback.setMessage("User doesn't updated. Name isn't valid");
            }
        }
        if(surname != null) {
            if(surname.length() > 50 || surname.length() < 3) {
                fallback.setSuccess(false);
                fallback.setMessage("User doesn't updated. Surname isn't valid");
            }
        }
        if(phone != null) {
            if(phone.length() < 12 || phone.length() > 20) {
                fallback.setSuccess(false);
                fallback.setMessage("User doesn't updated. Phone isn't valid");
            }
        }
        if(email != null) {
            if(email.length() > 100 || email.length() < 10) {
                fallback.setSuccess(false);
                fallback.setMessage("User doesn't updated. Email isn't valid");
            }
        }
        if(country != null) {
            if(country.length() > 30 || country.length() < 3) {
                fallback.setSuccess(false);
                fallback.setMessage("User doesn't updated. Country isn't valid");
            }
        }

        if(fallback.isSuccess()) {
            try {
                User user = new User();
                user.setId((Integer)req.get("id"));
                user.setName(name);
                user.setSurname(surname);
                user.setPhone(phone);
                user.setCountry(country);
                user.setEmail(email);

                this.dataBind.updateUser(user);
                fallback.setMessage("User has been updated");
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
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "*")
            .header("Access-Control-Allow-Headers", "*")
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }

    @Override
    @ResponseBody
    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity<Fallback> delete(@RequestParam(value = "id", defaultValue = "-1") int id) {
        Fallback fallback = new Fallback();

        this.dataBind.deleteUser(id);

        fallback.setMessage("User has been deleted");

        return ResponseEntity
            .ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "*")
            .header("Access-Control-Allow-Headers", "*")
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }

    @Override
    @DeleteMapping(path = "/deleteAllUsers")
    public ResponseEntity<Fallback> deleteAll() {
        Fallback fallback = new Fallback();
        this.dataBind.deleteAllUsers();

        fallback.setMessage("All users have been deleted");

        return ResponseEntity
            .ok()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "*")
            .header("Access-Control-Allow-Headers", "*")
            .contentType(MediaType.APPLICATION_JSON)
            .body(fallback);
    }
}
