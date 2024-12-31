package ptithcm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;
import ptithcm.entity.NhanVien;
import ptithcm.entity.SanPham;
import ptithcm.entity.Size;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/bill/")
public class BillController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("list")
	@Transactional
	public String bill(ModelMap model, @RequestParam(value = "startDate", required = false) String startDateStr,
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
		model.addAttribute("listNV", getList_NV());

		// 2 bằng null
		if (startDateStr == null && endDateStr == null || startDateStr.equals("") && endDateStr.equals("")) {
			model.addAttribute("listHD", getList_HD());
			System.out.print("2 ko có 2 đều null");

			return "views/quanly/hoadon";
		}
		// 1 bằng null
		else if (startDateStr.equals("") || endDateStr.equals("")) {
			model.addAttribute("listHD", getList_HD());
			model.addAttribute("thongBao", "Điền đủ thời gian");
			
			System.out.print("1 ko có");
			return "views/quanly/hoadon";
		} else if (startDate.after(ngayHienTai) || endDate.after(ngayHienTai)) {
			model.addAttribute("thongBao", "Thời gian phải bé hơn ngày hôm nay");
			model.addAttribute("listHD", getList_HD());
			System.out.print("Thời gian phải bé hơn ngày hôm nay");
			return "views/quanly/hoadon";
		}

		if (startDate.equals(endDate)) {
			model.addAttribute("thongBao", "Ngày bắt đầu phải bé hơn ngày kết thúc");
			model.addAttribute("listHD", getList_HD());
			System.out.print("Ngày bắt đầu phải bé hơn ngày kết thúc");
			return "views/quanly/hoadon";
		}

		// ko nào null nhưng bằng nhau
		if (startDate.equals(endDate)) {
			model.addAttribute("thongBao", "Ngày bắt đầu phải bé hơn ngày kết thúc");
			model.addAttribute("listHD", getList_HD());
			System.out.print("Ngày bắt đầu phải bé hơn ngày kết thúc");
			return "views/quanly/hoadon";
		}

		// ko cái nào null
		List<HoaDon> myList_time = getList_HD_time(startDate, endDate);
		// List<ChiTietHoaDon> myListCTHD = chiTietHoaDon_time;
		
		if (myList_time.size() == 0) {
			 List<HoaDon> tt = new ArrayList<>();
			model.addAttribute("thongBao", "Không có hóa đơn nào trong ngày này");
		}
		
		model.addAttribute("listHD", myList_time);
		return "views/quanly/hoadon";
	}

	public List<HoaDon> getList_HD() {
		Session session = factory.openSession();
		Query query = session.createQuery("FROM HoaDon");
		List<HoaDon> list = query.list();
		session.close();
		return list;
	}
	public List<NhanVien> getList_NV() {
		Session session = factory.openSession();
		Query query = session.createQuery("FROM NhanVien");
		List<NhanVien> list = query.list();
		session.close();
		return list;
	}

	public int update(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
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

//	/ chưa mã hóa lại mật khẩu   //  chức năng này cho phép cập nhật lại mật khẩu của tài khoản đó
//	@RequestMapping(value = "/list/{TENDANGNHAP}.htm", params = "linkReset")
//	public String reset_Account(ModelMap model, @ModelAttribute("tk") TaiKhoan tk) {
	public List<ChiTietHoaDon> getList_CTHD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietHoaDon");
		@SuppressWarnings("unchecked")
		List<ChiTietHoaDon> list = query.list();
		return list;
	}
	@RequestMapping(value = "/list/{IDHD}.htm", params = "linkChangeStatus")
	public String change_status_bill(ModelMap model, @PathVariable("IDHD") String ID) {
		System.out.println("Không lỗi, ID: " + ID);

		HoaDon temp = get1Bill(ID);
		// Cập nhật trạng thái
		if (temp != null) {
			if (temp.getPHANLOAI().equals("1")) {
				temp.setPHANLOAI("0");
			} else {
				temp.setPHANLOAI("1");
			}
			int check = update(temp);
			System.out.println(check != 0 ? "Cập nhật thành công" : "Cập nhật thất bại");
		} else {
			System.out.println("Không tìm thấy hóa đơn với ID: " + ID);
		}
		List<HoaDon> myList = this.getList_HD();

		model.addAttribute("listHD", myList);

		return "redirect:/bill/list.htm"; // Hoặc URL trang danh sách của bạn
	}

	@Transactional
	public HoaDon get1Bill(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon where IDHD = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		return (HoaDon) query.uniqueResult();
	}

}
