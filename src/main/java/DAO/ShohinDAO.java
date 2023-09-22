package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.data.ShohinRecord;

public class ShohinDAO {

	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/shop";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "root";

	// 商品テーブルから「商品名」と「商品分類」でデータを検索し、検索結果を返す
	public List<ShohinRecord> select(String shohinMei, String shohinBunrui) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<ShohinRecord> sRecords = new ArrayList<>();
		try {
			// JDBCドライバの定義
			Class.forName("org.postgresql.Driver");
			// PostgreSQLへの接続
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SQL文
			String sql = "SELECT * FROM shohin " + "WHERE shohin_mei like ? AND shohin_bunrui like ? "
					+ "ORDER BY shohin_id ASC;";

			st = con.prepareStatement(sql);
			st.setString(1, "%" + shohinMei + "%");
			st.setString(2, "%" + shohinBunrui + "%");

			rs = st.executeQuery();

			// 結果をリストに移動
			sRecords = makeResultData(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
			return null;
		} finally {
			// PostgreSQLと切断
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					;
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					;
				}
			}

		}
		return sRecords;

	}

	// 検索結果をリストで返すメソッド
	public ArrayList<ShohinRecord> makeResultData(ResultSet rs) throws Exception {
		// 全検索結果を格納するリストを準備
		ArrayList<ShohinRecord> sRecords = new ArrayList<>();
		while (rs.next()) {
			// 1レコード文のデータ読み込み
			String shohinId = rs.getString("shohin_id");
			String shohinMei = rs.getString("shohin_mei");
			String shohinBunrui = rs.getString("shohin_bunrui");
			Integer hanbaiTanka = rs.getInt("hanbai_tanka");
			Integer shiireTanka = rs.getInt("shiire_tanka");
			String torokubi = rs.getString("torokubi");
			// 1レコード文のデータを格納
			ShohinRecord sRecord = new ShohinRecord(shohinId, shohinMei, shohinBunrui, hanbaiTanka, shiireTanka,
					torokubi);
			sRecords.add(sRecord);
		}

		return sRecords;
	}

	public int delete(String shohinId) {
		return 0;
	}

	public List<ShohinRecord> findAll() {
		List<ShohinRecord> sRecords = new ArrayList<>();
		String sql = "SELECT * FROM shohin ORDER BY ID DESC;";
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
					PreparedStatement pStmt = conn.prepareStatement(sql);) {

				ResultSet rs = pStmt.executeQuery();
				sRecords = makeResultData(rs);
				rs.close(); // pStmtが閉じられると自動的に閉じられるため、finally処理不要。
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return sRecords;
	}

	public boolean insert(ShohinRecord shohin) {
		String sql = "INSERT INTO shohin(shohin_id,shohin_mei,shohin_bunrui,hanbai_tanka,shiire_tanka,torokubi)"
				+ " VALUES(?,?,?,?,?,null);";
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
					PreparedStatement pStmt = conn.prepareStatement(sql);) {

				pStmt.setString(1, shohin.getShohinId());
				pStmt.setString(2, shohin.getShohinMei());
				pStmt.setString(3, shohin.getShohinBunrui());
				pStmt.setInt(4, shohin.getHanbaiTanka());
				pStmt.setInt(5, shohin.getShiireTanka());

				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
