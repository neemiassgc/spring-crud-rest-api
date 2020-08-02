package web.spring.crud.controller;

import org.springframework.http.ResponseEntity;
import web.spring.crud.service.Fallback;
import web.spring.crud.entity.User;

import java.util.List;
import java.util.Map;

interface RestAdapter {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity<Fallback> update(Map<String, Object> req);

    ResponseEntity<Fallback> insert(Map<String, Object> req);

    ResponseEntity<Fallback> delete(int id);

    ResponseEntity<Fallback> deleteAll();
}
