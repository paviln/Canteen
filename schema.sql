/*
    Schema for building the database structure of the Canteen application.
    - Old database is dropped.
*/
USE MASTER
GO
DROP DATABASE IF EXISTS Canteen

CREATE DATABASE Canteen;
GO
use Canteen;

CREATE TABLE product
(
	id int IDENTITY(1,1) PRIMARY KEY,
	name NCHAR(255) NOT NULL,
	category int NOT NULL,
)