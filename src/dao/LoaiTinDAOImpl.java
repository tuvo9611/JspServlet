/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LoaiTin;
import model.TinTuc;

/**
 *
 * @author Nguyen
 */
public class LoaiTinDAOImpl implements LoaiTinDAO {

	@Override
	public ArrayList<LoaiTin> getListLoaiTin() {
		Connection connection = DBConnector.getConnection();
		String sql = "Select * from loai_tin";
		ArrayList<LoaiTin> list = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LoaiTin loaiTin = new LoaiTin();
				loaiTin.setMa_loai_tin(rs.getInt("ma_loai_tin"));
				loaiTin.setTen_loai_tin(rs.getString("ten_loai_tin"));
				list.add(loaiTin);
			}
			connection.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Logger.getLogger(LoaiTinDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;
	}

	private int layMaTinCuoiCung() {
		Connection connection = DBConnector.getConnection();
		String sql = "SELECT * FROM loai_tin ORDER BY ma_loai_tin DESC LIMIT 1";
		int maTinCuoiCung = 0;
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				maTinCuoiCung = rs.getInt("ma_loai_tin");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return maTinCuoiCung;
	}

	@Override
	public boolean insertLoaiTin(LoaiTin loaitin) {

		int maTinCuoi = layMaTinCuoiCung() + 1;

		Connection connection = DBConnector.getConnection();
		String sql = "INSERT INTO loai_tin (ma_loai_tin,ten_loai_tin) VALUES(?,?)";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, maTinCuoi);
			ps.setString(2, loaitin.getTen_loai_tin());
			int row = ps.executeUpdate();
			if (row > 0) {
				return true;
			}

		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean updateLoaiTin(LoaiTin loaitin) {
		Connection connection = DBConnector.getConnection();
		String sql = "UPDATE loai_tin SET ten_loai_tin=? where ma_loai_tin=?";
		try {
			PreparedStatement ps = connection.prepareCall(sql);

			ps.setString(1, loaitin.getTen_loai_tin());

			ps.setInt(2, loaitin.getMa_loai_tin());
			return ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean deleteLoaiTin(int ma_loai_tin) {
		try {
			Connection connection = DBConnector.getConnection();
			String sql = "delete from loai_tin where ma_loai_tin=?";

			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, ma_loai_tin);
			return ps.executeUpdate() == 1;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new LoaiTinDAOImpl().deleteLoaiTin(5));
	}

	@Override
	public LoaiTin getLoaiTin(int ma_loai_tin) {
		try {
			Connection connection = DBConnector.getConnection();
			String sql = "SELECT * FROM loai_tin WHERE ma_loai_tin=?";
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, ma_loai_tin);

			ResultSet rs = ps.executeQuery();

			LoaiTin loaitin = new LoaiTin();
			while (rs.next()) {
				loaitin.setTen_loai_tin(rs.getString("ten_loai_tin"));

			}
			return loaitin;

		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public ArrayList<LoaiTin> getListLoaiTinTheoTrang(ArrayList<LoaiTin> arr, int start, int end) {
		ArrayList<LoaiTin> list = new ArrayList<>();
		for (int i = start; i < end; i++) {
			list.add(arr.get(i));
		}
		return list;
	}

}
