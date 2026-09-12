import '../assets/style/murImages.css';

import dataImages from "../data/dataImages.js";
import ImageWall from './imageWall.component.jsx';
import ImageDetails from './imageDetails.component.jsx';
import { useState } from 'react';

const ImageApp = () => {
  const [image, setImage] = useState("../images/image5.jpg");
  const [texte, setTexte] = useState("la plus belle....");
  const [filterTexte, setFilterTexte] = useState('');
  const imageChanged = (newImage, newTexte) => {
    setImage(newImage);
    setTexte(newTexte);
  };
  return (
    <div><ImageWall images={dataImages} func={imageChanged} filter={filterTexte}/><ImageDetails image={image} texte={texte} filter={filterTexte} filterFunc={setFilterTexte}/></div>
  );
}


export default ImageApp;
