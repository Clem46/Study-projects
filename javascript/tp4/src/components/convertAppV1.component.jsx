import '../assets/style/app.css';

import Currency from './currency.component.jsx';
import Data from '../data/currencies.js';
import { useState, useEffect, useRef } from 'react';

const ConvertAppV1 = () => {
    const [euro, setEuro] = useState('1');
    const [currencies, setCurrencies] = useState([]);
    const myRef = useRef(null);
    useEffect( () => {setCurrencies(Data), []})
    const changeEuro = () => {setEuro(myRef.current.value);}
    return <div className="app">
                <input ref={myRef}
                    id="" type="text" placeholde="euro"
                />
                <button onClick={changeEuro}>OK</button>
                {currencies.map(cur => <Currency monnaie = {cur} euro = {parseFloat(euro)} key={cur.code}/ >)}
            </div>
}

export default ConvertAppV1;