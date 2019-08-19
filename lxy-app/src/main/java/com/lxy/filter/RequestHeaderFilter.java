package com.lxy.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lxy.dto.JsonResult;
import com.lxy.entity.Header;
import com.lxy.entity.RequestHeader;
import com.lxy.utils.ValidUtil;
import net.sf.json.JSONObject;

public class RequestHeaderFilter implements Filter {

	Logger log = LoggerFactory.getLogger(RequestHeaderFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");

		Header header = new RequestHeader((HttpServletRequest) req);
		if (ValidUtil.isNull(header.getClientId(), header.getJx(), header.getOsType(), header.getTimeStamp(),header.getVerCode())) {
			log.error("RequestHeader请求头参数不完整");
			JsonResult<Object> result = new JsonResult<Object>(11111, "请求头参数不完整");
			String jsonResult = JSONObject.fromObject(result).toString();
			PrintWriter pw = res.getWriter();
			pw.print(jsonResult);
			pw.close();
			return;
		}
		chain.doFilter(request, response);
	}

}
