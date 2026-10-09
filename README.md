# ecommerce-webapp
A simple shopping e commerce app using spring boot, mysql and react js

## Instructions to use
* First clone the repository using gitbash <br>
  <code>git clone https://github/01abhishek10/ecommerce-webapp.git</code>
* Navigate to the frontend folder and install the dependencies using the below command <br/>
  <code>npm install </code>
* Create a database stock_db and give the mysql username and password in application.properties file in both microservices <br/>
  <pre><code>spring.datasource.username=USERNAME</code>
  <code>spring.datasource.password=PASSWORD </code></pre>
* Enable CORS for both the microservices using the below property </br>
  <code>app.cors.allowedOrigins = FRONT_END_URL </code>

## Architecture of the Application
* This application is divided into 3 microservices
   1. Front end
   2. User Microservice
   3. Product Microservice

## Features of the App
* This application uses JWT Authorization to ensure secure login of the user
* Password is stored in hashed format. 

## Backend APIs
* API to fetch all products </br>
<code>GET http://localhost:8080/products/</code>
* API to fetch the current user </br>
<code>GET http://localhost:9000/users/{userName}</code>
* API to add money to user Wallet </br>
<code>POST http://localhost:9000/users/{userName}/addMoney</code>
* API to login </br>
<code>POST http://localhost:9000/signin</code>
* API to signup </br>
<code>POST http://localhost:9000/signup</code>
