package comxx.util.db;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class MybatisUtilCore {

	public static void main(String[] args) throws Exception {
		// query();

		// fun_dyn();

		queryByStaticShiti();

	}

	private static void queryByStaticShiti() throws Exception {
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession(true);

		Map m = Maps.newConcurrentMap();
		m.put("ids", "1,2,3");
		List<Cls1> li = session.selectList("slkt1", m);

//	List<Cls1>	li=session.selectList("fehwiShTi",m);
		System.out.println(JSON.toJSONString(li));

	}

	private static void fun_dyn() throws Exception {
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession(true);

		Map objPrm = Maps.newConcurrentMap();
		objPrm.put("name", "ati");
		objPrm.put("time_startx", "2012");
		objPrm.put("time_endx", 2022);
		objPrm.put("pagex", 1);
		objPrm.put("pagesizex", 9);

		Map m = Maps.newConcurrentMap();
		m.put("objname", "fun_dyn");
		m.put("objprm", JSON.toJSONString(objPrm));

		List<Map> li = session.selectList("fun_prmjson", m);
		System.out.println(li);

	}

	void fun_prmjson() throws Exception {
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession(true);

		Map objPrm = Maps.newConcurrentMap();
		objPrm.put("name", "ati");
		objPrm.put("time_startx", "2012");
		objPrm.put("time_endx", 2022);
		objPrm.put("pagex", 1);
		objPrm.put("pagesizex", 9);

		Map m = Maps.newConcurrentMap();
		m.put("objname", "fun_prmjson");
		m.put("objprm", JSON.toJSONString(objPrm));

		List<Map> li = session.selectList("fun_prmjson", m);
		System.out.println(li);
	}

	private static void query() throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		// 创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession(true);

		Map m = Maps.newConcurrentMap();
		m.put("prm1", "arg1");
		String statement = "slkt1";
		List<Map> li = session.selectList(statement, m);
		System.out.println(li);
	}

}
