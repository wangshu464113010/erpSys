package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Page;
import cn.erp.domain.Supplier;
/**
 * 渚涘簲鍟嗙殑dao灞傛帴鍙�
 * @author wangshu 
 *
 */
public interface SupplierDao {
	//鏌ヨ鎵�鏈�
	public List<Supplier> findAll() throws SQLException;
	
	//List<Supplier> findAll() throws SQLException;
	Supplier findSupplierById(Integer id)throws SQLException;
	
	//鏌ユ壘涓�涓�,鏍规嵁id
	public Supplier finddOne(Integer id)throws SQLException;
	//瀵规暟鎹簱涓墍鏈変緵搴斿晢鐨勬暟閲忚鏁�
	public int count()throws SQLException;
	//缁欏畾鏉′欢锛屽鏁版嵁搴撲腑鐨勬弧瓒虫潯浠剁殑渚涘簲鍟嗚鏁�
	public int countLike(String name)throws SQLException;
	//鍒嗛〉鏌ヨ
	public List<Supplier> findPage(Page page) throws SQLException;
	//妯＄硦鏌ヨ锛屾牴鎹叕鍙稿悕绉�
	public List<Supplier> findLikePage(Page page ,String name)throws SQLException;
	//鎸夋潯浠舵ā绯婃煡璇㈡墍鏈変緵搴斿晢
	public List<Supplier> findLike(String name) throws SQLException ;
	/** 澧炲垹鏀�*/
	public int insert(Supplier supplier)throws SQLException;
	
	public int update(Supplier supplier)throws SQLException;
	
	public int delete(Supplier supplier)throws SQLException;
	/**鏍规嵁涓�涓暟缁�(涓婚敭)鍒犻櫎 */
	public int delete(int[] ids)throws SQLException;
	
}
