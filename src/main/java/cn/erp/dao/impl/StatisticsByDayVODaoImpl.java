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
		String sql = "select purchasing.suma amountCost,"
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
+") d";
		
		return null;
	}

}
