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
    //returns list
    public List<Role> getAll() throws Exception {
        List<Role> roles = new ArrayList<>();
        //retrieve the connection pool-using singleton pattern(cannot call a constructor directly
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT role_id, role_name FROM role";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Role role = new Role(id, name);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }

    public Role get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        

        try {
           Role role = em.find(Role.class, id);
           return role;
        } finally {
           em.close();
        }

    }
}
