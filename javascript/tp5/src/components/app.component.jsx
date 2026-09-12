import ProductList from './productList.component.jsx';
import ShoppingCart from './shoppingCart.component.jsx';
import Product from '../data/products.js';
import { useState } from 'react';

const App = () => {
  const [productInCartList, setProductInCartList] = useState([]);
  const [ProductCopy, setProductCopy] = useState(Product);

  const AjouterAuPanier = (prod) => {
      const exists = productInCartList.find((item) => item.id === prod.id);
      if (!exists) {
        setProductCopy(ProductCopy.map((p) =>  p.id === prod.id ? {...p, stock: Product[prod.id-1].stock-1  }:p));
        setProductInCartList([...productInCartList, {...prod, stock: 1}]);
    }
  }
  
  const RetirerDuPanier = (prod) => {
      const newProductInCartList = productInCartList.filter(product => product.id !== prod.id);
      setProductInCartList(newProductInCartList);
      setProductCopy(ProductCopy.map((p) =>  p.id === prod.id ? {...p, stock: Product[prod.id-1].stock}:p));
  }

  const UpdateQuantity = (Id, newQuantity) => {
      setProductCopy(ProductCopy.map((p) => p.id === Id ? { ...p, stock: newQuantity } : p));
      setProductInCartList(prevCart => prevCart.map((prod) => prod.id === Id ? { ...prod, stock: Product[Id-1].stock-newQuantity } : prod));
    };

  return (
    <div>
      <ProductList Product={ProductCopy} addToCart={AjouterAuPanier}/>
      <ShoppingCart ProductInCartList={productInCartList} removeFromCart={RetirerDuPanier} updateQuantity={UpdateQuantity} ProductCopy={ProductCopy}/>
    </div>
  );
}
export default App;
