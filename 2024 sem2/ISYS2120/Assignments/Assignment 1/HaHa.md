```SQL
-- Then, create the Customer table

CREATE TABLE Agent(
    AgentID INT PRIMARY KEY,
    AFirstName VARCHAR(255),
    ALastName VARCHAR(255),
    APhone VARCHAR(255)
);

CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
    CFirstName VARCHAR(20),
    CLastName VARCHAR(20),
    CMailAddress VARCHAR(20),
    CPhone VARCHAR(20),

    KnownToAgent INT REFERENCES Agent(AgentID)
);


CREATE TABLE Owner (
    TFN VARCHAR(20),
    BankAcct VARCHAR(20),
    MgtFeeRate FLOAT,
    CustomerID INT PRIMARY KEY REFERENCES Customer(CustomerID)
);

CREATE TABLE Complex (
    NumberOfApts INT,
    Address VARCHAR(20),
    Suburb VARCHAR(20),
    OwnerID_IS_SECRETARY INT REFERENCES Owner(CustomerID),
    PRIMARY KEY (Address, Suburb)
);


-- Now, create the Renter table
CREATE TABLE Renter (
    CustomerID INT PRIMARY KEY REFERENCES Customer(CustomerID),
    BALANCE FLOAT
);

-- Finally, create the Apartment table
CREATE TABLE Apartment (
    ANumber INT,
    Bedrooms INT,
    Area VARCHAR(20),

    -- Complex
    Address VARCHAR(20),
    Suburb VARCHAR(20),
    Floor VARCHAR(20),

    PRIMARY KEY (ANumber, Address, Suburb),
    FOREIGN KEY (Address, Suburb) REFERENCES Complex(Address, Suburb),

    -- Owner
    CustomerID_OWNER INT,
    FOREIGN KEY (CustomerID_OWNER) REFERENCES Owner(CustomerID),

    -- Renter
    CustomerID_RENTS INT,
    EndDate DATE,
    WeeklyRent FLOAT,
    FOREIGN KEY (CustomerID_RENTS) REFERENCES Renter(CustomerID)
);

CREATE TABLE Facility(
    Address VARCHAR(20),
    Suburb VARCHAR(20),
    FacilityName VARCHAR(255),
    PRIMARY KEY (Address,Suburb,FacilityName),
    FOREIGN KEY (Address, Suburb) REFERENCES Complex(Address, Suburb)
);


CREATE TABLE DaysWorking(
    AgentID INT REFERENCES Agent(AgentID),
    DAYS VARCHAR(255),
    PRIMARY KEY (AgentID,DAYS)
);

CREATE TABLE OffersFor(
    Renter_ID INT,
    Address VARCHAR(20),
    Suburb VARCHAR(20),
    ANumber INT,
    FOREIGN KEY (Address,Suburb,ANumber) REFERENCES Apartment(Address,Suburb,ANumber)
)



```