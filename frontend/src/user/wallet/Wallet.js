import React, { Component } from 'react';
import { addMoney } from '../../util/APIUtils';
import '../../poll/NewPoll.css';  
import { Form, Input, Button, Icon, Select, Col, notification } from 'antd';
const Option = Select.Option;
const FormItem = Form.Item;
const { TextArea } = Input

class Wallet extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: {
                value: ''
            },
            amount: {
                value: ''
            }
        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const userAmount = {
            username: this.state.username.value,
            amount: this.state.amount.value
        };

        addMoney(userAmount)
        .then(response => {
            notification.success({
                message: 'My Store',
                description: "Amount added to Wallet!",
            });         
            this.props.history.push("/users"+userAmount.username);
        }).catch(error => {
            if(error.status === 401) {
                this.props.handleLogout('/login', 'error', 'You have been logged out. Please login to add amount.');    
            } else {
                notification.error({
                    message: 'My Store',
                    description: error.message || 'Sorry! Something went wrong. Please try again!'
                });              
                console.log("getting error: ",error.message);
            }
        });
    }

    handleInputChange(event) {
        const target = event.target;
        const inputName = target.name;        
        const inputValue = target.value;

        this.setState({
            [inputName] : {
                value: inputValue
            }
        });
    }

    render() {

        return (
            <div className="new-poll-container">
                <h1 className="page-title">Add Amount to Your Wallet</h1>
                <div className="new-poll-content">
                    <Form onSubmit={this.handleSubmit} className="create-poll-form">
                        <FormItem 
                            label="Amount"
                            hasFeedback>
                            <Input 
                                size="large"
                                name="amount" 
                                type="number" 
                                autoComplete="off"
                                placeholder="Amount"
                                value={this.state.amount.value} 
                                onChange={(event) => this.handleInputChange(event)}/>    
                        </FormItem>
                        <FormItem 
                            label="username"
                            hasFeedback>
                            <Input 
                                size="large"
                                name="username" 
                                type="text" 
                                autoComplete="off"
                                placeholder="Your Name"
                                value={this.state.username.value} 
                                onChange={(event) => this.handleInputChange(event)}/>    
                        </FormItem>
                        <FormItem className="poll-form-row">
                            <Button type="primary" 
                                htmlType="submit" 
                                size="large" 
                                
                                className="create-poll-form-button">Add Money</Button>
                        </FormItem>
                    </Form>
                </div>    
            </div>
        );
    }
}


export default Wallet;