package ptithcm.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.LichSu;

@Controller
@Transactional
@RequestMapping("/history")
public class HistoryController {
	@Autowired
	SessionFactory factory;

	public List<LichSu> getList_LS() {
		Session session = factory.getCurrentSession();
		String hql = "from LichSu";
		Query query = session.createQuery(hql);
		List<LichSu> list = query.list();
		return list;
	}
	public List<ChiTietSanPham> getList_CTSP() {
		Session session = factory.getCurrentSession();
		String hql = "from ChiTietSanPham";
		Query query = session.createQuery(hql);
		List<ChiTietSanPham> list = query.list();
		return list;
	}

	@RequestMapping("list")
	public String dslichsu(ModelMap model) {
		
		model.addAttribute("dslichsu", getList_LS());
		model.addAttribute("dsCTSP", getList_CTSP());
		
		return "views/quanly/xem/lichsu";
	}
}