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

import es.upm.dit.apsv.cris.dao.ResearcherDAO;
import es.upm.dit.apsv.cris.dao.ResearcherDAOImplementation;
import es.upm.dit.apsv.cris.model.Researcher;

@WebServlet("/CreateResearcherServlet")
public class CreateResearcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;      

	final String ADMIN = "root";

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);

	}

	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Researcher researcher = null;
		Researcher r = (Researcher)request.getSession().getAttribute("user");
		if ((r!=null) && r.getId().equals("root")) {
			
		try {
			 Client client = ClientBuilder.newClient(new ClientConfig());
			List<Researcher> researcherslist  = client.target("http://localhost:8080/CRISSERVICE/rest/Researchers")
			.request().accept(MediaType.APPLICATION_JSON)
			.get(new GenericType<List<Researcher>>() {});
			
			for(Researcher investigator: researcherslist) {
				
				if (investigator.getId().equals(request.getParameter("id"))) 
					//Se debe indicar que ya hay alguien que tiene esa id
				{
					request.getSession().invalidate();

					request.setAttribute("message", "Invalid user because other user has the same ID");

					getServletContext().getRequestDispatcher("/AdminView.jsp").forward(request, response);

				}
		}
			researcher = new Researcher();
			researcher.setId(request.getParameter("id"));
			researcher.setName(request.getParameter("name"));
			researcher.setLastname(request.getParameter("lastname"));
			researcher.setEmail(request.getParameter("email"));
			
			ResearcherDAO dao = ResearcherDAOImplementation.getInstance();
			dao.create(researcher);
			
			response.sendRedirect(request.getContentType() + "/AdminServlet");
			
		} catch(Exception e) {}
	}		
		else
			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(request, response);
	}	

}
