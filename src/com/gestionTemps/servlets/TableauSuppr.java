package com.gestionTemps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.service.TableauxService;

@WebServlet("/TableauSuppr")
public class TableauSuppr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TableauSuppr() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null) {
			response.sendRedirect("./");
		}
		else {
			TableauxService tableauxService = new TableauxService();
			tableauxService.supprimerTableau(request);
			response.sendRedirect("tableaux");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
