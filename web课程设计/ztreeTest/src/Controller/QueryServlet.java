package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import vo.City;
import ztreeTest.DBUtil;
@WebServlet(urlPatterns = "/query.do")
public class QueryServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		DBUtil util=null;
		String parentId=req.getParameter("id");
		
		if(parentId==null||parentId.equals("")){
			parentId=0+"";
		}
		try {
			util=new DBUtil();
			List<City> list=util.query(Integer.parseInt(parentId));
			String jsonStr = new Gson().toJson(list);

			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		res.setContentType("text/html");
		
//		out.flush();
//		out.close();
	}

}
