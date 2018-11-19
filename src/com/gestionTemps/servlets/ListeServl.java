package com.gestionTemps.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.beans.Liste;
import com.gestionTemps.beans.Tache;
import com.gestionTemps.service.ListeService;

@WebServlet("/ListeServl")
public class ListeServl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListeService listeService = new ListeService();

    public ListeServl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Liste liste = listeService.retournerListe(request);
		List<Tache> taches = listeService.retournerTachesDeLaListe(liste.getIdListe());
		request.setAttribute("liste", liste);
		request.setAttribute("taches", taches);
		this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listeService.ajouterTacheDansListe(request);
		doGet(request, response);
	}

}
