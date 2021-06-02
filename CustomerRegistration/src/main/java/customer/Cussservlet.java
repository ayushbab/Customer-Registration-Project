package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.Cust;
import customer.Custjdbc;
@WebServlet("/")
public class  Cussservlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String firstname=request.getParameter("firstName");
		String lastname=request.getParameter("lastName");
		String password=request.getParameter("password");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String address=request.getParameter("address");
		int phone=request.getParameter("phone");
		int zip=request.getParameter("zip");
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				default:
					listUser(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<cust> listUser = cust.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.html");
			dispatcher.forward(request, response);
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.html");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("partyId"));
			Cust existingUser = Custjdbc.selectUser(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.html");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);

		}

		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("address");
			Cust newUser = new Cust();
			Cust.insertUser(newUser);
			response.sendRedirect("list");
		}

		private void updateUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("partyId"));
			String firstname = request.getParameter("firstName");
			String email = request.getParameter("lastName");
			
		
		}

		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("partyId"));
			Cust.deleteUser(id);
			response.sendRedirect("list");

		}

		
		
	


}