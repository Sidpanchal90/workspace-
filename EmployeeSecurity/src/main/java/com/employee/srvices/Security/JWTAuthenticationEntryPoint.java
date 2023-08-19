package com.employee.srvices.Security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        /*Prints formatted representations of objects to a text-output stream. Thisclass implements all of the print methods found in PrintStream.
        It does not contain methods for writing raw bytes, for whicha program should use unencoded byte streams. */
        
        //getWriter():-  Returns a PrintWriter object that can send character text to the client. 
        
        PrintWriter writer = response.getWriter();      
        writer.println("Access Denied !! " + authException.getMessage());
	}
}
