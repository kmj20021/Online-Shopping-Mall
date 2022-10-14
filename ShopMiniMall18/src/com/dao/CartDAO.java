package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

public class CartDAO {

	 public List<CartDTO> cartList(SqlSession session, String userid) {
		   List<CartDTO> list = 
				   session.selectList("CartMapper.cartList", userid);
		   return list;
	   }
	public int cartAdd(SqlSession session, CartDTO dto) {
		int n = session.insert("CartMapper.cartAdd", dto);
		return n;
	}
	public int cartDel(SqlSession session, String num) {
		int dnum = session.delete("CartMapper.cartDel",num);
		return dnum;
	}
	public int cartUpdate(SqlSession session, Map<String, String> map) {
		int a = session.update("CartMapper.cartUpdate",map);
		return a;
	}
	public int carAllDel(SqlSession session, List<String> list) {
		int num = session.delete("CartMapper.carAllDel",list);
		return num;
	}
	public CartDTO cartbyNum(SqlSession session, String num) {
		CartDTO cdto = session.selectOne("CartMapper.cartbyNum",num);
		return cdto;
	}
	public int orderDone(SqlSession session, OrderDTO dto2) {
		int n = session.insert("CartMapper.orderDone",dto2);
		return n;
	}

}
