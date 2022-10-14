package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.MemberService;

/**
 * Servlet implementation class CartOrderConfirmServlet
 */
@WebServlet("/CartOrderConfirmServlet")
public class CartOrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("login"); //로그인 한 사람 정보
		
		if (mdto == null) {
			session.setAttribute("mesg", "로그인이 필요합니다");
			response.sendRedirect("LoginUIServlet");
		}else {
		String num = request.getParameter("num");// 주문 번호
		
		CartService service = new CartService();
		CartDTO cdto = service.cartbyNum(num);
		//System.out.println(cdto);
		request.setAttribute("cDTO", cdto);
		
		MemberService Mservice = new MemberService();
		MemberDTO Mdto = Mservice.mypage(mdto.getUserid()); //db가서 id로 가져온 정보
		//System.out.println(Mdto);
		request.setAttribute("mDTO", Mdto);
		
		RequestDispatcher dis  = request.getRequestDispatcher("orderConfirm.jsp");
		dis.forward(request, response);
		}//회원 처리
		
	}//doget 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
