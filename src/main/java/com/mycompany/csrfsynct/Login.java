/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csrfsynct;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author KALINDU
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    
    
       String generateToken(){
            SecureRandom secureRandom = new SecureRandom();
            byte[] buffer = new byte[50];
            secureRandom.nextBytes(buffer);
            return DatatypeConverter.printHexBinary(buffer);
        }
    
    
    String jsession;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if(username.equals("admin")&&password.equals("admin")){
                Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("JSESSIONID")){
                            jsession = cookie.getValue();
                        }else{
                            out.println("no JSESSIONID cookies!");
                        }
                    }
                }else{
                    out.println("null cookies!");
                }
                String csrfToken = generateToken();
                System.out.println("Session ID : "+jsession);
                System.out.println("CSRF Token : "+csrfToken);
                new Map().setValue(jsession, csrfToken);

                response.sendRedirect("form.jsp");
                
            }else{
                out.println("Invalid username and/or password");
            }
        }finally{
            out.close();
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
