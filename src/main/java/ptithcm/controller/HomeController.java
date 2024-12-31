package ptithcm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.log.Log;

import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	SessionFactory factory;
	// biến p là biến để lưu mkk mới
	public String p = "";
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/index")
	public String index(ModelMap model) {
		return "views/dangnhap";
	}

	@RequestMapping("/trangchuquanly")
	public String indexq(ModelMap model) {
		return "views/trangchuquanly";
	}
	
	@RequestMapping("/trangchunhanvien")
	public String indexn(ModelMap model) {
		return "views/trangchunhanvien";
	}
	
	@RequestMapping("/changepassword")
	public String doimk() {
		return "views/doimatkhau";
	}
	
	@RequestMapping("/quenmatkhau")
	public String quenmatkhau() {
		return "views/quenmatkhau";
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
	// lấy thông tin 1 nhân viên
	public NhanVien getNVByMaNV(String manv) {
	    Session session = factory.openSession();
	    String hql = "FROM NhanVien WHERE MANV = :manv"; // Sử dụng tham số để tránh SQL Injection
	    Query query = session.createQuery(hql);
	    query.setParameter("manv", manv); // Gán giá trị cho tham số

	    NhanVien list = null; // Khởi tạo biến list

	    if (query.list().size() == 0) {
	        // Nếu không tìm thấy nhân viên, tạo một đối tượng NhanVien mới với giá trị rỗng
	        list = new NhanVien();
	        list.setDIACHI(""); // Gán giá trị rỗng cho DIACHI, có thể thêm các thuộc tính khác nếu cần
	    } else {
	        // Nếu tìm thấy, lấy nhân viên đầu tiên
	        list = (NhanVien) query.list().get(0);
	    }

	    session.close(); // Đóng phiên làm việc
	    return list; // Trả về đối tượng NhanVien
	}

	@RequestMapping("/changepasswordsuccess")
	public String quenmatkhau1(ModelMap model) {
//		model.addAttribute("tk", get1TaiKhoan(LoginController.manv));
		model.addAttribute("message1", "Thay đổi mật khẩu thành công!");
		return "views/doimatkhauthanhcong";
	}

	@RequestMapping("/password")   // ok
	public String doimatkhau(HttpServletRequest rq, ModelMap model, HttpSession ss) {
		String oldpassword = rq.getParameter("oldpassword");
		String newpassword1 = rq.getParameter("newpassword1");
		String newpassword2 = rq.getParameter("newpassword2");
		String tenDangNhap = LoginController.manv;
		System.out.println(LoginController.manv + " " + oldpassword + " " + newpassword1);
		System.out.println("giá trị manv ở Home  " + LoginController.manv  + "  " + LoginController.manv );
		
		
		if (oldpassword.equals("") || newpassword1.equals("") || newpassword2.equals("")) {
			model.addAttribute("message0", "Vui lòng nhập đủ mọi thông tin liên quan!");
		}
	
		else if (!newpassword1.equals(newpassword2)) {
	        model.addAttribute("message0", "Mật khẩu mới và mật khẩu xác nhận không khớp!");
	        }
		
		// nhập  đủ và đúng - chưa xét có đúng trong csdl ko
		
		else {
				p = newpassword1;
				Session session = factory.openSession();
				
				String hql = "FROM TaiKhoan WHERE TENDANGNHAP ='" + tenDangNhap + "'";
				Query query = session.createQuery(hql);
			//	System.out.println("long1 +  " + query.list() + " Ma nhan vien "+LoginController.manv);
				TaiKhoan tk = (TaiKhoan) query.list().get(0);
//				if (SupportController.descryptPassword(oldpassword.trim(), tk.getMATKHAU())) {
				
				// nếu mật khẩu cũ(csdl) = mk cũ(nhập)n
				System.out.print("\n" + oldpassword + " " +  tk.getMATKHAU() );
				if (oldpassword.trim().equalsIgnoreCase(tk.getMATKHAU())) {
					System.out.println("long2 +  " + oldpassword + " " + tk.getMATKHAU());
					TaiKhoan temp = new TaiKhoan();
					temp.setTENDANGNHAP(tk.getTENDANGNHAP());
					temp.setQUYEN(tk.getQUYEN());
					temp.setTRANGTHAI(tk.TRANGTHAI);
					temp.setMATKHAU(newpassword1);
//					temp.setMATKHAU(SupportController.encryptPassword(newpassword));
					int check = this.update(temp);
					if (check == 1) {
						return "redirect:http://localhost:8080/Highlands/home/changepasswordsuccess.htm";
					} else {
						model.addAttribute("message0", "Thay đổi mật khẩu thất bại!");
						return "views/doimatkhau";
					}
				} else {
					System.out.print("\n" + oldpassword + " " +  newpassword1 +" " +  newpassword2 );
					model.addAttribute("message0", "Mật khẩu cũ không đúng, vui lòng kiểm tra lại!");
				}
			}
			

		return "views/doimatkhau";
	}

	

	@RequestMapping("/profile")
	public String trangcanhan(ModelMap model) {
		String referringUrl = request.getHeader("referer");
//		System.out.println("Previous link: " + referringUrl);
		model.addAttribute("link", referringUrl);
		if(LoginController.manv.equals("") || LoginController.manv == null) {
			return "redirect:/login/logout.htm";
		}
		TaiKhoan tk = get1TaiKhoan(LoginController.manv);
		NhanVien temp = getNVByMaNV(LoginController.manv);
		model.addAttribute("tk", tk.getQUYEN().getMAQUYEN());
		model.addAttribute("nv", temp);
		model.addAttribute("hoten", temp.getHOTENNV());
		System.out.print("/n\ntài khoản " + tk.getTENDANGNHAP() + " quyền " + tk.getQUYEN().getMAQUYEN());
		return "views/quanly/profile";
	}
// kiểm tra cập nhập thông tin có thành công lên csdl ko
	public int update(Object object) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(object);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace(); // In chi tiết ngoại lệ ra console
			return 0;
		} finally {
			session.close();
		}
		return 1;	
	}
////////////////////////////          ko bt àm gì  /////////////////////
	@RequestMapping("/profile/edit")
	public String trangcanhan1(ModelMap model) {
//		System.out.print("tien1 "+ LoginController.manv );
		TaiKhoan tk = get1TaiKhoan(LoginController.manv);
		NhanVien temp = getNVByMaNV(LoginController.manv);
		model.addAttribute("tk", tk.getQUYEN().getMAQUYEN());	
		model.addAttribute("nv", temp);
		model.addAttribute("hoten", temp.getHOTENNV());
//		// xem lại
//		System.out.print("ma quyen : " + get1TaiKhoan(temp.getMANV()).getQuyen().getMAQUYEN());
		System.out.print("\n manv : "+ LoginController.manv);
		if(get1TaiKhoan(temp.getMANV()).getQUYEN().getMAQUYEN().equals("Q03"))
			return "views/quanly/trangcanhannv";
		return "views/quanly/trangcanhan";
	}
 
	@RequestMapping(value = "/profile/edit", params = "luu")
	public String edit_Thongtin(ModelMap model, @ModelAttribute("nv") NhanVien nv) throws ParseException {
		// giá trị này lưu thông tin ban dầu
		NhanVien temp1 = getNVByMaNV(LoginController.manv);
		
			
		TaiKhoan tk = get1TaiKhoan(LoginController.manv);
		if (tk == null) {
		    return "redirect:/home/index.htm";
		}

		model.addAttribute("tk", tk.getQUYEN().getMAQUYEN());
		model.addAttribute("nv", temp1);
		model.addAttribute("hoten", temp1.getHOTENNV());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(nv.getNGAYSINH());
		Date date = dateFormat.parse(dateStr);
		java.util.Date utilDate = new java.util.Date(date.getTime());
		// lưu thông tin nhập mới 
		NhanVien temp = new NhanVien();
//		if (nv.getEMAIL() != null)
//			temp.setEMAIL(nv.getEMAIL());
	//	temp = temp1;
		temp.setMANV(LoginController.manv);
		temp.setHOTENNV(nv.getHOTENNV());
		temp.setGT(nv.getGT());
		temp.setNGAYSINH(utilDate);
		temp.setSDT(nv.getSDT());
		temp.setDIACHI(nv.getDIACHI());
		temp.setEMAIL(nv.getEMAIL());
		temp.setNGAYSINH(nv.getNGAYSINH());
		System.out.print("\n\n temp1 cũ   "+temp1.toString());
		System.out.print("\n\n temp mới  "+temp.toString());
		// không thay đoổi thông tin gì cả
		if (temp1.toString().equals(temp.toString())) {
			model.addAttribute("message0", "Thông tin chưa được thay đổi!");
		}
		else {
			// đẩy lên csdl ở đây
			int check = this.update(temp);
			if (check != 0) {
				model.addAttribute("message1", "Sửa nhân viên thành công!");
			} else {
				model.addAttribute("message0", "Sửa nhân viên thất bại!");
			}
			model.addAttribute("nv", temp);
		
		}
		
		System.out.print("quyền :" + temp1.getTaikhoan().getQUYEN().getMAQUYEN());
		if(get1TaiKhoan(temp1.getMANV()).getQUYEN().getMAQUYEN().equals("Q03"))
			return "views/quanly/trangcanhannv";
		return "views/quanly/trangcanhan";
	}


}
