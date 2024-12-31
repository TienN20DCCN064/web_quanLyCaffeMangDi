package ptithcm.controller;

import java.math.BigDecimal;
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

import ptithcm.entity.ChiTietNguyenLieu;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.CongThuc;
import ptithcm.entity.LichSu;
import ptithcm.entity.LoaiSP;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.SanPham;
import ptithcm.entity.Size;

@Controller
@Transactional
@RequestMapping("/product/detail")
public class DetailProductController {
	@Autowired
	SessionFactory factory;

	public List<ChiTietSanPham> layDanhSachChiTietSanPham() {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietSanPham";
		Query query = session.createQuery(hql);
		List<ChiTietSanPham> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dschitiet(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("chitiet", new ChiTietSanPham());
		List<ChiTietSanPham> DS = layDanhSachChiTietSanPham();
		model.addAttribute("dschitiet", DS);
		return "views/quanly/sanpham/chitietsanpham";
	}
	
	public ChiTietSanPham getChiTietSanPham(String masp,String masize) {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietSanPham where pk.SANPHAM.MASP = :masp and pk.SIZE.MASIZE =:masize";
		Query query = session.createQuery(hql);
		query.setParameter("masp",masp);
		query.setParameter("masize", masize);
		ChiTietSanPham list = (ChiTietSanPham) query.list().get(0);
		return list;
	}
	@ModelAttribute("cacsize")
	public List<Size>layDanhSachSize(){
		Session session=factory.getCurrentSession();
		String hql="From Size";
		Query query=session.createQuery(hql);
		List<Size>list=query.list();
		return list;
	}
	@ModelAttribute("cacsanpham")
	public List<SanPham>layDanhSachSanPham(){
		Session session=factory.getCurrentSession();
		String hql="From SanPham";
		Query query=session.createQuery(hql);
		List<SanPham>list=query.list();
		return list;
	}
	@ModelAttribute("caccongthuc")
	public List<CongThuc>layDanhSachCongThuc(){
		Session session=factory.getCurrentSession();
		String hql="From CongThuc";
		Query query=session.createQuery(hql);
		List<CongThuc>list=query.list();
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
	

	@RequestMapping(value = "/list/{masp}/{masize}.htm", params = "linkDelete")
	public String deleteChiTietSanPham(ModelMap model,@PathVariable("masp")String masp,@PathVariable("masize")String masize,
			ChiTietSanPham chitiet) {
		System.out.println("linkDelete");
		ChiTietSanPham chitiettam=getChiTietSanPham(masp,masize);
		int check = this.delete(chitiettam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa chi tiết sản phẩm thành công");
		} else {
			model.addAttribute("message0", "Xóa chi tiết sản phẩm thất bại");
		}
		List<ChiTietSanPham> DS = this.layDanhSachChiTietSanPham();
		model.addAttribute("dschitiet", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("chitiet",chitiet);
		return "views/quanly/sanpham/chitietsanpham";
	}

	@RequestMapping(value = "/list/{masp}/{masize}.htm", params = "linkEdit")
	public String editChiTietSanPham(ModelMap model,
			@PathVariable("masp") String masp, @PathVariable("masize")String masize) {
		System.out.println("linkEdit");
		List<ChiTietSanPham> DS = this.layDanhSachChiTietSanPham();
		model.addAttribute("dschitiet", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("chitiet", this.getChiTietSanPham(masp,masize));
		return "views/quanly/sanpham/chitietsanpham";
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
	public String edit_ChiTietSanPham(ModelMap model, @ModelAttribute("chitiet") ChiTietSanPham chitiet, BindingResult errors) {
		System.out.println("btnEdit");
		List<ChiTietSanPham> DS = this.layDanhSachChiTietSanPham();
		BigDecimal giatam=getChiTietSanPham(chitiet.getSANPHAM().getMASP(),chitiet.getSIZE().getMASIZE()).getGIAHIENTHOI();
		if(chitiet.getGIAHIENTHOI()==null) {
			errors.rejectValue("GIAHIENTHOI", "chitiet", "Vui lòng nhập giá");
		}
		else if(chitiet.getGIAHIENTHOI().compareTo(new BigDecimal("0.00"))<=0){
			errors.rejectValue("GIAHIENTHOI", "chitiet", "Giá không được nhỏ hơn hoặc bằng 0");
		}
		if(chitiet.getCONGTHUC().getMACT().equals("")) {
			chitiet.setCONGTHUC(null);
		}
		if(!errors.hasErrors()) {
			int check = this.update(chitiet);
			if (check != 0) {
				model.addAttribute("message1", "Sửa chi tiết sản phẩm thành công!");
				 // Cập nhật đối tượng trong danh sách DS có MACT trùng với MACT của chitiet
	   
				
				 // Duyệt qua danh sách DS để tìm phần tử có MASP và MASIZE trùng với chitiet
		        for (int i = 0; i < DS.size(); i++) {
		            ChiTietSanPham ct = DS.get(i);
		            // tìm chi tiết sản phẩm
		            if (ct.getSANPHAM().getMASP().equals(chitiet.getSANPHAM().getMASP()) &&ct.getSIZE().getMASIZE().equals(chitiet.getSIZE().getMASIZE())) {
		                // Cập nhật chi tiết sản phẩm trong danh sách

		                ChiTietSanPham ctsp = DS.get(i);
		                // nếu muốn cập nhật 1 công thức rỗng cho ctsp
		                if(chitiet.getCONGTHUC() != null) {
		                	ctsp.setCONGTHUC(get_1_CT(chitiet.getCONGTHUC().getMACT()));	
		                }
		                else {
							ctsp.setCONGTHUC(null);
						}
		               //  ctsp.setCONGTHUC(get_1_CT(chitiet.getCONGTHUC().getMACT()));	   
		                DS.set(i, ctsp); 
		                break; // Thoát khỏi vòng lặp sau khi cập nhật xong
		            }
		        }
		        // nếu chitiet.gia != giá hiện tại thì cập nhật
				if(chitiet.getGIAHIENTHOI().compareTo(giatam)!=0) {
					LichSu lichsu=new LichSu(giatam,chitiet);
					check=this.insert(lichsu);
					if (check != 0) {
						model.addAttribute("message", "Lưu lịch sử thành công!");
					} else {
						model.addAttribute("message", "Lưu lịch sử thất bại!");
					}
				}
				model.addAttribute("chitiet", new ChiTietSanPham());
				model.addAttribute("btnStatus", "btnAdd");
			} else {
				model.addAttribute("message0", "Sửa chi tiết sản phẩm thất bại!");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		
		model.addAttribute("dschitiet", DS);
		return "views/quanly/sanpham/chitietsanpham";
	}
	public CongThuc get_1_CT(String maCT) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM CongThuc");
		@SuppressWarnings("unchecked")
		List<CongThuc> list = query.list();
		CongThuc congThuc = new CongThuc();
		for(CongThuc ct : list) {
			if(ct.getMACT().equals(maCT)) {
				congThuc = ct;
				break;
			}
		}	
		
		return congThuc;
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
	public String addChiTietSanPham(ModelMap model, @ModelAttribute("chitiet") ChiTietSanPham chitiet, BindingResult errors) {
		System.out.println("btnAdd");
		if(chitiet.getGIAHIENTHOI()==null) {
			errors.rejectValue("GIAHIENTHOI", "chitiet", "Vui lòng nhập giá");
		}
		else if(chitiet.getGIAHIENTHOI().compareTo(new BigDecimal("0.00"))<=0){
			errors.rejectValue("GIAHIENTHOI", "chitiet", "Giá không được nhỏ hơn hoặc bằng 0");
		}
		if(chitiet.getCONGTHUC().getMACT().equals(""))chitiet.setCONGTHUC(null);
		if(!errors.hasErrors()) {
			int check = this.insert(chitiet);
			if (check != 0) {
				model.addAttribute("message1", "Thêm chi tiết sản phẩm thành công!");
			//	model.addAttribute("chitiet", new ChiTietSanPham());
			} else {
				model.addAttribute("message0", "Thêm chi tiết sản phẩm thất bại! Trùng (mã sản phẩm + mã size) hoặc trùng mã công thức");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<ChiTietSanPham> DS = this.layDanhSachChiTietSanPham();
		model.addAttribute("dschitiet", DS);
		
		return "views/quanly/sanpham/chitietsanpham";
	}
}