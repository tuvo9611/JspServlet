/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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

import connect.DBConnector;
import model.ChiTietHoaDon;
import model.SanPham;

/**
 *
 * @author Nguyen
 */
public class SanPhamDAOImpl implements SanPhamDAO {
	private static final int BUFFER_SIZE = 4096;

	@Override
	public ArrayList<SanPham> getListSanPhamTheoDanhMuc(int ma_danh_muc) {
		Connection connection = DBConnector.getConnection();
		String sql = "select * from san_pham where ma_danh_muc='" + ma_danh_muc + "'";
		ArrayList<SanPham> arr = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SanPham san_pham = new SanPham();

				san_pham.setMa_san_pham(rs.getInt("ma_san_pham"));
				san_pham.setTen_san_pham(rs.getString("ten_san_pham"));
				san_pham.setMa_danh_muc(rs.getInt("ma_danh_muc"));
//				san_pham.setHinh_anh(null);

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
				san_pham.setHinh_annh(base64Image);
				
				san_pham.setSo_luong(rs.getInt("so_luong"));
				san_pham.setDon_gia(rs.getInt("don_gia"));
				san_pham.setGiamgia(rs.getInt("giam_gia"));
				san_pham.setMan_hinh(rs.getString("man_hinh"));
				san_pham.setCamera_sau(rs.getString("camera_sau"));
				san_pham.setCamera_truoc(rs.getString("camera_truoc"));
				san_pham.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
				san_pham.setCpu(rs.getString("cpu"));
				san_pham.setBo_nho(rs.getString("bo_nho"));
				san_pham.setRam(rs.getString("ram"));
				san_pham.setKet_noi(rs.getString("ket_noi"));
				san_pham.setPin_sac(rs.getString("pin_sac"));
				san_pham.setBao_hanh(rs.getString("bao_hanh"));
				san_pham.setMo_ta(rs.getString("mo_ta"));
				arr.add(san_pham);
			}
			connection.close();

		} catch (IOException | SQLException e) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return arr;
	}
	

	@Override
	public SanPham getChiTietSanPham(int ma_san_pham) {
		Connection connection = DBConnector.getConnection();
		String sql = "select * from san_pham where ma_san_pham='" + ma_san_pham + "'";
		SanPham san_pham = new SanPham();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				san_pham.setMa_san_pham(rs.getInt("ma_san_pham"));
				san_pham.setTen_san_pham(rs.getString("ten_san_pham"));
				san_pham.setMa_danh_muc(rs.getInt("ma_danh_muc"));
				
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
				san_pham.setHinh_annh(base64Image);
				
				
				san_pham.setSo_luong(rs.getInt("so_luong"));
				san_pham.setDon_gia(rs.getInt("don_gia"));
				san_pham.setGiamgia(rs.getInt("giam_gia"));
				san_pham.setMan_hinh(rs.getString("man_hinh"));
				san_pham.setCamera_sau(rs.getString("camera_sau"));
				san_pham.setCamera_truoc(rs.getString("camera_truoc"));
				san_pham.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
				san_pham.setCpu(rs.getString("cpu"));
				san_pham.setBo_nho(rs.getString("bo_nho"));
				san_pham.setRam(rs.getString("ram"));
				san_pham.setKet_noi(rs.getString("ket_noi"));
				san_pham.setPin_sac(rs.getString("pin_sac"));
				san_pham.setBao_hanh(rs.getString("bao_hanh"));
				san_pham.setMo_ta(rs.getString("mo_ta"));
			}

		} catch (IOException | SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return san_pham;
	}

	@Override
	public ArrayList<SanPham> getListSanPham(String ten_san_pham) {
		Connection connection = DBConnector.getConnection();
		String sql = "Select * from san_pham where ten_san_pham like '%" + ten_san_pham + "%'";
		ArrayList<SanPham> arr = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMa_san_pham(rs.getInt("ma_san_pham"));
				sp.setTen_san_pham(rs.getString("ten_san_pham"));
				sp.setMa_danh_muc(rs.getInt("ma_danh_muc"));
//				sp.setHinh_anh(null);
				
				
				
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
				sp.setHinh_annh(base64Image);
				
				
				
				sp.setSo_luong(rs.getInt("so_luong"));
				sp.setDon_gia(rs.getInt("don_gia"));
				sp.setGiamgia(rs.getInt("giam_gia"));
				sp.setMan_hinh(rs.getString("man_hinh"));
				sp.setCamera_sau(rs.getString("camera_sau"));
				sp.setCamera_truoc(rs.getString("camera_truoc"));
				sp.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
				sp.setCpu(rs.getString("cpu"));
				sp.setBo_nho(rs.getString("bo_nho"));
				sp.setRam(rs.getString("ram"));
				sp.setKet_noi(rs.getString("ket_noi"));
				sp.setPin_sac(rs.getString("pin_sac"));
				sp.setBao_hanh(rs.getString("bao_hanh"));
				sp.setMo_ta(rs.getString("mo_ta"));
				arr.add(sp);

			}
		} catch (IOException | SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return arr;
	}

	@Override
	public ArrayList<SanPham> getListSanPhamMoi() {
		Connection connection = DBConnector.getConnection();
		String sql = "SELECT TOP 8 * FROM san_pham ORDER BY ma_san_pham DESC;";
		ArrayList<SanPham> arr = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMa_san_pham(rs.getInt("ma_san_pham"));
				sp.setTen_san_pham(rs.getString("ten_san_pham"));

				sp.setMa_danh_muc(rs.getInt("ma_danh_muc"));
//				sp.setHinh_anh(null);
				
				
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
				sp.setHinh_annh(base64Image);
				
				
				
				sp.setSo_luong(rs.getInt("so_luong"));
				sp.setDon_gia(rs.getInt("don_gia"));
				sp.setGiamgia(rs.getInt("giam_gia"));
				sp.setMan_hinh(rs.getString("man_hinh"));
				sp.setCamera_sau(rs.getString("camera_sau"));
				sp.setCamera_truoc(rs.getString("camera_truoc"));
				sp.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
				sp.setCpu(rs.getString("cpu"));
				sp.setBo_nho(rs.getString("bo_nho"));
				sp.setRam(rs.getString("ram"));
				sp.setKet_noi(rs.getString("ket_noi"));
				sp.setPin_sac(rs.getString("pin_sac"));
				sp.setBao_hanh(rs.getString("bao_hanh"));
				sp.setMo_ta(rs.getString("mo_ta"));
				arr.add(sp);

			}
		} catch (IOException|SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return arr;
	}

	@Override
	public ArrayList<SanPham> getListSanPhamTheoGia(int a, int b) {
		Connection connection = DBConnector.getConnection();
		String sql = "select * from san_pham where don_gia>='" + a + "'and don_gia <'" + b + "'";
		ArrayList<SanPham> arr = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SanPham san_pham = new SanPham();

				san_pham.setMa_san_pham(rs.getInt("ma_san_pham"));
				san_pham.setTen_san_pham(rs.getString("ten_san_pham"));

				san_pham.setMa_danh_muc(rs.getInt("ma_danh_muc"));

//				san_pham.setHinh_anh(null);
				
				
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
				san_pham.setHinh_annh(base64Image);

				
				
				san_pham.setSo_luong(rs.getInt("so_luong"));
				san_pham.setDon_gia(rs.getInt("don_gia"));
				san_pham.setGiamgia(rs.getInt("giam_gia"));
				san_pham.setMan_hinh(rs.getString("man_hinh"));
				san_pham.setCamera_sau(rs.getString("camera_sau"));
				san_pham.setCamera_truoc(rs.getString("camera_truoc"));
				san_pham.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
				san_pham.setCpu(rs.getString("cpu"));
				san_pham.setBo_nho(rs.getString("bo_nho"));
				san_pham.setRam(rs.getString("ram"));
				san_pham.setKet_noi(rs.getString("ket_noi"));
				san_pham.setPin_sac(rs.getString("pin_sac"));
				san_pham.setBao_hanh(rs.getString("bao_hanh"));
				san_pham.setMo_ta(rs.getString("mo_ta"));
				arr.add(san_pham);
			}
			connection.close();

		} catch (IOException|SQLException e) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return arr;
	}

//	@Override
//	public ArrayList<SanPham> getListSanPham() {
//		Connection connection = DBConnector.getConnection();
//		String sql = "SELECT * from san_pham ORDER BY ma_san_pham ASC";
//		ArrayList<SanPham> arr = new ArrayList<>();
//		try {
//			PreparedStatement ps = connection.prepareCall(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				SanPham sp = new SanPham();
//				sp.setMa_san_pham(rs.getInt("ma_san_pham"));
//				sp.setTen_san_pham(rs.getString("ten_san_pham"));
//				sp.setMa_danh_muc(rs.getInt("ma_danh_muc"));
////				sp.setHinh_anh(rs.getBlob("hinh_anh"));
//				sp.setHinh_anh(null);
//				sp.setSo_luong(rs.getInt("so_luong"));
//				sp.setDon_gia(rs.getInt("don_gia"));
//				sp.setGiamgia(rs.getInt("giam_gia"));
//				sp.setMan_hinh(rs.getString("man_hinh"));
//				sp.setCamera_sau(rs.getString("camera_sau"));
//				sp.setCamera_truoc(rs.getString("camera_truoc"));
//				sp.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
//				sp.setCpu(rs.getString("cpu"));
//				sp.setBo_nho(rs.getString("bo_nho"));
//				sp.setRam(rs.getString("ram"));
//				sp.setKet_noi(rs.getString("ket_noi"));
//				sp.setPin_sac(rs.getString("pin_sac"));
//				sp.setBao_hanh(rs.getString("bao_hanh"));
//				sp.setMo_ta(rs.getString("mo_ta"));
//				arr.add(sp);
//
//			}
//		} catch (SQLException ex) {
//			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return arr;
//	}

	@Override
	public ArrayList<SanPham> getListSanPham() {
		Connection connection = DBConnector.getConnection();
		String sql = "SELECT * from san_pham ORDER BY ma_san_pham ASC";
		ArrayList<SanPham> arr = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMa_san_pham(rs.getInt("ma_san_pham"));
				sp.setTen_san_pham(rs.getString("ten_san_pham"));
				sp.setMa_danh_muc(rs.getInt("ma_danh_muc"));

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
				sp.setHinh_annh(base64Image);

				sp.setSo_luong(rs.getInt("so_luong"));
				sp.setDon_gia(rs.getInt("don_gia"));
				sp.setGiamgia(rs.getInt("giam_gia"));
				sp.setMan_hinh(rs.getString("man_hinh"));
				sp.setCamera_sau(rs.getString("camera_sau"));
				sp.setCamera_truoc(rs.getString("camera_truoc"));
				sp.setHe_dieu_hanh(rs.getString("he_dieu_hanh"));
				sp.setCpu(rs.getString("cpu"));
				sp.setBo_nho(rs.getString("bo_nho"));
				sp.setRam(rs.getString("ram"));
				sp.setKet_noi(rs.getString("ket_noi"));
				sp.setPin_sac(rs.getString("pin_sac"));
				sp.setBao_hanh(rs.getString("bao_hanh"));
				sp.setMo_ta(rs.getString("mo_ta"));
				arr.add(sp);
			}
		} catch (IOException | SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return arr;
	}

	@Override
	public boolean insertSanPham(SanPham sanpham) {
		
		Connection connection = DBConnector.getConnection();

		// SQL statement
		String sql = "INSERT INTO san_pham (ma_san_pham , ten_san_pham, ma_danh_muc, hinh_anh_part, so_luong, don_gia, giam_gia, man_hinh, camera_sau, camera_truoc, he_dieu_hanh, cpu, bo_nho, ram,"
				+ "ket_noi, pin_sac, bao_hanh, mo_ta) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int maSP = layMaSPCuoiCung() + 1;

		// ***************** XU LY LUU ANH *************************
		InputStream inputStream = null;
		Part hinhanhPart = sanpham.getHinh_anh();
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
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, maSP);
			ps.setString(2, sanpham.getTen_san_pham());
			ps.setInt(3, sanpham.getMa_danh_muc());
			if (inputStream != null) {
				ps.setBlob(4, inputStream);
			}
			ps.setInt(5, sanpham.getSo_luong());
			ps.setInt(6, sanpham.getDon_gia());
			ps.setInt(7, sanpham.getGiamgia());
			ps.setString(8, sanpham.getMan_hinh());
			ps.setString(9, sanpham.getCamera_sau());
			ps.setString(10, sanpham.getCamera_truoc());
			ps.setString(11, sanpham.getHe_dieu_hanh());
			ps.setString(12, sanpham.getCpu());
			ps.setString(13, sanpham.getBo_nho());
			ps.setString(14, sanpham.getRam());
			ps.setString(15, sanpham.getKet_noi());
			ps.setString(16, sanpham.getPin_sac());
			ps.setString(17, sanpham.getBao_hanh());
			ps.setString(18, sanpham.getMo_ta());

			return ps.executeUpdate()==1;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());}}}
		return false;
	}

	private int layMaSPCuoiCung() {
		int idLastRow = 0;
		try {
			Connection connection = DBConnector.getConnection();
			String sql = "select * from san_pham ORDER BY ma_san_pham DESC LIMIT 1";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				idLastRow = Integer.parseInt(rs.getString("ma_san_pham"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return idLastRow;
	}
	
	@Override
	public boolean deleteSanPham(int ma_san_pham) {
		try {
			Connection connection = DBConnector.getConnection();
			String sql = "delete from san_pham where ma_san_pham=?";

			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, ma_san_pham);
			return ps.executeUpdate() == 1;

		} catch (SQLException ex) {
			Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean updateSanPham(SanPham sanpham) {
		Connection connection = DBConnector.getConnection();
		String sql = "UPDATE san_pham SET so_luong = ? where ma_san_pham = ?";
		
		try {
			PreparedStatement ps = connection.prepareCall(sql);

			ps.setInt(1, sanpham.getSo_luong());
			ps.setInt(2, sanpham.getMa_san_pham());
			
			return (ps.executeUpdate() > 1);
		} catch (SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public ArrayList<SanPham> getListSanPhamTheo(ArrayList<SanPham> arr, int start, int end) {
		ArrayList<SanPham> list = new ArrayList<>();
		for (int i = start; i < end; i++) {
			list.add(arr.get(i));
		}
		return list;
	}

	@Override
	public ArrayList<SanPham> getListSanPhamBanChay() {
		Connection connection = DBConnector.getConnection();
		String sql = "select sp.ma_san_pham,sp.ten_san_pham,cthd.so_luong from san_pham sp,chi_tiet_hoa_don cthd where sp.ma_san_pham=cthd.ma_san_pham limit 5;";
//		String sql = "select top 5 sp.ma_san_pham,sp.ten_san_pham,cthd.so_luong from san_pham sp,chi_tiet_hoa_don cthd where sp.ma_san_pham=cthd.ma_san_pham;";
		ArrayList<SanPham> arr = new ArrayList<>();
		try {

			PreparedStatement ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				sp.setMa_san_pham(rs.getInt("ma_san_pham"));
				sp.setTen_san_pham(rs.getString("ten_san_pham"));

				sp.setSo_luong(rs.getInt("so_luong"));

				arr.add(sp);
			}
		} catch (SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return arr;
	}

	@Override
	public boolean deleteSanPhamTheoMaDanhMuc(int ma_danh_muc) {
		Connection connection = DBConnector.getConnection();
		String sql = "delete from san_pham Where ma_danh_muc=?";
		try {
			PreparedStatement ps = connection.prepareCall(sql);
			ps.setInt(1, ma_danh_muc);
			return ps.executeUpdate() == 1;
		} catch (SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;
	}

	public int laySoLuongSanPham() {
		Connection connection = DBConnector.getConnection();
		String sql = "select * from san_pham;";
		int soLuongSP = 0;
		try {
			Statement ps = connection.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				soLuongSP++;
			}
		} catch (SQLException ex) {
			Logger.getLogger(SanPhamDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return soLuongSP;
	}

}
