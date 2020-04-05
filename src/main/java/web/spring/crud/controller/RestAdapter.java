package web.spring.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import web.spring.crud.entity.User;

import java.util.Map;

interface RestAdapter {

    String get(Model model);

    ResponseEntity<String> put(Map<String, Object> payload);

    ResponseEntity<String> post(User user);

    ResponseEntity<String> delete(int id);
}
