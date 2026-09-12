import '../assets/style/murImages.css';

const ImageDetails = ({image, texte, filter, filterFunc}) => {
    return (
        <div id="details">
            <img src={image} alt={texte}/>
            <div class="legende">{texte}</div>
            <input
                id="filtre" type="text" placeholder="filtre image..."
                value = {filter}
                onChange = {(event) => filterFunc(event.target.value)}
            />
        </div>
    );
}

export default ImageDetails;