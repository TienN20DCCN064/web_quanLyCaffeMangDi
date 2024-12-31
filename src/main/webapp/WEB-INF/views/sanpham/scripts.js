const maloaiButtons = document.querySelectorAll('.category');
const products = document.querySelectorAll('.product');
let selectedProduct = null;

function toggleCheck(event) {
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


function addProduct(event) {
   const addButton = event.currentTarget;
   const productName = addButton.dataset.productName;
   const productSize = addButton.dataset.productSize;
   const productQuantity = addButton.dataset.productQuantity;
   const productPrice = addButton.dataset.productPrice;

   const checkButton = document.createElement("input");
   checkButton.type = "checkbox";
   checkButton.checked = true;


   // Check if a product with the same name and size is already in the cart
   const productDetails = document.querySelectorAll(".product-details .product-info");
   let foundDuplicate = false;
   for (let i = 0; i < productDetails.length; i++) {
      const nameSpan = productDetails[i].querySelector(".product-name");
      const sizeSpan = productDetails[i].querySelector(".product-size");
      const quantitySpan = productDetails[i].querySelector(".product-quantity");

      const isProductChecked = productDetails[i].getAttribute("data-product-choice");

      if (nameSpan.textContent === productName && sizeSpan.textContent === productSize && isProductChecked != false) {
         let newQuantity = parseInt(quantitySpan.textContent) + parseInt(productQuantity);
         quantitySpan.textContent = newQuantity;
         productDetails[i].dataset.productQuantity = newQuantity;
         foundDuplicate = true;
         break;
      }
   }

   // If no duplicate product was found, create a new product-info element
   if (!foundDuplicate) {
      const product = {
         name: productName,
         size: productSize,
         quantity: productQuantity,
         price: productPrice
      };

      const productDetails = document.querySelector('.product-details');

      const newProductInfo = document.createElement('div');

      newProductInfo.classList.add('product-info');
      newProductInfo.setAttribute("data-product-choice", "true");
      newProductInfo.setAttribute('data-product-name', product.name);
      newProductInfo.setAttribute('data-product-size', product.size);
      newProductInfo.setAttribute('data-product-quantity', product.quantity);
      newProductInfo.setAttribute('data-product-price', product.price);


      const nameSpan = document.createElement('span');
      nameSpan.classList.add('product-name');
      nameSpan.textContent = product.name;
      newProductInfo.appendChild(nameSpan);

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

}

products.forEach(product => {
   const image = product.querySelector('img');
   const sizeButtons = product.querySelector('.sizes');
   const quantityButtons = product.querySelector('.quantity-selector');
   const actionButtons = product.querySelector('.action-buttons');

   const quantityDisplay = product.querySelector('.number-display');
   let quantity = parseInt(quantityDisplay.getAttribute('data-quantity'), 10);

   const addButton = product.querySelector('.add-button');
   const minusButton = quantityButtons.querySelector('.minus-button');
   const plusButton = quantityButtons.querySelector('.plus-button');

   const updateQuantityDisplay = (newQuantity) => {
      quantityDisplay.textContent = newQuantity;
      quantityDisplay.setAttribute('data-quantity', newQuantity);
      addButton.dataset.productQuantity = newQuantity;
   };

   addButton.addEventListener('click', addProduct);

   minusButton.addEventListener('click', () => {
      if (quantity > 1) {
         quantity--;
         updateQuantityDisplay(quantity);
      }
   });

   plusButton.addEventListener('click', () => {
      quantity++;
      updateQuantityDisplay(quantity);
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

document.addEventListener("click", function (event) {
   const target = event.target;
   if (target.classList.contains("check-button")) {
      toggleCheck(event);
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