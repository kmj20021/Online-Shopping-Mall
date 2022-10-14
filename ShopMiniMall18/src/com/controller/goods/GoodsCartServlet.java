package com.controller.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;
import com.service.MemberService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/GoodsCartServlet")
public class GoodsCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("login");
		 String nextPage = null;
	      if(dto!=null) {
	    	String gImage = request.getParameter("gImage");
	  		String gCode = request.getParameter("gCode");
	  		String gName = request.getParameter("gName");
	  		String gPrice = request.getParameter("gPrice");
	  		String gSize = request.getParameter("gSize");
	  		String gColor = request.getParameter("gColor");
	  		String gAmount = request.getParameter("gAmount");
	  		String userid=dto.getUserid();
	  		
	  		CartDTO xx  = new CartDTO();
	  		xx.setgImage(gImage);
	  		xx.setgCode(gCode);
	  		xx.setgName(gName);
	  		xx.setgPrice(Integer.parseInt(gPrice));
	  		xx.setgSize(gSize);
	  		xx.setgColor(gColor);
	  		xx.setgAmount(Integer.parseInt(gAmount));
	  		xx.setUserid(userid);
	  		
	  		CartService service = new CartService();
	  		int n=service.cartAdd(xx);
			nextPage = "GoodsRetrieveServlet?gCode="+gCode;
			session.setAttribute("mesg",gCode+" Cart저장성공" );

	      }else {
			  nextPage = "LoginUIServlet";
			  session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		  }
	     
	    response.sendRedirect(nextPage);
	/*	RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
