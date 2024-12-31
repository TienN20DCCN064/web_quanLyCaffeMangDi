package ptithcm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	SessionFactory factory;

	public List<NhanVien> LayDanhSachNhanVien() {
		Session session = factory.openSession();
		String hql = "FROM NhanVien";
		Query query = session.createQuery(hql);
		List<NhanVien> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dsnhanvien(ModelMap model) {
		System.out.println("No Mapping");
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("nv", new NhanVien());
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		return "views/quanly/nhanvien";
	}

	public NhanVien getNVByMaNV(String manv) {
		Session session = factory.openSession();
		String hql = "FROM NhanVien WHERE MANV ='" + manv + "'";
		Query query = session.createQuery(hql);
//		NhanVien list = (NhanVien) query.list().get(0);
//		return list;
		
		 List<NhanVien> list = query.list();
		    if (list.isEmpty()) {
		        return null;
		    }
		    return list.get(0);
	}

	public TaiKhoan get1TaiKhoan(String ma) {
		Session session = factory.openSession();
		String hql = "FROM TaiKhoan where TENDANGNHAP = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
//		TaiKhoan list = (TaiKhoan) query.list().get(0);
//		return list;
		List<TaiKhoan> list = query.list();
	    if (list.isEmpty()) {
	        return null;
	    }
	    return list.get(0);
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

	@RequestMapping(value = "/list/{MANV}.htm", params = "linkDelete")
	public String delete(ModelMap model, @ModelAttribute("nv") NhanVien nv) {
		System.out.println("linkDelete - MANV: " + nv.getMANV());
		NhanVien nvtemp = getNVByMaNV(nv.getMANV());
		
		int check = this.delete(nvtemp);
		if (check == 1) {
			
			model.addAttribute("message1", "Xóa nhân viên thành công!");

			
		} else {
			TaiKhoan temp = new TaiKhoan();
			temp = get1TaiKhoan(nv.getMANV());
			temp.setTRANGTHAI(false);
			delete(temp);
			int check1 = delete(nvtemp);
			if (check1 == 1)
				model.addAttribute("message1", "Xóa nhân viên thành công!");
			else
				model.addAttribute("message0",
						"Xóa nhân viên thất bại do nhân viên đã được lập tài khoản, tài khoản hiện có thể liên quan đến nhiều tác vụ");
		}
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("nv", new NhanVien());
		return "views/quanly/nhanvien";
	}

	@RequestMapping(value = "/list/{MANV}.htm", params = "linkEdit")
	public String editAccount(ModelMap model, @ModelAttribute("nv") NhanVien nv, @PathVariable("MANV") String MANV) {
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		model.addAttribute("nv", this.getNVByMaNV(MANV));
		model.addAttribute("btnStatus", "btnEdit");
		return "views/quanly/nhanvien";
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

	@RequestMapping(value = "/list", params = "btnEdit")
	public String edit_Account(ModelMap model, @ModelAttribute("nv") NhanVien nv, BindingResult errors) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(nv.getNGAYSINH());
		Date date = dateFormat.parse(dateStr);
		java.util.Date utilDate = new java.util.Date(date.getTime());
		nv.setNGAYSINH(date);
		if(nv.getHOTENNV().isBlank()) {
			errors.rejectValue("HO", "nv","Mời nhập mã");
		}
		
		if(nv.getHOTENNV().isBlank()) {
			errors.rejectValue("TEN", "nv","Mời nhập tên");
		}
		if(nv.getDIACHI().isBlank()) {
			errors.rejectValue("DIACHI", "nv","Mời nhập địa chỉ");
		}
		if(nv.getEMAIL().isBlank()) {
			errors.rejectValue("EMAIL1", "nv","Mời nhập email");
		}
		if(nv.getSDT().isBlank()) {
			errors.rejectValue("SDT1", "nv","Mời nhập số điện thoại");
		}
		if(!errors.hasErrors()) {
		NhanVien temp = new NhanVien();
		temp.setDIACHI(nv.getDIACHI());
		temp.setEMAIL(nv.getEMAIL());
		if (!nv.getEMAIL().isBlank())
			temp.setEMAIL(nv.getEMAIL());
		else
			temp.setEMAIL(" ");
		temp.setGT(nv.getGT());
		temp.setHOTENNV(nv.getHOTENNV());
		temp.setMANV(nv.getMANV());
		temp.setNGAYSINH(utilDate);
		temp.setSDT(nv.getSDT());
		if (!nv.getSDT().isBlank())
			temp.setSDT(nv.getSDT());
		else
			temp.setSDT(" ");
		temp.setHOTENNV(nv.getHOTENNV());
		System.out.print("Thông tin nhân viên sửa: " + temp.getMANV() + " " + temp.getDIACHI() + " " + temp.getEMAIL()
				+ " " + temp.getGT() + " " + temp.getHOTENNV() + " " + temp.getHOTENNV() + " " + temp.getSDT() + " "
				+ temp.getNGAYSINH());

		int check = this.update(temp);
		if (check != 0) {
			model.addAttribute("message1", "Sửa nhân viên thành công!");
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("nv", new NhanVien());
		} else {
			model.addAttribute("message0", "Sửa nhân viên thất bại!");
			model.addAttribute("btnStatus", "btnEdit");
		}
		}
		else {
			model.addAttribute("message0", "Sửa nhân viên thất bại!");
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		return "views/quanly/nhanvien";
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

	@RequestMapping(value = "/list", params = "btnAdd")
	public String addAccount(ModelMap model, @ModelAttribute("nv") NhanVien nv, BindingResult errors) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(nv.getNGAYSINH());
		Date date = dateFormat.parse(dateStr);
		java.util.Date utilDate = new java.util.Date(date.getTime());
		nv.setNGAYSINH(utilDate);
		if(nv.getMANV().isBlank()) {
			errors.rejectValue("MANV", "nv","Mời nhập mã");
		}

		if(nv.getHOTENNV().isBlank()) {
			errors.rejectValue("HOTENNV", "nv","Mời nhập họ tên nhân viên");
		}
		if(nv.getDIACHI().isBlank()) {
			errors.rejectValue("DIACHI", "nv","Mời nhập địa chỉ");
		}
		if(nv.getEMAIL().isBlank()) {
			errors.rejectValue("EMAIL", "nv","Mời nhâp email");
		}
		if(nv.getSDT().isBlank()) {
			errors.rejectValue("SDT", "nv","Mời nhập số điện thoại");
		}
		if(!errors.hasErrors()) {
		NhanVien temp = new NhanVien();
		temp.setDIACHI(nv.getDIACHI());
		temp.setEMAIL(nv.getEMAIL());
		if (!nv.getEMAIL().isBlank())
			temp.setEMAIL(nv.getEMAIL());
		else
			temp.setEMAIL(" ");
		temp.setGT(nv.getGT());
		temp.setHOTENNV(nv.getHOTENNV());
		temp.setMANV(nv.getMANV());
		temp.setNGAYSINH(utilDate);
		temp.setSDT(nv.getSDT());
		if (!nv.getSDT().isBlank())
			temp.setSDT(nv.getSDT());
		else
			temp.setSDT(" ");
		temp.setHOTENNV(nv.getHOTENNV());
		System.out.print("Thông tin nhân viên thêm: " + temp.getMANV() + " / " + temp.getDIACHI() + " / " + temp.getEMAIL()
				+ " / " + temp.getGT() + "  / " + temp.getHOTENNV() + " / " + temp.getSDT() + " / "
				+ temp.getNGAYSINH());
		int check = this.insert(temp);
		if (check != 0) {
			model.addAttribute("message1", "Thêm nhân viên thành công!");
			model.addAttribute("nv", new NhanVien());
		} else {
			model.addAttribute("message0", "Thêm nhân viên thất bại!");
		}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<NhanVien> DS = this.LayDanhSachNhanVien();
		model.addAttribute("dsnhanvien", DS);
		return "views/quanly/nhanvien";
	}
}
