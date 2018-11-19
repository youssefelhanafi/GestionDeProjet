package com.gestionTemps.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.beans.Marque;
import com.gestionTemps.beans.Tache;
import com.gestionTemps.service.TacheService;

@WebServlet("/TacheServl")
public class TacheServl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TacheService tacheService = new TacheService();

    public TacheServl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tache tache = tacheService.retournerTache(request);
		request.setAttribute("tache", tache);
		List<Marque> marques = tacheService.retournerTouesLesMarquesDeLaTache(tache.getIdTache());
		request.setAttribute("marques", marques);
		this.getServletContext().getRequestDispatcher("/WEB-INF/tache.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		tacheService.ajouterTache(request);
		response.sendRedirect("tableau?id="+request.getParameter("tableauID"));
	}

}
