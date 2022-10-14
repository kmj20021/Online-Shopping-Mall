package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.dto.GoodsDTO;

public class GoodsDAO {

	public GoodsDTO goodsRetrieve(SqlSession session, String gCode) {
		GoodsDTO list = 
				   session.selectOne("GoodsMapper.goodsRetrieve", gCode);
		   return list;
	   }
   public List<GoodsDTO> goodsList(SqlSession session, String gCategory) {
	   List<GoodsDTO> list = 
			   session.selectList("GoodsMapper.goodsList", gCategory);
	   return list;
   }

}
