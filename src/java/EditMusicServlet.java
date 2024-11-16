import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditMusicServlet extends HttpServlet {
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
//        processRequest(request, response);
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Music WHERE id ="+request.getParameter("id");
            ResultSet rs = stmt.executeQuery(query);

           int id=0;
           String title, artist, genre, yearReleased;
           title = artist= genre= yearReleased="";
           
           while (rs.next()){
               id = rs.getInt("id");
               title = rs.getString("title");
               artist = rs.getString("artist");
               genre = rs.getString("genre");
               yearReleased = rs.getString("yearReleased");
           }
           response.setContentType("text/html;charset=UTF-8");
           PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<title>Edit Music</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body class='bg-light'>");
            out.println("<div class='container d-flex justify-content-center align-items-center min-vh-100'>");
            out.println("<div class='card shadow-sm p-4' style='width: 100%; max-width: 500px;'>");
            out.println("<h1 class='text-center mb-4'>Edit Music</h1>");
            out.println("<form action='EditMusicServlet' method='POST'>");
            out.println("<input type='hidden' name='id' value='" + id + "'>");
            out.println("<div class='mb-3'>");
            out.println("<label for='title' class='form-label'>Title</label>");
            out.println("<input type='text' class='form-control' id='title' name='title' value='" + title + "' required>");
            out.println("</div>");
            out.println("<div class='mb-3'>");
            out.println("<label for='artist' class='form-label'>Artist</label>");
            out.println("<input type='text' class='form-control' id='artist' name='artist' value='" + artist + "' required>");
            out.println("</div>");
            out.println("<div class='mb-3'>");
            out.println("<label for='genre' class='form-label'>Genre</label>");
            out.println("<input type='text' class='form-control' id='genre' name='genre' value='" + genre + "' required>");
            out.println("</div>");
            out.println("<div class='mb-3'>");
            out.println("<label for='yearReleased' class='form-label'>Year Released</label>");
            out.println("<input type='text' class='form-control' id='yearReleased' name='yearReleased' value='" + yearReleased + "' required>");
            out.println("</div>");
            out.println("<button type='submit' class='btn btn-primary w-100 mb-3'>Update Music</button>");
            out.println("</form>");
            out.println("<a href='ListMusicServlet' class='btn btn-secondary w-100'>Back</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
//        processRequest(request, response);
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String artist = request.getParameter("artist");
            String genre = request.getParameter("genre");
            String yearReleased = request.getParameter("yearReleased");

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
            String query = "UPDATE Music SET title = ?, artist = ?, genre = ?, yearReleased = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, artist);
            stmt.setString(3, genre);
            stmt.setString(4, yearReleased);
            stmt.setInt(5, id);

            stmt.executeUpdate();
            conn.close();

            response.sendRedirect("ListMusicServlet");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
