package dataaccess;

import java.sql.*;
import java.util.*;
import javax.persistence.EntityManager;
import models.Role;

/**
 *
 * @author khanhhoanguyen
 */
public class RoleDB {

    public Role get(int roleId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        

        try {
           Role role = em.find(Role.class, roleId);
           return role;
        } finally {
           em.close();
        }

    }
}
