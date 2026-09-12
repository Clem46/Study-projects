import '../assets/style/murImages.css';

const ImageWall = ({images, func, filter}) => {
    const ImagesRes = images
        .filter((img) =>  img.texte.toLowerCase().includes(filter.toLowerCase()))
        .map((image) => (
            <img 
                src={image.image}
                alt={image.texte}
                key={image.image}
                onMouseOver={() => func(image.image, image.texte)}
            />
    ));

    return (
        <div id="mur">{ImagesRes}</div>
    );
}

export default ImageWall;