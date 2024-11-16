/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.music.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList; 
import com.music.model.MusicItem;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author USER
 */
public class ListLibraryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List musics = new ArrayList(); 
        musics.add(new MusicItem("Cardigan", "2020", "Indie Folk","Taylor Swift")); 
        musics.add(new MusicItem("Someone like you", "2011", "Pop/Soul","Adele")); 
        musics.add(new MusicItem("Fight Song", "2015", "Pop","Rachel Platten")); 
        
       
        // Sorting by year (alphabetically)
        Collections.sort(musics, new Comparator<MusicItem>() {
            public int compare(MusicItem m1, MusicItem m2) {
                return m1.getYear().compareTo(m2.getYear());
            }
        });
        // Sorting by artist name (alphabetically)
        Collections.sort(musics, new Comparator<MusicItem>() {
            public int compare(MusicItem m1, MusicItem m2) {
                return m1.getArtist().compareTo(m2.getArtist());
            }
        });
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListLibraryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("You currently have <b>" +musics.size()+ "</b> Musics in your collection <br>");
            out.println("<table border='1' cellspacing='1' cellpadding='5'>");
            out.println("<tr>");
            out.println("<th>TITLE</th>");
            out.println("<th>GENRE</th>");
            out.println("<th>YEAR</th>");
            out.println("<th>ARTIST</th>");
            out.println("</tr>");
            
            
            Iterator it =musics.iterator();
            while (it.hasNext()){
                
                MusicItem item = (MusicItem) it.next();
                out.println("<tr>");
                out.println("<td>" + item.getTitle() + "</td>");
                out.println("<td>" + item.getGenre() + "</td>");
                out.println("<td>" + item.getYear() + "</td>");
                out.println("<td>" + item.getArtist() + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            out.close();
            
        }
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
