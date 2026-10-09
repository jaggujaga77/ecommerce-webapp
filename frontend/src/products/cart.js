import React, { useState, useMemo } from 'react';
import './style.css';
import {Button} from 'antd';
import { getAllProducts } from '../util/APIUtils';
import { withRouter } from 'react-router-dom';
import '../poll/PollList.css';

class Cart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            last: true,
            isLoading: false
        };
        this.addToCart = this.addToCart.bind(this);
    }

    //load product list 
    addToCart() {
        let promise;
        console.log("coming here: ");
        if(this.props.currentUser) {
            promise = getAllProducts();
            console.log(promise);
        }

        if(!promise) {
            return;
        }

        this.setState({
            isLoading: true
        });

        promise            
        .then(response => {
            console.log("response : ",promise,"products: ",response[0]);
            this.setState({
                products: response,
                page: response.page,
                size: response.size,
                totalElements: response.totalElements,
                totalPages: response.totalPages,
                last: response.last,
                isLoading: false
            })
        }).catch(error => {
            this.setState({
                isLoading: false
            })
        });  
        
    }
    componentDidMount() {
        this.loadProductList();
    }

    componentDidUpdate(nextProps) {
        if(this.props.isAuthenticated !== nextProps.isAuthenticated) {
            // Reset State
            this.setState({
                products: [],
                page: 0,
                size: 10,
                totalElements: 0,
                totalPages: 0,
                last: true,
                isLoading: false
            });    
            this.loadProductList();
        }
    }

     // Creates a ProductTile object to be rendered
     renderTile = (current_item) => {
        return <ProductTile product={current_item}></ProductTile>;
    }
    render() {
        let tiles = [];
        console.log("products: ", this.state.products);
        if(!this.state.isLoading && this.state.products.length === 0){
            return (<div className="no-polls-found">
                <span>No Products Found.</span>
            </div> ); 
           
        }
        else {
        for (const current_item of this.state.products) {
            tiles.push(this.renderTile(current_item));
        }
        return (
                <div className='wrapper'>
                    <div className='product-catalog'>{tiles}</div>
                </div>
            );
        }
        
    }
}

class ProductTile extends React.Component {

    // Renders the ProductTile with Product Information
    render() {
        return (
            
                    <div className="card">
                        <div className="card-image">
                        </div>
                        <div className="card-content">
                            <p className="title product-title">{this.props.product.productName}</p>
                            <div className="content">
                                <p>About: {this.props.product.productDesc}</p>
                                <p>Mfg Date: {this.props.product.productMfgDate}</p>
                                <p>Available Items: {this.props.product.productStock}</p>
                                <p>Price: {this.props.product.productPrice}</p>
                                
                            </div>
                        </div>
                    </div>
            
        )
    }
}
export default withRouter(Cart);