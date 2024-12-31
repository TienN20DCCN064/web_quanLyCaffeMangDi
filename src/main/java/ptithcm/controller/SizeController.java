package ptithcm.controller;

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

import ptithcm.entity.Size;
import ptithcm.entity.Size;

@Controller
@Transactional
@RequestMapping("/product/size")
public class SizeController {
	@Autowired
	SessionFactory factory;

	public List<Size> layDanhSachSize() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Size";
		Query query = session.createQuery(hql);
		List<Size> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dssize(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("size",new Size());
		List<Size> DS = layDanhSachSize();
		model.addAttribute("dssize", DS);
		return "views/quanly/sanpham/size";
	}
	public Size getSize(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from Size where MASIZE = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		Size list = (Size) query.list().get(0);
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

	@RequestMapping(value = "/list/{MASIZE}.htm", params = "linkDelete")
	public String deleteSize(ModelMap model,@PathVariable("MASIZE")String MASIZE,
			Size size) {
		System.out.println("linkDelete");
		Size sizetam=getSize(MASIZE);
		int check = this.delete(sizetam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa size thành công");
		} else {
			model.addAttribute("message0", "Xóa size thất bại");
		}
		List<Size> DS = this.layDanhSachSize();
		model.addAttribute("dssize", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("size",size);
		return "views/quanly/sanpham/size";
	}

	@RequestMapping(value = "/list/{MASIZE}.htm", params = "linkEdit")
	public String editSize(ModelMap model,
			@PathVariable("MASIZE") String MASIZE) {
		System.out.println("linkEdit");
		List<Size> DS = this.layDanhSachSize();
		model.addAttribute("dssize", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("size", this.getSize(MASIZE));
		return "views/quanly/sanpham/size";
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
	public String edit_Size(ModelMap model,   @ModelAttribute("size") Size size, BindingResult errors) {
		System.out.println("btnEdit");
		if(size.getMASIZE().isBlank()) {
			errors.rejectValue("MASIZE", "size","Vui lòng nhập mã");
		}
		if(size.getTENSIZE().isBlank()) {
			errors.rejectValue("TENSIZE", "size","Vui lòng nhập tên");
		}
		if(!errors.hasErrors()) {
			int check = this.update(size);
			if (check != 0) {
				model.addAttribute("message1", "Sửa size thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("size", new Size());
			} else {
				model.addAttribute("message0", "Sửa size thất bại! Mã không tồn tại hoặc trùng tên size");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<Size> DS = this.layDanhSachSize();
		model.addAttribute("dssize", DS);
		
		return "views/quanly/sanpham/size";
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
	public String addSize(ModelMap model,  @ModelAttribute("size") Size size, BindingResult errors) {
		System.out.println("btnAdd");
		if(size.getMASIZE().isBlank()) {
			errors.rejectValue("MASIZE", "size","Vui lòng nhập mã");
		}
		if(size.getTENSIZE().isBlank()) {
			errors.rejectValue("TENSIZE", "size","Vui lòng nhập tên");
		}
		if(!errors.hasErrors()) {
			int check = this.insert(size);
			if (check != 0) {
				model.addAttribute("message1", "Thêm size thành công!");
				model.addAttribute("size", new Size());
			} else {
				model.addAttribute("message0", "Thêm size thất bại! Mã hoặc tên trùng");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<Size> DS = this.layDanhSachSize();
		model.addAttribute("dssize", DS);
		
		return "views/quanly/sanpham/size";
	}
}
