import React, { Component } from 'react';
import {
    Link,
    Route,
    withRouter,
    Switch
  } from 'react-router-dom';
import { getUserProfile, getWeatherData } from '../../util/APIUtils';
import { Avatar, Tabs } from 'antd';
import { getAvatarColor } from '../../util/Colors';
import LoadingIndicator  from '../../common/LoadingIndicator';
import './Profile.css';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';
import { Form, Input, Button, Icon, notification } from 'antd';
const FormItem = Form.Item;

const TabPane = Tabs.TabPane;

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            q:{
                value:''
            },
            isLoading: false
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.loadUserProfile = this.loadUserProfile.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    
    handleSubmit(event) {
        event.preventDefault();
        const weatherData = {
            q: this.state.q.value
        };

        getWeatherData(weatherData)
        .then(response => {
            notification.success({
                message: 'My Store',
                description: "The weather is "+response
            });         
            
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

    loadUserProfile(username) {
        this.setState({
            isLoading: true
        });

        getUserProfile(username)
        .then(response => {
            this.setState({
                user: response,
                isLoading: false
            });
        }).catch(error => {
            if(error.status === 404) {
                this.setState({
                    notFound: true,
                    isLoading: false
                });
            } else {
                this.setState({
                    serverError: true,
                    isLoading: false
                });        
            }
        });        
    }
      
    componentDidMount() {
        const username = this.props.match.params.username;
        this.loadUserProfile(username);
    }

    componentDidUpdate(nextProps) {
        if(this.props.match.params.username !== nextProps.match.params.username) {
            this.loadUserProfile(nextProps.match.params.username);
        }        
    }

    handleInputChange(event,value) {
        this.state.q.value = value;
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
        if(this.state.isLoading) {
            return <LoadingIndicator />;
        }

        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }

        const tabBarStyle = {
            textAlign: 'center'
        };

        return (
            <div className="profile">
                { 
                    this.state.user ? (
                        <div className="user-profile">
                            <div className="user-details">
                                <div className="user-avatar">
                                    <Avatar className="user-avatar-circle" style={{ backgroundColor: getAvatarColor(this.state.user.name)}}>
                                        {this.state.user.name[0].toUpperCase()}
                                    </Avatar>
                                </div>
                                <div className="user-summary">
                                    <div className="full-name">{this.state.user.name}</div>
                                    <div className="username">@{this.state.user.username}</div>
                                </div>
                            </div>
                            <div className="user-poll-details">    
                                
                                <Tabs defaultActiveKey="1" 
                                    animated={false}
                                    tabBarStyle={tabBarStyle}
                                    size="large"
                                    className="profile-tabs">
                                    <TabPane tab={`Wallet`} key="1">
                                        <div className='wallet-details'>
                                        <h2>Wallet Balance: {this.state.user.wallet_balance}</h2>
                                        <Link to={`/users/${this.state.user.username}/wallet`}>Add Balance</Link>
                                        </div>
                                        {/* <PollList username={this.props.match.params.username} type="USER_CREATED_POLLS" /> */}
                                        
                                    </TabPane>
                                    <TabPane tab={`Weather`}  key="2">
                                        {/* <PollList username={this.props.match.params.username} type="USER_VOTED_POLLS" /> */}
                                        <div className='wallet-details'>
                                        <h2>Check today's weather</h2>
                                        <Form onSubmit={this.handleSubmit} className="create-poll-form">
                                            <FormItem 
                                                label="Amount"
                                                hasFeedback>
                                                <Input 
                                                    size="large"
                                                    name="q" 
                                                    type="text" 
                                                    autoComplete="off"
                                                    placeholder="Location, Country Code"
                                                    value={this.state.q.value} 
                                                    onChange={(event,value) => this.handleInputChange(event,value)}/>    
                                            </FormItem>
                                            <Button type="primary" 
                                            htmlType="submit" 
                                            size="large" 
                                            className="create-poll-form-button">Get Weather</Button>
                                        </Form>
                                        {/* <Link to={`/users/${this.state.user.username}/wallet`}>Get Weather</Link> */}
                                        </div>
                                    </TabPane>
                                </Tabs>
                            </div>  
                        </div>  
                    ): null               
                }
            </div>
        );
    }
}

export default Profile;