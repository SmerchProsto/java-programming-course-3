package com.webservlets.webservlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.util.Random;
import java.io.File;


@WebServlet("/image/png")
public class PhotoServlet extends HttpServlet {
    public void createImg() {
        BufferedImage image = new BufferedImage(640, 120, BufferedImage.TYPE_INT_ARGB);
        Font font = new Font("Arial", Font.BOLD, 72);

        String text = "Java language";

        Random rnd = new Random();
        int red = rnd.nextInt(256);
        int green = rnd.nextInt(256);
        int blue = rnd.nextInt(256);

        Color c1 = new Color(red, green, blue);
        Graphics g = image.getGraphics();
        g.setFont(font);
        g.setColor(c1);
        g.drawString(text, 100, 100);
        g.dispose();
        File outputfile = new File("C:\\Users\\smerc\\OneDrive\\Рабочий стол\\лабы 3ий курс\\Лабы ПП 3 курс\\Лаба 6\\WebServlets\\src\\main\\webapp\\image\\java2.png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        createImg();
        PrintWriter out = resp.getWriter();
        String path = "\"java2.png\"";
        out.println("<img src=" + path + ">");
    }
}
