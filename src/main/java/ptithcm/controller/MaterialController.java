package ptithcm.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import ptithcm.entity.NguyenLieu;

@Controller
@Transactional
@RequestMapping("/material")
public class MaterialController {
	@Autowired
	SessionFactory factory;

	public List<NguyenLieu> layDanhSachNguyenLieu() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu";
		Query query = session.createQuery(hql);
		List<NguyenLieu> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dstaikhoan(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("nguyenlieu", new NguyenLieu());
		List<NguyenLieu> DS = layDanhSachNguyenLieu();
		model.addAttribute("dsnguyenlieu", DS);
		return "views/quanly/nguyenlieu";
	}
	public NguyenLieu getNguyenLieu(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from NguyenLieu where MANL = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		NguyenLieu list = (NguyenLieu) query.list().get(0);
		return list;
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

	@RequestMapping(value = "/list/{MANL}.htm", params = "linkDelete")
	public String deleteNguyenLieu(ModelMap model,@PathVariable("MANL")String MANL,
			NguyenLieu nguyenlieu) {
		System.out.println("linkDelete");
		NguyenLieu nguyenlieutam=getNguyenLieu(MANL);
		int check = this.delete(nguyenlieutam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa nguyên liệu thành công");
		} else {
			model.addAttribute("message0", "Xóa nguyên liệu thất bại");
		}
		List<NguyenLieu> DS = this.layDanhSachNguyenLieu();
		model.addAttribute("dsnguyenlieu", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("nguyenlieu",nguyenlieu);
		return "views/quanly/nguyenlieu";
	}

	@RequestMapping(value = "/list/{MANL}.htm", params = "linkEdit")
	public String editNguyenLieu(ModelMap model,
			@PathVariable("MANL") String MANL) {
		System.out.println("linkEdit");
		List<NguyenLieu> DS = this.layDanhSachNguyenLieu();
		model.addAttribute("dsnguyenlieu", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("nguyenlieu", this.getNguyenLieu(MANL));
		return "views/quanly/nguyenlieu";
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
	public String edit_NguyenLieu(ModelMap model,  @ModelAttribute("nguyenlieu") NguyenLieu nguyenlieu, BindingResult errors) {
		System.out.println("btnEdit");
		if(nguyenlieu.getMANL().isBlank()) {
			errors.rejectValue("MANL", "nguyenlieu", "Vui lòng nhập mã");
		}
		if(nguyenlieu.getTENNL().isBlank()) {
			errors.rejectValue("TENNL", "nguyenlieu", "Vui lòng nhập tên");
		}
		if(nguyenlieu.getDONVI().isBlank()) {
			errors.rejectValue("DONVI", "nguyenlieu", "Vui lòng nhập đơn vị");
		}
		if(nguyenlieu.getSLTON()==null) {
			errors.rejectValue("SLTON", "nguyenlieu", "Số lượng tồn không được để trống");
		}
		else if(nguyenlieu.getSLTON().compareTo(new BigDecimal("0.00"))<0) {
			errors.rejectValue("SLTON", "nguyenlieu", "Số lượng tồn không âm");
		}
		if(!errors.hasErrors()) {
			int check = this.update(nguyenlieu);
			if (check != 0) {
				model.addAttribute("message1", "Sửa nguyên liệu thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("nguyenlieu", new NguyenLieu());
			} else {
				model.addAttribute("message0", "Sửa nguyên liệu thất bại! Mã không tồn tại hoặc trùng tên nguyên liệu khác");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<NguyenLieu> DS = this.layDanhSachNguyenLieu();
		model.addAttribute("dsnguyenlieu", DS);
		
		return "views/quanly/nguyenlieu";
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
	public String addNguyenLieu(ModelMap model,@ModelAttribute("nguyenlieu") NguyenLieu nguyenlieu, BindingResult errors) {
		System.out.println("btnAdd");
		if(nguyenlieu.getMANL().isBlank()) {
			errors.rejectValue("MANL", "nguyenlieu", "Vui lòng nhập mã");
		}
		if(nguyenlieu.getTENNL().isBlank()) {
			errors.rejectValue("TENNL", "nguyenlieu", "Vui lòng nhập tên");
		}
		if(nguyenlieu.getDONVI().isBlank()) {
			errors.rejectValue("DONVI", "nguyenlieu", "Vui lòng nhập đơn vị");
		}
		if(nguyenlieu.getSLTON()==null) {
			errors.rejectValue("SLTON", "nguyenlieu", "Số lượng tồn không được để trống");
		}
		else if(nguyenlieu.getSLTON().compareTo(new BigDecimal("0.00"))<0) {
			errors.rejectValue("SLTON", "nguyenlieu", "Số lượng tồn không âm");
		}
		if(!errors.hasErrors()) {
			int check = this.insert(nguyenlieu);
			if (check != 0) {
				model.addAttribute("message1", "Thêm nguyên liệu thành công!");
				model.addAttribute("nguyenlieu", new NguyenLieu());
			} else {
				model.addAttribute("message0", "Thêm nguyên liệu thất bại! Trùng mã hoặc tên");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<NguyenLieu> DS = this.layDanhSachNguyenLieu();
		model.addAttribute("dsnguyenlieu", DS);
		
		return "views/quanly/nguyenlieu";
	}



}
