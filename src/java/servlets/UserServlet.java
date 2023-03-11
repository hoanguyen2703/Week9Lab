package servlets;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import services.*;
import java.util.logging.*;
import models.*;

/**
 *
 * @author khanhhoanguyen
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //servlet-service-userdb
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");

        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
            if (users.isEmpty()) {
                request.setAttribute("message", "empty");
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        if (action != null) {
            if (action.equals("edit")) {
                try {
                    String email = request.getParameter("email");
                    String role_name = request.getParameter("role");
                    User user = us.get(email);
                    request.setAttribute("email", email);
                    request.setAttribute("selectedUser", user);
                    request.setAttribute("message", "edit");
                    
                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("delete")) {
                try {
                    String email = request.getParameter("email");
                    us.delete(email);
                    request.setAttribute("message", "delete");
                    response.sendRedirect("/");
                    return;
                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       UserService us = new UserService();
        RoleService rs = new RoleService();

        String email = request.getParameter("email");
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String pw = request.getParameter("pw");
        String id = request.getParameter("role"); //name
        int user_role_id = 0;

        String action = request.getParameter("action");

        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
            if (email == null || email.equals("") || first == null || first.equals("") || last == null || last.equals("")
                    || pw == null || pw.equals("")) {
                request.setAttribute("mes", "All fields are required");
                if (users.isEmpty()) {
                    request.setAttribute("message", "empty");
                }
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
            }

            if (action != null) {
                switch (action) {
                    case "add":
                        if (id.equals("1")) {
                            user_role_id = 1;
                        } else {
                            user_role_id = 2;
                        }
                        //check the email                     
                        for (int i = 0; i < users.size(); i++) {
                            if (email.equals(users.get(i).getEmail())) {
                                request.setAttribute("mes", "Error. Email is already taken");
                                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                            }
                        }
                        us.insert(email, first, last, pw, rs.get(user_role_id));
                        request.setAttribute("message", "add");
                        break;
                    case "update":
                        if (id.equals("system admin")) {
                            user_role_id =1 ;
                        } else{
                           user_role_id = 2;
                        }
                        
                        us.update(email, first, last, pw, rs.get(user_role_id));
                        request.setAttribute("message", "update");
                        break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }


}
