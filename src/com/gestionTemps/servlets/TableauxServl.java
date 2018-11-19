package com.gestionTemps.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionTemps.beans.Tableau;
import com.gestionTemps.service.TableauxService;

@WebServlet("/Tableaux")
public class TableauxServl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableauxService tableauxService = new TableauxService();
       
    public TableauxServl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null) {
			response.sendRedirect("./");
		}
		else {
			List<Tableau> tableaux = tableauxService.retournerTousLesTableaux(request);
			request.setAttribute("tableaux", tableaux);
			this.getServletContext().getRequestDispatcher("/WEB-INF/tableaux.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		tableauxService.ajouterTableau(request);
		doGet(request, response);
	}

}
