import '../assets/style/currency.css';

const Currency = ({monnaie, euro}) => {
    return <div className="currency">{euro * monnaie.rate}{monnaie.symbol}</div>
}

export default Currency;