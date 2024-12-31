package ptithcm.controller;

//
//import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.hibernate.Query;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.netlib.util.booleanW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import javassist.expr.NewArray;

import java.lang.foreign.ValueLayout;
import java.math.BigDecimal;
import java.util.Collections;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.ChiTietKhuyenMai;
import ptithcm.entity.ChiTietNguyenLieu;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.CongThuc;
import ptithcm.entity.HoaDon;
import ptithcm.entity.KhuyenMai;
import ptithcm.entity.LoaiSP;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.NhanVien;
import ptithcm.entity.Quyen;
import ptithcm.entity.SanPham;
import ptithcm.entity.TaiKhoan;
import ptithcm.entity.Size;

@Controller
@Transactional
@RequestMapping("/sell/")
public class SellController {
	@Autowired
	SessionFactory factory;
//	 @Autowired
//	private EmployeeController employeeController;  // Tiêm EmployeeController
//	

//	@RequestMapping("viewSP")
//	@Transactional
//	public String func1(ModelMap model) {

	@RequestMapping(value = "/viewSP", method = RequestMethod.GET)
	public String viewProducts(ModelMap model) {

//		List<SanPham> myListSP = layDanhSachSanPham();

		List<LoaiSP> myListLSP = getList_LSP();
		// List<ChiTietSanPham> myListChiTietSanPham = getList_CTSP();
		List<HoaDon> myListHoaDon = getList_HD();

		List<ChiTietNguyenLieu> myListCTNguyenLieu = getList_CTNL();

		List<NguyenLieu> myListNguyenLieu = getList_NL();
		List<CongThuc> myListCongThuc = getList_CT();


		model.addAttribute("listSP", getList_SP_co_CTNL());
		model.addAttribute("listLSP", myListLSP);
		/* model.addAttribute("listCTSP", myListChiTietSanPham); */
		model.addAttribute("listCTSP", getList_CTSP_co_CTNL());

		model.addAttribute("listHD", myListHoaDon);
		model.addAttribute("nextID", myListHoaDon.size());
		model.addAttribute("manv", LoginController.manv);
		model.addAttribute("listCTNL", myListCTNguyenLieu);
		model.addAttribute("listCT_KM_today", getList_CTKM_today());
		model.addAttribute("listNL", myListNguyenLieu);
		
		model.addAttribute("listCT", myListCongThuc);

		// Truyền vào model
		
		
	//	model.addAttribute("dssanpham", layDanhSachSanPham());
		
		
		

		TmpClass.danhSachSPDuocChon = new ArrayList<>();
		TmpClass.tongTien = new BigDecimal(0);

		System.out.print("\n manv : " + LoginController.manv);
		// return "views/sanpham/view3";
		return "views/banhang/viewSanPham";
	}

	// danh sachs san pham - quan ly - xem quản lý sản phẩm
	@RequestMapping("list")
	public String dssanpham(ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("sanpham", new SanPham());
		return "views/quanly/sanpham";
	}

	@RequestMapping(value = "/home.htm", params = "backHome", method = RequestMethod.GET)
	public String trangChu(ModelMap model) {
		String maNV = LoginController.manv;
		List<TaiKhoan> listTaiKhoan = getListTaiKhoan();
		String viewTraVe = null;
		String quyenHan = null;
		System.out.print("\n manv : " + LoginController.manv);
		System.out.print("\nokkkkkk : " + maNV);
		for (TaiKhoan tk : listTaiKhoan) {
			// nếu tìm thấy tài khoản trong list tài khoản thì lấy quyền ra
			if (maNV.equalsIgnoreCase(tk.getTENDANGNHAP())) {
				quyenHan = tk.getQUYEN().getMAQUYEN();
				break;
			}
		}
		// quan ly
		if (quyenHan != null) {
			if (quyenHan.equalsIgnoreCase("Q02")) {
				viewTraVe = "trangchuquanly.htm";
			} else {
				viewTraVe = "trangchunhanvien.htm";
			}
		}

		// không tìm thấy mã nhân viên// kiểu như chạy lại code
		else {
			// trả về trang đăng nhập
			return "redirect:http://localhost:8080/Highlands/home/index.htm";
		}
		return "redirect:/home/" + viewTraVe;

	}

	@RequestMapping(value = "/viewSP/{ID_loaiSP}.htm", params = "linkSearch", method = RequestMethod.GET)
	public String listSP_theo_loaiSp(ModelMap model, @PathVariable("ID_loaiSP") String IDLoaiSp) {
		TmpClass.check_loai_sp = IDLoaiSp;

		List<SanPham> myListSP_theoLoai;

		if (IDLoaiSp.equalsIgnoreCase("All")) {
			myListSP_theoLoai = getList_SP_co_CTNL();

		} else {
			myListSP_theoLoai = layDSSanPhamTheoLoai(IDLoaiSp);
		}

		// List<SanPham> myListSP_theoLoai = layDSSanPhamTheoLoai(IDLoaiSp);
		List<LoaiSP> myListLSP = getList_LSP();

		// List<ChiTietSanPham> myListChiTietSanPham = getList_CTSP();

		// locDanhSachSanPham(myListSP_theoLoai);

		model.addAttribute("listLSP", myListLSP);
		model.addAttribute("listSP", myListSP_theoLoai);
		model.addAttribute("listCTSP", getList_CTSP_co_CTNL());
		model.addAttribute("listSize_choise", getList_Size());
		model.addAttribute("dssanpham", layDanhSachSanPham());
		model.addAttribute("listCT_KM_today", getList_CTKM_today());
		model.addAttribute("product_choise_List", TmpClass.danhSachSPDuocChon);
		model.addAttribute("tongTien_choise_List", TmpClass.tinh_tong_tien());

//	    System.out.print("\n\ncheck ttt 2 "+ TmpClass.danhSachSPDuocChon);

//	    System.out.println("Danh sách sản phẩm (listSP): " + myListSP_theoLoai);
//	    System.out.println("Danh sách sản phẩm đã chọn (product_choise_List): " + TmpClass.danhSachSPDuocChon);

//	    System.out.print(TmpClass.thongBaoSP_ko_du);
		model.addAttribute("thongBao", TmpClass.thongBaoSP_ko_du);
		return "views/banhang/viewSanPham";
		// return "redirect:/bill/list.htm"; // Hoặc URL trang danh sách của bạn
	}

	// click vào size của từng sp
	@RequestMapping(value = "/viewSP/{TEN_SP}/{MASIZE}.htm", params = "clickSIZE", method = RequestMethod.GET)
	public String show_SP_clickSIZE(ModelMap model, @PathVariable("TEN_SP") String TEN_SP,
			@PathVariable("MASIZE") String maSIZE) {

		// Lấy danh sách các loại sản phẩm
		List<LoaiSP> myListLSP = getList_LSP();
		// List<ChiTietSanPham> myListChiTietSanPham = getList_CTSP();
	//	List<SanPham> myListSP = layDanhSachSanPham();

		// Truyền vào model
		model.addAttribute("listSize", layDanhSachSizeTheoMaSanPham(TEN_SP)); // Cập nhật danh sách các size của sản
																				// phẩm
		model.addAttribute("listLSP", myListLSP);
		// model.addAttribute("listCTSP", myListChiTietSanPham);
		model.addAttribute("listCTSP", getList_CTSP_co_CTNL());
		model.addAttribute("showSizes", true); // Truyền tham số showSizes vào model
		// model.addAttribute("listCT_KM", getList_CTKM());
		model.addAttribute("listCT_KM_today", getList_CTKM_today());
		if (TmpClass.check_loai_sp.equalsIgnoreCase("All")) {
			model.addAttribute("listSP", getList_SP_co_CTNL());

		} else {
			model.addAttribute("listSP", layDSSanPhamTheoLoai(TmpClass.check_loai_sp));
		}

		BigDecimal giaHienThoi = new BigDecimal(0);
		String maKhyenMai = "";
		for (ChiTietSanPham chiTiet : danhSachChiTietSP_theoLoai(TEN_SP)) {

			if (chiTiet.getSIZE().getMASIZE().equals(maSIZE)) {
				giaHienThoi = chiTiet.getGIAHIENTHOI();
				break;
			}
		}
		// trả về danh sách khuyển mãi
		for (ChiTietKhuyenMai chiTiet : getList_CTKM_today()) {
			// lấy ra chi tiết khuyển mãi
			// nếu tồn tại chi tiết sản phẩm trong chi tiết khuến mãi khuyêns mãi
			if (chiTiet.getSANPHAM().getMASP().equals(TEN_SP) && chiTiet.getSIZE().getMASIZE().equals(maSIZE)) {
				maKhyenMai = chiTiet.getKHUYENMAI().getMAKM();
				break;
			}
		}

		// truyền giá trị vào để hiện thị // chưa xong
//	    public chiTietDanhSachSanPhamDuocChon(String name, String size, int soLuong, double gia, double khuyenMai) {
		chiTietDanhSachSanPhamDuocChon sanPhamDuocChon = new chiTietDanhSachSanPhamDuocChon(TEN_SP, maSIZE, 1,
				giaHienThoi, maKhyenMai);
		// nếu trùng
		check_ChiTietDanhSachSanPhamDuocChon_trung(sanPhamDuocChon);

		model.addAttribute("dssanpham", layDanhSachSanPham());
		model.addAttribute("listSize_choise", getList_Size());
		model.addAttribute("product_choise_List", TmpClass.danhSachSPDuocChon);
		model.addAttribute("tongTien_choise_List", TmpClass.tinh_tong_tien());

		// return "redirect:views/banhang/viewSanPham";
		return "views/banhang/viewSanPham";
	}

	@RequestMapping(value = "/viewSP/billAction/{id}.htm", params = "deleteBill")
	public String deleteBillAction(ModelMap model, @PathVariable("id") String id) {
		if (id.equalsIgnoreCase("All")) {
			List<chiTietDanhSachSanPhamDuocChon> tmp = new ArrayList<>();
			TmpClass.danhSachSPDuocChon = tmp;
			TmpClass.tongTien = new BigDecimal(0);
			TmpClass.thongBaoSP_ko_du = "";
			model.addAttribute("thongBao", TmpClass.thongBaoSP_ko_du);
			return "redirect:/sell/viewSP/All.htm?linkSearch=true";
		}

		else {
			String loaiSp = TmpClass.check_loai_sp;
			int index = Integer.parseInt(id);
			TmpClass.danhSachSPDuocChon.remove(index);
			TmpClass.tongTien = new BigDecimal(0);
			TmpClass.thongBaoSP_ko_du = "";
			model.addAttribute("thongBao", TmpClass.thongBaoSP_ko_du);
			return "redirect:/sell/viewSP/" + loaiSp + ".htm?linkSearch=true";
		}

	}

	@RequestMapping(value = "/viewSP/billAction/All.htm", params = "addBill")
	public String addBillAction(ModelMap model) {
		List<chiTietDanhSachSanPhamDuocChon> tmp = new ArrayList<>();

		Date currentDate = new Date();
		// Lấy danh sách nhân viên qua EmployeeController

//        List<NhanVien> danhSachNhanVien = employeeController.LayDanhSachNhanVien();
		List<NhanVien> danhSachNhanVien = getList_nhanVien();
		List<KhuyenMai> danhSachKhuyenMai = getList_KM();
		// Sinh ID tự động dựa vào số lượng hóa đơn hiện tại
		String idHDMoi = taoIDhoaDon();
		// đặt mạc định là nhân viên đầu
		NhanVien NhanVienTao = get_1_NhanVien_quanLy();
		KhuyenMai MAKM_tao = null;
		System.out.print("/n\n manv dung " + TmpClass.maTk_dung);

		for (NhanVien nv : danhSachNhanVien) {
			if (nv.getMANV().equalsIgnoreCase(TmpClass.maTk_dung)) {
				NhanVienTao = nv;
				break;
			}
		}
		for (KhuyenMai km : danhSachKhuyenMai) {
			if (km.getMAKM().equals(TmpClass.maTk_dung)) {
				MAKM_tao = km;
				break;
			}
		}

		BigDecimal testBigDecimal = TmpClass.tinh_tong_tien();
		HoaDon hoaDonCanThem = new HoaDon();

		if (TmpClass.danhSachSPDuocChon.size() != 0) {

			// nếu thêm vào được đầy đủ => trừ sản phẩm
			if (check_du_soLuongNL(false).size() > 0) {

				// kiểm tra add bill
				int checkBill = 0;
				if (NhanVienTao != null) {
					hoaDonCanThem = new HoaDon(idHDMoi, null, NhanVienTao, testBigDecimal, "1");
					checkBill = add(hoaDonCanThem);

				}
				// thêm chi tiết hóa đơn
				if (checkBill != 0) {
					for (int i = 0; i < TmpClass.danhSachSPDuocChon.size(); i++) {
						add_1_ChiTietBill(i, idHDMoi, hoaDonCanThem);
						if (add_1_ChiTietBill(i, idHDMoi, hoaDonCanThem) == 1) {
							System.out.print("\n  add chi tiết bill id " + i);
						} else {
							System.out.print("\n lỗi add chi tiết bill id " + i);
						}
					}
				}
				// tru_soLuongNL();
				check_du_soLuongNL(true);
				TmpClass.danhSachSPDuocChon = tmp;
				TmpClass.tongTien = new BigDecimal(0);
				TmpClass.thongBaoSP_ko_du = "";
				model.addAttribute("thongBao" + "", TmpClass.thongBaoSP_ko_du);
				model.addAttribute("listHD", getList_HD());
				System.out.print("\n  đủ sản phậm");

			}

		} else {
			model.addAttribute("thongBao" + " Sản phẩm ko đủ ", TmpClass.thongBaoSP_ko_du);
			System.out.print("\n   Sản phẩm ko đủ ");
		}
		return "redirect:/sell/viewSP/All.htm?linkSearch=true";

	}

	public int add_1_ChiTietBill(int index, String idHDMoi, HoaDon hoaDonCanThem) {

		HoaDon tmpHoaDon = new HoaDon();
		SanPham tmpSanPham = new SanPham();
		Size tmpSize = new Size();
		KhuyenMai tmpKhuyenMai = new KhuyenMai();

		// cần sửa lại đây là tên chứ không phải là mã
		String name = TmpClass.danhSachSPDuocChon.get(index).name;
		String size = TmpClass.danhSachSPDuocChon.get(index).size;
		int soLuong = TmpClass.danhSachSPDuocChon.get(index).soLuong;
		BigDecimal gia = TmpClass.danhSachSPDuocChon.get(index).gia;
		String khuyenMai = TmpClass.danhSachSPDuocChon.get(index).maKhuyenMai;
		BigDecimal tongTien = TmpClass.danhSachSPDuocChon.get(index).tongTien;
		// BigDecimal tongTien_co_khuyenMai =
		// TmpClass.danhSachSPDuocChon.get(index).tongTien_co_khuyenMai;

		tmpHoaDon = hoaDonCanThem;
		for (SanPham spT : layDanhSachSanPham()) {
			if (spT.getMASP().equals(name)) {
				tmpSanPham = spT;
			}
		}

		for (Size sizeT : getList_Size()) {

			if (sizeT.getMASIZE().equals(size)) {
				tmpSize = sizeT;
			}
		}

		boolean checkMaKm = false;
		for (KhuyenMai kmT : getList_KM()) {

			if (kmT.getMAKM().equals(khuyenMai)) {

				tmpKhuyenMai = kmT;
				checkMaKm = true;
				break;
			}
		}
		// nếu ko có khuyến mãi thì mặc định nó là ko có gì
		if (checkMaKm == false) {
			for (KhuyenMai km : getList_KM()) {
				if (km.getMAKM().equals("0"))
					tmpKhuyenMai = km;
			}
		}

		ChiTietHoaDon.PK pk = new ChiTietHoaDon.PK(tmpHoaDon, tmpSanPham, tmpSize, tmpKhuyenMai);
		ChiTietHoaDon ctHoaDon = new ChiTietHoaDon(pk, soLuong);
		int check = add(ctHoaDon);
		return check;
	}

	public int add(Object object) {
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

	public String taoIDhoaDon() {
		// Lấy danh sách hóa đơn
		List<HoaDon> danhSachHoaDon = this.getList_HD();

		String idMoi = null;
		boolean isUnique = false;

		while (!isUnique) {
			// Sinh ID tự động dựa vào số lượng hóa đơn hiện tại
			idMoi = "HD" + (danhSachHoaDon.size() + 1 + (int) (Math.random() * 1000)); // Thêm phần ngẫu nhiên để tránh
																						// trùng lặp.

			// Kiểm tra xem ID có tồn tại trong danh sách hay không
			isUnique = true; // Mặc định là ID mới không trùng.
			for (HoaDon hd : danhSachHoaDon) {
				if (hd.getID().equals(idMoi)) {
					isUnique = false; // Nếu trùng, đặt lại là false để tiếp tục vòng lặp.
					break;
				}
			}
		}

		return idMoi;
	}

	public List<TaiKhoan> getListTaiKhoan() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TaiKhoan");
		@SuppressWarnings("unchecked")
		List<TaiKhoan> list = query.list();
		return list;
	}

	public List<LoaiSP> get_sp_hien_thoi() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM LoaiSP");
		@SuppressWarnings("unchecked")
		List<LoaiSP> list = query.list();
		return list;
	}
//	public List<HoaDon> getList_HD() {
//	    Session session = factory.openSession();
//	    Query query = session.createQuery("FROM HoaDon");
//	    List<HoaDon> list = query.list();
//	    session.close();
//	    return list;
//	}
// 

	@ModelAttribute("cacloai")
	public List<LoaiSP> layDanhSachLoai() {
		Session session = factory.getCurrentSession();
		String hql = "From LoaiSP";
		Query query = session.createQuery(hql);
		List<LoaiSP> list = query.list();
		return list;
	}

	public List<SanPham> layDanhSachSanPham() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		return list;
	}

	public List<HoaDon> getList_HD() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HoaDon");
		@SuppressWarnings("unchecked")
		List<HoaDon> list = query.list();
		return list;
	}

	public List<LoaiSP> getList_LSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM LoaiSP");
		@SuppressWarnings("unchecked")
		List<LoaiSP> list = query.list();
		return list;
	}

	public List<Size> getList_Size() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM Size");
		@SuppressWarnings("unchecked")
		List<Size> list = query.list();
		return list;
	}

	public List<ChiTietSanPham> getList_CTSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietSanPham");
		@SuppressWarnings("unchecked")
		List<ChiTietSanPham> list = query.list();
		return list;
	}

	public List<ChiTietSanPham> getList_CTSP_co_CTNL() {

		List<ChiTietSanPham> list_CTSP_co_CTNL = new ArrayList<>();
		for (ChiTietSanPham ctsp : getList_CTSP()) {
			for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
				if (ctsp.getPk() == ctnl.getPk().getCT_SANPHAM().getPk()) {
					list_CTSP_co_CTNL.add(ctsp);
					break;
				}
			}
		}
		return list_CTSP_co_CTNL;
	}

	public List<SanPham> getList_SP_co_CTNL() {
		List<SanPham> list_SP_co_CTNL = new ArrayList<>();
		for (SanPham sp : layDanhSachSanPham()) {
			for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
				if (sp.getMASP() == ctnl.getPk().getCT_SANPHAM().getSANPHAM().getMASP()) {
					list_SP_co_CTNL.add(sp);
					break;
				}
			}
		}
		return list_SP_co_CTNL;
	}

	public List<ChiTietKhuyenMai> getList_CTKM() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ChiTietKhuyenMai");
		@SuppressWarnings("unchecked")
		List<ChiTietKhuyenMai> list = query.list();
		return list;
	}

	public List<ChiTietKhuyenMai> getList_CTKM_today() {
		// Lấy danh sách các chương trình khuyến mãi
		List<ChiTietKhuyenMai> chiTietKhuyenMaiList_today = new ArrayList<>();
		List<KhuyenMai> khuyenMaiList_today = getListKhuyenMai_today();
		List<ChiTietKhuyenMai> chiTietKhuyenMaiList = getList_CTKM();

		for (KhuyenMai km : khuyenMaiList_today) {
			for (ChiTietKhuyenMai ctkm : chiTietKhuyenMaiList) {
				if (km.equals(ctkm.getKHUYENMAI())) {
					chiTietKhuyenMaiList_today.add(ctkm);

				}
			}
		}
//        System.out.print("\n sôs lượng ctkm today : " +  chiTietKhuyenMaiList_today.size());
//        System.out.print("\n sôs lượng ctkm : " +  chiTietKhuyenMaiList_today.size());
//        System.out.print("\n sôs lượng km : " +  khuyenMaiList_today.size());
        
		// Trả về danh sách các chương trình khuyến mãi
		return chiTietKhuyenMaiList_today;
	}

	public List<KhuyenMai> getList_KM() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM KhuyenMai");
		@SuppressWarnings("unchecked")
		List<KhuyenMai> list = query.list();
		return list;
	}

//	public String getMaKhuyenMai_today() {
//		Timestamp thoigian = Timestamp.valueOf(LocalDateTime.now());
//		Session session = factory.getCurrentSession();
//		String hql = "from KhuyenMai where TGBD <= :thoigian and TGKT >= :thoigian ";
//		Query query = session.createQuery(hql);
//		query.setParameter("thoigian", thoigian);
//
//		if (query.list().size() == 0)
//			return "";
//
//		KhuyenMai list = (KhuyenMai) query.list().get(0);
//        System.out.print("\n khuyến mãi : " + list.getMAKM());
//		return list.getMAKM();
//	}
	public List<KhuyenMai> getListKhuyenMai_today() {
		// Lấy thời gian hiện tại
		Timestamp thoigian = Timestamp.valueOf(LocalDateTime.now());

		// Tạo phiên làm việc với cơ sở dữ liệu
		Session session = factory.getCurrentSession();

		// Câu truy vấn HQL để lấy các chương trình khuyến mãi còn hiệu lực tại thời
		// điểm hiện tại
		String hql = "from KhuyenMai where TGBD <= :thoigian and TGKT >= :thoigian";
		Query query = session.createQuery(hql);
		query.setParameter("thoigian", thoigian);

		// Lấy danh sách các chương trình khuyến mãi
		List<KhuyenMai> khuyenMaiList = query.list();

		// In ra thông tin của các khuyến mãi nếu có
//	    if (khuyenMaiList.isEmpty()) {
//	        System.out.println("\n Không có khuyến mãi nào còn hiệu lực.");
//	    } else {
//	        for (KhuyenMai km : khuyenMaiList) {
//	            System.out.println("\n Khuyến mãi: " + km.getMAKM());
//	        }
//	    }

		// Trả về danh sách các chương trình khuyến mãi
		return khuyenMaiList;
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

	public List<NhanVien> getList_nhanVien() {
		Session session = factory.openSession();
		String hql = "FROM NhanVien";
		Query query = session.createQuery(hql);
		List<NhanVien> list = query.list();
		return list;
	}

	public NhanVien get_1_NhanVien_quanLy() {
		Session session = factory.openSession();
		String hql = "FROM TaiKhoan";
		Query query = session.createQuery(hql);
		List<TaiKhoan> list = query.list();
		String tkCanTim = list.get(0).getTENDANGNHAP();
		// tìm mã quản ly
		for (TaiKhoan tk : list) {
			if (tk.getQUYEN().getMAQUYEN().equalsIgnoreCase("Q02")) {
				tkCanTim = tk.getTENDANGNHAP();
			}
		}
		for (NhanVien nv : getList_nhanVien()) {
			if (nv.getMANV().equals(tkCanTim)) {
				return nv;
			}
		}

		return getList_nhanVien().get(0);
	}

	public List<CongThuc> getList_CT() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM CongThuc");
		@SuppressWarnings("unchecked")
		List<CongThuc> list = query.list();
		return list;
	}

	public List<SanPham> layDSSanPhamTheoLoai(String loaiSp) {

		Session session = factory.getCurrentSession();
		// Viết câu truy vấn HQL để lấy các sản phẩm theo mã loại sản phẩm
		String hql = "FROM SanPham s WHERE s.LOAISP.MALOAI = :MALOAI";
		Query query = session.createQuery(hql);
		query.setParameter("MALOAI", loaiSp); // Thiết lập tham số mã loại sản phẩm
		@SuppressWarnings("unchecked")
		List<SanPham> listSP_loai = query.list(); // Trả về danh sách sản phẩm

		List<SanPham> list_SP_co_CTNL = new ArrayList<>();

		for (SanPham sp : listSP_loai) {
			for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
				if (sp.getMASP() == ctnl.getPk().getCT_SANPHAM().getSANPHAM().getMASP()) {
					list_SP_co_CTNL.add(sp);
					break;
				}
			}

		}

		return list_SP_co_CTNL;
	}

	// lấy ra danh sách chi tíết sản phẩm cùng tên
	public List<ChiTietSanPham> danhSachChiTietSP_theoLoai(String maSp) {
		Session session = factory.getCurrentSession();
		// Viết câu truy vấn HQL để lấy chi tiết sản phẩm theo mã sản phẩm (masp)
		String hql = "FROM ChiTietSanPham ctsp WHERE ctsp.pk.SANPHAM.MASP = :MASP";
		Query query = session.createQuery(hql);
		query.setParameter("MASP", maSp); // Thiết lập tham số mã sản phẩm

		@SuppressWarnings("unchecked")
		List<ChiTietSanPham> list = query.list(); // Trả về chi tiết sản phẩm

		return list;
	}

	public List<NguyenLieu> check_du_soLuongNL(boolean trangThai) {

		List<ChiTietNguyenLieu> listSpCapNhat = new ArrayList<>();
		// Tạo danh sách lưu nguyên liệu đã cập nhật
		List<NguyenLieu> updatedNguyenLieuList = new ArrayList<>();
		List<NguyenLieu> khongCoGi = new ArrayList<>();
		// Mở session và bắt đầu giao dịch tại đây, thay vì gọi update nhiều lần

		for (chiTietDanhSachSanPhamDuocChon spDuocChon : TmpClass.danhSachSPDuocChon) {
			System.out.print("\n/nsp đươc chon1  : " + spDuocChon.getName() + "  " + spDuocChon.getSize());
			// List ctnl nguyên liệu của 1 sản phẩm trong bill
			List<ChiTietNguyenLieu> listCTNL = getListCTNL_1_CTSP(spDuocChon.getName(), spDuocChon.getSize());

			//System.out.print("\n/nsize ctnl 1 cái  : " + listCTNL.size() + " \n/n ");
			// Duyệt qua từng nguyên liệu của sản phẩm
			for (ChiTietNguyenLieu ctnl : listCTNL) {
				System.out.print(ctnl.getNGUYENLIEU().getMANL() + "  ");

				for (NguyenLieu nl : getList_NL()) {
					if (ctnl.getNGUYENLIEU().getMANL().equals(nl.getMANL())) {
						BigDecimal soLuongTrongBill_big = new BigDecimal(spDuocChon.getSoLuong());
                        
						if (nl.getSLTON().compareTo(ctnl.getSOLUONG().multiply(soLuongTrongBill_big)) >= 0) {
							// Cập nhật số lượng tồn

							// chấp nhận thay đổi
							if (trangThai == true) {
								nl.setSLTON(nl.getSLTON().subtract(ctnl.getSOLUONG().multiply(soLuongTrongBill_big)));
							}

							System.out.print("\n" + nl.getTENNL() + " ton 1 " + nl.getSLTON());
							// update(nl); // Cập nhật vào session

							updatedNguyenLieuList.add(nl);

							System.out.print("\n" + nl.getTENNL() + " ton 2 " + nl.getSLTON());
							break; // Dừng vòng lặp nguyên liệu
						} else {

							TmpClass.thongBaoSP_ko_du = "Cần thêm " + (nl.getSLTON().subtract(ctnl.getSOLUONG().multiply(soLuongTrongBill_big))).abs() + " " +nl.getDONVI() +" " +  nl.getTENNL() +" ! ";

							return khongCoGi;
						}
					}
				}
			}
		}
		System.out.print("\nchck thành công " + updatedNguyenLieuList.size());
		System.out.print("\n\n");
		// Commit giao dịch nếu tất cả các thay đổi được thực hiện thành công

		for (NguyenLieu updated : updatedNguyenLieuList) {
			System.out.println(updated.getTENNL() + " - Số lượng còn lại: " + updated.getSLTON());
		}

		return updatedNguyenLieuList;

	}

	public List<ChiTietNguyenLieu> getListCTNL_1_CTSP(String maSP, String maSize) {
		List<ChiTietNguyenLieu> ctnl_Duoc_Chon = new ArrayList<>();
		for (ChiTietNguyenLieu ctnl : getList_CTNL()) {

//			if (ctnl.getPk().getCT_SANPHAM().getSIZE().getMASIZE().equalsIgnoreCase(maSize)
//					&& ctnl.getCT_SANPHAM().getSANPHAM().getMASP().equalsIgnoreCase(maSP)) {
//				ctnl_Duoc_Chon.add(ctnl);
//
//			}
			if (ctnl.getCT_SANPHAM().getSIZE().getMASIZE().equalsIgnoreCase(maSize)
					&& ctnl.getCT_SANPHAM().getSANPHAM().getMASP().equalsIgnoreCase(maSP)) {
				ctnl_Duoc_Chon.add(ctnl);
			}
		}
		return ctnl_Duoc_Chon;
	}

	public List<String> layDanhSachSizeTheoMaSanPham(String maSp) {
		// Gọi phương thức đã có để lấy danh sách ChiTietSanPham
		List<ChiTietSanPham> listChiTietSanPham = danhSachChiTietSP_theoLoai(maSp);
		List<String> listSize = new ArrayList<>();
		for (ChiTietSanPham ctsp : listChiTietSanPham) {
			String maSize = ctsp.getSIZE().getMASIZE();
			listSize.add(maSize);
		}

		return listSize; // Trả về danh sách mã size
	}

	public boolean check_ChiTietDanhSachSanPhamDuocChon_trung(chiTietDanhSachSanPhamDuocChon doiTuongCanKiemTra) {
		// Kiểm tra nếu danhSachSPDuocChon đã chứa sản phẩm có maSP và size trùng
		boolean isExist = false;
		for (chiTietDanhSachSanPhamDuocChon sp : TmpClass.danhSachSPDuocChon) {
			if (sp.getName().equals(doiTuongCanKiemTra.getName())
					&& sp.getSize().equals(doiTuongCanKiemTra.getSize())) {
				isExist = true; // Nếu tìm thấy phần tử có maSP và size trùng
				sp.setSoLuong(sp.getSoLuong() + 1);
				break;
			}
		}
		// nếu chưa tông tại thì thêm vào
		if (!isExist) {
			TmpClass.danhSachSPDuocChon.add(doiTuongCanKiemTra);

		}
		// TmpClass.tongTien = TmpClass.tinh_tong_tien();
		return isExist;

	}

	/*
	 * public List<SanPham> locDanhSachSanPham(List<SanPham> danhSachSanPhamCanLoc)
	 * {
	 * 
	 * List<ChiTietSanPham> myListChiTietSanPham = getList_CTSP(); // Danh sách chi
	 * tiết sản phẩm
	 * 
	 * boolean found;
	 * 
	 * for (int i = 0; i < danhSachSanPhamCanLoc.size(); i++) { found = false; for
	 * (int j = 0; j < myListChiTietSanPham.size(); j++) { if
	 * (danhSachSanPhamCanLoc.get(i).getMASP()
	 * .equals(myListChiTietSanPham.get(j).getPk().getSANPHAM().getMASP())) { found
	 * = true; break; } } if (found == false) { danhSachSanPhamCanLoc.remove(i);
	 * i--; }
	 * 
	 * }
	 * 
	 * return danhSachSanPhamCanLoc; // Trả về danh sách đã lọc }
	 */

	public String layTenTheoSize(String maSize) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT s.TENSIZE FROM Size s WHERE s.MASIZE = :maSize");
		query.setParameter("maSize", maSize);
		String tenSize = (String) query.uniqueResult();
		return tenSize;
	}

	public class chiTietDanhSachSanPhamDuocChon {
		private String name;
		private String size;
		private int soLuong;
		private BigDecimal gia;
		private String maKhuyenMai;
		private BigDecimal tongTien;
		private BigDecimal gia_km_big;

		public chiTietDanhSachSanPhamDuocChon(String name, String size, int soLuong, BigDecimal gia,
				String maKhuyenMai) {
			this.name = name;
//	        this.size =String.pa(layChiTiet1SanPham(name).get(0).getPk().getSIZE());
			this.size = size;

			this.soLuong = soLuong;
			this.gia = gia;
			this.maKhuyenMai = maKhuyenMai;

			BigDecimal soLuong_Big = new BigDecimal(this.soLuong);
			this.tongTien = gia.multiply(soLuong_Big);

			BigDecimal gia_km = new BigDecimal(0);
			for (ChiTietKhuyenMai ctkm : getList_CTKM_today()) {

				if (ctkm.getSANPHAM().getMASP().equals(name) && ctkm.getSIZE().getMASIZE().equals(size)
						&& ctkm.getKHUYENMAI().getMAKM().equals(maKhuyenMai)) {
					gia_km = new BigDecimal(1 - ctkm.getPHANTRAM() / 100);
					break;
				}
			}

			if (gia_km.compareTo(BigDecimal.ZERO) != 0) {

				this.gia_km_big = this.tongTien.multiply(gia_km);

			} else {
				this.gia_km_big = this.tongTien;
			}
			// System.out.print("\n/ntổng tiền : " + this.tongTien.multiply(soLuong_Big) + "
			// số lượng : " + getSoLuong());

		}

		// trùng thì true, ko trùng thì false
		public BigDecimal getGia_km_big() {
			return gia_km_big;
		}

		public String getSize() {
			return size;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSoLuong() {
			return soLuong;
		}

		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}

		public BigDecimal getGia() {
			return gia;
		}

		public void setGia(BigDecimal gia) {
			this.gia = gia;
		}

		public String getMaKhuyenMai() {
			return maKhuyenMai;
		}

		public BigDecimal getTongTien() {
			return this.gia.multiply(new BigDecimal(soLuong));
			// return BigDecimal(getGia()*getSoLuong());
		}

		public void setTongTien(BigDecimal tongTien) {
			this.tongTien = tongTien;
		}

	}

	public class TmpClass {
		// Biến static
		public static String check_loai_sp = "ALL";
		public static List<chiTietDanhSachSanPhamDuocChon> danhSachSPDuocChon = new ArrayList<>();
		public static BigDecimal tongTien = new BigDecimal(0);
		public static String maTk_dung;
		public static String thongBaoSP_ko_du = "";

		public static BigDecimal tinh_tong_tien() {
			tongTien = new BigDecimal(0);
			for (chiTietDanhSachSanPhamDuocChon chiTiet : danhSachSPDuocChon) {
				// TmpClass.tongTien =
				// TmpClass.tongTien.add(chiTiet.tongTien.multiply(chiTiet.soLuong));
				// tính tổng tiền của 1 sản phẩm

				// BigDecimal tongTienCua1Sp = chiTiet.tongTien.multiply(chiTiet.getTongTien());
				BigDecimal gia_Big = chiTiet.getGia_km_big(); // giả sử getGia() trả về BigDecimal
				BigDecimal soLuong_Big = BigDecimal.valueOf(chiTiet.getSoLuong()); // sử dụng valueOf để tránh vấn đề
																					// với kiểu double
				BigDecimal tongTienCua1Sp = gia_Big.multiply(soLuong_Big); // tính tổng tiền cho 1 sản phẩm

				// tính tôngr tiền của tất cá
				TmpClass.tongTien = TmpClass.tongTien.add(tongTienCua1Sp);
//		    		  System.out.print("\n\n tien 1 sp "+chiTiet.getGia());
//		    		  System.out.print("\ntong tien 1 sp " + tongTienCua1Sp);
//		    		  System.out.print("\ntong tien all sp " + tongTien);
			}
			return TmpClass.tongTien;
		}

	}
}
