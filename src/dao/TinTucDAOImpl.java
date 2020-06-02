/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnector;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import model.LoaiTin;
import model.TinTuc;

/**
 *
 * @author Nguyen
 */
public class TinTucDAOImpl implements TinTucDAO {

	@Override
	public ArrayList<TinTuc> getListTinTuc() {
		Connection connection = DBConnector.getConnection();
		String sql = "select * from tin_tuc";
		ArrayList<TinTuc> list = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TinTuc tintuc = new TinTuc();
				tintuc.setMa_tin_tuc(rs.getInt("ma_tin_tuc"));
				tintuc.setMa_loai_tin(rs.getInt("ma_loai_tin"));
				tintuc.setTen_tin_tuc(rs.getString("ten_tin_tuc"));
				tintuc.setTieu_de(rs.getString("tieu_de"));
				tintuc.setNoi_dung(rs.getString("noi_dung"));
//				tintuc.setHinh_anh(rs.getString("hinh_anh"));
				
				// xu ly anh
				Blob blob = rs.getBlob("hinh_anh_part");
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1064];
				int bytesRead = -1;
 
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();
				tintuc.setHinh_anh(base64Image);
				
				
				tintuc.setNgay_dang(rs.getString("ngay_dang"));
				list.add(tintuc);
			}
			connection.close();
		} catch (IOException | SQLException ex) {
			Logger.getLogger(TinTucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return list;

	}

	@Override
	public TinTuc getListTinTucTheoMa(int ma_tin_tuc) {
//		readnew
		Connection connection = DBConnector.getConnection();
		String sql = "select * from tin_tuc where ma_tin_tuc= '" + ma_tin_tuc + "'";
		TinTuc tt = new TinTuc();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tt.setMa_tin_tuc(rs.getInt("ma_tin_tuc"));
				tt.setMa_loai_tin(rs.getInt("ma_loai_tin"));
				tt.setTen_tin_tuc(rs.getString("ten_tin_tuc"));
				tt.setTieu_de(rs.getString("tieu_de"));
				tt.setNoi_dung(rs.getString("noi_dung"));
				
				// xu ly anh
				Blob blob = rs.getBlob("hinh_anh_Part");
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1064];
				int bytesRead = -1;
 
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();
				tt.setHinh_anh(base64Image);
				
				
				tt.setNgay_dang(rs.getString("ngay_dang"));
		System.out.println("ngay dang: "+rs.getString("ngay_dang"));
			
			}
		} catch (IOException | SQLException ex) {
			Logger.getLogger(TinTucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return tt;
	}
	
	public static void main(String[] args) {
		System.out.println(new TinTucDAOImpl().getListTinTucTheoMa(2));
	}

	@Override
	public ArrayList<TinTuc> getListTinTucTheoLoaiTin(int ma_loai_tin) {
		// typenews
		Connection connection = DBConnector.getConnection();
		String sql = "select * from tin_tuc where ma_loai_tin='" + ma_loai_tin + "'";
		ArrayList<TinTuc> arr = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TinTuc tin_tuc = new TinTuc();
				tin_tuc.setMa_tin_tuc(rs.getInt("ma_tin_tuc"));
				tin_tuc.setTen_tin_tuc(rs.getString("ten_tin_tuc"));
				tin_tuc.setMa_loai_tin(rs.getInt("ma_loai_tin"));
				tin_tuc.setTieu_de(rs.getString("tieu_de"));
				tin_tuc.setNoi_dung(rs.getString("noi_dung"));
				
				// xu ly anh
				Blob blob = rs.getBlob("hinh_anh_part");
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1064];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				inputStream.close();
				outputStream.close();
				tin_tuc.setHinh_anh(base64Image);
				
				tin_tuc.setNgay_dang(rs.getString("ngay_dang"));
				arr.add(tin_tuc);
			}
			connection.close();
		} catch (IOException|SQLException e) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return arr;
	}
	

	@Override
	public ArrayList<TinTuc> getListTinTucMoi() {
		Connection connection = DBConnector.getConnection();
		String sql = "SELECT TOP 8 * FROM tin_tuc ORDER BY ma_tin_tuc DESC;";
		ArrayList<TinTuc> arr = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TinTuc tin_tuc = new TinTuc();

				tin_tuc.setMa_tin_tuc(rs.getInt("ma_tin_tuc"));
				tin_tuc.setMa_loai_tin(rs.getInt("ma_loai_tin"));
				tin_tuc.setTen_tin_tuc(rs.getString("ten_tin_tuc"));
				tin_tuc.setTieu_de(rs.getString("tieu_de"));
				tin_tuc.setNoi_dung(rs.getString("noi_dung"));
				tin_tuc.setHinh_anh(rs.getString("hinh_anh"));
				tin_tuc.setNgay_dang(rs.getString("ngay_dang"));
				arr.add(tin_tuc);
			}
			connection.close();

		} catch (SQLException e) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return arr;

	}

	@Override
	public boolean insertTinTuc(TinTuc tintuc) {
		Connection connection = DBConnector.getConnection();
		String sql = "INSERT INTO tin_tuc VALUES(?,?,?,?,?,?,?)";
		int maTinTuc = layMaTinTucCuoiCung() + 1;

		// ***************** XU LY LUU ANH *************************
		InputStream inputStream = null;
		Part hinhanhPart = tintuc.getHinh_anh_Part();
		if (hinhanhPart != null) {
			try {
				inputStream = hinhanhPart.getInputStream();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		// *********************************************************

		try {
			PreparedStatement ps = connection.prepareCall(sql);

			ps.setInt(1, maTinTuc);
			ps.setInt(2, tintuc.getMa_loai_tin());
			ps.setString(3, tintuc.getTen_tin_tuc());
			ps.setString(4, tintuc.getTieu_de());
			ps.setString(5, tintuc.getNoi_dung());
			if (inputStream != null) {
				ps.setBlob(6, inputStream);
			}
			ps.setString(7, tintuc.getNgay_dang());

			return ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	private int layMaTinTucCuoiCung() {
		String sql = "SELECT * FROM tin_tuc ORDER BY ma_tin_tuc DESC LIMIT 1";
		int id = 0;
		Connection conn = DBConnector.getConnection();

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public boolean updateTinTuc(TinTuc tintuc) {
		Connection connection = DBConnector.getConnection();
		String sql = "UPDATE tin_tuc SET ma_loai_tin=?,ten_tin_tuc=?,"
				+ "tieu_de=?,noi_dung=?,hinh_anh=?,ngay_dang=? where ma_tin_tuc=?";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, tintuc.getMa_loai_tin());
			ps.setString(2, tintuc.getTen_tin_tuc());
			ps.setString(3, tintuc.getTieu_de());
			ps.setString(4, tintuc.getNoi_dung());
			ps.setString(5, tintuc.getHinh_anh());
			ps.setString(6, tintuc.getNgay_dang());
			ps.setInt(7, tintuc.getMa_tin_tuc());
			return ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean deleteTinTuc(int ma_tin_tuc) {
		try {
			Connection connection = DBConnector.getConnection();
			String sql = "delete from tin_tuc where ma_tin_tuc=?";

			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, ma_tin_tuc);
			return ps.executeUpdate() == 1;

		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public ArrayList<TinTuc> getListTinTucTheoTrang(ArrayList<TinTuc> arr, int start, int end) {
		ArrayList<TinTuc> list = new ArrayList<>();
		for (int i = start; i < end; i++) {
			list.add(arr.get(i));
		}
		return list;
	}

	@Override
	public boolean deleteTinTucTheoMaLoaiTin(int ma_loai_tin) {
		Connection connection = DBConnector.getConnection();
		String sql = "delete from tin_tuc Where ma_loai_tin=?";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, ma_loai_tin);
			return ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(TinTucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;
	}

}
