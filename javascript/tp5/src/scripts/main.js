import { createRoot } from 'react-dom/client';

// import React components
import App from '../components/app.component.jsx';

import '../assets/style/app.css';
import '../assets/style/cart.css';
import '../assets/style/product.css';
import '../assets/style/productList.css';

/*
* create React root element and insert it into document
*/

const bootstrapReact =
   () => {
      const root = createRoot(document.getElementById('insertReactHere'));
      root.render(
         <App />
      );
   }

bootstrapReact();

console.log('le bundle a été généré !');
