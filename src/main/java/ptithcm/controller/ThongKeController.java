package ptithcm.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.*;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;
import ptithcm.entity.SanPham;
import ptithcm.entity.Size;

@Controller
@Transactional
public class ThongKeController {
	@Autowired
	SessionFactory factory;


	@RequestMapping("/thongke")
	public String name(ModelMap model, @RequestParam(value = "startDate", required = false) String startDateStr,
			@RequestParam(value = "endDate", required = false) String endDateStr) throws ParseException {

		Date startDate = null;
		Date endDate = null;
		Date ngayHienTai = new Date(System.currentTimeMillis());
		Date thangTruoc = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

		if (startDateStr == null || startDateStr.isEmpty()) {
			startDate = thangTruoc;
		} else {
			startDate = convertStringToSqlDate(startDateStr);
		}
		if (endDateStr == null || endDateStr.isEmpty()) {
			endDate = ngayHienTai;
		} else {
			endDate = convertStringToSqlDate(endDateStr);
		}

		model.addAttribute("startDate", startDateStr);
		model.addAttribute("endDate", endDateStr);
		// 2 bằng null
		if (startDateStr == null && endDateStr == null  || startDateStr.equals("") && endDateStr.equals("")) {
			model.addAttribute("listHD", getList_HD());
//			model.addAttribute("listCTHD", getList_CTHD().get(0).getPk().getSIZE().getTENSIZE());
			model.addAttribute("listCTHD", getList_CTHD());
			model.addAttribute("listSP", getList_SP());
			model.addAttribute("doanhThu", tongTienHoaDon(getList_HD()));
			model.addAttribute("topSanPham", gopSoLuongSanPham(getList_CTHD()));
			System.out.print("2 ko có 2 đều null");
			
			return "views/thongke/thongkehoadon"; // Trả về trang JSP
		}
		// 1 bằng null
		else if ( startDateStr.equals("") ||endDateStr.equals("")) {

			model.addAttribute("thongBao", "Điền đủ thời gian");
			System.out.print("1 ko có");
			return "views/thongke/thongkehoadon"; // Trả về trang JSP
		    } else if (startDate.after(ngayHienTai) || endDate.after(ngayHienTai)) {
						model.addAttribute("thongBao", "Thời gian phải bé hơn ngày hôm nay");
						System.out.print("Thời gian phải bé hơn ngày hôm nay");
						return "views/thongke/thongkehoadon"; // Trả về trang JSP
					}

						if (startDate.equals(endDate)) {
							model.addAttribute("thongBao", "Ngày bắt đầu phải bé hơn ngày kết thúc");
							System.out.print("Ngày bắt đầu phải bé hơn ngày kết thúc");
							return "views/thongke/thongkehoadon"; // Trả về trang JSP
						}
		
			
            // ko nào null nhưng bằng nhau
			if (startDate.equals(endDate)) {
				model.addAttribute("thongBao", "Ngày bắt đầu phải bé hơn ngày kết thúc");
				System.out.print("Ngày bắt đầu phải bé hơn ngày kết thúc");
				return "views/thongke/thongkehoadon"; // Trả về trang JSP
			}
		
		List<ChiTietHoaDon> chiTietHoaDon_time = getList_CTHD_time(startDate, endDate);
		List<HoaDon> myList_time = getList_HD_time(startDate, endDate);
		// List<ChiTietHoaDon> myListCTHD = chiTietHoaDon_time;
		List<SanPham> myListSP = getList_SP();
        if(myList_time.size()==0) {
        	model.addAttribute("thongBao", "Không có hóa đơn nào trong ngày này");
        }
		model.addAttribute("listHD", myList_time);
		model.addAttribute("listCTHD", chiTietHoaDon_time);
		model.addAttribute("listSP", myListSP);
		model.addAttribute("listSoLuongBan", gopSoLuongSanPham(chiTietHoaDon_time));
		model.addAttribute("doanhThu", tongTienHoaDon(myList_time));
		model.addAttribute("topSanPham", gopSoLuongSanPham(getList_CTHD()));
		
		System.out.print(gopSoLuongSanPham(chiTietHoaDon_time).toString());
		return "views/thongke/thongkehoadon";

	}
	public BigDecimal tongTienHoaDon (List<HoaDon> listHoaDon) {
		BigDecimal tongTien = new BigDecimal(0);
		for (HoaDon hd : listHoaDon) {
			tongTien = tongTien.add(hd.getTONGTIEN());
		}
		return tongTien;
		
	}
	public List<HoaDon> getList_HD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HoaDon WHERE PHANLOAI <> '0'");
		@SuppressWarnings("unchecked")
		List<HoaDon> list = query.list();

		for (HoaDon hoaDon : list) {
			System.out.print(hoaDon.getID() + " ");

		}
		return list;
	}

	public List<ChiTietHoaDon> getList_CTHD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietHoaDon");
		@SuppressWarnings("unchecked")
		List<ChiTietHoaDon> list = query.list();
		return list;
	}

	public List<SanPham> getList_SP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SanPham");
		@SuppressWarnings("unchecked")
		List<SanPham> list = query.list();
		return list;
	}

	public Map<String, Integer> gopSoLuongSanPham(List<ChiTietHoaDon> chiTietHoaDonList) {
	    // Sử dụng một Map để gộp số lượng sản phẩm giống nhau (cả mã sản phẩm và mã size)
	    Map<String, Integer> productQuantityMap = new HashMap<>();

	    for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
	        SanPham sanPham = chiTiet.getPk().getSANPHAM();
	        Size size = chiTiet.getPk().getSIZE(); // Lấy size của sản phẩm
	        int soLuong = chiTiet.getSOLUONG();

	        // Tạo một chuỗi nhận diện duy nhất cho sản phẩm dựa trên MASP và MASIZE
	        String productKey = sanPham.getMASP() + "-" + size.getMASIZE();

	        // Nếu sản phẩm đã có trong Map, cộng thêm số lượng
	        if (productQuantityMap.containsKey(productKey)) {
	            productQuantityMap.put(productKey, productQuantityMap.get(productKey) + soLuong);
	        } else {
	            productQuantityMap.put(productKey, soLuong);
	        }
	    }

	    // Sắp xếp Map theo giá trị (soLuong) từ lớn đến nhỏ
	    List<Map.Entry<String, Integer>> sortedEntries = productQuantityMap.entrySet()
	        .stream()
	        .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sắp xếp giảm dần
	        .collect(Collectors.toList());

	    // Tạo một LinkedHashMap để duy trì thứ tự đã sắp xếp
	    Map<String, Integer> sortedMap = new LinkedHashMap<>();
	    for (Map.Entry<String, Integer> entry : sortedEntries) {
	        sortedMap.put(entry.getKey(), entry.getValue());
	    }
	    // Trả về Map đã sắp xếp
	    return sortedMap;
	    }


	public Date convertStringToSqlDate(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
		java.util.Date utilDate = formatter.parse(dateStr);
		return new java.sql.Date(utilDate.getTime());
	}

	public List<HoaDon> getList_HD_time(Date startDate, Date endDate) {

		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		
		// danh sachs hóa đơn theo thời gian
		Query query = session.createQuery("FROM HoaDon cthd WHERE cthd.NGAYLAP BETWEEN :startDate AND :endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		List<HoaDon> hoaDons_time = query.list();

		return hoaDons_time; // Không cần đóng session
	}

	public List<ChiTietHoaDon> getList_CTHD_time(Date startDate, Date endDate) {

		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
	
		// danh sachs hóa đơn theo thời gian
		Query query = session.createQuery("FROM HoaDon cthd WHERE cthd.NGAYLAP BETWEEN :startDate AND :endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		List<ChiTietHoaDon> cthd = new ArrayList<>();
		List<HoaDon> hoaDons_time = query.list();

		// lấy ra danh sách chi tiết hóa đơn theo hd(ID)
		for (HoaDon hd : hoaDons_time) {
			cthd.addAll(getList_CTHD_1HD(hd));
		}
//		System.out.print("danh sachs háo đơn " + query.list().size());
//		System.out.print("danh sachs chi tiết háo đơn " + cthd.size());

		return cthd; // Không cần đóng session
	}

	public List<ChiTietHoaDon> getList_CTHD_1HD(HoaDon HD) {
		List<ChiTietHoaDon> listCthd = new ArrayList<>();
		for (ChiTietHoaDon cthd : getList_CTHD()) {
			if (cthd.getPk().getHOADON().getID().equals(HD.getID())) {
				listCthd.add(cthd);
			}
		}
		return listCthd;
	}
	public List<Size> getList_size() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM Size");
		return query.list(); // Không cần đóng session

	}

	public String layTenSP (String chuoi) {
		String[] parts = chuoi.split("-");
		String maSanPham = ""; // "SP001"
		maSanPham = parts[0]; // "SP001"
		for(SanPham sp : getList_SP()) {
			if(sp.getMASP().equalsIgnoreCase(maSanPham))
		    return sp.getTENSP();
		}
		return " lỗi lấy tên sản phẩm ";
	}
	public String layTenSize (String chuoi) {
		String[] parts = chuoi.split("-");
		if (parts.length > 1) {
			String maSizeMa = parts[1];  // "SizeL"
		    String maSize = maSizeMa.substring(maSizeMa.length() - 1);  // Lấy ký tự cuối của maSize (L)
			for(Size size : getList_size()) {
				if(size.getMASIZE().equalsIgnoreCase(maSize))
			    return size.getTENSIZE();
			}
		}
	
		return " lỗi lấy size sản phẩm ";
	}

}

//@RequestMapping("/statistics")
//public String name(ModelMap model) {
//	List<HoaDon> myList = getList_HD();
//	List<ChiTietHoaDon> myList1 = getList_CTHD();
//	List<SanPham> myList2 = getList_SP();
//	
//	model.addAttribute("listHD", myList);
//	model.addAttribute("listCTHD", myList1);
//	model.addAttribute("listSP", myList2);
//	
//;		return "views/thongke/danhsachhoadon";
//}
