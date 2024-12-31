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

import ptithcm.entity.LoaiSP;
import ptithcm.entity.NguyenLieu;

@Controller
@Transactional
@RequestMapping("/product/type")
public class TypeController {
	@Autowired
	SessionFactory factory;

	public List<LoaiSP> layDanhSachLoai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiSP";
		Query query = session.createQuery(hql);
		List<LoaiSP> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dsloai(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("loai",new LoaiSP());
		List<LoaiSP> DS = layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		return "views/quanly/sanpham/loaisanpham";
	}
	public LoaiSP getLoai(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from LoaiSP where MALOAI = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		LoaiSP list = (LoaiSP) query.list().get(0);
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

	@RequestMapping(value = "/list/{MALOAI}.htm", params = "linkDelete")
	public String deleteLoai(ModelMap model,@PathVariable("MALOAI")String MALOAI,LoaiSP loai) {
		System.out.println("linkDelete");
		LoaiSP loaitam=getLoai(MALOAI);
		int check = this.delete(loaitam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa loại thành công");
		} else {
			System.out.println(loai.toString());
			System.out.println(check);
			model.addAttribute("message0", "Xóa loại thất bại, có thể tồn tại sản phẩm có loại này");
		}
		List<LoaiSP> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("loai",loai);
		return "views/quanly/sanpham/loaisanpham";
	}

	@RequestMapping(value = "/list/{MALOAI}.htm", params = "linkEdit")
	public String editLoai(ModelMap model, @PathVariable("MALOAI") String MALOAI) {
		System.out.println("linkEdit");
		List<LoaiSP> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("loai", this.getLoai(MALOAI));
		return "views/quanly/sanpham/loaisanpham";
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
	public String edit_Loai(ModelMap model,   @ModelAttribute("loai") LoaiSP loai, BindingResult errors) {
		System.out.println("btnEdit");
		if(loai.getMALOAI().isBlank()) {
			errors.rejectValue("MALOAI","loai","Vui lòng nhập mã");
		}
		if(loai.getTENLOAI().isBlank()) {
			errors.rejectValue("TENLOAI","loai","Vui lòng nhập tên");
		}
		if(!errors.hasErrors()) {
		int check = this.update(loai);
			if (check != 0) {
				model.addAttribute("message1", "Sửa loại thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("loai", new LoaiSP());
			} else {
				model.addAttribute("message0", "Sửa loại thất bại! Không tồn tại mã hoặc trùng tên");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<LoaiSP> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		
		return "views/quanly/sanpham/loaisanpham";
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
	public String addLoai(ModelMap model,  @ModelAttribute("loai") LoaiSP loai, BindingResult errors) {
		System.out.println("btnAdd");
	    // Chuyển các giá trị của MALOAI và TENLOAI sang chữ in hoa
	    loai.setMALOAI(loai.getMALOAI().toUpperCase());
	    loai.setTENLOAI(loai.getTENLOAI().toUpperCase());
	    
		if(loai.getMALOAI().isBlank()) {
			errors.rejectValue("MALOAI","loai","Nhập mã loại sản phẩmm");
		}
		if(loai.getTENLOAI().isBlank()) {
			errors.rejectValue("TENLOAI","loai","Nhập tên loại sản phẩm");
		}
		if(!errors.hasErrors()) {
			int check = this.insert(loai);
			if (check != 0) {
				model.addAttribute("message1", "Thêm loại thành công!");
				model.addAttribute("loai", new LoaiSP());
			} else {
				model.addAttribute("message0", "Thêm loại thất bại! Trùng mã hoặc tên");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<LoaiSP> DS = this.layDanhSachLoai();
		model.addAttribute("dsloai", DS);
		
		return "views/quanly/sanpham/loaisanpham";
	}

}