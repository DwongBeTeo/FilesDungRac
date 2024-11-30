package com.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Servlet implementation class DisplayBooksServlet
 */
@WebServlet("/displaybooks")
public class DisplayBooksServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/libary";
        String user = "root";
        String password = "duong19082004"; // Thay "password" bằng mật khẩu thật của bạn.

        resp.setContentType("text/html");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            resp.getWriter().write("<h1>Danh sách sách Trần Đăng Dương:</h1><ul>");
            while (rs.next()) {
                resp.getWriter().write("<li>" + rs.getString("title") + " - " + rs.getString("author") + " (" + rs.getInt("published_year") + ")</li>");
            }
            resp.getWriter().write("</ul>");
        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}