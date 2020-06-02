package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@WebServlet("/testSaveImg")
public class testSaveImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_FOLDER = "ImagesProduct";

	public static String UploadFile(HttpServletRequest request) {
		String fileName = "";
		try {
			Part filePart = request.getPart("HinhAnh");
			fileName = getFileName(filePart);
			String applicationPath = request.getServletContext().getRealPath("");
			String basePath = applicationPath + File.separator + UPLOAD_FOLDER + File.separator;

			InputStream inputStream = null;
			OutputStream outputStream = null;
			try {
				File outputfilePath = new File(basePath + fileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputfilePath);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (IOException e) {
				e.printStackTrace();
				fileName = "";
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			}
		} catch (IOException | ServletException e) {
			fileName = "";
		}
		return fileName;
	}

	// trich xuat ten file tu header HTTP
	private static String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("***** partHeader" + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}