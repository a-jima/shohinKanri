package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShohinDAO;
import model.data.ShohinRecord;

@WebServlet("/ShohinSearch")
public class ShohinSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 1.ブラウザからURLが入力され、index.jspから転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shohinSearch.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = ""; // レスポンス後に画面に表示する結果ﾒｯｾｰｼﾞ

		// リクエストパラメータより処理種別を取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action.equals("search")) {
			String shohinMei = request.getParameter("shohinMei");
			String shohinBunrui = request.getParameter("shohinBunrui");

			ShohinDAO shohinDAO = new ShohinDAO();
			List<ShohinRecord> shohinList = shohinDAO.select(shohinMei, shohinBunrui);

			request.setAttribute("shohinList", shohinList);
		} else if (action.equals("delete")) {
			String id = request.getParameter("select");

			// 検索条件に該当する商品情報を商品テーブルから検索
			ShohinDAO shohinDAO = new ShohinDAO();
			int delCnt = shohinDAO.delete(id);

			// 削除結果から結果メッセージを設定
			if (delCnt == 1) {
				message = "削除が完了しました";
			}
		}
		// 結果メッセージをリクエストスコープに保存
		request.setAttribute("message", message);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shohinSearch.jsp");
		dispatcher.forward(request, response);

	}

}
