<%-- <%@page import="ptithcm.entity.CongThuc"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.List" %>
<%@ page import="ptithcm.entity.ChiTietNguyenLieu" %>
<%@ page import="ptithcm.entity.NguyenLieu" %>


<%
  Connection conn = null;
  PreparedStatement pstmt = null;
  try {
	 Class.forName("com.mysql.cj.jdbc.Driver");


	 String url = "jdbc:mysql://localhost:3306/QLCF?useUnicode=true&characterEncoding=UTF-8";
    String username = "sa";
    String password = "1";
    conn = DriverManager.getConnection(url, username, password);
    
    String sql = "INSERT INTO HOADON (ID, NGAYLAP, TONGTIEN, PHANLOAI, MANV, MAKM) VALUES (?, GETDATE(), ?, 0, ?, ?)";
	String sql1 = "INSERT INTO CT_HOADON (ID, MASP, MASIZE, SOLUONG) VALUES (?, ?, ?, ?)";
    String sql2 = "UPDATE NGUYENLIEU SET SLTON = ? WHERE MANL = ?";
    String sql3 = "INSERT INTO HOADON (ID, NGAYLAP, TONGTIEN, PHANLOAI, MANV, MAKM) VALUES (?, GETDATE(), ?, 0, ?, NULL)";

    
    
	String code = request.getParameter("sql");
	

	if (code != null) {
		if (code.equals("1")) {
			String id = request.getParameter("id");
			String tongtienStr = request.getParameter("tongtien");
			int total = 0;
			if (tongtienStr != null && !tongtienStr.isEmpty()) {
			    total = Integer.parseInt(tongtienStr);
			}	
			String manv = request.getParameter("manv");
			String km = request.getParameter("idkm");
			
			if (km != "") {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, total);
				pstmt.setString(3, manv);
				pstmt.setString(4, km);
			} else {
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, id);
				pstmt.setInt(2, total);
				pstmt.setString(3, manv);
			}

			pstmt.executeUpdate();
			pstmt.close();
		}
		if (code.equals("2")) {
			String id = request.getParameter("ID");
			String masp = request.getParameter("MASP");
			String masize = request.getParameter("MASIZE");
			String soluong = request.getParameter("SOLUONG");
			
			System.out.println(id + " " + masp + " " + masize + " " + soluong);
			
			int soluongInt = 0;
			if (soluong != null && !soluong.isEmpty()) {
				soluongInt = Integer.parseInt(soluong);
			}
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2, masp);
			pstmt.setString(3, masize);
			pstmt.setInt(4, soluongInt);
			
			pstmt.executeUpdate();
			pstmt.close();
		}
		
		if (code.equals("3")) {
			String maNL = request.getParameter("maNL");
			String soluong = request.getParameter("slt");
			
			System.out.println(maNL + " " + soluong);
			
			float soluongFloat = 0;
			if (soluong != null && !soluong.isEmpty()) {
			    soluongFloat = Float.parseFloat(soluong);
			}

			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setFloat(1, soluongFloat);
			pstmt.setString(2, maNL);
			
			pstmt.executeUpdate();
			pstmt.close();
		}
	}
	
	conn.close();

    // Return a response to the client-side
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  } catch (SQLException e) {
    e.printStackTrace();
  } finally {
    try {
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
%>

<!DOCTYPE html>
<html>
	<c:set var="root" value="${pageContext.servletContext.contextPath}" />
	<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" type="image/png" href="${root}/resources/img/highlands coffee red logo.png"/>
	<title>Bán hàng</title>
	
	<link href="${root}/resources/sanpham/css/styles.css" rel="stylesheet"/>
	<script>
	    var ctnlList = [];
	    <% 
	        @SuppressWarnings("unchecked")
	        List<ChiTietNguyenLieu> listCTNL = (List<ChiTietNguyenLieu>) request.getAttribute("listCTNL");
	        for (ChiTietNguyenLieu ctnl : listCTNL) { 
	    %>
	    ctnlList.push({
	    	maSP: '<%= ctnl.getPk().getCT_SANPHAM().getSANPHAM().getMASP() %>',
	        maSize: '<%= ctnl.getPk().getCT_SANPHAM().getSIZE().getMASIZE() %>',
	        maNL: '<%= ctnl.getNGUYENLIEU().getMANL() %>',
	        soLuong: <%= ctnl.getSOLUONG() %>
	    });
	    <% } %>
	</script>
	<script>
	    var nlList = [];
	    <% 
	        @SuppressWarnings("unchecked")
	        List<NguyenLieu> listNL = (List<NguyenLieu>) request.getAttribute("listNL");
	        for (NguyenLieu nl : listNL) { 
	    %>
	    nlList.push({
	    	maNL: '<%= nl.getMANL() %>',
	        slt: <%= nl.getSLTON() %>,
	    });
	    <% } %>
	</script>
	<script>
	    var ctList = [];
	    <% 
	        @SuppressWarnings("unchecked")
	        List<CongThuc> listCT = (List<CongThuc>) request.getAttribute("listCT");
	        for (CongThuc ct : listCT) { 
	    %>
	    ctList.push({
	    	maCT: '<%= ct.getMACT() %>',
	        congThuc: '<%= ct.getCONGTHUC() %>'
	    });
	    <% } %>
	</script>
	</head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>	
<body>
<div class="none-display">
  <c:forEach var="ctkm" items="${listCTKM}">
    <c:if test="${makm == ctkm.pk.getKHUYENMAI().getMAKM()}">
      <p class="noDP" data-sales-masp="${ctkm.pk.getSANPHAM().getMASP()}" data-sales-size="${ctkm.pk.getSIZE().getMASIZE()}" data-sales-pt="${ctkm.PHANTRAM}"></p>
    </c:if>
  </c:forEach>
</div>
<div class="left-column">
  <div class="categories">
    <div class="category active" data-maloai="all">All</div>
    <c:forEach var="loaiSP" items="${listLSP}">
      <div class="category" data-maloai="${loaiSP.MALOAI}">${loaiSP.TENLOAI}</div>
    </c:forEach>
  </div>
  <hr>
  <div id="product-container">

    <c:forEach var="sanpham" items="${listSP}">
      <div class="product" data-maloai="${sanpham.LOAISP.MALOAI}">
        <img src="${root}/resources/img/sanpham/${sanpham.HINHANH}" alt="${sanpham.TENSP}" />
        <h3>${sanpham.TENSP}</h3>
      <!--   button chọn mã size -->
        <div class="sizes">
          <c:forEach var="ctsp" items="${listCTSP}">
            <c:if test="${sanpham.MASP == ctsp.getSANPHAM().getMASP() && ctsp.getSIZE().getMASIZE() != 'K'}">
              <button class="size" data-size="${ctsp.getSIZE().getMASIZE()}" data-price="${ctsp.GIAHIENTHOI}">${ctsp.getSIZE().getMASIZE()}</button>
            </c:if>
          </c:forEach>
        </div>
        <div class="quantity-selector">
          <button class="minus-button">-</button>
          <div class="number-display" data-quantity="1">1</div>
          <button class="plus-button">+</button>
        </div>
       <!--  button thêm vào giỏ hàng  -->
        <div class="action-buttons" data-test="${sanpham.TENSP}">
        
          <c:forEach var="ctsp" items="${listCTSP}">
            <c:if test="${sanpham.MASP == ctsp.getSANPHAM().getMASP()}">
            
            
              <c:if test="${ctsp.getSIZE().getMASIZE() == 'K'}">
                <button class="add-button" data-product-id="${sanpham.MASP}" data-product-name="${sanpham.TENSP}" data-product-size="K" data-product-quantity="1" data-product-price="${ctsp.GIAHIENTHOI}" data-product-max="1000" data-product-ct="${ctsp.getCONGTHUC().getMACT()}">Add</button>
              </c:if>
        
              <c:if test="${ctsp.getSIZE().getMASIZE() == 'S'}">
                <button class="add-button" data-product-id="${sanpham.MASP}" data-product-name="${sanpham.TENSP}" data-product-size="S" data-product-quantity="1" data-product-price="${ctsp.GIAHIENTHOI}" data-product-max="1000" data-product-ct="${ctsp.getCONGTHUC().getMACT()}">Add</button>
              </c:if>
              
            </c:if>
          </c:forEach>
          
          
          <button class="reset-button">Reset</button>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
<div class="right-column">
  <div class="product-details">
    <h3>Chi tiết sản phẩm</h3>
  </div>
  <div class="total-cost-box">
    <p>Tổng cộng:</p>
    <p id="total-cost-value"></p>
  </div>
  <div class="confirm-button" data-access-hd="${nextID}" data-access-nv="${manv}" data-access-km="${makm}">
    <button id="confirm-btn">Xác nhận</button>
  </div>
</div>
<div id="overlay">
  <div id="left-section">
    <h2>ĐƠN ĐẶT HÀNG</h2>
    <h3 id="mahd"></h3>
    <div class="product-how"></div>
  </div>
  <div id="right-section">
    <h4 style="text-align: center;">HIGHLANDS COFFEE</h4>
    <p style="text-align: center;">331 Le Van Viet, Hiep Phu W, Dis 9</p>
    <p style="text-align: center;">Tel: (84-28) 7108 4949</p>
    <p id="hd-nv" style="text-align: center;"></p>
    <h2 style="text-align: center;">*Receipt*</h2>
    <p style="text-align: center;">---------------------------------------------</p>
    <div class="bill-product"></div>
    <p style="text-align: center;">---------------------------------------------</p>
    <div class="bill-total"></div>
    <p style="text-align: center;">---------------------------------------------</p>
    <p style="text-align: center;">*Chia se y kien cua ban voi chung toi*</p>
    <p style="text-align: center;">customerservice@highland</p>
  </div>
  <button id="close-overlay">Close</button>
</div>
	
<script type="text/javascript">
const maloaiButtons = document.querySelectorAll('.category');
const kmList = document.querySelectorAll(".none-display .noDP");
const products = document.querySelectorAll('.product');
const confirmButton = document.querySelector('.confirm-button');
let selectedProduct = null;
let total = 0;
let nop = 0;
let nextID = confirmButton.dataset.accessHd;
const nvID = confirmButton.dataset.accessNv;
let makm = confirmButton.dataset.accessKm;

nextID = parseInt(nextID) + 1;

let paymentDisplayed = false;

function sendCTSP() {
    const productDetails = document.querySelectorAll(".product-details .product-info");
    for (let i = 0; i < productDetails.length; i++) {
        const isProductChecked = productDetails[i].dataset.productChoice;
        if ((isProductChecked) === "true") {
            const masp = productDetails[i].dataset.productId;
            const size = productDetails[i].dataset.productSize;
            const quantity = productDetails[i].dataset.productQuantity;

            setTimeout(function() {
                $.ajax({
                    type: "POST",
                    url: "viewSP.htm",
                    data: {
                        sql: 2,
                        ID: nextID,
                        MASP: masp,
                        MASIZE: size,
                        SOLUONG: quantity
                    },
                    success: function(response) {
                        console.log("ctsp");
                    },
                    error: function(xhr, status, error) {
                        console.error(error);
                    }
                });
            }, 500);
        }
    }
}

function sendDataToServer() {
    $.ajax({
        type: "POST",
        url: "viewSP.htm",
        data: {
            sql: 1,
            id: nextID,
            manv: nvID,
            tongtien: total,
            idkm: makm
        },
        success: function(response) {
            console.log("dawuhawidh");
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function sendNL() {
    for (let i = 0; i < nlList.length; i++) {
        setTimeout(function() {
            $.ajax({
                type: "POST",
                url: "viewSP.htm",
                data: {
                    sql: 3,
                    maNL: nlList[i].maNL,
                    slt: nlList[i].slt
                },
                success: function(response) {
                    // Handle the response from the server-side
                    console.log("nl has been save");
                },
                error: function(xhr, status, error) {
                    // Handle any errors that occur during the AJAX call
                    console.error(error);
                }
            });
        }, 500);
    }
}

function toggleCheck(event) {
    nop -= 1;

    const paymentButton = document.getElementById('payment-btn');
    if (nop == 0 && paymentButton != null) paymentButton.style.display = 'none';


    const checkButton = event.target;
    const productInfo = checkButton.closest(".product-info");
    const isChecked = checkButton.checked;
    productInfo.setAttribute("data-product-choice", isChecked);

    filterList = ctnlList.filter(item => item.maSP === productInfo.dataset.productId && item.maSize === productInfo.dataset.productSize);

    if (filterList.length != 0) {
        for (let j = 0; j < filterList.length; j++) {
            for (let i = 0; i < nlList.length; i++) {
                if (filterList[j].maNL === nlList[i].maNL) {
                    nlList[i].slt += parseInt(productInfo.dataset.productQuantity) * filterList[j].soLuong;
                    break;
                }
            }
        }
    }

    console.log(nlList);

    if (isChecked) {
        checkButton.classList.remove("unchecked");
        productInfo.setAttribute("data-product-choice", "true");
    } else {
        checkButton.classList.add("unchecked");
        productInfo.setAttribute("data-product-choice", "false");
    }
}

function toggleCheckVer1(event) {
    const checkButton = event.target;
    const productInfo = checkButton.closest(".product-info");
    let isChecked = productInfo.getAttribute("data-product-choice") === "true";

    if (isChecked) {
        checkButton.classList.add("unchecked");
        isChecked = false;
    } else {
        checkButton.classList.remove("unchecked");
        isChecked = true;
    }

    productInfo.setAttribute("data-product-choice", isChecked);
}

function payment() {
    let subTotal = 0;

    const productDetails = document.querySelectorAll(".product-details .product-info");
    const totalDisplay = document.getElementById("total-cost-value");
    for (let i = 0; i < productDetails.length; i++) {
        const isProductChecked = productDetails[i].dataset.productChoice;
        if ((isProductChecked) === "true") {
            const price = productDetails[i].dataset.productPrice;
            const quantity = productDetails[i].dataset.productQuantity;
            subTotal += parseInt(productDetails[i].dataset.productPrice, 10) * parseInt(productDetails[i].dataset.productQuantity, 10);
        }
    }

    total = subTotal;
    totalDisplay.textContent = subTotal.toString();
}

function addProduct(event) {
    const addButton = event.currentTarget;
    const productName = addButton.dataset.productName;
    const productSize = addButton.dataset.productSize;
    const productQuantity = addButton.dataset.productQuantity;
    let productPrice = addButton.dataset.productPrice;
    const productID = addButton.dataset.productId;
    const productCT = addButton.dataset.productCt;
    let qtyArray = []

    filterList = ctnlList.filter(item => item.maSP === addButton.dataset.productId && item.maSize === addButton.dataset.productSize);
    filterList.sort((a, b) => b.soLuong - a.soLuong);

    for (let j = 0; j < filterList.length; j++) {
        nlSelected = nlList.find(item => item.maNL === filterList[j].maNL);
        qtyArray.push(parseInt(nlSelected.slt / filterList[j].soLuong));
    }


    if (qtyArray.length != 0) addButton.setAttribute("data-product-max", parseInt(qtyArray[0]).toString());

    for (let j = 0; j < filterList.length; j++) {
        for (let i = 0; i < nlList.length; i++) {
            if (filterList[j].maNL === nlList[i].maNL) {
                nlList[i].slt -= parseInt(productQuantity) * filterList[j].soLuong;
                break;
            }
        }
    }

    console.log(nlList);


    for (let j = 0; j < kmList.length; j++) {
        const masp = kmList[j].dataset.salesMasp;
        const size = kmList[j].dataset.salesSize;
        const pt = kmList[j].dataset.salesPt;

        if (productID == masp && size == productSize) {
            let tmp = 0;
            tmp = parseFloat(1 - parseInt(pt) / 100) * parseFloat(productPrice);
            productPrice = tmp.toString();
            break;
        }
    }

    const checkButton = document.createElement("input");
    checkButton.type = "checkbox";
    checkButton.checked = true;

    const productDetails = document.querySelectorAll(".product-details .product-info");
    let foundDuplicate = false;
    for (let i = 0; i < productDetails.length; i++) {
        const nameSpan = productDetails[i].querySelector(".product-name");
        const sizeSpan = productDetails[i].querySelector(".product-size");
        const quantitySpan = productDetails[i].querySelector(".product-quantity");
        const detailsSpan = productDetails[i].querySelector(".price-detail");

        const isProductChecked = productDetails[i].dataset.productChoice;

        console.log(nameSpan.textContent.split('[')[0]);

        if (nameSpan.textContent.split('[')[0] === productName && sizeSpan.textContent === productSize && isProductChecked === "true") {
            let newQuantity = parseInt(quantitySpan.textContent) + parseInt(productQuantity);
            if (newQuantity <= parseInt(addButton.dataset.productMax)) {
                quantitySpan.textContent = newQuantity;
                productDetails[i].dataset.productQuantity = newQuantity;
                detailsSpan.textContent = '[' + parseInt(productDetails[i].dataset.productPrice) + ' x ' + newQuantity + ' = ' + parseInt(productDetails[i].dataset.productPrice) * parseInt(newQuantity) + ']';
            }
            foundDuplicate = true;
        }
    }

    if (!foundDuplicate && parseInt(productQuantity) <= parseInt(addButton.dataset.productMax)) {
        nop += 1;

        const product = {
            name: productName,
            size: productSize,
            quantity: productQuantity,
            price: productPrice,
            id: productID,
            ct: productCT
        }

        const productDetails = document.querySelector('.product-details');

        const newProductInfo = document.createElement('div');

        newProductInfo.classList.add('product-info');
        newProductInfo.setAttribute("data-product-choice", "true");
        newProductInfo.setAttribute("data-product-id", product.id);
        newProductInfo.setAttribute('data-product-name', product.name);
        newProductInfo.setAttribute('data-product-size', product.size);
        newProductInfo.setAttribute('data-product-quantity', product.quantity);
        newProductInfo.setAttribute('data-product-price', product.price);
        newProductInfo.setAttribute('data-product-ct', product.ct);


        const nameSpan = document.createElement('span');
        nameSpan.classList.add('product-name');
        nameSpan.textContent = product.name;
        newProductInfo.appendChild(nameSpan);

        const detailsSpan = document.createElement('span');
        detailsSpan.classList.add('price-detail');
        detailsSpan.textContent = '[' + parseInt(product.price) + ' x ' + product.quantity + ' = ' + parseInt(product.price) * parseInt(product.quantity) + ']';
        nameSpan.appendChild(document.createElement('br'));
        nameSpan.appendChild(detailsSpan);

        const sizeSpan = document.createElement('span');
        sizeSpan.classList.add('product-size');
        sizeSpan.textContent = product.size;
        newProductInfo.appendChild(sizeSpan);

        const quantitySpan = document.createElement('span');
        quantitySpan.classList.add('product-quantity');
        quantitySpan.textContent = product.quantity;
        newProductInfo.appendChild(quantitySpan);

        const checkButton = document.createElement("button");
        checkButton.classList.add("check-button");
        checkButton.setAttribute("aria-pressed", "true");
        checkButton.innerHTML = "✓";
        newProductInfo.appendChild(checkButton);

        productDetails.appendChild(newProductInfo);
    }

    payment();

}

products.forEach(product => {
    const image = product.querySelector('img');
    const sizeButtons = product.querySelector('.sizes');
    const quantityButtons = product.querySelector('.quantity-selector');
    const actionButtons = product.querySelector('.action-buttons');

    const quantityDisplay = product.querySelector('.number-display');
    let quantity = parseInt(quantityDisplay.getAttribute('data-quantity'), 10);

    const addButton = product.querySelector('.add-button');
    const resetButton = product.querySelector('.reset-button');
    const minusButton = quantityButtons.querySelector('.minus-button');
    const plusButton = quantityButtons.querySelector('.plus-button');
    let quantityTimer = null;

    const updateQuantityDisplay = (newQuantity) => {
        quantityDisplay.textContent = newQuantity;
        quantityDisplay.setAttribute('data-quantity', newQuantity);
        addButton.dataset.productQuantity = newQuantity;
    };

    addButton.addEventListener('click', addProduct);

    resetButton.addEventListener('click', () => {
        updateQuantityDisplay(1);
        quantity = 1;
    });

    minusButton.addEventListener('click', () => {
        if (quantity > 1) {
            quantity--;
            updateQuantityDisplay(quantity);
        }
    });

    minusButton.addEventListener('mousedown', () => {
        quantityTimer = setTimeout(() => {
            quantityTimer = setInterval(() => {
                if (quantity > 1) {
                    quantity--;
                    updateQuantityDisplay(quantity);
                }
            }, 200);
        }, 500);
    });

    minusButton.addEventListener('mouseup', () => {
        clearInterval(quantityTimer);
    });

    plusButton.addEventListener('click', () => {
        if (quantity < parseInt(addButton.dataset.productMax)) {
            quantity++;
            updateQuantityDisplay(quantity);
        }
    });

    plusButton.addEventListener('mousedown', () => {
        quantityTimer = setTimeout(() => {
            quantityTimer = setInterval(() => {
                if (quantity < parseInt(addButton.dataset.productMax)) {
                    quantity++;
                    updateQuantityDisplay(quantity);
                }
            }, 174);
        }, 400);
    });

    plusButton.addEventListener('mouseup', () => {
        clearInterval(quantityTimer);
    });

    product.addEventListener('click', () => {
        quantityButtons.addEventListener('click', event => {
            event.stopPropagation();
        });

        actionButtons.addEventListener('click', event => {
            event.stopPropagation();
        });

        sizeButtons.addEventListener('click', event => {
            const selectedSize = event.target;
            const selectedPrice = selectedSize.dataset.price;
            addButton.dataset.productSize = selectedSize.textContent;
            addButton.dataset.productPrice = selectedPrice;
            event.stopPropagation();
        });

        if (selectedProduct && selectedProduct !== product) {
            selectedProduct.querySelector('img').style.display = 'block';
            selectedProduct.querySelector('.sizes').style.display = 'none';
            selectedProduct.querySelector('.quantity-selector').style.display = 'none';
            selectedProduct.querySelector('.action-buttons').style.display = 'none';
            selectedProduct.querySelector('.number-display').setAttribute('data-quantity', 1);
            updateQuantityDisplay(1);
            quantity = 1;
        }

        image.style.display = image.style.display === 'none' ? 'block' : 'none';

        if (image.style.display === 'block') {
            sizeButtons.style.display = 'none';
            quantityButtons.style.display = 'none';
            actionButtons.style.display = 'none';
        } else {
            sizeButtons.style.display = 'flex';
            quantityButtons.style.display = 'flex';
            actionButtons.style.display = 'flex';
        }

        selectedProduct = product;
    });
});

document.addEventListener("click", function(event) {
    const target = event.target;
    if (target.classList.contains("check-button")) {
        toggleCheck(event);
        payment();
    }
});

document.getElementById("confirm-btn").addEventListener("click", function() {
    if (!paymentDisplayed && nop != 0) {
        totalDisplay = document.getElementById('total-cost-value');
        let paymentBtn = document.createElement("button");
        paymentBtn.id = "payment-btn";
        paymentBtn.innerText = "Thanh toán";
        document.querySelector(".confirm-button").appendChild(paymentBtn);
        paymentBtn.style.display = 'inline-block';

        paymentBtn.addEventListener("click", () => {
        	sendDataToServer();
        	
        	setTimeout(function() {
                nextID += 1;
            }, 1000);
        	
            sendCTSP();
            
            sendNL();            

            const overlay = document.querySelector('#overlay');
            const productDetails = document.querySelectorAll(".product-details .product-info");
            const billProduct = document.querySelector(".bill-product");
            const howProduct = document.querySelector(".product-how");
            const billTotal = document.querySelector(".bill-total");
            const mahd = document.getElementById('mahd');
            const hdnv = document.getElementById('hd-nv');
            const totalCost = document.getElementById('total-cost-value');
            const line4 = document.createElement('span');
            const line5 = document.createElement('span');

            line4.textContent = "Total";
            line5.textContent = totalCost.textContent;

            mahd.textContent = '#' + nextID;
            hdnv.textContent = '#' + nextID + ' - ' + nvID;

            overlay.style.display = 'block';

            for (let i = 0; i < productDetails.length; i++) {
                if (productDetails[i].dataset.productChoice === "true") {
                    const handle = document.createElement('div');
                    handle.classList.add('handle');
                    const line = document.createElement('span');
                    const line1 = document.createElement('span');
                    const line3 = document.createElement('span');

                    line1.style.marginRight = '10px';

                    const info = document.createElement('div');
                    const line2 = document.createElement('div');

                    line.textContent = productDetails[i].dataset.productName + ' ' + productDetails[i].dataset.productSize;
                    line1.textContent = productDetails[i].dataset.productQuantity;
                    line3.textContent = parseInt(productDetails[i].dataset.productQuantity) * parseInt(productDetails[i].dataset.productPrice);
                    line2.textContent = productDetails[i].dataset.productName + '-' + productDetails[i].dataset.productSize + ' SL: ' + productDetails[i].dataset.productQuantity;
                    info.textContent = '-> Công thức: none';
                    for (let j = 0; j < ctList.length; j++) {
                        if (ctList[j].maCT === productDetails[i].dataset.productCt) {
                            info.textContent = '-> Công thức: ' + ctList[j].congThuc;
                            break;
                        }
                    }

                    handle.appendChild(line);
                    handle.appendChild(line1);
                    handle.appendChild(line3);

                    billTotal.appendChild(line4);
                    billTotal.appendChild(line5);

                    billProduct.appendChild(handle);
                    howProduct.appendChild(line2);
                    howProduct.appendChild(info);
                }
            }
            

            const productDetailsContainer = document.querySelector('.product-details');
            while (productDetailsContainer.firstChild) {
                productDetailsContainer.removeChild(productDetailsContainer.firstChild);
            }
            productDetailsContainer.innerHTML = '<h3>Chi tiết sản phẩm</h3>';


            totalDisplay.textContent = "0";
            paymentBtn.style.display = 'none';
            nop = 0;
        });

        paymentDisplayed = true;
    } else if (paymentDisplayed && nop != 0) {
        const paymentBtn = document.getElementById("payment-btn");
        paymentBtn.style.display = 'inline-block';
    }
});

maloaiButtons.forEach(button => {
    button.addEventListener('click', () => {
        const maloai = button.dataset.maloai;

        products.forEach(product => {
            if (maloai === 'all' || product.dataset.maloai === maloai) {
                product.style.display = 'block';
            } else {
                product.style.display = 'none';
            }
        });
    });
});

const overlay = document.querySelector('#overlay');

const closeButton = document.querySelector('#close-overlay');

closeButton.addEventListener('click', function() {
    overlay.style.display = 'none';
    const billProduct = document.querySelector(".bill-product");
    const howProduct = document.querySelector(".product-how");
    const billTotal = document.querySelector(".bill-total");

    while (billProduct.firstChild) {
        billProduct.removeChild(billProduct.firstChild);
    }
    while (howProduct.firstChild) {
        howProduct.removeChild(howProduct.firstChild);
    }
    while (billTotal.firstChild) {
        billTotal.removeChild(billTotal.firstChild);
    }
});

</script>
</body>
</html> --%>