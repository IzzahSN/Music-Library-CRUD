/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class AddMusicServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //check if the user is logged in
        HttpSession session = request.getSession(false);
        if(session==null || session.getAttribute("username")==null) {
        //redirect to login page if no session exists
        response.sendRedirect("login.html");
        return;
        }
        
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");
        String yearReleased = request.getParameter("yearReleased");
        
        try {
           Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app","app");
           String query = "INSERT INTO Music (title, artist, genre, yearReleased) VALUES (?,?,?,?)";
           PreparedStatement stmt = conn.prepareStatement(query);
           stmt.setString(1,title);
           stmt.setString(2,artist);
           stmt.setString(3,genre);
           stmt.setInt(4,Integer.parseInt(yearReleased));
           stmt.executeUpdate();
           conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        response.sendRedirect("ListMusicServlet");
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
