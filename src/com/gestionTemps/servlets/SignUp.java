package com.gestionTemps.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gestionTemps.beans.Utilisateur;
import com.gestionTemps.service.UtilisateurService;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null) {
			response.sendRedirect("tableaux");
		}
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurService utilisateurService = new UtilisateurService();
		Utilisateur utilisateur = utilisateurService.ajouterUtilisateur(request);
		HttpSession session = request.getSession(true);
		session.setAttribute("userID", utilisateur.getIdUtilisateur());
		session.setAttribute("userFirstName", utilisateur.getPrenomUtilisateur());
		session.setAttribute("userLastName", utilisateur.getNomUtilisateur());
		session.setAttribute("userEmail", utilisateur.getEmailUtilisateur());
		response.sendRedirect("login");
	}

}
