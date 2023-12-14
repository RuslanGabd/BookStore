/*package com.ruslan.servlet;

import com.ruslan.services.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/Books")

public class BookServlet extends HttpServlet {
    @Autowired
   private   BookService bookService;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>List of Books</h1>");
            printWriter.write("<ul>");
            bookService.listBookDto().stream().forEach(bookDto -> {
                printWriter.write(
                        """
                                <li>
                                %s - %s
                                </li>
                                """.formatted(bookDto.getId(), bookDto.getDescription())
                );
            });
            printWriter.write("</ul>");
        }
        ;
        //    super.doGet(req, resp);
    }
}*/
