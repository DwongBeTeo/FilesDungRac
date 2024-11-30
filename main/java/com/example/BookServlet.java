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
 * Servlet implementation class BookServlet
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Cấu hình thông tin kết nối
		String url = "jdbc:mysql://localhost:3306/libary?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root"; // Thay bằng user của MySQL
        String password = "duong19082004"; // Thay bằng mật khẩu MySQL

        // Kết nối tới cơ sở dữ liệu và chèn dữ liệu
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO books (title, author, published_year) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Harry Potter");
            pstmt.setString(2, "J.K. Rowling");
            pstmt.setInt(3, 1997);
            pstmt.executeUpdate();

            // Phản hồi lại trình duyệt
            resp.setContentType("text/html");
            resp.getWriter().write("<h1>Thêm sách thành công Trần Đăng Dương!</h1>");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write("<h1>Thêm sách thất bại! Trần Đăng Dương</h1><pre>" + e.getMessage() + "</pre>");
        }
    }
}
