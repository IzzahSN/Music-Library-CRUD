import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListMusicServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Music Library</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'>");
        out.println("</head>");
        out.println("<body class='bg-light'>");

        out.println("<div class='container my-5'>");
        out.println("<h1 class='text-center mb-4'>Music Library</h1>");
        
        out.println("<div class='card shadow-sm p-4 mb-4'>");
        out.println("<h4 class='card-title'>Music Collection</h4>");
        
        out.println("<div class='table-responsive'>");
        out.println("<table class='table table-hover table-bordered'>");
        out.println("<thead class='table-dark'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Title</th>");
        out.println("<th>Artist</th>");
        out.println("<th>Genre</th>");
        out.println("<th>Year Released</th>");
        out.println("<th>Actions</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Music";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                int yearReleased = rs.getInt("yearReleased");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + title + "</td>");
                out.println("<td>" + artist + "</td>");
                out.println("<td>" + genre + "</td>");
                out.println("<td>" + yearReleased + "</td>");
                out.println("<td class='d-flex w-100'>");
                out.println("<a href='EditMusicServlet?id=" + id + "' class='btn btn-warning btn-sm flex-fill me-2'>Edit</a>");
                out.println("<a href='DeleteMusicServlet?id=" + id + "' class='btn btn-danger btn-sm flex-fill' onclick='return confirm(\"Are you sure you want to delete this music item?\")'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='6' class='text-center text-danger'>Error retrieving music data: " + e.getMessage() + "</td></tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</div>");

        // Back button
        out.println("<div class='d-flex justify-content-between'>");
        out.println("<a href='index.html' class='btn btn-secondary'>Back</a>");
        out.println("<a href='add_music.html' class='btn btn-primary'>Add New Music</a>");
        out.println("</div>");
        
        out.println("</div>");

        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js'></script>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
