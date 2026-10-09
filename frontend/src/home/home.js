import ProductCatalog from '../products';

// Setup function to render our ProductCatalog on the homepage
const setup = function() {
    ReactDOM.render(<ProductCatalog products={products}></ProductCatalog>, document.getElementById('product-catalog'));
}

// Call our setup function
setup();