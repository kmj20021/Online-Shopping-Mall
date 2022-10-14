package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.CartService;

/**
 * Servlet implementation class CartOrderDoneServlet
 */
@WebServlet("/CartOrderDoneServlet")
public class CartOrderDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderDoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("login");
		
		if (mdto == null) {
			session.setAttribute("mesg", "로그인이 필요한 서비스 입니다.");
			response.sendRedirect("LoginUIServlet");
		}else {
			
			String userid = mdto.getUserid();
			String gCode = request.getParameter("gCode");
			String gName = request.getParameter("gName");
			String gPrice = request.getParameter("gPrice");
			String gSize = request.getParameter("gSize");
			String gColor = request.getParameter("gColor");
			String gAmount = request.getParameter("gAmount");
			String gImage = request.getParameter("gImage");
			String orderName = request.getParameter("orderName");
			String post = request.getParameter("post");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String phone = request.getParameter("phone");
			String payMethod = request.getParameter("payMethod");
			
			String orderNum = request.getParameter("orderNum");
			
			//insert
			System.out.println(gCode+gName+gPrice+gSize+gColor+gAmount+gImage+orderName+post+addr1+addr2+phone+payMethod);
			//delete
			System.out.println(orderNum);
			
			OrderDTO dto2 = new OrderDTO(0, userid, gCode, gName, Integer.parseInt(gPrice), gSize, gColor, 
					Integer.parseInt(gAmount), 
					gImage, 
					orderName, 
					post, 
					addr1, 
					addr2,
					phone,
					payMethod, null);
			
			CartService cService = new CartService();
			String mesg = cService.orderDone(dto2, orderNum);
			System.out.println(mesg);
			
			request.setAttribute("orderDTO", dto2);
			RequestDispatcher dis = request.getRequestDispatcher("orderDone.jsp");
			dis.forward(request, response);
		}//로그인 처리
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
