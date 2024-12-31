package ptithcm.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java_cup.runtime.virtual_parse_stack;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.ChiTietNguyenLieu;
import ptithcm.entity.ChiTietSanPham;
import ptithcm.entity.Size;
import ptithcm.entity.HoaDon;
import ptithcm.entity.LichSu;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.SanPham;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.*;
import java.time.LocalDate; // Để sử dụng LocalDate

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Transactional
@RequestMapping("/machineLearning")
public class MachineLearningController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/hocMay")
	public String dstaikhoanad(ModelMap model, @RequestParam(value = "startDate", required = false) String startDateStr,
			@RequestParam(value = "endDate", required = false) String endDateStr,
			@RequestParam(value = "numberInput", required = false) Integer numberInput) throws ParseException {

		Date startDate = null;
		Date endDate = null;
		Date ngayHienTai = new Date(System.currentTimeMillis());
		Date thangTruoc = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

		// Tiến hành xử lý với dữ liệu sau khi đã đảm bảo các giá trị ngày
		ArrayList<String> listMaSanPham = new ArrayList<>();
		ArrayList<String> listMaSize = new ArrayList<>();
		ArrayList<Integer> listSoLuong = new ArrayList<>();

		List<ChiTietHoaDon> chiTietHoaDon_time = new ArrayList<>();
		// List<ChiTietHoaDon> chiTietHoaDon = getList_CTHD();

		if (startDateStr == null || startDateStr.isEmpty()) {
			startDate = thangTruoc;
		} else {
			startDate = convertStringToSqlDate(startDateStr);
		}
		if (endDateStr == null || endDateStr.isEmpty()) {
			endDate = ngayHienTai;
		} else {
			endDate = convertStringToSqlDate(endDateStr);
		}

		model.addAttribute("startDate", startDateStr);
		model.addAttribute("endDate", endDateStr);
//        if(startDateStr.equals("") && endDateStr.equals("")) {
//        	
//        }

		// Định dạng ngày tháng
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Chuyển đổi String thành LocalDate

		String minKey = null;
		BigDecimal minValue = null;
		long daysBetween = 0;
		Map<String, BigDecimal> listNguyenLieu = new HashMap<>();
		Map<String, BigDecimal> ngayHetNguyenLieu = new HashMap<>();

		Map<String, BigDecimal> mapDuDoanSoNlCanNhapTrong_soNgay = new HashMap<>();

		// 1 null

		// 2 bằng null
		if ((startDateStr == null && endDateStr == null) || (startDateStr.equals("") && endDateStr.equals(""))) {
			System.out.print("2 null");
			// Tính số ngày giữa hai ngày
			chiTietHoaDon_time = getList_CTHD(getMinDate_hoaDon(), ngayHienTai);

			daysBetween = soNgay_min_hienTai();
			listNguyenLieu = gopNguyenLieuTheoSanPham(chiTietHoaDon_time);
			ngayHetNguyenLieu = duDoanNgayHetNguyenLieu(listNguyenLieu, daysBetween);
			model.addAttribute("thongbao_soNgay", "Dự đoán cách ngày hôm nay là : " + daysBetween + " ngày");

		} else {

			// ko cái nào null
//			if (startDateStr != null && endDateStr != null) {
			if (startDateStr.equals("") != true && endDateStr.equals("") != true) {

				System.out.print("2 khác null");
				model.addAttribute("thongbao_soNgay", "Ngày dự đoán từ : " + startDateStr + " đến " + endDateStr);
				model.addAttribute("soNgayDuDoan", "Số ngày dự đoán : " + daysBetween + " ngày");

				LocalDate startLocalDate = LocalDate.parse(startDateStr, formatter);
				LocalDate endLocalDate = LocalDate.parse(endDateStr, formatter);
				if (startDate.after(ngayHienTai) || endDate.after(ngayHienTai)) {
					model.addAttribute("sanPhamBanChay", "Thời gian phải bé hơn ngày hôm nay");

					return "views/quanly/xem/machineLearning"; // Trả về trang JSP
				}
				if (startDate.after(endDate)) {
					model.addAttribute("sanPhamBanChay", "Thời gian bắt đầu bé hơn ngày kết thúc");
					return "views/quanly/xem/machineLearning"; // Trả về trang JSP
				}
				// bằằng nhau
				if (endDate.equals(startDate)) {
					model.addAttribute("sanPhamBanChay", "Ngày bắt đầu phải khác ngày kết thúc");
					return "views/quanly/xem/machineLearning"; // Trả về trang JSP
				}

				chiTietHoaDon_time = getList_CTHD(startDate, endDate);
				if (chiTietHoaDon_time.size() <= 0) {
					model.addAttribute("sanPhamBanChay", "Không có hóa đơn nào trong thời gian này");
					return "views/quanly/xem/machineLearning"; // Trả về trang JSP
				}
				daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
//				   LocalDate day_min_hoaDonLocalDate = getMinDate_hoaDon().toInstant()            // Chuyển đổi Date sang Instant
//                           .atZone(ZoneId.systemDefault()) // Sử dụng múi giờ hệ thống
//                           .toLocalDate();               // Chuyển đổi Instant sang LocalDate
//				   LocalDate day_min_hoaDonLocalDate = getMinDate_hoaDon().toLocalDate(); // Chuyển đổi trực tiếp sang LocalDate

				Date sqlDate = getMinDate_hoaDon();
				LocalDate day_min_hoaDonLocalDate = LocalDate.of(sqlDate.getYear() + 1900, sqlDate.getMonth() + 1,
						sqlDate.getDate());

				// xét trường hợp ngày bắt đầu > ngày có hóa đơn đầu tiên
				// So sánh startLocalDate với day_min_hoaDon
				if (startLocalDate.isAfter(day_min_hoaDonLocalDate)) {
					// Nếu startLocalDate sau day_min_hoaDonLocalDate
					System.out.println("startLocalDate lớn hơn day_min_hoaDon, thực hiện hành động.");

					// Thực hiện hành động tại đây
				} else {
					// phải đổi ngày bắt đầu xét lại
					daysBetween = ChronoUnit.DAYS.between(day_min_hoaDonLocalDate, endLocalDate);
					System.out.println("startLocalDate không lớn hơn day_min_hoaDon.");
				}

				listNguyenLieu = gopNguyenLieuTheoSanPham(chiTietHoaDon_time);
				ngayHetNguyenLieu = duDoanNgayHetNguyenLieu(listNguyenLieu, daysBetween);

			}
			// trường hớp có 1 cái
			else {

				System.out.print("1 null");
				model.addAttribute("sanPhamBanChay", "Nhập đủ thời gian");
				return "views/quanly/xem/machineLearning"; // Trả về trang JSP

			}
		} // In ra toàn bộ các phần tử trong Map
		for (ChiTietHoaDon ct : chiTietHoaDon_time) {
			listMaSanPham.add(ct.getPk().getSANPHAM().getMASP());
			listMaSize.add(ct.getPk().getSIZE().getMASIZE());
			listSoLuong.add(ct.getSOLUONG());

		}

		for (int i = 0; i < chiTietHoaDon_time.size(); i++) {
			System.out.print(listMaSanPham.get(i));
			System.out.print(listMaSize.get(i));
			System.out.print(listSoLuong.get(i));
		}
		String spBanChayTrongTuongLai = RandomForestExample(listMaSanPham, listMaSize, listSoLuong, model);

		if (spBanChayTrongTuongLai != null) {
			model.addAttribute("sanPhamBanChay", "Sản phẩm bán chạy nhất trong tương lai: "
					+ layTenSP(spBanChayTrongTuongLai) + " size " + layTenSize(spBanChayTrongTuongLai));
			model.addAttribute("listCTNL_BanChay", get_1_CTNL(spBanChayTrongTuongLai));

		}

		model.addAttribute("soNgayDuDoan", "Số ngày dự đoán : " + daysBetween + " ngày");
		model.addAttribute("map_nguyenLieu_ngay", ngayHetNguyenLieu);
//		model.addAttribute("sp_it_ngay_nhat", "Sản phẩm sắp hết :" + get_1_NameNl(minKey) + " hết sau " + minValue + " ngày ");

		// lấy ra những sp có 2 ngày là hết hạn
		model.addAttribute("list_nl_sap_het", mapToString(get_listMap_spSapHet(ngayHetNguyenLieu, new BigDecimal(5))));
		model.addAttribute("listNL", getList_NL());
		// In ra nội dung của Map
		System.out.println("Nội dung của Map ngayHetNguyenLieu:");
		for (Map.Entry<String, BigDecimal> entry : ngayHetNguyenLieu.entrySet()) {
			System.out.println("Tên nguyên liệu: " + entry.getKey() + ", Giá trị: " + entry.getValue());
		}

		if (numberInput != null) {
			BigDecimal soNgay = new BigDecimal(numberInput);
			mapDuDoanSoNlCanNhapTrong_soNgay = duDoanSoNlCanNhapTrong_soNgay(ngayHetNguyenLieu, soNgay);
			// Giả sử bạn đang trong một phương thức và đã xử lý xong phần lọc Map
			System.out.println("locMap_theoSoNgay trước khi trả về:");
			for (Map.Entry<String, BigDecimal> entry : mapDuDoanSoNlCanNhapTrong_soNgay.entrySet()) {
				System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			}
			model.addAttribute("nhapSo", numberInput);
			model.addAttribute("soNgay_nhapThemNL", " " + numberInput + " ngày");
			model.addAttribute("mapDuDoanSoNlCanNhapTrong_soNgay", mapDuDoanSoNlCanNhapTrong_soNgay);

			model.addAttribute("startDate", startDateStr);
			model.addAttribute("endDate", endDateStr);

		} else {

			model.addAttribute("startDate", startDateStr);
			model.addAttribute("endDate", endDateStr);
		}
		// in ra số lượng sản phẩm từ hóa đơn
//		gopSoLuongSanPham(chiTietHoaDon_time);

		///

		///

		///

		///
		return "views/quanly/xem/machineLearning"; // Trả về trang JSP

	}

	public Map<String, BigDecimal> get_listMap_spSapHet(Map<String, BigDecimal> ngayHetNguyenLieu, BigDecimal soNgay) {
		return ngayHetNguyenLieu.entrySet().stream().filter(entry -> entry.getValue().compareTo(soNgay) < 0) // Lọc
																												// value
																												// >
																												// threshold
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); // Tạo map mới
	}

	public String mapToString(Map<String, BigDecimal> map) {
		StringBuilder result = new StringBuilder(); // Sử dụng StringBuilder để tạo chuỗi

		for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {

			result.append(get_1_NameNl(entry.getKey())).append(" còn ").append(entry.getValue()).append(" ngày - ");

		}

		return result.toString(); // Trả về chuỗi kết quả
	}

	public long soNgay_min_hienTai() {
		Session session = factory.getCurrentSession();
		System.out.println("Lấy ngày nhỏ nhất...");

		// Truy vấn lấy ngày nhỏ nhất từ bảng HoaDon
		Query query = session.createQuery("SELECT MIN(hd.NGAYLAP) FROM HoaDon hd");
		Date minDate = (Date) query.uniqueResult();

		if (minDate == null) {
			System.out.println("Không có dữ liệu ngày nhỏ nhất!");
			return -1; // Trả về -1 nếu không có dữ liệu
		}

		// Kiểm tra kiểu của đối tượng minDate
		LocalDate minLocalDate;
		if (minDate instanceof java.sql.Date) {
			minLocalDate = ((java.sql.Date) minDate).toLocalDate(); // Chuyển đổi nếu là java.sql.Date
		} else {
			minLocalDate = minDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi nếu là
																								// java.util.Date
		}

		LocalDate now = LocalDate.now();

		// Tính số ngày giữa ngày nhỏ nhất và hiện tại
		long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(minLocalDate, now);


		return daysBetween;
	}

	public String get_1_NameNl(String maNL) {
		for (NguyenLieu nl : getList_NL()) {
			if (nl.getMANL().equals(maNL)) {
				return nl.getTENNL();
			}
		}
		return maNL;
	}

	public Date convertStringToSqlDate(String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
		java.util.Date utilDate = formatter.parse(dateStr);
		return new java.sql.Date(utilDate.getTime());
	}

	public String RandomForestExample(ArrayList<String> maSanPham, ArrayList<String> maSize, ArrayList<Integer> soLuong,
			ModelMap model) {
		String spBanChay = "";
		try {
			// 1. Gộp dữ liệu theo nhãn
			HashMap<String, Integer> labelToQuantityMap = new HashMap<>();

			for (int i = 0; i < maSanPham.size(); i++) {
				String label = maSanPham.get(i) + "-Size" + maSize.get(i);
				labelToQuantityMap.put(label, labelToQuantityMap.getOrDefault(label, 0) + soLuong.get(i));
			}

			// 2. Tạo danh sách các thuộc tính
			ArrayList<Attribute> attributes = new ArrayList<>();
			attributes.add(new Attribute("soLuong")); // Thuộc tính số lượng

			// Tạo thuộc tính class (label)
			ArrayList<String> classValues = new ArrayList<>(labelToQuantityMap.keySet());
			Attribute classAttribute = new Attribute("label", classValues);
			attributes.add(classAttribute);

			// 3. Tạo tập dữ liệu
			Instances dataset = new Instances("Dataset", attributes, 0);
			dataset.setClassIndex(dataset.numAttributes() - 1); // Đặt thuộc tính cuối là class

			// Thêm dữ liệu vào tập huấn luyện
			for (String label : labelToQuantityMap.keySet()) {
				double[] instanceValues = { labelToQuantityMap.get(label), // Số lượng tổng cộng
						dataset.attribute("label").indexOfValue(label) // Nhãn
				};
				dataset.add(new DenseInstance(1.0, instanceValues));
			}

			// 4. Tạo và thiết lập mô hình RandomForest
			RandomForest randomForest = new RandomForest();
			String[] options = new String[] { "-I", "100", // Số lượng cây trong rừng
					"-depth", "10" // Chiều sâu tối đa của cây
			};
			randomForest.setOptions(options);

			// Huấn luyện mô hình
			randomForest.buildClassifier(dataset);

			// 5. In thông tin mô hình
			System.out.println("Mô hình RandomForest:");
			System.out.println(randomForest);

			// 6. Dự đoán nhãn phát triển mạnh nhất
			spBanChay = duDoanSPBanChay(dataset, randomForest);
			System.out.println("Nhãn được dự đoán phát triển mạnh nhất trong tương lai: " + spBanChay);
			System.out
					.println("Nguyên liệu được dự đoán phát triển mạnh nhất trong tương lai: " + get_1_CTNL(spBanChay));
			System.out.print("\n Số lượng " + duDoanSoLuong(dataset, randomForest));

//			for (ChiTietNguyenLieu ctnl : get_1_CTNL(bestLabel)) {
//				System.out.print(ctnl.getNGUYENLIEU().getTENNL() + "  " + ctnl.getSOLUONG()
//						+ ctnl.getNGUYENLIEU().getSLTON() + ctnl.getNGUYENLIEU().getDONVI());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return spBanChay;
	}

	public double duDoanSoLuong(Instances dataset, RandomForest randomForest) {
		double maxPrediction = Double.MIN_VALUE; // Biến lưu trữ số lượng bán lớn nhất
		double predictedQuantity = 0; // Biến lưu trữ số lượng bán dự đoán

		try {
			// Lặp qua từng instance (mẫu dữ liệu) trong tập dữ liệu
			for (Instance instance : dataset) {
				// Dự đoán nhãn của instance (sản phẩm)
				double predictedClassIndex = randomForest.classifyInstance(instance);
				System.out.print("\n Số lượngt " + predictedClassIndex);

				// Lấy giá trị số lượng bán của sản phẩm từ dữ liệu instance
				double quantity = instance.value(dataset.attribute("soLuong"));

				// Nếu số lượng bán của sản phẩm lớn hơn số lượng bán hiện tại, cập nhật giá trị
				// số lượng
				if (quantity > maxPrediction) {
					maxPrediction = quantity;
					predictedQuantity = quantity; // Cập nhật số lượng bán dự đoán
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // In ra lỗi nếu có
		}

		// Trả về số lượng bán dự đoán
		return predictedQuantity;
	}


	public String duDoanSPBanChay(Instances dataset, RandomForest randomForest) {
	    double giaTriMax = Double.MIN_VALUE; // Biến lưu trữ số lượng bán lớn nhất
	    String nhanDinhTotNhat = ""; // Biến lưu trữ nhãn của sản phẩm bán chạy nhất

	    try {
	        // Lặp qua tất cả các instance trong dataset
	        for (Instance instance : dataset) {
	            // Dự đoán nhãn của instance
	            double chiSoLopDuDoan = randomForest.classifyInstance(instance);
	            String nhanDuDoan = dataset.classAttribute().value((int) chiSoLopDuDoan);

	            // Xét giá trị số lượng
	            double soLuongDuDoan = instance.value(dataset.attribute("soLuong"));

	            // Nếu số lượng bán của sản phẩm lớn hơn số lượng bán lớn nhất, cập nhật nhãn
	            if (soLuongDuDoan > giaTriMax) {
	                giaTriMax = soLuongDuDoan;
	                nhanDinhTotNhat = nhanDuDoan; // Cập nhật nhãn sản phẩm bán chạy nhất
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // In ra lỗi nếu có
	    }

	    return nhanDinhTotNhat; // Trả về nhãn của sản phẩm bán chạy nhất
	}

	public Map<String, BigDecimal> duDoanSoNlCanNhapTrong_soNgay(Map<String, BigDecimal> ngayHetNguyenLieu,
			BigDecimal soNgay) {
		// Lọc ra các phần tử trong Map có giá trị <= soNgay
		Map<String, BigDecimal> locMap_theoSoNgay = new HashMap<>();

		Map<String, BigDecimal> map_tinhNL_theoSoNgay = new HashMap<>();
		for (Map.Entry<String, BigDecimal> entry : ngayHetNguyenLieu.entrySet()) {
			if (entry.getValue().compareTo(soNgay) <= 0) {
				locMap_theoSoNgay.put(entry.getKey(), entry.getValue());
			}
		}
		// Duyệt qua locMap_theoSoNgay để tính toán giá trị cho map_tinhNL_theoSoNgay
		for (Map.Entry<String, BigDecimal> entry : locMap_theoSoNgay.entrySet()) {
			// Lấy giá trị tồn kho (soLuongTon) cho mã nguyên liệu
			BigDecimal soLuongTon = get_TonKho_1NL(entry.getKey());
			
			
			if (entry.getValue().compareTo(BigDecimal.ZERO) != 0) {
				// Tính toán giá trị mới: (soLuongTon / dudoanNgayHet) * soNgay
				BigDecimal newValue = soLuongTon.divide(entry.getValue(), 2, RoundingMode.HALF_UP).multiply(soNgay);
				System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue() + " tiêu thu 1 ngay "
						+ soLuongTon.divide(entry.getValue(), 2, RoundingMode.HALF_UP) + " so ngay " + soNgay + " = "
						+ newValue);
				// Thêm vào map_tinhNL_theoSoNgay
				map_tinhNL_theoSoNgay.put(entry.getKey(), newValue);
			} else {
			    // Xử lý khi entry.getValue() bằng 0 (ví dụ: thông báo lỗi hoặc trả về giá trị mặc định)
			}

		}

		// Trả về Map đã lọc
		return map_tinhNL_theoSoNgay;
	}

	public BigDecimal get_TonKho_1NL(String maNL) {
		BigDecimal soLuongTon = new BigDecimal(0);
		for (NguyenLieu nl : getList_NL()) {
			if (nl.getMANL().equals(maNL)) {
				soLuongTon = nl.getSLTON();
				break;
			}
		}
		return soLuongTon;
	}

	public Map<String, BigDecimal> duDoanNgayHetNguyenLieu(Map<String, BigDecimal> danhSachNguyenLieu, long soNgay) {
		Map<String, BigDecimal> ngayHetNguyenLieu = new HashMap<>();
		for (Map.Entry<String, BigDecimal> entry : danhSachNguyenLieu.entrySet()) {
			String maNguyenLieu = entry.getKey();
			BigDecimal tongSoLuongTieuThu = entry.getValue();
			BigDecimal soLuongTon = new BigDecimal(0);

//	        // Tính tỷ lệ tiêu thụ hàng ngày
//	        BigDecimal tyLeTieuThuHangNgay = tongSoLuongTieuThu / soNgay;
			// Tính tỷ lệ tiêu thụ hàng ngày
			BigDecimal tyLeTieuThuHangNgay = tongSoLuongTieuThu.divide(BigDecimal.valueOf(soNgay),
					RoundingMode.HALF_UP);

			for (NguyenLieu nl : getList_NL()) {
				if (nl.getMANL().equalsIgnoreCase(maNguyenLieu)) {
					soLuongTon = nl.getSLTON();
					break;
				}
			}
			// Lấy số lượng tồn (giả sử bạn có phương pháp lấy tồn từ database hoặc API)

			// Tính số ngày còn lại
			BigDecimal soNgayConLai = BigDecimal.ZERO;
			if (tyLeTieuThuHangNgay.compareTo(BigDecimal.ZERO) > 0) { // Kiểm tra không chia cho 0
				soNgayConLai = soLuongTon.divide(tyLeTieuThuHangNgay, RoundingMode.HALF_UP);
			}
			// Lưu vào map
			ngayHetNguyenLieu.put(maNguyenLieu, soNgayConLai);

			String minKey = null;
			BigDecimal minValue = null;
			// In ra toàn bộ các phần tử trong Map
			for (Map.Entry<String, BigDecimal> entry1 : ngayHetNguyenLieu.entrySet()) {
				String maNguyenLieu1 = entry1.getKey();
				BigDecimal soNgayConLai1 = entry1.getValue();
				/*
				 * // In ra mã nguyên liệu và số ngày còn lại
				 * System.out.println("Mã nguyên liệu: " + maNguyenLieu1 + ", Số ngày còn lại: "
				 * + soNgayConLai1);
				 */
				// Tìm khóa có giá trị nhỏ nhất
				if (minValue == null || soNgayConLai1.compareTo(minValue) < 0) {
					minValue = soNgayConLai;
					minKey = maNguyenLieu;
				}
			}
		}
		return ngayHetNguyenLieu;
	}

//	public String soNgayHetNguyenLieu(ChiTietHoaDon chiTietHoaDon_time) {
//		
//	}

	public Map<String, Integer> gopSoLuongSanPham(List<ChiTietHoaDon> chiTietHoaDonList) {
		// Sử dụng một Map để gộp số lượng sản phẩm giống nhau (cả mã sản phẩm và mã
		// size)
		Map<String, Integer> productQuantityMap = new HashMap<>();

		for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
			SanPham sanPham = chiTiet.getPk().getSANPHAM();
			Size size = chiTiet.getPk().getSIZE(); // Lấy size của sản phẩm
			int soLuong = chiTiet.getSOLUONG();

			// Tạo một chuỗi nhận diện duy nhất cho sản phẩm dựa trên MASP và MASIZE
			String productKey = sanPham.getMASP() + "-" + size.getMASIZE();

			// Nếu sản phẩm đã có trong Map, cộng thêm số lượng
			if (productQuantityMap.containsKey(productKey)) {
				productQuantityMap.put(productKey, productQuantityMap.get(productKey) + soLuong);
			} else {
				productQuantityMap.put(productKey, soLuong);
			}
		}

		// In ra kết quả gộp
		for (Map.Entry<String, Integer> entry : productQuantityMap.entrySet()) {
			System.out.println("Mã sản phẩm + Mã size: " + entry.getKey() + ", Tổng số lượng: " + entry.getValue());
		}

		// Trả về Map chứa mã sản phẩm + mã size và tổng số lượng
		return productQuantityMap;
	}

	public Map<String, BigDecimal> gopNguyenLieuTheoSanPham(List<ChiTietHoaDon> chiTietHoaDon_time) {
		List<ChiTietNguyenLieu> chiTietNguyenLieuList = new ArrayList<>();

		// Lấy danh sách chi tiết nguyên liệu từ chi tiết hóa đơn
		chiTietNguyenLieuList = getList_CTNL_time(chiTietHoaDon_time);

		for (ChiTietNguyenLieu chiTiet : chiTietNguyenLieuList) {
			// nếu sanPham trong cthd bằng sanPham trong nguyên liệu)
			System.out.println("Mã nl2: " + chiTiet.getPk().getNGUYENLIEU().getMANL() + ", Mã sp: "
					+ chiTiet.getPk().getCT_SANPHAM().getSANPHAM().getMASP() + ", Mã size: "
					+ chiTiet.getPk().getCT_SANPHAM().getSIZE().getMASIZE() + ", SỐ luọng " + chiTiet.getSOLUONG());
		}
		// Tạo một Map để lưu trữ tổng số lượng theo mã nguyên liệu
		Map<String, BigDecimal> tongSoLuongMap = new HashMap<>();

		// Duyệt qua danh sách chi tiết nguyên liệu
		for (ChiTietNguyenLieu chiTiet : chiTietNguyenLieuList) {
			String maNguyenLieu = chiTiet.getPk().getNGUYENLIEU().getMANL();
			BigDecimal soLuong = chiTiet.getSOLUONG();

			// Sử dụng BigDecimal.ZERO làm giá trị mặc định
			BigDecimal tongSoLuongHienTai = tongSoLuongMap.getOrDefault(maNguyenLieu, BigDecimal.ZERO);

			// Cộng thêm số lượng hiện tại
			tongSoLuongMap.put(maNguyenLieu, tongSoLuongHienTai.add(soLuong));
		}

		// In kết quả
		for (Map.Entry<String, BigDecimal> entry : tongSoLuongMap.entrySet()) {
			System.out.println("Mã nguyên liệu: " + entry.getKey() + ", Tổng số lượng: " + entry.getValue());
		}

		// Trả về Map chứa mã sản phẩm + mã size và danh sách nguyên liệu với số lượng
		return tongSoLuongMap;
	}

	public String getName_NL(String maNL) {
		String tenNL = "";
		for (NguyenLieu nl : getList_NL()) {
			if (nl.getMANL().equals(maNL)) {
				tenNL = nl.getTENNL();
			}
		}
		return "ko tim được tên";
	}

	public String layTenSP(String chuoi) {
		String[] parts = chuoi.split("-");
		String maSanPham = ""; // "SP001"
		maSanPham = parts[0]; // "SP001"
		for (SanPham sp : getList_SP()) {
			if (sp.getMASP().equalsIgnoreCase(maSanPham))
				return sp.getTENSP();
		}
		return " lỗi lấy tên sản phẩm ";
	}

	public String layTenSize(String chuoi) {
		String[] parts = chuoi.split("-");
		if (parts.length > 1) {
			String maSizeMa = parts[1]; // "SizeL"
			String maSize = maSizeMa.substring(maSizeMa.length() - 1); // Lấy ký tự cuối của maSize (L)
			for (Size size : getList_size()) {
				if (size.getMASIZE().equalsIgnoreCase(maSize))
					return size.getTENSIZE();
			}
		}

		return " lỗi lấy size sản phẩm ";
	}

	public List<ChiTietNguyenLieu> get_1_CTNL(String bestLabel) {
		// Tách nhãn bằng dấu "-"
		String[] parts = bestLabel.split("-");
		String maSanPham = ""; // "SP001"
		String maSize = ""; // "L"
		List<ChiTietNguyenLieu> listCTNL = new ArrayList<>();
		ChiTietNguyenLieu tongCtnl = new ChiTietNguyenLieu();
		if (parts.length == 2) {
			maSanPham = parts[0]; // "SP001"
			maSize = parts[1].replace("Size", ""); // "L"

			// Hiển thị kết quả
			System.out.println("Mã sản phẩm: " + maSanPham);
			System.out.println("Mã size: " + maSize);
		} else {
			System.out.println("Định dạng nhãn không hợp lệ: " + bestLabel);
		}

		for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
			if (ctnl.getCT_SANPHAM().getSANPHAM().getMASP().equals(maSanPham)
					&& ctnl.getCT_SANPHAM().getSIZE().getMASIZE().equals(maSize)) {
				listCTNL.add(ctnl);

			}
		}
		return listCTNL;

	}

	public List<ChiTietHoaDon> getList_CTHD() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM ChiTietHoaDon");
		return query.list(); // Không cần đóng session

	}

	public List<Size> getList_size() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM Size");
		return query.list(); // Không cần đóng session

	}

	public Date getMinDate_hoaDon() {
		Session session = factory.getCurrentSession();

		Query query = session.createQuery("SELECT MIN(hd.NGAYLAP) FROM HoaDon hd");

		Date farthestPastDate = (Date) query.uniqueResult();

		return farthestPastDate;
	}

	public List<ChiTietHoaDon> getList_CTHD(Date startDate, Date endDate) {

		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		System.out.print("tuấn tuấn");
		// danh sachs hóa đơn theo thời gian
		Query query = session.createQuery("FROM HoaDon cthd WHERE cthd.NGAYLAP BETWEEN :startDate AND :endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		List<ChiTietHoaDon> cthd = new ArrayList<>();
		List<HoaDon> hoaDons_time = query.list();

		// lấy ra danh sách chi tiết hóa đơn theo hd(ID)
		for (HoaDon hd : hoaDons_time) {
			cthd.addAll(getList_CTHD_1HD(hd));
		}
		System.out.print("danh sachs háo đơn " + query.list().size());
		System.out.print("danh sachs chi tiết háo đơn " + cthd.size());

		return cthd; // Không cần đóng session
	}

	public List<ChiTietNguyenLieu> getList_CTNL_time(List<ChiTietHoaDon> listCthd_time) {

		List<ChiTietNguyenLieu> listCtnl_time = new ArrayList<>();
//		List<ChiTietNguyenLieu> listCtl = getList_CTNL();
//		
		for (ChiTietHoaDon chiTiet : listCthd_time) {
			System.out.println("Mã HD: " + chiTiet.getPk().getHOADON().getID() + ", Mã sp: "
					+ chiTiet.getPk().getSANPHAM().getMASP() + ", Mã size: " + chiTiet.getPk().getSIZE().getMASIZE());
		}

		for (ChiTietHoaDon cthd : listCthd_time) {

			for (ChiTietNguyenLieu ctnl : getList_CTNL()) {
				// nếu sanPham trong cthd bằng sanPham trong nguyên liệu)
				if (cthd.getPk().getSANPHAM().equals(ctnl.getCT_SANPHAM().getSANPHAM())) {

					listCtnl_time.add(ctnl);
				}
			}

		}

		for (ChiTietNguyenLieu chiTiet : getList_CTNL()) {
			// nếu sanPham trong cthd bằng sanPham trong nguyên liệu)
			System.out.println("Mã nl: " + chiTiet.getPk().getNGUYENLIEU().getMANL() + ", Mã sp: "
					+ chiTiet.getPk().getCT_SANPHAM().getSANPHAM().getMASP() + ", Mã size: "
					+ chiTiet.getPk().getCT_SANPHAM().getSIZE().getMASIZE());
		}

		return listCtnl_time; // Không cần đóng session
	}

	public List<ChiTietHoaDon> getList_CTHD_1HD(HoaDon HD) {
		List<ChiTietHoaDon> listCthd = new ArrayList<>();
		for (ChiTietHoaDon cthd : getList_CTHD()) {
			if (cthd.getPk().getHOADON().getID().equals(HD.getID())) {
				listCthd.add(cthd);
			}
		}
		return listCthd;
	}

//
//    public List<ChiTietHoaDon> getList_CTHD() {
//        Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
//        Query query = session.createQuery("FROM ChiTietHoaDon");
//        return query.list();  // Không cần đóng session
//    }

	public List<ChiTietSanPham> getList_CTSP() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM ChiTietSanPham");
		return query.list(); // Không cần đóng session
	}

	public List<SanPham> getList_SP() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM SanPham");
		return query.list(); // Không cần đóng session
	}

	public List<ChiTietNguyenLieu> getList_CTNL() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM ChiTietNguyenLieu");
		return query.list(); // Không cần đóng session
	}

	public List<NguyenLieu> getList_NL() {
		Session session = factory.getCurrentSession(); // Sử dụng getCurrentSession()
		Query query = session.createQuery("FROM NguyenLieu");
		return query.list(); // Không cần đóng session
	}
}