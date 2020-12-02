package es.upm.dit.apsv.cris.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig; 

import es.upm.dit.apsv.cris.model.Researcher;

@WebServlet("/ResearchersListServlet")
public class ResearchersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = ClientBuilder.newClient(new ClientConfig());
		List<Researcher> researcherslist  = client.target("http://localhost:8080/CRISSERVICE/rest/Researchers")
				.request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Researcher>>() {});
		request.setAttribute ("researcherslist", researcherslist);
		getServletContext().getRequestDispatcher("/ResearchersListView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
