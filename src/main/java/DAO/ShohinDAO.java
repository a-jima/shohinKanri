package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Shohin;

public class ShohinDAO {

	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/shop";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "root";

	public List<Shohin> find(String searchName, String searchBunrui) {
		List<Shohin> shohinList = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
				String sql = "SELECT * FROM shohin WHERE shohin_mei=? AND shohin_bunrui=? ORDER BY ID DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, searchName);
				pStmt.setString(2, searchBunrui);

				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					String id = rs.getString("shohin_id");
					String name = rs.getString("shohin_mei");
					String bunrui = rs.getString("shohin_bunrui");
					Integer hanbai = rs.getInt("hanbai_tanka");
					Integer shiire = rs.getInt("shiire_tanka");
					Shohin shohin = new Shohin(id, name, bunrui, hanbai, shiire);
					shohinList.add(shohin);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return shohinList;
	}

	public List<Shohin> find(String searchBunrui) {
		List<Shohin> shohinList = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
				String sql = "SELECT * FROM shohin WHERE shohin_bunrui=? ORDER BY ID DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, searchBunrui);

				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					String id = rs.getString("shohin_id");
					String name = rs.getString("shohin_mei");
					String bunrui = rs.getString("shohin_bunrui");
					Integer hanbai = rs.getInt("hanbai_tanka");
					Integer shiire = rs.getInt("shiire_tanka");
					Shohin shohin = new Shohin(id, name, bunrui, hanbai, shiire);
					shohinList.add(shohin);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return shohinList;
	}

	public List<Shohin> findAll() {
		List<Shohin> shohinList = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
				String sql = "SELECT * FROM shohin ORDER BY ID DESC;";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					String id = rs.getString("shohin_id");
					String name = rs.getString("shohin_mei");
					String bunrui = rs.getString("shohin_bunrui");
					Integer hanbai = rs.getInt("hanbai_tanka");
					Integer shiire = rs.getInt("shiire_tanka");
					Shohin shohin = new Shohin(id, name, bunrui, hanbai, shiire);
					shohinList.add(shohin);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return shohinList;
	}

	public boolean insert(Shohin shohin) {
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {
				String sql = "INSERT INTO shohin(shohin_id,shohin_mei,shohin_bunrui,hanbai_tanka,shiire_tanka,torokubi)"
						+ " VALUES(?,?,?,?,?,null);";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1, shohin.getId());
				pStmt.setString(2, shohin.getName());
				pStmt.setString(3, shohin.getBunrui());
				pStmt.setInt(4, shohin.getHanbai());
				pStmt.setInt(5, shohin.getShiire());

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
