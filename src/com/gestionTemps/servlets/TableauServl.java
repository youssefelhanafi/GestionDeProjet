package com.gestionTemps.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.beans.Marque;
import com.gestionTemps.beans.Tableau;
import com.gestionTemps.beans.TableauCommit;
import com.gestionTemps.beans.Tache;
import com.gestionTemps.service.TableauService;

@WebServlet("/Tableau")
public class TableauServl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableauService tableauService = new TableauService();
       
    public TableauServl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null) {
			Tableau t = tableauService.retournerTableau(request);
			if(t.getUserID() != request.getSession().getAttribute("userID")) {
				response.sendRedirect("tableaux");
			} else {
				List<TableauCommit> commits = tableauService.recupererToutesLesCommitesDuTableau(request);
				List<Marque> marques = tableauService.retournerToutesLesMarquesDuTableau(t.getIdTableau());
				List<Tache> taches = tableauService.retournerToutesLesTachesDuTableau(t.getIdTableau());
				t.setNbrCommits(commits.size());
				t.setNbrTaches(taches.size());
				request.setAttribute("tableau", t);
				request.setAttribute("commits", commits);
				request.setAttribute("marques", marques);
				request.setAttribute("taches", taches);
				
				request.setAttribute("dateDebut", tableauService.getStartDate(taches));
				request.setAttribute("dateLimite", tableauService.getEndDate(taches));
				request.setAttribute("hautes", tableauService.getHighPriorityCount(taches));
				request.setAttribute("moyennes", tableauService.getNormalPriorityCount(taches));
				request.setAttribute("basses", tableauService.getLowPriorityCount(taches));
				request.setAttribute("nbrTaches", taches.size());
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/tableau.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		tableauService.ajouterListeDansLeTableau(request);
		doGet(request, response);
	}

}
