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
import java.sql.SQLException;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        int year = Integer.parseInt(req.getParameter("year"));

        // Thông tin kết nối cơ sở dữ liệu
        String url = "jdbc:mysql://localhost:3306/libary?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "duong19082004"; // Thay bằng mật khẩu của bạn

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Thực hiện truy vấn SQL
            String sql = "INSERT INTO books (title, author, published_year) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, year);
            pstmt.executeUpdate();

            // Phản hồi thành công
            resp.setContentType("text/html");
            resp.getWriter().write("<h1>Thêm sách thành công Trần Đăng Dương!</h1>");
        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}
