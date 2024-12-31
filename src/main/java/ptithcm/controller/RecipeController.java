//
// công thức 
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.entity.ChiTietNguyenLieu;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.CongThuc;
import ptithcm.entity.SanPham;
@Controller
@Transactional
@RequestMapping("/product/recipe")
public class RecipeController {
	@Autowired
	SessionFactory factory;

	public List<CongThuc> layDanhSachCongThuc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CongThuc";
		Query query = session.createQuery(hql);
		List<CongThuc> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dscongthuc(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("congthuc", new CongThuc());
		List<CongThuc> DS = layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		return "views/quanly/sanpham/congthuc";
	}
	public CongThuc getCongThuc(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from CongThuc where MACT = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		 List<CongThuc> list = query.list();  // Lưu kết quả vào danh sách
		
		if (list != null && !list.isEmpty()) {  // Kiểm tra danh sách có rỗng không
	        return list.get(0);  // Lấy phần tử đầu tiên nếu danh sách không rỗng
	    } else {
	        return null;  // Trả về null nếu không tìm thấy công thức
	    }
	}



	@RequestMapping(value = "/list/{MACT}.htm", params = "linkEdit")
	public String editCongThuc(ModelMap model,
			@PathVariable("MACT") String MACT) {
		System.out.println("linkEdit");
		List<CongThuc> DS = this.layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("congthuc", this.getCongThuc(MACT));
		return "views/quanly/sanpham/congthuc";
	}

	
	
	@RequestMapping(value = "/list", params = "btnEdit")
	public String edit_CongThuc(ModelMap model,@ModelAttribute("congthuc")  CongThuc congthuc, BindingResult errors) {
		System.out.println("btnEdit");
		if(congthuc.getMACT().isBlank()) {
			errors.rejectValue("MACT", "congthuc", "Vui lòng nhập mã");
		}
		if(congthuc.getCONGTHUC().isBlank()) {
			errors.rejectValue("CONGTHUC", "congthuc", "Vui lòng nhập công thức");
		}
		if(!errors.hasErrors()) {
		int check = this.update(congthuc);
			if (check != 0) {
				model.addAttribute("message1", "Sửa công thức thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("congthuc", new CongThuc());
			} else {
				model.addAttribute("message0", "Sửa công thức thất bại! Mã công thức không tồn tại!");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}else
		{
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<CongThuc> DS = this.layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		
		return "views/quanly/sanpham/congthuc";
	}
	

	@RequestMapping(value = "/list", params = "btnAdd")
	public String addCongThuc(ModelMap model,@ModelAttribute("congthuc") CongThuc congthuc, BindingResult errors) {
		if(congthuc.getMACT().isBlank()) {
			errors.rejectValue("MACT", "congthuc", "Vui lòng nhập mã");
		}
		if(congthuc.getCONGTHUC().isBlank()) {
			errors.rejectValue("CONGTHUC", "congthuc", "Vui lòng nhập công thức");
		}
		System.out.println("btnAdd");
		if(!errors.hasErrors()) {
			int check = this.insert(congthuc);
			
			if (check != 0) {
				model.addAttribute("message1", "Thêm công thức thành công!");
				model.addAttribute("congthuc", new CongThuc());
			} else {
				model.addAttribute("message0", "Thêm công thức thất bại! Mã công thức bị trùng!");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<CongThuc> DS = this.layDanhSachCongThuc();
		model.addAttribute("dscongthuc", DS);
		
		return "views/quanly/sanpham/congthuc";
	}


	@RequestMapping(value = "/list/{MACT}.htm", params = "btnDelete")
	public String deleteCongThuc(ModelMap model,@PathVariable("MACT") String MACT) {
	
		CongThuc congthuc = getCongThuc(MACT);
		// lấy luôn danh sách công thức trước khi cập nhâtj
		List<CongThuc> DS = this.layDanhSachCongThuc();
		System.out.println("btnDelete ");
//		System.out.println("btnDelete " + congthuc.getMACT() + congthuc.toString() );
		// xóa luôn công thức trong chi tiết sản phẩm
		for (ChiTietSanPham ctsp : getList_CTSP()) {
			if(ctsp.getCONGTHUC() != null && ctsp.getCONGTHUC().equals(congthuc)) {
				// câpj nhật lại bên công thức
				ctsp.setCONGTHUC(null);
				update(ctsp);
				System.out.print("\ntien ");
			}
		}
		
			int check = delete(congthuc);
			
			if (check ==1) {
				model.addAttribute("message1", "Xóa công thức thành công!");
				// Loại bỏ công thức đã xóa khỏi danh sách
			    DS.removeIf(ct -> ct.getMACT().equals(congthuc.getMACT()));

				
			} else {
				model.addAttribute("message0", "Xóa công thức thất bại!");
			}
		
	
      // truyền vào trong view ko có giá trị
		model.addAttribute("congthuc",new CongThuc());
		model.addAttribute("btnStatus", "btnDelete");
		model.addAttribute("dscongthuc", DS);
		System.out.println("số lượng " +  DS.size());
		
//		return "redirect:product/recipe/list.htm";
		return "views/quanly/sanpham/congthuc";
	}
	public List<ChiTietSanPham> getList_CTSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietSanPham");
		@SuppressWarnings("unchecked")
		List<ChiTietSanPham> list = query.list();
		return list;
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
	
}