import panier from "../assets/images/panier.jpg";

const AddToCart = ({Product, addToCart}) => {
    
    return <img 
        className="button" src={panier} alt="panier"
        onClick={() => addToCart(Product)}
    />
}

export default AddToCart;