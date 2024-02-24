package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Card;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/EditCardServlet")
public class EditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CardHelper dao = new CardHelper();
		String number = request.getParameter("cardNumber");
		String name = request.getParameter("cardName");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		Card cardToUpdate = dao.searchForCardById(tempId);
		cardToUpdate.setCardNumber(number);
		cardToUpdate.setCardName(name);
		dao.updateCard(cardToUpdate);
		getServletContext().getRequestDispatcher("/ViewAllCardsServlet").forward(request, response);
	}

}
