package com.gestionTemps.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.service.ListeDAOImpl;

@WebServlet("/ListeSuppr")
public class ListeSuppr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListeSuppr() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListeDAOImpl listeDAOImpl = new ListeDAOImpl();
		Long listeID = Long.parseLong(request.getParameter("id"));
		listeDAOImpl.supprimerListe(listeID);
		ServletContext context= getServletContext();
		RequestDispatcher rd= context.getRequestDispatcher("/tableaux");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
