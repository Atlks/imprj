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

public class MapOp {

	public static void main(String[] args) throws IOException {

		Reader reader = Resources.getResourceAsReader("mybatis.xml");
        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession(true);
        
        
        Map objPrm=Maps.newConcurrentMap();
        objPrm.put("name", "ati99");
        objPrm.put("time_startx", "2012");
        objPrm.put("time_endx", 2022);
        objPrm.put("pagex", 1);
        objPrm.put("pagesizex", 9);
        
       Map m=Maps.newConcurrentMap();
       m.put("objname","fun_dyn");
       m.put("objprm", JSON.toJSONString(objPrm));
      
	List<Map>	li=session.selectList("fun_prmjson",m);
	for (Map map : li) {
		getExtname("name1","nameExt",map);
	
	}
	System.out.println(li);
		
	}

	private static void getExtname(String fld, String asFld, Map map) {
		String val=map.get(fld)+"ext...";
		map.put(asFld, val);
		
	}

}
