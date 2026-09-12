import AddToCart from './addToCart.component.jsx'

const ForSaleProduct =({Product, addToCart}) => {
    return <div className="product">
        <div className="info">
            <div className="name">{Product.name}</div> 
            <div className="petit">{Product.description}</div>
            <div className="weight"> {Product.weight}</div>
        </div>
        <div className="imageProduit"><img alt={Product} src={Product.image}/></div>
        <div className="stock">qté<br/>{Product.stock}</div>
        <div className="price">{Product.price}</div>
        <AddToCart addToCart={addToCart} Product={Product} key={Product.id}/>
    </div>
}

export default ForSaleProduct;