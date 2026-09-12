const ProductFilter = ({FilterFunc}) => {
    return <div className="filter">
        <input 
            type="text" placeholder="filtrer les produits"
            onChange = {(event) => FilterFunc(event.target.value)}
        />
    </div>
}

export default ProductFilter;