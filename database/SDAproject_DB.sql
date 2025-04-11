use sdaproject;

CREATE TABLE Users (
	UserID varchar (255),
	Password varchar(255) not null,
	Role varchar(255),
	DOB date,
	Gender varchar(255),
	CNIC varchar(255),
	Contact varchar(255),

	primary key(UserID)
);

CREATE TABLE Admin (
	UserID varchar(255),
    
	primary key (UserID),
	foreign key (UserID) references Users(UserID) on delete cascade on update cascade
);

CREATE TABLE Passenger (
	UserID varchar(255),
    PassportNum varchar(255) not null,
    
	primary key (UserID),
	foreign key (UserID) references users(UserID) on delete cascade on update cascade
);

CREATE TABLE Flight (
    FlightID varchar(255) primary key,
	Departure varchar(255),
    Rating varchar(255),
    Destination varchar(255),
    Date date,
    Time varchar(255),
    RatePerSeat int
    
);

CREATE TABLE Feedback (
  
    UserID varchar(255),
    FlightID varchar(255),
    Description varchar(255),
    Score int,
    
    PRIMARY KEY(UserID,FlightID),
	FOREIGN KEY (FlightID) REFERENCES Flight(FlightID) on delete cascade on update cascade,
	FOREIGN KEY (UserID) REFERENCES Passenger(UserID) on delete cascade on update cascade
);

CREATE TABLE Attends
(
	UserID varchar(255),
	FlightID varchar(255),
	status varchar(255),
	
    PRIMARY KEY (UserID,FlightID),
	FOREIGN KEY (FlightID) REFERENCES Flight(FlightID) on delete cascade on update cascade,
	FOREIGN KEY (UserID) REFERENCES Passenger(UserID) on delete cascade on update cascade
	
);


CREATE TABLE Booking (
    UserID varchar(255),
    FlightID varchar(255),
    
	FOREIGN KEY (UserID) REFERENCES Passenger(UserID) on delete cascade on update cascade,
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID) on delete cascade on update cascade,
    PRIMARY KEY (UserID,FlightID)
);

CREATE TABLE SeatData(
	FlightID varchar(255) primary key,
    SeatsAvailable int,
	FOREIGN KEY (FlightID) REFERENCES Flight(FlightID) on delete cascade on update cascade

);

CREATE TABLE PaymentInformation (
   
    UserID varchar(255),
	CardNum varchar(255),
	CVV varchar(255),
    ExpiryDate varchar(255),
    
    primary key (UserID,CardNum),
	FOREIGN KEY (UserID) REFERENCES Passenger(UserID) on delete cascade on update cascade
    
);

CREATE TABLE Transactions (

	TID int primary key,
	UserID varchar(255),
	CardNum varchar(255),
    Amount int,
    Date varchar(255),
    Balance int,
    
	FOREIGN KEY (UserID,CardNum) REFERENCES PaymentInformation(UserID,CardNum) on delete cascade on update cascade
);


 
 select * from flight
 update flight set date = '2023-12-05' where flightID = 'F-0001'
 
 
 
 update attends set status = 'Attended' where FlightID = 'F-0001'