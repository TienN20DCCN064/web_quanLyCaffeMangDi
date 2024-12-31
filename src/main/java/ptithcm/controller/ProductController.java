package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.ChiTietKhuyenMai;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.CongThuc;
import ptithcm.entity.HoaDon;
import ptithcm.entity.KhuyenMai;
import ptithcm.entity.LoaiSP;
import ptithcm.entity.SanPham;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.ChiTietNguyenLieu;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;

import org.hibernate.Transaction;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Transactional
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	public List<HoaDon> getList_HD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HoaDon");
		@SuppressWarnings("unchecked")
		List<HoaDon> list = query.list();
		return list;
	}
	

	
	public List<SanPham> layDanhSachSanPham() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		return list;
	}
	
	public List<LoaiSP> getList_LSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM LoaiSP");
		@SuppressWarnings("unchecked")
		List<LoaiSP> list = query.list();
		return list;
	}
	
	public List<ChiTietSanPham> getList_CTSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietSanPham");
		@SuppressWarnings("unchecked")
		List<ChiTietSanPham> list = query.list();
		return list;
	}
	
	public List<ChiTietKhuyenMai> getList_CTKM() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietKhuyenMai");
		@SuppressWarnings("unchecked")
		List<ChiTietKhuyenMai> list = query.list();
		return list;
	}
	
	public List<KhuyenMai> getList_KM() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM KhuyenMai");
		@SuppressWarnings("unchecked")
		List<KhuyenMai> list = query.list();
		return list;
	}
	public String getMaKhuyenMai() {
		Timestamp thoigian=Timestamp.valueOf(LocalDateTime.now());
		Session session = factory.getCurrentSession();
		String hql = "from KhuyenMai where TGBD <= :thoigian and TGKT >= :thoigian ";
		Query query = session.createQuery(hql);
		query.setParameter("thoigian",thoigian);
		
		if (query.list().size() == 0) return "";
		
		KhuyenMai list = ( KhuyenMai) query.list().get(0);
		
		return list.getMAKM();
	}
	public List<NguyenLieu> getList_NL() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM NguyenLieu");
		@SuppressWarnings("unchecked")
		List<NguyenLieu> list = query.list();
		return list;
	}
	
	public List<ChiTietNguyenLieu> getList_CTNL() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietNguyenLieu");
		@SuppressWarnings("unchecked")
		List<ChiTietNguyenLieu> list = query.list();
		return list;
	}
	
	public List<CongThuc> getList_CT() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM CongThuc");
		@SuppressWarnings("unchecked")
		List<CongThuc> list = query.list();
		return list;
	}
	
	

	@RequestMapping("list")
	public String dssanpham(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("sanpham", new SanPham());
		List<SanPham> DS = layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		return "views/quanly/sanpham";
	}
	@ModelAttribute("cacloai")
	public List<LoaiSP>layDanhSachLoai(){
		Session session=factory.getCurrentSession();
		String hql="From LoaiSP";
		Query query=session.createQuery(hql);
		List<LoaiSP>list=query.list();
		return list;
	}
	
	public SanPham getSanPham(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "from SanPham where MASP = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		SanPham list = (SanPham) query.list().get(0);
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

	@RequestMapping(value = "/list/{MASP}.htm", params = "linkDelete")
	public String deleteSanPham(ModelMap model,@PathVariable("MASP")String MASP,SanPham sanpham) {
		System.out.println("linkDelete");
		SanPham sanphamtam=getSanPham(MASP);
		int check = this.delete(sanphamtam);
		if (check == 1) {
			model.addAttribute("message1", "Xóa sản phẩm thành công");
		} else {
			model.addAttribute("message0", "Xóa sản phẩm thất bại");
		}
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("sanpham",sanpham);
		return "views/quanly/sanpham";
	}

	@RequestMapping(value = "/list/{MASP}.htm", params = "linkEdit")
	public String editSanPham(ModelMap model,
			@PathVariable("MASP") String MASP) {
		System.out.println("linkEdit");
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("sanpham", this.getSanPham(MASP));
		return "views/quanly/sanpham";
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
	public String edit_SanPham(ModelMap model, @ModelAttribute("sanpham") SanPham sanpham,
			@RequestParam("photo")MultipartFile photo, BindingResult errors) {
		System.out.println("btnEdit");
		if(sanpham.getMASP().isBlank()) {
			errors.rejectValue("MASP", "sanpham", "Vui lòng nhập mã sản phẩm");
		}
		if(sanpham.getTENSP().isBlank()) {
			errors.rejectValue("TENSP","sanpham","Vui lòng nhập tên sản phẩm");
		}
		if(!errors.hasErrors()) {
			if(!photo.isEmpty()) {
				try {
					String photoPath=context.getRealPath("/resources/img/sanpham/"+photo.getOriginalFilename());
					photo.transferTo(new File(photoPath));
					sanpham.setHINHANH(photo.getOriginalFilename());
				}
				catch(Exception e) {
					model.addAttribute("message","Lỗi lưu hình !");
				}
			}
			int check = this.update(sanpham);
			if (check != 0) {
				model.addAttribute("message1", "Sửa sản phẩm thành công!");
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("sanpham", new SanPham());
			} else {
				model.addAttribute("message0", "Sửa sản phẩm thât bại! Mã sản phẩm không tồn tại hoặc tên trùng");
				model.addAttribute("btnStatus", "btnEdit");
			}
		}
		else {
			model.addAttribute("btnStatus", "btnEdit");
		}
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		
		return "views/quanly/sanpham";
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
	public String addSanPham(ModelMap model,  @ModelAttribute("sanpham") SanPham sanpham, 
			@RequestParam("photo")MultipartFile photo, BindingResult errors) {
		System.out.println("btnAdd");
		if(sanpham.getMASP().isBlank()) {
			errors.rejectValue("MASP", "sanpham", "Vui lòng nhập mã sản phẩm");
		}
		if(sanpham.getTENSP().isBlank()) {
			errors.rejectValue("TENSP","sanpham","Vui lòng nhập tên sản phẩm");
		}
		if(!errors.hasErrors()) {
			try {
				String photoPath=context.getRealPath("/resources/img/sanpham/"+photo.getOriginalFilename());
					photo.transferTo(new File(photoPath));
					sanpham.setHINHANH(photo.getOriginalFilename());
				}
				catch(Exception e) {
					model.addAttribute("message","Lỗi lưu hình !");
				}
			int check = this.insert(sanpham);
			if (check != 0) {
				model.addAttribute("message1", "Thêm sản phẩm thành công!");
				model.addAttribute("sanpham", new SanPham());
			} else {
				model.addAttribute("message0", "Thêm sản phẩm thất bại! Trùng mã hoặc tên");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		List<SanPham> DS = this.layDanhSachSanPham();
		model.addAttribute("dssanpham", DS);
		return "views/quanly/sanpham";
	}
}
