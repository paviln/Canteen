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

CREATE TABLE tblSupplier
(
    fldSupplierId int IDENTITY(1,1) PRIMARY KEY,
    fldName VARCHAR(50) NOT NULL,
    fldPhone VARCHAR(50) NOT NULL,
    fldDeliveryTime TIME(7) NOT NULL
)

CREATE TABLE tblCategory
(
    fldCategoryId int IDENTITY(1,1) PRIMARY KEY,
    fldName VARCHAR(50) NOT NULL
)

CREATE TABLE tblProduct
(
	fldProductId int IDENTITY(1,1) PRIMARY KEY,
	fldName VARCHAR(50) NOT NULL,
	fldCategory int NOT NULL,
	fldPrice FLOAT NOT NULL,
	fldCurrentStock int NOT NULL,
	fldMinimumStock int NOT NULL,
	fldSupplierId INT FOREIGN KEY REFERENCES tblSupplier(fldSupplierId)
)

CREATE TABLE tblStaff
(
    fldStaffId INT IDENTITY(1,1) PRIMARY KEY,
    fldName VARCHAR NOT NULL
)

CREATE TABLE tblEmployee
(
   fldEmployeeId INT IDENTITY(1,1) PRIMARY KEY,
   fldName VARCHAR NOT NULL,
   fldCurrency MONEY NOT NULL,
)

CREATE TABLE tblTransaction
(
    fldTransactionId int IDENTITY(1,1) PRIMARY KEY,
    fldOrderDate TIMESTAMP NOT NULL,
    fldStaffId INT FOREIGN KEY REFERENCES tblStaff(fldStaffId),
    fldEmployeeId INT FOREIGN KEY REFERENCES tblEmployee(fldEmployeeId)
)

CREATE TABLE tblTransactionProduct
(
    fldTransactionId INT FOREIGN KEY REFERENCES tblTransaction(fldTransactionId),
    fldProductId INT FOREIGN KEY REFERENCES tblProduct(fldProductId)
)