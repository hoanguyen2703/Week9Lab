package services;

import dataaccess.RoleDB;
import models.Role;
/**
 *
 * @author khanhhoanguyen
 */
public class RoleService {
    public Role get(int roleId) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        return role;
    }
}
