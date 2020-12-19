package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import vo.City;
import ztreeTest.DBUtil;

@WebServlet(urlPatterns = "/modif.do")
public class modif extends HttpServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 1.按照表单的各元素的name属性值获取各请求参数值
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int parentId = Integer.parseInt(request.getParameter("parentId"));

		// 2.获取HttpSession对象
		HttpSession session = request.getSession();
		// Map存放放回的信息
		Map<String, Object> map = new HashMap<String, Object>();
		City city = new City(id, name, parentId);
		
		boolean success = false;
		String info = "";
		DBUtil util;
		try {
			util = new DBUtil();
			success = util.update(city);
			info = "修改";
			// 存放返回信息的Map
			if (success) {
				map.put("code", 0);
				map.put("info", info + "成功!");
			} else {
				map.put("code", 1);
				map.put("info", info + "失败!");
			}
			String jsonStr = new Gson().toJson(map);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html");

	}

}
