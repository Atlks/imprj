package comxx.controler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import comxx.util.JpaUtil;
import comxx.util.RequestUtil;
import io.swagger.annotations.ApiOperation;

public class MysqlRestApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//as php same
	@ApiOperation(value = "查询无翻页", notes = "查询无翻页")
	@GetMapping("/ApiControllerQuery")
	public Object query() throws Exception {
		LinkedHashMap m = Maps.newLinkedHashMap();
		String from=req.getParameter("@dt");
		Map reqM = RequestUtil.getMap(req);
		String where = getWhere(reqM);
		String sql = "select * from " + from + "  where 1=1 " + where;
		logger.info(sql);
		
		
		return JpaUtil.querySql(sql);
	}
	
	@ApiOperation(value = "查询条数")
	/**
	 * sum 聚合查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/queryCount")
	public int queryCount() throws Exception {

		LinkedHashMap m = Maps.newLinkedHashMap();
		String from=req.getParameter("@dt");
		Map reqM = RequestUtil.getMap(req);
		String where = getWhere(reqM);
		String sql = "select count(*) as cnt from " + from + "  where 1=1 " + where;
		logger.info(sql);
		
		
		List<Map> lima= (List<Map>) JpaUtil.querySql(sql);

		return   (Integer)lima.get(0).get("cnt");

	}
	
	
	public static String getWhere(Map reqM) {
		// LinkedHashMap<String , String> m=Maps.newLinkedHashMap();
		final List<String> li = Lists.newArrayList();
		reqM.forEach(new BiConsumer<String, String>() {

			public void accept(String t, String u) {
				if(t.startsWith("@"))
					return;
				String colTrim = t.trim();

				li.add(t + "='" + u + "'");
			}

		});

		String join = Joiner.on(" and ").join(li);
		if (join.trim().length() > 0)
			return " and " + join;
		else
			return join;// empty
	}

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	public HttpServletResponse res;

	@Autowired
	public HttpServletRequest req;
}
