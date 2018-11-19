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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null) {
			response.sendRedirect("tableaux");
		}
		else {
			response.sendRedirect("./");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurService utilisateurService = new UtilisateurService();
		Utilisateur utilisateur = null;
		System.out.println(request.getRequestURI());
		try {
			utilisateur = utilisateurService.verifierIdentifiant(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(utilisateur == null)
			response.sendRedirect("./");
		else {
			HttpSession session = request.getSession(true);
			session.setAttribute("userID", utilisateur.getIdUtilisateur());
			session.setAttribute("userFirstName", utilisateur.getPrenomUtilisateur());
			session.setAttribute("userLastName", utilisateur.getNomUtilisateur());
			session.setAttribute("userEmail", utilisateur.getEmailUtilisateur());
			response.sendRedirect("tableaux");
		}
	}

}
