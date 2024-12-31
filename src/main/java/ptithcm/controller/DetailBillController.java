package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.HoaDon;
import ptithcm.entity.NhanVien;
import ptithcm.entity.Size;
import ptithcm.entity.TaiKhoan;

@Controller
@Transactional
@RequestMapping("/bill/detail")
public class DetailBillController {

    @Autowired
    SessionFactory factory;

    public List<ChiTietHoaDon> getList_CTHD() {
        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
        Query query = session.createQuery("FROM ChiTietHoaDon");
        return query.list();  // Không cần đóng session
    }

    public List<ChiTietSanPham> getList_CTSP() {
        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
        Query query = session.createQuery("FROM ChiTietSanPham");
        return query.list();  // Không cần đóng session
    }

    @RequestMapping("list")
    public String bill(ModelMap model) {
        // Lấy danh sách ChiTietHoaDon và ChiTietSanPham
        List<ChiTietHoaDon> myListCTHD = getList_CTHD();
        List<ChiTietSanPham> myListChiTietSanPham = getList_CTSP();

        // Thêm vào model để truyền cho JSP
        model.addAttribute("listCTHD", myListCTHD);
        model.addAttribute("listCTSanPham", myListChiTietSanPham);

		/*
		 * for (ChiTietHoaDon item : myListCTHD) { if (item != null && item.getPk() !=
		 * null) { if (item.getPk().getHOADON() != null) {
		 * System.out.print("\nHoaDon ID: " + item.getPk().getHOADON().getID()); } if
		 * (item.getPk().getSANPHAM() != null) { System.out.print("+" +
		 * item.getPk().getSANPHAM().getMASP()); } if (item.getPk().getSIZE() != null) {
		 * System.out.print("+" + item.getPk().getSIZE().getMASIZE()); }
		 * System.out.print("SoLuong: " + item.getSOLUONG()); if (item.getPk().getMAKM()
		 * != null) { System.out.print("+" + item.getPk().getMAKM().getMAKM()); } } }
		 */


        // Trả về view
        return "views/quanly/chitiethoadon";
    }

    public int update(Object object) {
        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
        try {
            session.update(object);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Transactional
    public ChiTietHoaDon get1_CT_Bill(String ma) {
        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
        String hql = "FROM ChiTietHoaDon where ID = :ma";
        Query query = session.createQuery(hql);
        query.setParameter("ma", ma);
        return (ChiTietHoaDon) query.uniqueResult();
    }
}
