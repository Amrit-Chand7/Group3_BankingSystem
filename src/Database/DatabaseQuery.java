//
//CREATE TABLE IF NOT EXISTS admin (
//id INT AUTO_INCREMENT PRIMARY KEY,
//full_name VARCHAR(100) NOT NULL,
//email VARCHAR(100) UNIQUE NOT NULL,
//password VARCHAR(100) NOT NULL,
//role VARCHAR(100) NOT NULL
//);


//use BankingSystem;
//

//CREATE TABLE notices (
//id INT AUTO_INCREMENT PRIMARY KEY,
//message TEXT NOT NULL,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
//


//
//CREATE TABLE employee (
//em_id INT PRIMARY KEY AUTO_INCREMENT,
//em_full_name VARCHAR(100) NOT NULL,
//em_email VARCHAR(100) UNIQUE NOT NULL,
//em_password VARCHAR(255) NOT NULL,
//em_phone_number VARCHAR(20),
//em_address VARCHAR(100),
//em_role VARCHAR(100) NOT NULL
//);
//


//
//CREATE TABLE customers (
//customer_id1 INT AUTO_INCREMENT PRIMARY KEY,
//full_name1 VARCHAR(255),
//email1 VARCHAR(255),
//dob1 VARCHAR(50),
//phone1 VARCHAR(50),
//address1 VARCHAR(255),
//id_proof1 VARCHAR(100),
//account_type1 VARCHAR(50),
//account_number1 VARCHAR(20) UNIQUE
//);


//CREATE TABLE accounts (
//account_number VARCHAR(20) PRIMARY KEY,
//balance DOUBLE NOT NULL DEFAULT 0
//);


//CREATE TABLE transactions (
//transaction_id INT AUTO_INCREMENT PRIMARY KEY,
//account_number VARCHAR(20),
//amount DOUBLE,
//type VARCHAR(20),
//date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);


