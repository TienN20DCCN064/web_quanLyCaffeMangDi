package ptithcm.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.ChiTietNguyenLieu;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.CongThuc;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.SanPham;
import ptithcm.entity.Size; 

@Controller
@Transactional
@RequestMapping("/product/detailMaterial")
public class DetailMaterialController {
	@Autowired
	SessionFactory factory;
	
	public List<ChiTietNguyenLieu> layDanhSachChiTietNguyenLieu() {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietNguyenLieu";
		Query query = session.createQuery(hql);
		List<ChiTietNguyenLieu> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dschitiet(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("ct_sanpham",new ChiTietSanPham());
		model.addAttribute("chitiet",new ChiTietNguyenLieu());
		List<ChiTietNguyenLieu> DS = layDanhSachChiTietNguyenLieu();
		model.addAttribute("dschitiet", DS);
		return "views/quanly/sanpham/chitietnguyenlieu";
	}
	@ModelAttribute("cacchitietsanpham")
	public List<ChiTietSanPham> layDanhSachChiTietSanPham() {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietSanPham";
		Query query = session.createQuery(hql);
		List<ChiTietSanPham> list = query.list();
		return list;
	}
//	@ModelAttribute("cacchitietsanpham")
//	public Map<String, ChiTietSanPham> layDanhSachChiTietSanPham(){
//		Session session=factory.getCurrentSession();
//		String hql="From ChiTietSanPham";
//		Query query=session.createQuery(hql);
//		List<ChiTietSanPham>list=query.list();
//		Map<String, ChiTietSanPham>map=new HashMap<>();
//		for(ChiTietSanPham x:list) {
//			map.put(x.getSANPHAM().getTEN()+"_"+x.getSIZE().getTENSIZE(),x);
//		}
//		return map;
//	}
	public ChiTietNguyenLieu getChiTietNguyenLieu(String masp,String masize,String manl) {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietNguyenLieu where pk.CT_SANPHAM.pk.SANPHAM.MASP = :masp and pk.CT_SANPHAM.pk.SIZE.MASIZE= :masize"
				+ " and pk.NGUYENLIEU.MANL= :manl";
		Query query = session.createQuery(hql);
		query.setParameter("masp", masp);
		query.setParameter("masize", masize);
		query.setParameter("manl",manl);
		ChiTietNguyenLieu list = (ChiTietNguyenLieu) query.list().get(0);
		return list;
	}
	public ChiTietSanPham getChiTietSanPham(String ma) {
		Session session = factory.getCurrentSession();
		String w[]=ma.split("\\|");
		String hql = "from ChiTietSanPham where pk.SANPHAM.MASP=:ma1 and pk.SIZE.MASIZE=:ma2";
		Query query = session.createQuery(hql);
		query.setParameter("ma1", w[0]);
		query.setParameter("ma2", w[1]);
		ChiTietSanPham list = (ChiTietSanPham) query.list().get(0);
		return list;
	}
	@ModelAttribute("cacnguyenlieu")
	public List<NguyenLieu>layDanhSachCacNguyenLieu(){
		Session session=factory.getCurrentSession();
		String hql="From NguyenLieu";
		Query query=session.createQuery(hql);
		List<NguyenLieu>list=query.list();
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

	@RequestMapping(value = "/list/{masp}/{masize}/{manl}.htm", params = "linkDelete")
	public String deleteChiTietNguyenLieu(ModelMap model,@PathVariable("masp")String masp,
			@PathVariable("masize")String masize,@PathVariable("manl")String manl) {
		System.out.println("linkDelete");
		ChiTietNguyenLieu chitiettam=getChiTietNguyenLieu(masp,masize,manl);
		int check = this.delete(chitiettam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa chi tiết nguyên liệu thành công");
			model.addAttribute("chitiet",new ChiTietNguyenLieu());
		} else {
			model.addAttribute("message0", "Xóa chi tiết nguyên liệu thất bại");
			model.addAttribute("chitiet",chitiettam);
			model.addAttribute("ct_sanpham",chitiettam.getCT_SANPHAM());
		}
		List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu();
		model.addAttribute("dschitiet", DS);
		model.addAttribute("btnStatus", "btnAdd");
		return "views/quanly/sanpham/chitietnguyenlieu";
	}

	@RequestMapping(value = "/list/{masp}/{masize}/{manl}.htm", params = "linkEdit")
	public String editChiTietNguyenLieu(ModelMap model, @PathVariable("masp")String masp
			,@PathVariable("masize")String masize,
			@PathVariable("manl") String manl) {
		System.out.println("linkEdit");
		List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu();
		ChiTietNguyenLieu chitiettam=getChiTietNguyenLieu(masp,masize,manl);
		model.addAttribute("dschitiet", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("chitiet", chitiettam);
		return "views/quanly/sanpham/chitietnguyenlieu";
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
	public String edit_ChiTietNguyenLieu(ModelMap model, @ModelAttribute("chitiet") ChiTietNguyenLieu chitiet, BindingResult errors) {
		System.out.println("btnEdit");
		if(chitiet.getSOLUONG()==null) {
			errors.rejectValue("SOLUONG","chitiet", "Mời nhập số lượng");
		}
		else if(chitiet.getSOLUONG().compareTo(new BigDecimal("0.00"))<=0) {
			errors.rejectValue("SOLUONG","chitiet", "Số lượng không được nhỏ hơn hoặc bằng 0");
		}
		if(!errors.hasErrors()) {
			int check = this.update(chitiet);
			if (check != 0) {
				model.addAttribute("message1", "Sửa chi tiết nguyên liệu thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("chitiet",new ChiTietNguyenLieu());
			} else {
				model.addAttribute("message0", "Sửa chi tiết nguyên liệu thất bại! Chi tiết chưa tồn tại");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu();
		model.addAttribute("dschitiet", DS);
		
		return "views/quanly/sanpham/chitietnguyenlieu";
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
	public String addChiTietNguyenLieu(ModelMap model, String ct_sanpham, @ModelAttribute("chitiet") ChiTietNguyenLieu chitiet, BindingResult errors) {
		System.out.println("btnAdd");
		System.out.println(ct_sanpham);
		chitiet.setCT_SANPHAM(getChiTietSanPham(ct_sanpham));
		if(chitiet.getSOLUONG()==null) {
			errors.rejectValue("SOLUONG","chitiet", "Mời nhập số lượng");
		}
		else if(chitiet.getSOLUONG().compareTo(new BigDecimal("0.00"))<=0) {
			errors.rejectValue("SOLUONG","chitiet", "Số lượng không được nhỏ hơn hoặc bằng 0");
		}
		if(!errors.hasErrors()) {
			int check = this.insert(chitiet);
			if (check != 0) {
				model.addAttribute("message1", "Thêm chi tiết nguyên liệu thành công!");
			//	model.addAttribute("chitiet",new ChiTietNguyenLieu());
			} else {
				model.addAttribute("message0", "Thêm chi tiết nguyên liệu thất bại! Trùng chi tiết");
				model.addAttribute("ct_sanpham",ct_sanpham);
			}
		}
		else {
			model.addAttribute("ct_sanpham",ct_sanpham);
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu();
		model.addAttribute("dschitiet", DS);
		
		return "views/quanly/sanpham/chitietnguyenlieu";
	}

	//Xử lý nguyên liệu
	public ChiTietSanPham getChiTietSanPham(String masp,String masize) {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietSanPham where pk.SANPHAM.MASP = :masp and pk.SIZE.MASIZE =:masize";
		Query query = session.createQuery(hql);
		query.setParameter("masp",masp);
		query.setParameter("masize", masize);
		ChiTietSanPham list = (ChiTietSanPham) query.list().get(0);
		return list;
	}
	public List<ChiTietNguyenLieu> layDanhSachChiTietNguyenLieu(String masp, String masize) {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietNguyenLieu where pk.CT_SANPHAM.pk.SANPHAM.MASP=:masp and pk.CT_SANPHAM.pk.SIZE.MASIZE=:masize";
		Query query = session.createQuery(hql);
		query.setParameter("masp", masp);
		query.setParameter("masize", masize);
		List<ChiTietNguyenLieu> list = query.list();
		return list;
	}
		@RequestMapping(value = "/{masp}/{masize}/list.htm")
		public String showChiTietNguyenLieu(ModelMap model,@PathVariable("masp") String masp, 
				@PathVariable("masize")String masize) {
			List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu(masp,masize);
			ChiTietSanPham chitiettam=this.getChiTietSanPham(masp,masize);
			ChiTietNguyenLieu nguyenlieutam=new ChiTietNguyenLieu();
			nguyenlieutam.setCT_SANPHAM(chitiettam);
			model.addAttribute("dschitiet", DS);
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("chitiet", chitiettam);
			model.addAttribute("nguyenlieu", nguyenlieutam);
			return "views/quanly/sanpham/nguyenlieu_sanpham";
		}
		@RequestMapping(value = "/{masp}/{masize}/list.htm", params = "btnAdd")
		public String addNguyenLieu(ModelMap model, @ModelAttribute("nguyenlieu") ChiTietNguyenLieu nguyenlieu, 
				BindingResult errors, @PathVariable("masp") String masp, 
				@PathVariable("masize")String masize) {
			System.out.println("btnAdd");
			if(nguyenlieu.getSOLUONG()==null) {
				errors.rejectValue("SOLUONG","nguyenlieu", "Mời nhập số lượng");
			}
			else if(nguyenlieu.getSOLUONG().compareTo(new BigDecimal("0.00"))<=0) {
				errors.rejectValue("SOLUONG","nguyenlieu", "Số lượng không được nhỏ hơn hoặc bằng 0");
			}
			ChiTietSanPham chitiettam= getChiTietSanPham(masp,masize);
			if(!errors.hasErrors()) {
				int check = this.insert(nguyenlieu);
				if (check != 0) {
					model.addAttribute("message1", "Thêm chi tiết nguyên liệu thành công!");
					ChiTietNguyenLieu nguyenlieutam=new ChiTietNguyenLieu();
					nguyenlieutam.setCT_SANPHAM(chitiettam);
					model.addAttribute("nguyenlieu",nguyenlieutam);
				} else {
					model.addAttribute("message0", "Thêm chi tiết nguyên liệu thất bại! Trùng chi tiết");
					
				}
			}
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("chitiet",chitiettam);
			List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu(masp,masize);
			model.addAttribute("dschitiet", DS);
			
			return "views/quanly/sanpham/nguyenlieu_sanpham";
		}
		@RequestMapping(value = "/{masp}/{masize}/{manl}/list.htm", params = "linkDelete")
		public String deleteNguyenLieu(ModelMap model,  @PathVariable("masp")String masp
				,@PathVariable("masize")String masize,
				@PathVariable("manl") String manl, @ModelAttribute("nguyenlieu")ChiTietNguyenLieu nguyenlieu) {
			System.out.println("linkDelete");
			ChiTietNguyenLieu nguyenlieutam=getChiTietNguyenLieu(masp,masize,manl);
			int check = this.delete(nguyenlieutam);
			if (check == 1) {
				model.addAttribute("message1", "Xóa chi tiết sản phẩm thành công");
			} else {
				model.addAttribute("message0", "Xóa chi tiết sản phẩm thất bại");
				
			}
			List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu(masp,masize);
			ChiTietSanPham chitiettam=getChiTietSanPham(masp,masize);
			model.addAttribute("chitiet",chitiettam);
			
			nguyenlieutam=new ChiTietNguyenLieu();
			nguyenlieutam.setCT_SANPHAM(chitiettam);
			model.addAttribute("nguyenlieu",nguyenlieutam);
			model.addAttribute("dschitiet", DS);
			model.addAttribute("btnStatus", "btnAdd");
			return "views/quanly/sanpham/nguyenlieu_sanpham";
		}

		@RequestMapping(value =  "/{masp}/{masize}/{manl}/list.htm", params = "linkEdit")
		public String editNguyenLieu(ModelMap model, @PathVariable("masp")String masp
				,@PathVariable("masize")String masize,
				@PathVariable("manl") String manl,@ModelAttribute("chitiet") ChiTietSanPham chitiet) {
			System.out.println("linkEdit");
			List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu(masp,masize);
			model.addAttribute("dschitiet", DS);
			model.addAttribute("btnStatus", "btnEdit");
			model.addAttribute("nguyenlieu", this.getChiTietNguyenLieu(masp,masize,manl));
			model.addAttribute("chitiet", getChiTietSanPham(masp,masize));
			return "views/quanly/sanpham/nguyenlieu_sanpham";
		}
		@RequestMapping(value = "/{masp}/{masize}/list.htm", params = "btnEdit")
		public String edit_NguyenLieu(ModelMap model,@ModelAttribute("nguyenlieu") ChiTietNguyenLieu nguyenlieu,
				BindingResult errors, @PathVariable("masp")String masp
				,@PathVariable("masize")String masize) {
			System.out.println("btnEdit");
			
			if(nguyenlieu.getSOLUONG()==null) {
				errors.rejectValue("SOLUONG","nguyenlieu", "Mời nhập số lượng");
			}
			else if(nguyenlieu.getSOLUONG().compareTo(new BigDecimal("0.00"))<=0) {
				errors.rejectValue("SOLUONG","nguyenlieu", "Số lượng không được nhỏ hơn hoặc bằng 0");
			}
			ChiTietSanPham chitiettam= getChiTietSanPham(masp,masize);
			if(!errors.hasErrors()) {
				int check = this.update(nguyenlieu);
				if (check != 0) {
					model.addAttribute("message1", "Sửa chi tiết nguyên liệu thành công!");
					model.addAttribute("btnStatus", "btnAdd");
					ChiTietNguyenLieu nguyenlieutam=new ChiTietNguyenLieu();
					
					nguyenlieutam.setCT_SANPHAM(chitiettam);
					model.addAttribute("nguyenlieu",nguyenlieutam);
					
				} else {
					model.addAttribute("message0", "Sửa chi tiết nguyên liệu thất bại! Chi tiết chưa tồn tại");
					model.addAttribute("btnStatus", "btnEdit");
				}
			}
			else {
				model.addAttribute("btnStatus", "btnEdit");
				
			}
			List<ChiTietNguyenLieu> DS = this.layDanhSachChiTietNguyenLieu(masp,masize);
			model.addAttribute("dschitiet", DS);
			model.addAttribute("chitiet",chitiettam);
			return "views/quanly/sanpham/nguyenlieu_sanpham";
		}
}