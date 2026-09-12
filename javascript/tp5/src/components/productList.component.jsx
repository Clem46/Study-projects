import ProductFilter from "./productFilter.component.jsx";
import ForSaleProduct from './forSaleProduct.component.jsx';
import { useState } from "react";

const ProductList = ({Product, addToCart}) => {
    const [filterTexte, setFilterTexte] = useState('');
    const filteredProduct = Product.filter(prod => {return prod.name.toLowerCase().includes(filterTexte.toLowerCase())});
    return <div className="productList">
        <h4>Boutique</h4>
        <ProductFilter FilterFunc={setFilterTexte}/>
        <div className="productsZone">
            {filteredProduct.map(prod => <ForSaleProduct Product={prod} addToCart={addToCart} key={prod.id}/>)}
        </div>
    </div>
}

export default ProductList;