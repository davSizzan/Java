DROP DATABASE IF EXISTS UTCDemo;
CREATE DATABASE UTCDemo;

USE UTCDemo;

DROP TABLE IF EXISTS Address;
CREATE TABLE Address(
    add_id          INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    add_country     NVARCHAR(100)   NOT NULL ,
    add_city        NVARCHAR(100)   NOT NULL
);

DROP TABLE IF EXISTS Guests;
CREATE TABLE `Guests`(
    g_id            INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    g_first_name    NVARCHAR(100)   NOT NULL ,
    g_last_name     NVARCHAR(100)   NOT NULL ,
    g_user_name     VARCHAR(100)    NOT NULL ,
    g_idCard        NVARCHAR(100)   NOT NULL UNIQUE ,
    g_credit_card   NVARCHAR(100)            UNIQUE ,
    g_email         NVARCHAR(100)   NOT NULL UNIQUE ,
    g_password      NVARCHAR(200)   NOT NULL ,
    g_type          ENUM('GUESTS','ADMIN') NOT NULL DEFAULT 'GUESTS',
    g_add_id        INT             NOT NULL ,
    FOREIGN KEY (g_add_id) REFERENCES Address(add_id)
);

DROP TABLE IF EXISTS `Hotel`;
CREATE TABLE `Hotel`(
    h_id             INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
    h_name           NVARCHAR(100)   NOT NULL UNIQUE ,
    h_email_address   NVARCHAR(100)   NOT NULL UNIQUE ,
    h_website        NVARCHAR(100)   NOT NULL UNIQUE ,
    h_description    NVARCHAR(100)   NOT NULL ,
    h_roomCount      INT             NOT NULL ,
    h_addId         INT ,
    FOREIGN KEY (h_addId) REFERENCES Address(add_id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS StarRate;
CREATE TABLE StarRate(
    sr_id       INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    sr_image    INT ,
    sr_hId      INT NOT NULL ,
    FOREIGN KEY (sr_hId) REFERENCES Hotel(h_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS HotelImage;
CREATE TABLE HotelImage(
    hi_id           INT             NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    hi_image_name   NVARCHAR(100)   NOT NULL ,
    hi_hId          INT             NOT NULL ,
    FOREIGN KEY (hi_hId) REFERENCES Hotel(h_id)
);

DROP TABLE IF EXISTS RoomType;
CREATE TABLE RoomType(
    rt_id            INT                                         NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    rt_name          NVARCHAR(100)                               NOT NULL ,
    rt_cost          DECIMAL(10,2)                               NOT NULL ,
    rt_description   ENUM('TRAVEL','GO ON BUSSINESS','RESORT')   NOT NULL DEFAULT 'TRAVEL'
);

DROP TABLE IF EXISTS RoomRateDiscount;
CREATE TABLE RoomRateDiscount(
  rrd_id            INT             NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  rrd_rate          DECIMAL(10,2)   NOT NULL ,
  rrd_startMonth    DATETIME        NOT NULL ,
  rrd_endMonth      DATETIME        NOT NULL ,
  rrd_rtId          INT ,
  FOREIGN KEY (rrd_rtId) REFERENCES RoomType(rt_id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS Room;
CREATE TABLE Room(
    r_id            INT                     NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    r_number        INT                     NOT NULL ,
    r_status        ENUM ('DRUM','BOOKING') NOT NULL DEFAULT 'DRUM' ,
    r_rtId          INT ,
    r_hId           INT ,
    FOREIGN KEY (r_rtId) REFERENCES RoomType(rt_id) ON DELETE SET NULL ,
    FOREIGN KEY (r_hId) REFERENCES Hotel(h_id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS Booking;
CREATE TABLE Booking(
    b_id                INT                     NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    b_date              DATETIME                NOT NULL DEFAULT NOW() ,
    b_durationOfStay    INT ,
    b_checkInDate       DATETIME                NOT NULL ,
    b_checkOutDate      DATETIME                NOT NULL ,
    b_typePayment       ENUM('BAKING','DIRECT') NOT NULL DEFAULT 'DIRECT' ,
    b_totalRoom         TINYINT                 NOT NULL DEFAULT 1 ,
    b_hId               INT                     NOT NULL ,
    b_gId               INT                     NOT NULL ,
    b_totalAmount       DECIMAL(10,2) ,
    b_status            ENUM('UNPAID','PAID')   NOT NULL DEFAULT 'UNPAID' ,
    FOREIGN KEY (b_hId) REFERENCES Hotel(h_id) ,
    FOREIGN KEY (b_gId) REFERENCES Guests(g_Id)

);

DROP TABLE IF EXISTS RoomBook;
CREATE TABLE RoomBook(
    rb_id           INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    rb_rId          INT NOT NULL ,
    rb_bId          INT NOT NULL ,
    FOREIGN KEY (rb_rId) REFERENCES Room(r_id) ON DELETE CASCADE ,
    FOREIGN KEY (rb_bId) REFERENCES Booking(b_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS HotelServices;
CREATE TABLE HotelServices(
    hs_id           INT             NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    hs_name         NVARCHAR(100)   NOT NULL ,
    hs_cost         DECIMAL(10,2)   NOT NULL ,
    hs_hId          INT             NOT NULL ,
    FOREIGN KEY (hs_hId) REFERENCES Hotel(h_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS UserServices;
CREATE TABLE UserServices (
    us_hsId     INT NOT NULL ,
    us_bId      INT NOT NULL ,
    PRIMARY KEY (us_hsId,us_bId) ,
    FOREIGN KEY (us_bId) REFERENCES Booking(b_id) ON DELETE CASCADE ,
    FOREIGN KEY (us_hsId) REFERENCES HotelServices(hs_id) ON DELETE CASCADE
);


