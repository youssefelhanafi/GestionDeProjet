package com.gestionTemps.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.service.TacheService;

@WebServlet("/TacheSuppr")
public class TacheSuppr extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TacheSuppr() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null) {
			response.sendRedirect("tableaux");
		}
		else {
			TacheService tacheService = new TacheService();
			tacheService.supprimerTache(request);
			response.sendRedirect("tableau?id="+request.getParameter("tableauID"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
