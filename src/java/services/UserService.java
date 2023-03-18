package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;
/**
 *
 * @author khanhhoanguyen
 */
public class UserService {
    
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    public void insert(String email, String firstName, String lastName, 
               String password, int roleId) throws Exception {        
        //gets role object based on roleID from parameter
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        
        //create new user object and set role
        User user = new User(email, firstName, lastName, password);
        user.setRole(role);
        
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, String firstName, String lastName, 
        String password, int roleId) throws Exception {
        
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
 
        //get user object from entity manager
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        
        //updating user object
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
    

}
