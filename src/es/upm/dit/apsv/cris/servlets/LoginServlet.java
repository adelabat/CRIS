package es.upm.dit.apsv.cris.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import es.upm.dit.apsv.cris.model.Researcher;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;      

	final String ADMIN = "root";

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);

	}

	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");

		String password = request.getParameter("password");

		Researcher researcher = null;

		if (ADMIN.equals(email) && ADMIN.equals(password)) {

			researcher = new Researcher();

			researcher.setId("root");

			request.getSession().setAttribute("user", researcher);

			response.sendRedirect(request.getContextPath() + "/AdminServlet");

			return;

		}

		try {

			Client client = ClientBuilder.newClient(new ClientConfig());

			Response resp = client.register(JsonProcessingFeature.class)

					.target("https://moodle.upm.es/titulaciones/oficiales/login/token.php"+

	          "?username="+URLEncoder.encode(email,"UTF-8")+

	          "&password="+URLEncoder.encode(password,"UTF-8")+

							"&service=moodle_mobile_app").request()

					.post(Entity.entity(researcher, MediaType.APPLICATION_JSON), Response.class);

			String token = resp.readEntity(JsonObject.class).get("token").toString();

			if ((token != null) && (token.length() > 0)) {

				researcher =

						client.target("http://localhost:8080/CRISSERVICE/rest/Researchers/email/")

						.queryParam("email", email).request()

						.accept(MediaType.APPLICATION_JSON).get(Researcher.class);

			}

		} catch(Exception e) {}

		if (null != researcher) {

			request.getSession().setAttribute("user", researcher);

			response.sendRedirect(request.getContextPath() + "/ResearcherServlet" + "?id="

	        + researcher.getId());

		}

		else {

			request.getSession().invalidate();

			request.setAttribute("message", "Invalid user or password");

			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(request, response);

		}

	}	

}
