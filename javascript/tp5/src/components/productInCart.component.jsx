import RemoveFromCart from './removeFromCart.component.jsx';
import Products from '../data/products.js';

const ProductInCart = ({Product, removeFromCart, updateQuantity}) => {
    return <div className="product">
        <div className="info">
            {Product.name}
        </div>
         <div className="imageProduit">
            <img alt={Product} src={Product.image}/>
        </div>
        <input
            type="number"
            value={Product.stock}
            onChange={(e) => updateQuantity(Product.id, parseInt(Products[Product.id-1].stock - e.target.value))}
            min="1"
            max={Products[Product.id-1].stock}
        />
        <RemoveFromCart Product={Product} removeFromCart={removeFromCart}/>
    </div>
}

export default ProductInCart;