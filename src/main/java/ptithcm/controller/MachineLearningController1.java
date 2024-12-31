////package ptithcm.controller;
////
////import java.util.List;
////
////import org.hibernate.Query;
////import org.hibernate.Session;
////import org.hibernate.SessionFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.format.annotation.DateTimeFormat;
////import org.springframework.stereotype.Controller;
////import org.springframework.transaction.annotation.Transactional;
////import org.springframework.ui.ModelMap;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import java.time.format.DateTimeFormatter;
////import org.springframework.ui.Model;
////
////import ptithcm.entity.ChiTietHoaDon;
////import ptithcm.entity.ChiTietNguyenLieu;
////import ptithcm.entity.ChiTietSanPham;
////import ptithcm.entity.Size;
////import ptithcm.entity.HoaDon;
////import ptithcm.entity.LichSu;
////import ptithcm.entity.SanPham;
////import weka.classifiers.trees.RandomForest;
////import weka.core.Instances;
////import weka.core.converters.ConverterUtils.DataSource;
////import org.springframework.format.annotation.DateTimeFormat;
////import java.util.Date;
////
////import weka.core.Attribute;
////import weka.core.DenseInstance;
////import weka.core.Instance;
////import weka.core.Instances;
////import java.text.ParseException;
////
////import java.text.SimpleDateFormat;
////import java.time.LocalDate;
////import java.time.ZoneId;
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.text.ParseException;
////import java.text.SimpleDateFormat;
////import java.util.Date;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////
////@Controller
////@Transactional
////@RequestMapping("/machineLearning")
////public class MachineLearningController1 {
////	@Autowired
////	SessionFactory factory;
////
////	@RequestMapping("/hocMay1")
////	public String dstaikhoanad(ModelMap model, @RequestParam(value = "startDate", required = false) String startDateStr,
////			@RequestParam(value = "endDate", required = false) String endDateStr) throws ParseException {
////
////		Date startDate = null;
////		Date endDate = null;
////		Date ngayHienTai = new Date(System.currentTimeMillis());
////		Date thangTruoc = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
////
////		// Tiến hành xử lý với dữ liệu sau khi đã đảm bảo các giá trị ngày
////		ArrayList<String> listMaSanPham = new ArrayList<>();
////		ArrayList<String> listMaSize = new ArrayList<>();
////		ArrayList<Integer> listSoLuong = new ArrayList<>();
////
////		List<ChiTietHoaDon> chiTietHoaDon_time = new ArrayList<>();
////
////		if (startDateStr == null || startDateStr.isEmpty()) {
////			startDate = thangTruoc;
////		} else {
////			startDate = convertStringToSqlDate(startDateStr);
////		}
////		if (endDateStr == null || endDateStr.isEmpty()) {
////			endDate = ngayHienTai;
////		} else {
////			endDate = convertStringToSqlDate(endDateStr);
////		}
////
////		model.addAttribute("startDate", startDateStr);
////		model.addAttribute("endDate", endDateStr);
////
////		if (startDate.after(ngayHienTai) || endDate.after(ngayHienTai)) {
////			model.addAttribute("sanPhamBanChay", "Thời gian phải bé hơn ngày hôm nay");
////			return "views/quanly/xem/machineLearning"; // Trả về trang JSP
////		}
////		if (startDate.equals(endDate)) {
////			model.addAttribute("sanPhamBanChay", "Ngày bắt đầu phải bé hơn ngày kết thúc");
////			return "views/quanly/xem/machineLearning"; // Trả về trang JSP
////		}
////
////		chiTietHoaDon_time = getList_CTHD(startDate, endDate);
////		System.out.print("\nkhoảng thời gian: " + startDate + " " + endDate);
////
////	
////		for (ChiTietHoaDon ct : chiTietHoaDon_time) {
////			listMaSanPham.add(ct.getPk().getSANPHAM().getMASP());
////			listMaSize.add(ct.getPk().getSIZE().getMASIZE());
////			listSoLuong.add(ct.getSOLUONG());
////		
////		}
////
////		String spBanChay = RandomForestExample(listMaSanPham, listMaSize, listSoLuong, model);
////
////		model.addAttribute("sanPhamBanChay", "Sản phẩm bán chạy: " + layTenSP(spBanChay)+ " size " +layTenSize(spBanChay));
////		model.addAttribute("listCTNL_BanChay", get_1_CTNL(spBanChay));
////
////		return "views/quanly/xem/machineLearning"; // Trả về trang JSP 
////
////	}
////
////	public Date convertStringToSqlDate(String dateStr) throws ParseException {
////		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
////		java.util.Date utilDate = formatter.parse(dateStr);
////		return new java.sql.Date(utilDate.getTime());
////	}
////
////	public String RandomForestExample(ArrayList<String> maSanPham, ArrayList<String> maSize, ArrayList<Integer> soLuong,
////			ModelMap model) {
////		String bestLabel = "";
////		try {
////			// 1. Gộp dữ liệu theo nhãn
////			HashMap<String, Integer> labelToQuantityMap = new HashMap<>();
////
////			for (int i = 0; i < maSanPham.size(); i++) {
////				String label = maSanPham.get(i) + "-Size" + maSize.get(i);
////				labelToQuantityMap.put(label, labelToQuantityMap.getOrDefault(label, 0) + soLuong.get(i));
////			}
////
////			// 2. Tạo danh sách các thuộc tính
////			ArrayList<Attribute> attributes = new ArrayList<>();
////			attributes.add(new Attribute("soLuong")); // Thuộc tính số lượng
////
////			// Tạo thuộc tính class (label)
////			ArrayList<String> classValues = new ArrayList<>(labelToQuantityMap.keySet());
////			Attribute classAttribute = new Attribute("label", classValues);
////			attributes.add(classAttribute);
////
////			// 3. Tạo tập dữ liệu
////			Instances dataset = new Instances("Dataset", attributes, 0);
////			dataset.setClassIndex(dataset.numAttributes() - 1); // Đặt thuộc tính cuối là class
////
////			// Thêm dữ liệu vào tập huấn luyện
////			for (String label : labelToQuantityMap.keySet()) {
////				double[] instanceValues = { labelToQuantityMap.get(label), // Số lượng tổng cộng
////						dataset.attribute("label").indexOfValue(label) // Nhãn
////				};
////				dataset.add(new DenseInstance(1.0, instanceValues));
////			}
////
////			// 4. Tạo và thiết lập mô hình RandomForest
////			RandomForest randomForest = new RandomForest();
////			String[] options = new String[] { "-I", "100", // Số lượng cây trong rừng
////					"-depth", "10" // Chiều sâu tối đa của cây
////			};
////			randomForest.setOptions(options);
////
////			// Huấn luyện mô hình
////			randomForest.buildClassifier(dataset);
////
////			// 5. In thông tin mô hình
////			System.out.println("Mô hình RandomForest:");
////			System.out.println(randomForest);
////
////			// 6. Dự đoán nhãn phát triển mạnh nhất
////			bestLabel = predictBestLabel(dataset, randomForest);
////			System.out.println("Nhãn được dự đoán phát triển mạnh nhất trong tương lai: " + bestLabel);
////			System.out
////					.println("Nguyên liệu được dự đoán phát triển mạnh nhất trong tương lai: " + get_1_CTNL(bestLabel));
////			for (ChiTietNguyenLieu ctnl : get_1_CTNL(bestLabel)) {
////				System.out.print(ctnl.getNGUYENLIEU().getTENNL() + "  " + ctnl.getSOLUONG()
////						+ ctnl.getNGUYENLIEU().getSLTON() + ctnl.getNGUYENLIEU().getDONVI());
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return bestLabel;
////	}
////
////	public String predictBestLabel(Instances dataset, RandomForest randomForest) {
////		double maxPrediction = Double.MIN_VALUE;
////		String bestLabel = "";
////
////		try {
////			for (Instance instance : dataset) {
////				// Dự đoán nhãn của instance
////				double predictedClassIndex = randomForest.classifyInstance(instance);
////				String predictedLabel = dataset.classAttribute().value((int) predictedClassIndex);
////
////				// Xét giá trị số lượng
////				double predictedQuantity = instance.value(dataset.attribute("soLuong"));
////
////				if (predictedQuantity > maxPrediction) {
////					maxPrediction = predictedQuantity;
////					bestLabel = predictedLabel;
////				}
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return bestLabel;
////	}
////	public String layTenSP (String chuoi) {
////		String[] parts = chuoi.split("-");
////		String maSanPham = ""; // "SP001"
////		maSanPham = parts[0]; // "SP001"
////		for(SanPham sp : getList_SP()) {
////			if(sp.getMASP().equals(maSanPham));
////		    return sp.getTENSP();
////		}
////		return " lỗi lấy tên sản phẩm ";
////	}
////	public String layTenSize (String chuoi) {
////		String[] parts = chuoi.split("-");
////		String maSize = ""; // "L"
////		maSize = parts[1]; // "L"
////		for(Size size : getList_size()) {
////			if(size.getMASIZE().equals(maSize));
////		    return size.getTENSIZE();
////		}
////		return " lỗi lấy tên sản phẩm ";
////	}
////
////
////	public List<ChiTietNguyenLieu> get_1_CTNL(String bestLabel) {
////		// Tách nhãn bằng dấu "-"
////		String[] parts = bestLabel.split("-");
////		String maSanPham = ""; // "SP001"
////		String maSize = ""; // "L"
////		List<ChiTietNguyenLieu> listCTNL = new ArrayList<>();
////		ChiTietNguyenLieu tongCtnl = new ChiTietNguyenLieu();
////		if (parts.length == 2) {
////			maSanPham = parts[0]; // "SP001"
////			maSize = parts[1].replace("Size", ""); // "L"
////
////			// Hiển thị kết quả
////			System.out.println("Mã sản phẩm: " + maSanPham);
////			System.out.println("Mã size: " + maSize);
////		} else {
////			System.out.println("Định dạng nhãn không hợp lệ: " + bestLabel);
////		}
////
////		for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
////			if (ctnl.getCT_SANPHAM().getSANPHAM().getMASP().equals(maSanPham)
////					&& ctnl.getCT_SANPHAM().getSIZE().getMASIZE().equals(maSize)) {
////				listCTNL.add(ctnl);
////
////			}
////		}
////		return listCTNL;
////
////	}
////
////	public List<ChiTietHoaDon> getList_CTHD() {
////		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////		Query query = session.createQuery("FROM ChiTietHoaDon");
////		return query.list(); // Không cần đóng session
////
////	}
////	public List<Size> getList_size() {
////		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////		Query query = session.createQuery("FROM Size");
////		return query.list(); // Không cần đóng session
////
////	}
////
////	public List<ChiTietHoaDon> getList_CTHD(Date startDate, Date endDate) {
////
////		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////		System.out.print("tuấn tuấn");
////		// danh sachs hóa đơn theo thời gian
////		Query query = session.createQuery("FROM HoaDon cthd WHERE cthd.NGAYLAP BETWEEN :startDate AND :endDate");
////		query.setParameter("startDate", startDate);
////		query.setParameter("endDate", endDate);
////
////		List<ChiTietHoaDon> cthd = new ArrayList<>();
////		List<HoaDon> hoaDons_time = query.list();
////
////		// lấy ra danh sách chi tiết hóa đơn theo hd(ID)
////		for (HoaDon hd : hoaDons_time) {
////			cthd.addAll(getList_CTHD_1HD(hd));
////		}
////		System.out.print("danh sachs háo đơn " + query.list().size());
////		System.out.print("danh sachs chi tiết háo đơn " + cthd.size());
////
////		return cthd; // Không cần đóng session
////	}
////
////	public List<ChiTietHoaDon> getList_CTHD_1HD(HoaDon HD) {
////		List<ChiTietHoaDon> listCthd = new ArrayList<>();
////		for (ChiTietHoaDon cthd : getList_CTHD()) {
////			if (cthd.getPk().getHOADON().getID().equals(HD.getID())) {
////				listCthd.add(cthd);
////			}
////		}
////		return listCthd;
////	}
////
//////
//////    public List<ChiTietHoaDon> getList_CTHD() {
//////        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//////        Query query = session.createQuery("FROM ChiTietHoaDon");
//////        return query.list();  // Không cần đóng session
//////    }
////
////	public List<ChiTietSanPham> getList_CTSP() {
////		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////		Query query = session.createQuery("FROM ChiTietSanPham");
////		return query.list(); // Không cần đóng session
////	}
////	public List<SanPham> getList_SP() {
////		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////		Query query = session.createQuery("FROM SanPham");
////		return query.list(); // Không cần đóng session
////	}
////	public List<ChiTietNguyenLieu> getList_CTNL() {
////		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////		Query query = session.createQuery("FROM ChiTietNguyenLieu");
////		return query.list(); // Không cần đóng session
////	}
////}
//
//
//
//
//package ptithcm.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import java.time.format.DateTimeFormatter;
//import org.springframework.ui.Model;
//
//import ptithcm.entity.ChiTietHoaDon;
//import ptithcm.entity.ChiTietNguyenLieu;
//import ptithcm.entity.ChiTietSanPham;
//import ptithcm.entity.Size;
//import ptithcm.entity.HoaDon;
//import ptithcm.entity.LichSu;
//import ptithcm.entity.NguyenLieu;
//import ptithcm.entity.SanPham;
//import weka.classifiers.trees.RandomForest;
//import weka.core.Instances;
//import weka.core.converters.ConverterUtils.DataSource;
//import org.springframework.format.annotation.DateTimeFormat;
//import java.util.Date;
//
//import weka.core.Attribute;
//import weka.core.DenseInstance;
//import weka.core.Instance;
//import weka.core.Instances;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@Transactional
//@RequestMapping("/machineLearning")
//public class MachineLearningController {
//	@Autowired
//	SessionFactory factory;
//
//	@RequestMapping("/hocMay")
//	public String dstaikhoanad(ModelMap model, @RequestParam(value = "startDate", required = false) String startDateStr,
//			@RequestParam(value = "endDate", required = false) String endDateStr) throws ParseException {
//
//		Date startDate = null;
//		Date endDate = null;
//		Date ngayHienTai = new Date(System.currentTimeMillis());
//		Date thangTruoc = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//		// Tiến hành xử lý với dữ liệu sau khi đã đảm bảo các giá trị ngày
//		ArrayList<String> listMaSanPham = new ArrayList<>();
//		ArrayList<String> listMaSize = new ArrayList<>();
//		ArrayList<Integer> listSoLuong = new ArrayList<>();
//
//		List<ChiTietHoaDon> chiTietHoaDon_time = new ArrayList<>();
//
//		if (startDateStr == null || startDateStr.isEmpty()) {
//			startDate = thangTruoc;
//		} else {
//			startDate = convertStringToSqlDate(startDateStr);
//		}
//		if (endDateStr == null || endDateStr.isEmpty()) {
//			endDate = ngayHienTai;
//		} else {
//			endDate = convertStringToSqlDate(endDateStr);
//		}
//
//		model.addAttribute("startDate", startDateStr);
//		model.addAttribute("endDate", endDateStr);
//
//		if (startDate.after(ngayHienTai) || endDate.after(ngayHienTai)) {
//			model.addAttribute("sanPhamBanChay", "Thời gian phải bé hơn ngày hôm nay");
//			return "views/quanly/xem/machineLearning"; // Trả về trang JSP
//		}
//		if (startDate.equals(endDate)) {
//			model.addAttribute("sanPhamBanChay", "Ngày bắt đầu phải bé hơn ngày kết thúc");
//			return "views/quanly/xem/machineLearning"; // Trả về trang JSP
//		}
//
//		chiTietHoaDon_time = getList_CTHD(startDate, endDate);
//		System.out.print("\nkhoảng thời gian: " + startDate + " " + endDate);
//
//	
//		for (ChiTietHoaDon ct : chiTietHoaDon_time) {
//			listMaSanPham.add(ct.getPk().getSANPHAM().getMASP());
//			listMaSize.add(ct.getPk().getSIZE().getMASIZE());
//			listSoLuong.add(ct.getSOLUONG());
//		
//		}
//
//		String spBanChay = RandomForestExample(listMaSanPham, listMaSize, listSoLuong, model);
//        if(spBanChay != null && spBanChay.equals("")) {
//        	model.addAttribute("sanPhamBanChay", "Sản phẩm bán chạy: " + layTenSP(spBanChay)+ " size " +layTenSize(spBanChay));
//    		model.addAttribute("listCTNL_BanChay", get_1_CTNL(spBanChay));
//
//        }
//
//		gopSoLuongSanPham(chiTietHoaDon_time);
//		
//		//gopNguyenLieuTheoSanPham(chiTietHoaDon_time);
//		
//		
//		
//		return "views/quanly/xem/machineLearning"; // Trả về trang JSP 
//
//	}
//
//	public Date convertStringToSqlDate(String dateStr) throws ParseException {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
//		java.util.Date utilDate = formatter.parse(dateStr);
//		return new java.sql.Date(utilDate.getTime());
//	}
//
//	public String RandomForestExample(ArrayList<String> maSanPham, ArrayList<String> maSize, ArrayList<Integer> soLuong,
//			ModelMap model) {
//		String spBanChay = "";
//		try {
//			// 1. Gộp dữ liệu theo nhãn
//			HashMap<String, Integer> labelToQuantityMap = new HashMap<>();
//
//			for (int i = 0; i < maSanPham.size(); i++) {
//				String label = maSanPham.get(i) + "-Size" + maSize.get(i);
//				labelToQuantityMap.put(label, labelToQuantityMap.getOrDefault(label, 0) + soLuong.get(i));
//			}
//
//			// 2. Tạo danh sách các thuộc tính
//			ArrayList<Attribute> attributes = new ArrayList<>();
//			attributes.add(new Attribute("soLuong")); // Thuộc tính số lượng
//
//			// Tạo thuộc tính class (label)
//			ArrayList<String> classValues = new ArrayList<>(labelToQuantityMap.keySet());
//			Attribute classAttribute = new Attribute("label", classValues);
//			attributes.add(classAttribute);
//
//			// 3. Tạo tập dữ liệu
//			Instances dataset = new Instances("Dataset", attributes, 0);
//			dataset.setClassIndex(dataset.numAttributes() - 1); // Đặt thuộc tính cuối là class
//
//			// Thêm dữ liệu vào tập huấn luyện
//			for (String label : labelToQuantityMap.keySet()) {
//				double[] instanceValues = { labelToQuantityMap.get(label), // Số lượng tổng cộng
//						dataset.attribute("label").indexOfValue(label) // Nhãn
//				};
//				dataset.add(new DenseInstance(1.0, instanceValues));
//			}
//
//			// 4. Tạo và thiết lập mô hình RandomForest
//			RandomForest randomForest = new RandomForest();
//			String[] options = new String[] { "-I", "100", // Số lượng cây trong rừng
//					"-depth", "10" // Chiều sâu tối đa của cây
//			};
//			randomForest.setOptions(options);
//
//			// Huấn luyện mô hình
//			randomForest.buildClassifier(dataset);
//
//			// 5. In thông tin mô hình
//			System.out.println("Mô hình RandomForest:");
//			System.out.println(randomForest);
//
//			// 6. Dự đoán nhãn phát triển mạnh nhất
//			spBanChay = duDoanSPBanChay(dataset, randomForest);
//			System.out.println("Nhãn được dự đoán phát triển mạnh nhất trong tương lai: " + spBanChay);
//			System.out.println("Nguyên liệu được dự đoán phát triển mạnh nhất trong tương lai: " + get_1_CTNL(spBanChay));
//			System.out.print("\n Số lượng " + duDoanSoLuong(dataset, randomForest));
//			
////			for (ChiTietNguyenLieu ctnl : get_1_CTNL(bestLabel)) {
////				System.out.print(ctnl.getNGUYENLIEU().getTENNL() + "  " + ctnl.getSOLUONG()
////						+ ctnl.getNGUYENLIEU().getSLTON() + ctnl.getNGUYENLIEU().getDONVI());
////			}
//            			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return spBanChay;
//	}
//	public double duDoanSoLuong(Instances dataset, RandomForest randomForest) {
//	    double maxPrediction = Double.MIN_VALUE; // Biến lưu trữ số lượng bán lớn nhất
//	    double predictedQuantity = 0; // Biến lưu trữ số lượng bán dự đoán
//
//	    try {
//	        // Lặp qua từng instance (mẫu dữ liệu) trong tập dữ liệu
//	        for (Instance instance : dataset) {
//	            // Dự đoán nhãn của instance (sản phẩm)
//	            double predictedClassIndex = randomForest.classifyInstance(instance);
//	            
//	            // Lấy giá trị số lượng bán của sản phẩm từ dữ liệu instance
//	            double quantity = instance.value(dataset.attribute("soLuong"));
//
//	            // Nếu số lượng bán của sản phẩm lớn hơn số lượng bán hiện tại, cập nhật giá trị số lượng
//	            if (quantity > maxPrediction) {
//	                maxPrediction = quantity;
//	                predictedQuantity = quantity; // Cập nhật số lượng bán dự đoán
//	            }
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace(); // In ra lỗi nếu có
//	    }
//
//	    // Trả về số lượng bán dự đoán
//	    return predictedQuantity;
//	}
//
//
//
//	public String duDoanSPBanChay(Instances dataset, RandomForest randomForest) {
//		double maxPrediction = Double.MIN_VALUE;
//		String bestLabel = "";
//
//		try {
//			for (Instance instance : dataset) {
//				// Dự đoán nhãn của instance
//				double predictedClassIndex = randomForest.classifyInstance(instance);
//				String predictedLabel = dataset.classAttribute().value((int) predictedClassIndex);
//
//				// Xét giá trị số lượng
//				double predictedQuantity = instance.value(dataset.attribute("soLuong"));
//
//				if (predictedQuantity > maxPrediction) {
//					maxPrediction = predictedQuantity;
//					bestLabel = predictedLabel;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return bestLabel;
//	}
//	
////	public String soNgayHetNguyenLieu(ChiTietHoaDon chiTietHoaDon_time) {
////		
////	}
//
//	public Map<String, Integer> gopSoLuongSanPham(List<ChiTietHoaDon> chiTietHoaDonList) {
//	    // Sử dụng một Map để gộp số lượng sản phẩm giống nhau (cả mã sản phẩm và mã size)
//	    Map<String, Integer> productQuantityMap = new HashMap<>();
//
//	    for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
//	        SanPham sanPham = chiTiet.getPk().getSANPHAM();
//	        Size size = chiTiet.getPk().getSIZE();  // Lấy size của sản phẩm
//	        int soLuong = chiTiet.getSOLUONG();
//
//	        // Tạo một chuỗi nhận diện duy nhất cho sản phẩm dựa trên MASP và MASIZE
//	        String productKey = sanPham.getMASP() + "-" + size.getMASIZE();
//
//	        // Nếu sản phẩm đã có trong Map, cộng thêm số lượng
//	        if (productQuantityMap.containsKey(productKey)) {
//	            productQuantityMap.put(productKey, productQuantityMap.get(productKey) + soLuong);
//	        } else {
//	            productQuantityMap.put(productKey, soLuong);
//	        }
//	    }
//
//	    // In ra kết quả gộp
//	    for (Map.Entry<String, Integer> entry : productQuantityMap.entrySet()) {
//	        System.out.println("Mã sản phẩm + Mã size: " + entry.getKey() + ", Tổng số lượng: " + entry.getValue());
//	    }
//
//
//	    // Trả về Map chứa mã sản phẩm + mã size và tổng số lượng
//	    return productQuantityMap;
//	}
//	public Map<String, List<String>> gopNguyenLieuTheoSanPham(List<ChiTietHoaDon> chiTietHoaDon_time) {
//		
//		
//
//		List<ChiTietNguyenLieu> chiTietNguyenLieuList = new ArrayList<>();
//
//				
//		chiTietNguyenLieuList = getList_CTNL_time(chiTietHoaDon_time) ;
//
//		
//	    // Sử dụng một Map để gộp nguyên liệu theo mã sản phẩm và mã size
//	    Map<String, List<String>> productMaterialMap = new HashMap<>();
//
//	    for (ChiTietNguyenLieu chiTiet : chiTietNguyenLieuList) {
//	        ChiTietSanPham chiTietSanPham = chiTiet.getCT_SANPHAM();
//	        NguyenLieu nguyenLieu = chiTiet.getNGUYENLIEU();  // Lấy nguyên liệu
//	        BigDecimal soLuong = chiTiet.getSOLUONG();  // Lấy số lượng nguyên liệu
//
//	        // Tạo chuỗi nhận diện duy nhất cho sản phẩm dựa trên mã sản phẩm và mã size
//	        String productKey = chiTietSanPham.getSANPHAM().getMASP() + "-" + chiTietSanPham.getSIZE().getMASIZE();
//
//	        // Tạo chuỗi thông tin nguyên liệu + số lượng
//	        String materialInfo = nguyenLieu.getTENNL() + " - " + soLuong;
//
//	        // Nếu sản phẩm đã có trong Map, thêm nguyên liệu vào danh sách
//	        if (productMaterialMap.containsKey(productKey)) {
//	            productMaterialMap.get(productKey).add(materialInfo);
//	        } else {
//	            List<String> materialList = new ArrayList<>();
//	            materialList.add(materialInfo);
//	            productMaterialMap.put(productKey, materialList);
//	        }
//	    }
//	    // In ra kết quả gộp
//	    System.out.println("\n\n Nguyên liệu");
//	    for (Map.Entry<String, List<String>> entry : productMaterialMap.entrySet()) {
//	        System.out.println("Mã sản phẩm + Mã size: " + entry.getKey() + ", Tổng số lượng: " + entry.getValue());
//	    }
//
//	    // Trả về Map chứa mã sản phẩm + mã size và danh sách nguyên liệu với số lượng
//	    return productMaterialMap;
//	}
//
//
//	
//	public String layTenSP (String chuoi) {
//		String[] parts = chuoi.split("-");
//		String maSanPham = ""; // "SP001"
//		maSanPham = parts[0]; // "SP001"
//		for(SanPham sp : getList_SP()) {
//			if(sp.getMASP().equals(maSanPham));
//		    return sp.getTENSP();
//		}
//		return " lỗi lấy tên sản phẩm ";
//	}
//	public String layTenSize (String chuoi) {
//		String[] parts = chuoi.split("-");
//		if (parts.length > 1) {
//			String maSize = ""; // "L"
//			maSize = parts[1]; // "L"
//			for(Size size : getList_size()) {
//				if(size.getMASIZE().equals(maSize));
//			    return size.getTENSIZE();
//			}
//		}
//	
//		return " lỗi lấy tên sản phẩm ";
//	}
//
//
//	public List<ChiTietNguyenLieu> get_1_CTNL(String bestLabel) {
//		// Tách nhãn bằng dấu "-"
//		String[] parts = bestLabel.split("-");
//		String maSanPham = ""; // "SP001"
//		String maSize = ""; // "L"
//		List<ChiTietNguyenLieu> listCTNL = new ArrayList<>();
//		ChiTietNguyenLieu tongCtnl = new ChiTietNguyenLieu();
//		if (parts.length == 2) {
//			maSanPham = parts[0]; // "SP001"
//			maSize = parts[1].replace("Size", ""); // "L"
//
//			// Hiển thị kết quả
//			System.out.println("Mã sản phẩm: " + maSanPham);
//			System.out.println("Mã size: " + maSize);
//		} else {
//			System.out.println("Định dạng nhãn không hợp lệ: " + bestLabel);
//		}
//
//		for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
//			if (ctnl.getCT_SANPHAM().getSANPHAM().getMASP().equals(maSanPham)
//					&& ctnl.getCT_SANPHAM().getSIZE().getMASIZE().equals(maSize)) {
//				listCTNL.add(ctnl);
//
//			}
//		}
//		return listCTNL;
//
//	}
//
//	public List<ChiTietHoaDon> getList_CTHD() {
//		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//		Query query = session.createQuery("FROM ChiTietHoaDon");
//		return query.list(); // Không cần đóng session
//
//	}
//	public List<Size> getList_size() {
//		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//		Query query = session.createQuery("FROM Size");
//		return query.list(); // Không cần đóng session
//
//	}
//
//	public List<ChiTietHoaDon> getList_CTHD(Date startDate, Date endDate) {
//
//		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//		System.out.print("tuấn tuấn");
//		// danh sachs hóa đơn theo thời gian
//		Query query = session.createQuery("FROM HoaDon cthd WHERE cthd.NGAYLAP BETWEEN :startDate AND :endDate");
//		query.setParameter("startDate", startDate);
//		query.setParameter("endDate", endDate);
//
//		List<ChiTietHoaDon> cthd = new ArrayList<>();
//		List<HoaDon> hoaDons_time = query.list();
//
//		// lấy ra danh sách chi tiết hóa đơn theo hd(ID)
//		for (HoaDon hd : hoaDons_time) {
//			cthd.addAll(getList_CTHD_1HD(hd));
//		}
//		System.out.print("danh sachs háo đơn " + query.list().size());
//		System.out.print("danh sachs chi tiết háo đơn " + cthd.size());
//
//		return cthd; // Không cần đóng session
//	}
//	public List<ChiTietNguyenLieu> getList_CTNL_time(List<ChiTietHoaDon> listCthd_time) {
//
//		List<ChiTietNguyenLieu> listCtnl_time = new ArrayList<>();
////		List<ChiTietNguyenLieu> listCtl = getList_CTNL();
////		
//		
//		for(ChiTietHoaDon cthd : listCthd_time ) {
//			
//			for(ChiTietNguyenLieu ctnl : getList_CTNL()) {
//				// nếu sanPham trong cthd bằng sanPham trong nguyên liệu)
//				if(cthd.getPk().getSANPHAM().equals(ctnl.getCT_SANPHAM().getSANPHAM())) {
//					listCtnl_time.add(ctnl);
//				}
//			}
//		
//		}
//		return listCtnl_time; // Không cần đóng session
//	}
//
//	public List<ChiTietHoaDon> getList_CTHD_1HD(HoaDon HD) {
//		List<ChiTietHoaDon> listCthd = new ArrayList<>();
//		for (ChiTietHoaDon cthd : getList_CTHD()) {
//			if (cthd.getPk().getHOADON().getID().equals(HD.getID())) {
//				listCthd.add(cthd);
//			}
//		}
//		return listCthd;
//	}
//
////
////    public List<ChiTietHoaDon> getList_CTHD() {
////        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
////        Query query = session.createQuery("FROM ChiTietHoaDon");
////        return query.list();  // Không cần đóng session
////    }
//
//	public List<ChiTietSanPham> getList_CTSP() {
//		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//		Query query = session.createQuery("FROM ChiTietSanPham");
//		return query.list(); // Không cần đóng session
//	}
//	public List<SanPham> getList_SP() {
//		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//		Query query = session.createQuery("FROM SanPham");
//		return query.list(); // Không cần đóng session
//	}
//	public List<ChiTietNguyenLieu> getList_CTNL() {
//		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//		Query query = session.createQuery("FROM ChiTietNguyenLieu");
//		return query.list(); // Không cần đóng session
//	}
//}
