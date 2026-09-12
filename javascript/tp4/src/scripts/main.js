import { createRoot } from 'react-dom/client';

// import App from '../components/imageApp.component.jsx';
import App from '../components/convertAppV2.component.jsx';

const bootstrapReact = () => {
    const root = createRoot(document.getElementById('insertReactHere')); 
    const component = <App />;
    root.render(component);
}

bootstrapReact();

console.log('le bundle a été généré !');
