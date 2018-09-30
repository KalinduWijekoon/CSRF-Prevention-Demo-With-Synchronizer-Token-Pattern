/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csrfsynct;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KALINDU
 */
@WebServlet(name = "Submit", urlPatterns = {"/submit"})
public class Submit extends HttpServlet {
    
    String csrfToken = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String csrf = request.getParameter("csrf");
            System.out.println("CSRF in Ajax : "+csrf);
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("JSESSIONID")){
                         csrfToken = new Map().getValue(cookie.getValue());
                         System.out.println("Token in Submit page : "+csrfToken);
                    }
                }
            }
            if(csrf != null){
                if(csrf.equals(csrfToken)){
                    out.println("Form submitted successfully");
                }else{
                    out.println("Error occur while validating the CSRF token");
                }
            }else{
                out.println("CSRF token absent or value is null/empty");
            }
        } finally {
            out.close();
        }
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
    }// </editor-fold>

}
