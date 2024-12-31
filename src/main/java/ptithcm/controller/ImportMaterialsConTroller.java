package ptithcm.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.CT_PhieuNhap;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.NguyenLieuDTO;
import ptithcm.entity.NhanVien;
import ptithcm.entity.PhieuNhapHang;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/import")
public class ImportMaterialsConTroller {
	@Autowired
	SessionFactory factory;
	public LocalDateTime currentDateTime;
	public String manh = "";
	String manhaphang = "";
	String nvdn = "";
	List<NguyenLieuDTO> dsnguyenlieunhap = new ArrayList<NguyenLieuDTO>();

	public String quyennhapnguyenlieu(String manv) {
		Session session = factory.openSession();
		String hql = "FROM TaiKhoan WHERE TENDANGNHAP ='" + manv + "'";
		Query query = session.createQuery(hql);
		TaiKhoan list = (TaiKhoan) query.list().get(0);
		String maquyennhap = list.getQUYEN().getMAQUYEN();
		return maquyennhap;
	}

	public NhanVien getNVByMaNV(String manv) {

		Session session = factory.openSession();
		String hql = "FROM NhanVien WHERE MANV ='" + manv + "'";
		Query query = session.createQuery(hql);
        System.out.print(manv);
		NhanVien list = (NhanVien) query.list().get(0);
		return list;
	}

	@ModelAttribute("dsnl")
	public List<NguyenLieu> getdsNguyenLieu() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu";
		Query query = session.createQuery(hql);
		List<NguyenLieu> list = query.list();
		return list;
	}

	@ModelAttribute("nguyenlieu")
	public List<NguyenLieuDTO> getNguyenLieu() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu";
		Query query = session.createQuery(hql);
		List<NguyenLieu> list = query.list();
		List<NguyenLieuDTO> lst = new ArrayList<NguyenLieuDTO>();
		for (int i = 0; i < list.size(); i++) {
			NguyenLieuDTO temp = new NguyenLieuDTO();
			temp.setDONVI(list.get(i).getDONVI());
			temp.setMANL(list.get(i).getDONVI());
			temp.setSLTON(list.get(i).getSLTON());
			temp.setTENNLDTO(list.get(i).getTENNL());
			temp.setSOLUONG(BigDecimal.valueOf(0));
			temp.setDONGIA(BigDecimal.valueOf(0));
			lst.add(temp);
		}
		return lst;
	}

	public List<PhieuNhapHang> getThongTinPN() {
		Session session = factory.openSession();
		String hql = "FROM PhieuNhapHang";
		Query query = session.createQuery(hql);
		List<PhieuNhapHang> list = query.list();
		return list;
	}

	public PhieuNhapHang getPN(String ma) {
		Session session = factory.openSession();
		String hql = "FROM PhieuNhapHang WHERE MANH = '" + ma + "'";
		Query query = session.createQuery(hql);
		PhieuNhapHang list = (PhieuNhapHang) query.list().get(0);
		return list;
	}
	
	public List<CT_PhieuNhap> getCTPN(String ma) {
		Session session = factory.openSession();
		String hql = "FROM CT_PhieuNhap WHERE MANH ='" + ma + "'";
		Query query = session.createQuery(hql);
		List<CT_PhieuNhap> list = query.list();
		return list;
	}
	
	
	@RequestMapping("defineId")  // ok
	public String dsnhanviendefineId(ModelMap model) {
		System.out.println("No Mapping");
		model.addAttribute("btnStatus", "btnAdd");
		List<PhieuNhapHang> pn = getThongTinPN();
		model.addAttribute("dspn", pn);
		currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		NhanVien nv = new NhanVien();
		if (!nvdn.equals(""))
			nv = getNVByMaNV(nvdn);
		else
			nv = getNVByMaNV(LoginController.manv);
		model.addAttribute("thoigiannhaphang", formattedDateTime);
//		model.addAttribute("nv", nv.getHO() + " " + nv.getTEN());
		model.addAttribute("nv", nv.getHOTENNV());
		System.out.println("quyền "+quyennhapnguyenlieu(LoginController.manv));
		if (quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
			return "views/quanly/nhaphang1nv";
		return "views/quanly/nhaphang1ql";
	}
// thêm 1 mã nhập hàng ( list nguyên liêu )     // ok
	@RequestMapping(value = "/defineId", params = "btnAdd")  // ok
	public String dsnheId(ModelMap model, HttpServletRequest rq) {
		model.addAttribute("quyennhap", quyennhapnguyenlieu(LoginController.manv));
		model.addAttribute("btnStatus", "btnAdd");
		manhaphang = rq.getParameter("manhaphang");
		if (manhaphang.equals("")) {
			/*
			 * model.addAttribute("message0", "Vui lòng điền thông tin mã nhập hàng.");
			 */
		} else {
			Session session = factory.openSession();
			String hql = "FROM PhieuNhapHang WHERE MANH = '" + manhaphang + "'";
			Query query = session.createQuery(hql);
			List<PhieuNhapHang> list = query.list();
			if (list.size() > 0) {
				List<PhieuNhapHang> pn = getThongTinPN();
				model.addAttribute("dspn", pn);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formattedDateTime = currentDateTime.format(formatter);
				NhanVien nv = getNVByMaNV(LoginController.manv);
				model.addAttribute("nv", nv.getHOTENNV());
				model.addAttribute("thoigiannhaphang", formattedDateTime);
		
				model.addAttribute("message0",
						" Vui lòng nhập mã phiếu nhập mới do mã đã tồn tại trong cơ sở dữ liệu, chi tiết xem ở bảng bên dưới");
				if (quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
					return "views/quanly/nhaphang1nv";
				return "views/quanly/nhaphang1ql";
			} else
				System.out.print("\nĐang ở http://localhost:8080/Highlands/import/list.htm" );
				return "redirect:http://localhost:8080/Highlands/import/list.htm";
		}
		System.out.print("\nĐang ở http://localhost:8080/Highlands/import/defineId.htm" );
		return "redirect:http://localhost:8080/Highlands/import/defineId.htm";
	}

	public int delete(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
  // quản lý mới có chưcs năg này
	@RequestMapping(value = "/defineId/{MANH}.htm", params = "linkDelete")   // ok
	public String delete(ModelMap model, @PathVariable("MANH") String ma) {
		model.addAttribute("quyennhap", quyennhapnguyenlieu(LoginController.manv));
		System.out.println("quyen nhp nguyen lieu: " + quyennhapnguyenlieu(LoginController.manv));
		System.out.println("ma nhap hang: " + ma);
		PhieuNhapHang pn = new PhieuNhapHang();
		pn = getPN(ma);
		System.out.print("TTPN: " + pn.getMANH() + " " + pn.getNGAYNHAP() + " " + pn.getNhanvien().getMANV());
		int check = this.delete(pn);
		if (check == 1) {
			model.addAttribute("message1", "Xóa phiếu nhập thành công!");
		} else {
			List<CT_PhieuNhap> dsxoa = new ArrayList<CT_PhieuNhap>();
			dsxoa = getCTPN(ma);
			for (int i = 0; i < dsxoa.size(); i++) {
				BigDecimal slnhap = dsxoa.get(i).getSOLUONG();
				System.out.print("SL nhap "+ dsxoa.get(i).getPk().getNGUYENLIEU().getTENNL()+": "+slnhap);
				NguyenLieu nguyenlieunhap = new NguyenLieu();
				nguyenlieunhap = get1NguyenLieu(dsxoa.get(i).getPk().getNGUYENLIEU().getMANL());
				System.out.print("SL ton: "+ nguyenlieunhap.getSLTON());
				nguyenlieunhap.setSLTON(nguyenlieunhap.getSLTON().subtract(slnhap));
				System.out.print("SL ton sau khi huy phieunhap: "+ nguyenlieunhap.getSLTON());
				
				update(nguyenlieunhap);
				System.out.println("dlt " + dsxoa.get(i).getPk().getNGUYENLIEU().getTENNL());
				CT_PhieuNhap temp = new CT_PhieuNhap();
				temp.setDONGIA(dsxoa.get(i).getDONGIA());
				temp.setPk(dsxoa.get(i).getPk());
				temp.setSOLUONG(dsxoa.get(i).getSOLUONG());
				this.delete(temp);
			}
			int check1 = this.delete(pn);
			if (check != 1)
				model.addAttribute("message1", "Xóa phiếu nhập thành công!");
		}
		List<PhieuNhapHang> dspn = getThongTinPN();
		model.addAttribute("dspn", dspn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		NhanVien nv = getNVByMaNV(LoginController.manv);
		model.addAttribute("nv", nv.getHOTENNV());
		model.addAttribute("thoigiannhaphang", formattedDateTime);
		model.addAttribute("btnStatus", "btnAdd");
		if (quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
			return "views/quanly/nhaphang1nv";
		return "views/quanly/nhaphang1ql";
	}

	@RequestMapping("list")    // ok
	public String dsnhanvien(ModelMap model) {
		model.addAttribute("quyennhap", quyennhapnguyenlieu(LoginController.manv));
		System.out.println("quyen nhp nguyen lieu: " + quyennhapnguyenlieu(LoginController.manv));
		model.addAttribute("btnStatus", "btnAdd");
		List<PhieuNhapHang> pn = getThongTinPN();
		model.addAttribute("dspn", pn);
		NhanVien nv = getNVByMaNV(LoginController.manv);
		model.addAttribute("nv", nv.getHOTENNV());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		model.addAttribute("thoigiannhaphang", formattedDateTime);
		model.addAttribute("manhaphang", manhaphang);
//		model.addAttribute("nguyenlieu", dsnguyenlieunhap);

		model.addAttribute("nguyenlieu", getNguyenLieu());
		if (quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
			return "views/quanly/nhaphangnv";
		return "views/quanly/nhaphangql";
	} 

	public int insert(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public NguyenLieu get1NguyenLieu(String ma) {
		Session session = factory.openSession();
		String hql = "FROM NguyenLieu where MANL = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		NguyenLieu list = (NguyenLieu) query.list().get(0);
		return list;
	}

	@RequestMapping(value = "/list", params = "btnAdd")  // ok
	public String addChiTietNguyenLieu(ModelMap model, HttpServletRequest rq) {
		NhanVien nv = getNVByMaNV(LoginController.manv);
		model.addAttribute("nv", nv.getHOTENNV());
		model.addAttribute("quyennhap", quyennhapnguyenlieu(LoginController.manv));
		System.out.println("quyen nhp nguyen lieu: " + quyennhapnguyenlieu(LoginController.manv));
		model.addAttribute("btnStatus", "btnAdd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		model.addAttribute("thoigiannhaphang", formattedDateTime);
		model.addAttribute("manhaphang", manhaphang);
		String chonnguyenlieu = rq.getParameter("chonnguyenlieu");
		NguyenLieu temp = new NguyenLieu();
		temp = get1NguyenLieu(chonnguyenlieu);
		model.addAttribute("nl", temp);
		String soluong = rq.getParameter("soluong");
		String dongia = rq.getParameter("dongia");
		model.addAttribute("sl", soluong);
		model.addAttribute("dg", dongia);
		if (soluong.trim().equals("") || dongia.trim().equals("")) {
			model.addAttribute("nguyenlieu", dsnguyenlieunhap);
			model.addAttribute("message0", "Vui lòng nhập đủ số lượng và đơn giá của nguyên liệu nhập");
		} else {
			BigDecimal sl = new BigDecimal(soluong);
			BigDecimal dg = new BigDecimal(dongia);
			
			NguyenLieuDTO nl = new NguyenLieuDTO();
			nl.setDONGIA(dg);
			nl.setMANL(chonnguyenlieu);
			nl.setSOLUONG(sl);
			int index = -1;
			for (int i = 0; i < dsnguyenlieunhap.size(); i++) {
				if (dsnguyenlieunhap.get(i).getMANL().equals(nl.getMANL())) {
					index = i;
					break;
				}
			}
			if (index > -1)
				dsnguyenlieunhap.remove(index);
			dsnguyenlieunhap.add(nl);
			Session session = factory.getCurrentSession();
			String hql = "FROM NguyenLieu";
			Query query = session.createQuery(hql);
			List<NguyenLieu> list = query.list();
			List<NguyenLieuDTO> lst = new ArrayList<NguyenLieuDTO>();
			for (int i = 0; i < list.size(); i++) {
				NguyenLieuDTO tempnl = new NguyenLieuDTO();
				tempnl.setDONVI(list.get(i).getDONVI());
				tempnl.setMANL(list.get(i).getDONVI());
				tempnl.setSLTON(list.get(i).getSLTON());
				tempnl.setTENNLDTO(list.get(i).getTENNL());
				tempnl.setSOLUONG(BigDecimal.valueOf(0));
				tempnl.setDONGIA(BigDecimal.valueOf(0));
				for (int j = 0; j < dsnguyenlieunhap.size(); j++) {
					if (list.get(i).getMANL().equals(dsnguyenlieunhap.get(j).getMANL())) {
						tempnl.setSOLUONG(dsnguyenlieunhap.get(j).getSOLUONG());
						tempnl.setDONGIA(dsnguyenlieunhap.get(j).getDONGIA());
						break;
					}
				}
				lst.add(tempnl);
			}
			model.addAttribute("nguyenlieu", lst);
			
		}
		if (quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
			return "views/quanly/nhaphangnv";
		return "views/quanly/nhaphangql";
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

	@RequestMapping(value = "/list", params = "btnSave")
	public String luuctpn(ModelMap model, HttpServletRequest rq) {
		model.addAttribute("quyennhap", quyennhapnguyenlieu(LoginController.manv));
		System.out.println("quyen nhp nguyen lieu: " + quyennhapnguyenlieu(LoginController.manv));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		model.addAttribute("thoigiannhaphang", formattedDateTime);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("manhaphang", manhaphang);
		PhieuNhapHang pn = new PhieuNhapHang();
		pn.setMANH(manhaphang);
		pn.setNGAYNHAP(Timestamp.valueOf(currentDateTime));
		pn.setNhanvien(getNVByMaNV(LoginController.manv));
		this.insert(pn);
		if (dsnguyenlieunhap.size() == 0) {
			model.addAttribute("message0", "Bạn chưa 'Nhập mặt hàng' nào, vui lòng nhập hàng để lưu phiếu nhập");
			if (quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
				return "views/quanly/nhaphangnv";
			return "views/quanly/nhaphangql";
		}
		for (int i = 0; i < dsnguyenlieunhap.size(); i++) {
			NguyenLieu nl = get1NguyenLieu(dsnguyenlieunhap.get(i).getMANL());
			CT_PhieuNhap ctpn = new CT_PhieuNhap();
			CT_PhieuNhap.PK pk = new CT_PhieuNhap.PK();
			pk.setNGUYENLIEU(nl);
			pk.setCT_SANPHAM(pn);
			ctpn.setPk(pk);
			ctpn.setDONGIA(dsnguyenlieunhap.get(i).getDONGIA());
			ctpn.setSOLUONG(dsnguyenlieunhap.get(i).getSOLUONG());
			nl.setSLTON(nl.getSLTON().add(dsnguyenlieunhap.get(i).getSOLUONG()));
			update(nl);
			this.insert(ctpn);
		}
		nvdn = LoginController.manv;
		model.addAttribute("message1", "Lưu phiếu nhập hàng thành công!");
		model.addAttribute("nguyenlieu", getNguyenLieu());
		dsnguyenlieunhap = new ArrayList<NguyenLieuDTO>();
		model.addAttribute("dspn", getThongTinPN());
		if(quyennhapnguyenlieu(LoginController.manv).equals("Q03"))
			return "views/quanly/nhaphangnv";
		return "views/quanly/nhaphangql";
	}
}
