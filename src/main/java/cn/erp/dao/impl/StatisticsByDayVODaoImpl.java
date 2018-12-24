package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.StatisticsByDayVODao;
import cn.erp.domain.StatisticsByDayVO;
import cn.erp.utils.C3P0Util;

public class StatisticsByDayVODaoImpl implements StatisticsByDayVODao {

	@Override
	public List<StatisticsByDayVO> findAll(Date beginDate, Date endDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="select                                                          "
+"sum(purchasing_price*s.num) amountCost,sum(s.total) amountSale                       "
+",s.sale_date date,(sum(s.total)-sum(purchasing_price*s.num)) amountProfit       "
+"from t_goods g,                                                                      "
+"(                                                                                    "
+"select goods_id,slg.num num,sl.sale_date sale_date,slg.total total                   "
+"from t_sale_list sl,t_sale_list_goods slg                                            "
+"where                                                                                "
+" sl.id= slg.sale_list_id and                                                         "
+" sl.sale_date>=? and sl.sale_date<=?                           "
+" ) s                                                                                 "
+" where g.id=s.goods_id                                                               "
+" group by s.sale_date";
				
			return	qr.query(sql, new BeanListHandler<StatisticsByDayVO>(StatisticsByDayVO.class),
						beginDate,endDate);
				/*"select purchasing.suma amountCost,"
				+ "s.t amountSale,(s.t-purchasing.suma) amountProfit,d.sale_date date"
+"from "
+"(                                                                              "
+"	select sum(f.a) suma  from                                                 "
+"	(                                                                          "
+"		select (priceT.purchasing_price)*(numberT.num) a                       "
+"		from                                                                   "
+"		(                                                                      "
+"			(	select purchasing_price,id                                     "
+"				from t_goods                                                   "
+"				where id in                                                    "
+"				(	select goods_id                                            "
+"					from t_sale_list_goods                                     "
+"					where sale_list_id in                                      "
+"					(	select id                                              "
+"						from t_sale_list                                       "
+"						where sale_date = ?                "
+"					)                                                          "
+"				)                                                              "
+"			) priceT                                                           "
+"		,                                                                      "
+"			(                                                                  "
+"				select num,goods_id                                            "
+"				from t_sale_list_goods                                         "
+"				where sale_list_id in                                          "
+"				(	select id                                                  "
+"					from t_sale_list                                           "
+"					where sale_date = ?                    "
+"				)                                                              "
+"			) numberT                                                          "
+"		)                                                                      "
+"		where priceT.id = numberT.goods_id                                     "
+"	) f"
+") purchasing,"
+"("
+"	select sum(total) t"
+"	from t_sale_list_goods"
+"	where sale_list_id in"
+"	(	"
+"		select id"
+"		from t_sale_list"
+"		where sale_date = ?"
+"	)"
+") s,"
+"("
+"	select sale_date"
+"	from t_sale_list"
+"	where sale_date = ?"
+") d";*/	
	}

}
