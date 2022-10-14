package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

public class CartService {
		
	
	 public List<CartDTO> cartList(String userid) {
			SqlSession session = MySqlSessionFactory.getSession();
			List<CartDTO> list = null;
			try {
				CartDAO dao = new CartDAO();
				 list = dao.cartList(session, userid);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
			return list;
		}//end idCheck
	public int cartAdd(CartDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartAdd(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}// end cartAdd
	public int cartDel(String num) {
		SqlSession session = MySqlSessionFactory.getSession();
		int dnum = 0;
		try {
			CartDAO dao = new CartDAO();
			dnum = dao.cartDel(session ,num);
			session.commit();
		} finally {
			session.close();
		}
		return dnum;
	}
	public int cartUpdate(Map<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		int a = 0;
		try {
			CartDAO dao = new CartDAO();
			a = dao.cartUpdate(session,map);
			session.commit();
		} finally {
			session.close();
		}
		return a;
	}
	public int carAllDel(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		int num = 0;
		try {
			CartDAO dao = new CartDAO();
			num =  dao.carAllDel(session,list);
			session.commit();
		} catch (Exception e) {
			session.close();
		}
		return num;
	}
	public CartDTO cartbyNum(String num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDTO cdto = null;
		try {
			CartDAO dao = new CartDAO();
			cdto = dao.cartbyNum(session,num);
		} finally {
			session.close();
		}
		return cdto;
	}
	public String orderDone(OrderDTO dto2, String orderNum) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n =0;
		int m = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.orderDone(session,dto2);
			System.out.println("insert :"+n);
			
			m = dao.cartDel(session, orderNum);
			System.out.println("delete :"+ n);
			
			session.commit();
			System.out.println("commit 완");
		}catch (Exception e) {
				session.rollback();
		} finally {
			session.close();
		}
		String mesg = "insert :"+n+" delete :"+m;
		return mesg;
	}

}// end class
