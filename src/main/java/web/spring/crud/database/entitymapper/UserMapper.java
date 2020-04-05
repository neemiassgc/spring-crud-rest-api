package web.spring.crud.database.entitymapper;

import web.spring.crud.entity.User;

import java.util.Collection;
import java.util.Map;

public final class UserMapper {

    public static User mapperToUser(Map<String, Object> map) {
        User newUser = new User();

        Collection<Object> objectCollection = map.values();
        Object[] objects = objectCollection.toArray(new Object[0]);

        newUser.setId((Integer) objects[0]);
        newUser.setUsername((String) objects[1]);
        newUser.setPhone((String) objects[2]);
        newUser.setEmail((String) objects[3]);
        newUser.setCountry((String) objects[4]);

        return newUser;
    }
}
