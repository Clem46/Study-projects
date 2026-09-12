import '../assets/style/app.css';

import Currency from './currency.component.jsx';
import Data from '../data/currencies.js';
import { useState, useEffect} from 'react';

const ConvertAppV2 = () => {
    const [euro, setEuro] = useState('1');
    const [currencies, setCurrencies] = useState([]);
    useEffect( () => {setCurrencies(Data), []})
    return <div className="app">
                <input
                    id="euro" type="text" placeholde="euro"
                    onChange={(event) => setEuro(event.target.value)}
                />
                {currencies.map(cur => <Currency monnaie = {cur} euro = {parseFloat(euro)} key={cur.code}/ >)}
            </div>
}

export default ConvertAppV2;