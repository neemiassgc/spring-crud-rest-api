package web.spring.crud.service;

import org.springframework.stereotype.Service;
import web.spring.crud.entity.User;

@Service
public final class Validator {

    static public Fallback isUserValid(User user) {
        final Fallback fallback = new Fallback();
        String msg = "";

        if(user.getName().length() > 50) msg = "User's name is too long";
        else if(user.getName().length() < 3) msg = "User's name is too sort";
        else if(user.getSurname().length() > 50) msg = "User's surname is too long";
        else if(user.getSurname().length() < 3) msg = "User's surname is too sort";
        else if(user.getPhone().length() < 12) msg = "User's phone is too long";
        else if(user.getPhone().length() > 20) msg = "User's phone is too sort";
        else if(user.getEmail().length() > 100) msg = "User's email is too long";
        else if(user.getEmail().length() < 10) msg = "User's email is too sort";
        else if(user.getCountry().length() > 30) msg = "User's country is too long";
        else if(user.getCountry().length() < 3) msg = "User's country is too sort";
        else if(!user.getPhone().matches("^\\+\\d{11,}"))
            msg = "User's phone is not valid phone number";
        else if(!user.getEmail().matches("\\w{3,}@\\w{2,}\\.\\w{2,}"))
            msg = "User's email is not valid email";
        else {
            fallback.setSuccess(true);
            msg = "User have been created successfully";
        }

        fallback.setMessage(msg);
        return fallback;
    }
}
