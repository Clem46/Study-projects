import poubelle from "../assets/images/poubelle.jpg";

const RemoveFromCart = ({Product, removeFromCart}) => {
    
    return <img 
        className="button" src={poubelle} alt="poubelle"
        onClick={() => removeFromCart(Product)}
    />
}

export default RemoveFromCart;