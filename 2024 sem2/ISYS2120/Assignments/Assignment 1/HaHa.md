```SQL
CREATE TABLE Agent(
    AgentID INT PRIMARY KEY,
    AFirstName VARCHAR(255) NOT NULL,
    ALastName VARCHAR(255) NOT NULL,
    APhone VARCHAR(20) NOT NULL
);

CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
    CFirstName VARCHAR(20) NOT NULL,
    CLastName VARCHAR(20) NOT NULL,
    CMailAddress VARCHAR(20) NOT NULL,
    CPhone VARCHAR(20) NOT NULL
);


CREATE TABLE CustomerAgent (

    -- The ER diagram indicates that every customer should be known by at least one agent.
    -- However, this constraint cannot be directly enforced using standard SQL constraints.
    -- It requires additional mechanisms to ensure that every customer is indeed associated with at least one agent.

    CustomerID INT REFERENCES Customer(CustomerID),
    AgentID INT REFERENCES Agent(AgentID),
    LatestContactDate DATE,
    PRIMARY KEY(CustomerID,AgentID)
);

CREATE TABLE Owner (
    CustomerID INT PRIMARY KEY REFERENCES Customer(CustomerID),

    -- The TFN is an 8 (older numbers) or 9-digit number 
    TFN VARCHAR(9), 
    BankAcct VARCHAR(20),
    MgtFeeRate FLOAT
);

CREATE TABLE Renter (
    CustomerID INT PRIMARY KEY REFERENCES Customer(CustomerID),
    BALANCE FLOAT
);


CREATE TABLE Complex (
    PRIMARY KEY (Address, Suburb),
    NumberOfApts INT NOT NULL,
    Address VARCHAR(255),
    Suburb VARCHAR(32),
    OwnerID INT REFERENCES Owner(CustomerID) NOT NULL,
    EndDate DATE NOT NULL
);

CREATE TABLE Facility(
    PRIMARY KEY (Address,Suburb,FacilityName),
    Address VARCHAR(255),
    Suburb VARCHAR(32),
    FacilityName VARCHAR(255),
    FOREIGN KEY (Address, Suburb) REFERENCES Complex(Address, Suburb)
);


CREATE TABLE Apartment (
    -- Apartment's attributes
    ANumber INT ,
    Bedrooms INT,
    Area VARCHAR(128),

    -- Strong Entity: Complex
    Address VARCHAR(255),
    Suburb VARCHAR(32),
    AptFloor VARCHAR(20) NOT NULL, -- Relation LocateIn

    -- Weak Entity: Combine Strong entity's pk with discriminator As its PK
    PRIMARY KEY (ANumber, Address, Suburb),
    FOREIGN KEY (Address, Suburb) REFERENCES Complex(Address, Suburb),

    -- Owner
    OwnerID INT REFERENCES Owner(CustomerID) NOT NULL,

    -- Renter
    RenterID INT,
    EndDate DATE,
    WeeklyRent FLOAT,
    FOREIGN KEY (RenterID) REFERENCES Renter(CustomerID),

    -- Agent
    AgentID INT REFERENCES Agent(AgentID) NOT NULL
);


CREATE TABLE DaysWorking(
    AgentID INT REFERENCES Agent(AgentID),
    Days VARCHAR(32),
    PRIMARY KEY (AgentID,Days)
);

CREATE TABLE OffersFor(
    Duration VARCHAR(32) NOT NULL,
    OfferedRent FLOAT NOT NULL,

    PRIMARY KEY(RenterID,Address,Suburb,ANumber),
    RenterID INT REFERENCES Renter(CustomerID),
    Address VARCHAR(20),
    Suburb VARCHAR(20),
    ANumber INT,

    FOREIGN KEY (Address,Suburb,ANumber) REFERENCES Apartment(Address,Suburb,ANumber)
)



```