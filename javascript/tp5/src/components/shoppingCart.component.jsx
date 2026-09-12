import ProductInCart from './productInCart.component.jsx';
import { useState, useEffect } from 'react';

const ShoppingCart = ({ProductInCartList, removeFromCart, updateQuantity, ProductCopy}) => {
    const [poidsTotal, setPoidsTotal] = useState(0);
    const [prixTotal, setPrixTotal] = useState(0);
    useEffect(() => {
        const NvPoidsTotal = ProductInCartList.reduce((res1,prod) => res1 + (prod.weight * prod.stock), 0);
        const NvPrixTotal = ProductInCartList.reduce((res2, prod) => res2 + (prod.stock * prod.price), 0);
        setPoidsTotal(NvPoidsTotal);
        setPrixTotal(NvPrixTotal);

    }, ProductCopy)
    return <div className="cart">
        <h4>Panier</h4>
        <div className="weight">
            poids total {poidsTotal}
        </div>  
        <div className="productsZone">
            {ProductInCartList.map(prod => <ProductInCart Product={prod} removeFromCart={removeFromCart} updateQuantity={updateQuantity} key={prod.id}/>)}
        </div>
        <div className="total">
            total comande : <div className="price">{prixTotal}</div>
        </div>
    </div>
}

export default ShoppingCart;