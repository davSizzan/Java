


CREATE TABLE TacGia(
    MaTacGia    CHAR(10)        NOT NULL ,
    TenTacGia   NVARCHAR(50)    NOT NULL ,
    DiaChi      NVARCHAR(50)     ,
    ButDanh     NVARCHAR(50)     ,
	TrangThai   NVARCHAR(40)     NOT NULL,
    PRIMARY KEY (MaTacGia)
);
CREATE TABLE ViTri(
    MaViTri     CHAR(10) NOT NULL ,
    Khu         CHAR(10) NOT NULL ,
    Ngan        CHAR(10) NOT NULL ,
    Ke          CHAR(10) NOT NULL ,
	TrangThai   NVARCHAR(40) NOT NULL,
    PRIMARY KEY (MaViTri)
);

CREATE TABLE LoaiSach(
    MaLoai  CHAR(10)        NOT NULL ,
    TenLoai NVARCHAR(50)    NOT NULL ,
    SoLuong SMALLINT ,
    PRIMARY KEY (MaLoai)
);

CREATE TABLE NhaXuatBan(
    MaNXB   CHAR(10)        NOT NULL ,
    TenNXB  NVARCHAR(50)    NOT NULL ,
    DiaChi  NVARCHAR(50)    NOT NULL ,
	TrangThai NVARCHAR(40) NOT NULL,
    PRIMARY KEY (MaNXB)
);

CREATE TABLE Sach(
    MaSach      CHAR(10)        NOT NULL ,
    TenSach     NVARCHAR(50)    NOT NULL ,
    SoTrang     SMALLINT        NOT NULL ,
    NamXuatBan  DATE            NOT NULL ,
    Gia         DECIMAL(10,2)   NOT NULL ,
    TinhTrang   NVARCHAR(30)    NOT NULL ,
    MaTacGia    CHAR(10)        NOT NULL ,
    MaViTri     CHAR(10)        NOT NULL ,
    MaLoai      CHAR(10)        NOT NULL ,
    MaNXB       CHAR(10)        NOT NULL ,
	SoLuong     INT             NOT NULL,
    PRIMARY KEY (MaSach) ,
    FOREIGN KEY (MaTacGia) REFERENCES TacGia(MaTacGia)  ON DELETE CASCADE ,
    FOREIGN KEY (MaViTri) REFERENCES ViTri(MaViTri)     ON DELETE CASCADE ,
    FOREIGN KEY (MaLoai) REFERENCES LoaiSach(MaLoai)    ON DELETE CASCADE ,
    FOREIGN KEY (MaNXB) REFERENCES NhaXuatBan(MaNXB)    ON DELETE CASCADE
);

CREATE TABLE BoPhan(
    MaBoPhan    CHAR(10)        NOT NULL ,
    TenBoPhan   NVARCHAR(50)    NOT NULL,
    PRIMARY KEY (MaBoPhan)
);

CREATE TABLE NhanVien(
    MaNhanVien  CHAR(10)        NOT NULL ,
    TenNhanVien NVARCHAR(50)    NOT NULL ,
    NgaySinh    DATE            NOT NULL ,
    DiaChi      NVARCHAR(50)    NOT NULL ,
    MaBoPhan    CHAR(10)        NOT NULL ,
    PRIMARY KEY (MaNhanVien) ,
    FOREIGN KEY (MaBoPhan) REFERENCES BoPhan(MaBoPhan) ON DELETE CASCADE
);

CREATE TABLE PhanLoai(
    MaPhanLoai  CHAR(3)         NOT NULL ,
    PhanLoai    NVARCHAR(20)    NOT NULL ,
    PRIMARY KEY (MaPhanLoai)
);

CREATE TABLE SinhVien(
    MaSinhVien  INT             NOT NULL ,
    TenSinhVien NVARCHAR(50)    NOT NULL ,
    SoDienThoai CHAR(10) ,
    MaPhanLoai  CHAR(3)         NOT NULL ,
    PRIMARY KEY (MaSinhVien) ,
    FOREIGN KEY (MaPhanLoai) REFERENCES PhanLoai(MaPhanLoai) ON DELETE CASCADE
);

CREATE TABLE CapThe(
    MaThe       INT         NOT NULL ,
    MaSinhVien  INT         NOT NULL ,
    NgayCap     DATE        NOT NULL ,
    NgayHetHan  DATE        NOT NULL ,
    MaNhanVien  CHAR(10)    NOT NULL ,
    PRIMARY KEY (MaThe) ,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien) ON DELETE CASCADE ,
    FOREIGN KEY (MaSinhVien) REFERENCES SinhVien(MaSinhVien) ON DELETE CASCADE
);

CREATE TABLE MuonSach(
    MaMuon          INT         NOT NULL ,
    MaNhanVien      CHAR(10)    NOT NULL ,
    MaThe           INT         NOT NULL ,
    TongSoSachMuon  INT ,
    PRIMARY KEY (MaMuon) ,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien) ON DELETE CASCADE ,
    FOREIGN KEY (MaThe) REFERENCES CapThe(MaThe)
);

CREATE TABLE ChiTietMuon(
    MaMuon              INT NOT NULL ,
    MaSach              CHAR(10) NOT NULL ,
    SoLuongMuon         TINYINT NOT NULL ,
    NgayTra             DATE NOT NULL ,
    TinhTrangKhiMuon    NVARCHAR(30) NOT NULL ,
    FOREIGN KEY (MaMuon) REFERENCES MuonSach(MaMuon)    ON DELETE CASCADE ,
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)        ON DELETE CASCADE
);

CREATE TABLE TraSach(
    MaTra           INT         NOT NULL ,
    MaNhanVien      CHAR(10)    NOT NULL ,
    MaThe           INT         NOT NULL ,
    TongSoSachTra   INT ,
    PRIMARY KEY (MaTra) ,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien) ON DELETE CASCADE ,
    FOREIGN KEY (MaThe) REFERENCES CapThe(MaThe)
);

CREATE TABLE ChiTietTra(
    MaTra               INT         NOT NULL ,
    MaSach              CHAR(10)    NOT NULL ,
    SoLuongTra          TINYINT     NOT NULL ,
    NgayTra             DATE         not null,
    TinhTrangSachTra    NVARCHAR(30) ,
    FOREIGN KEY (MaTra) REFERENCES TraSach(MaTra)   ON DELETE CASCADE ,
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)    ON DELETE CASCADE
);

-- CREATE TABLE Phat(
--     TenSinhVien NVARCHAR(50) ,
--     TinhTrangSachTra NVARCHAR(50) ,
--     TienPhat DECIMAL(10,2) ,
--     LyDoPhat NVARCHAR(50) ,
--     MaNhanVien CHAR(10) NOT NULL ,
--     MaThe INT NOT NULL
-- )

-- Data Insert

INSERT NhaXuatBan
VALUES
    ('NXB01',N'NXB Kim Đồng',   N'Hà Nội',N'Hoạt Động'),
    ('NXB02',N'NXB Trẻ',        N'Hà Nội',N'Hoạt Động'),
    ('NXB03',N'NXB Thăng Long', N'Thanh Hóa','Hoạt Động'),
    ('NXB04',N'NXB Nhã Nam',    N'Vĩnh Phúc',N'Hoạt Động'),
    ('NXB05',N'NXB Giáo Dục',   N'TP HCM',N'Hoạt Động'),
    ('NXB06',N'NXB Fahasa',     N'Vĩnh Phúc',N'Hoạt Động'),
    ('NXB07',N'NXB Phương Nam', N'Hà Nội',N'Hoạt Động'),
    ('NXB08',N'NXB ĐH GTVT',    N'Hà Nội',N'Hoạt Động'),
    ('NXB09',N'NXB ĐH QGHN',    N'Hà Nội',N'Hoạt Động'),
    ('NXB10',N'NXB Thủ Đô',     N'Hà Nội',N'Hoạt Động');

INSERT ViTri
VALUES (1,  'A','A-1','AK-1',N'Hoạt Động'),
       (2,  'A','A-2','AK-2',N'Hoạt Động'),
       (3,  'A','A-3','AK-3',N'Hoạt Động'),
       (4,  'B','B-1','BK-1',N'Hoạt Động'),
       (5,  'B','B-2','BK-2',N'Hoạt Động'),
       (6,  'B','B-3','BK-3',N'Hoạt Động'),
       (7,  'B','B-4','BK-4',N'Hoạt Động'),
       (8,  'C','C-1','CK-1',N'Hoạt Động'),
       (9,  'C','C-2','CK-2',N'Hoạt Động'),
       (10, 'C','C-3','CK-3',N'Hoạt Động');

INSERT LoaiSach
VALUES ('TL01',N'Bí quyết Cuộc sống',                   null),
       ('TL02',N'Sách Tin học',                         null),
       ('TL03',N'Thể Thao, Du lịch',                    null),
       ('TL04',N'Văn hóa, Nghệ thuật',                  null),
       ('TL05',N'Sách Kinh doanh',                      null),
       ('TL06',N'Sách Khuyến mại, Sách mới phát hành',  null),
       ('TL07',N'Sách Toán Học',                        null),
       ('TL08',N'Sách Văn Học',                         null);

INSERT TacGia
VALUES ('TG01',N'Trần Đăng Khoa',           N'Hà Nội',      null,N'Hoạt Động'),
       ('TG02',N'Vương Ba, Vương Trí Diễm', N'Hà Nội',      null,N'Hoạt Động'),
       ('TG03',N'Maya banks',               N'Ý',           null,N'Hoạt Động'),
       ('TG04',N'Gào',                      N'Hà Nội',      null,N'Hoạt Động'),
       ('TG05',N'Thomas L.Friedman',        N'Pháp',        null,N'Hoạt Động'),
       ('TG06',N'GS. Phạm Văn Ất',          N'Hà Nội',      null,N'Hoạt Động'),
       ('TG07',N'Jim White',                N'Pháp',        null,N'Hoạt Động'),
       ('TG08',N'Sidney Sheldon',           N'Anh',         null,N'Hoạt Động'),
       ('TG09',N'Mai Hương',                N'Hà Nội',      null,N'Hoạt Động'),
       ('TG10',N'Triệu Phàm Vũ',            N'Trung Hoa',   null,N'Hoạt Động'),
       ('TG11',N'Hồ Đắc Phương',            N'Hà Nội',      null,N'Hoạt Động');

INSERT Sach VALUES ('S01',N'Sống và khát vọng',                                 250,'2020-12-5',100000,N'Nguyên Vẹn','TG01',1,'TL01','NXB01',4)
 INSERT Sach VALUES      ('S02',N'Làm Chủ Tư Duy, Thay Đổi Vận Mệnh',                 350,'2020-12-5',200000,N'Nguyên Vẹn','TG01',1,'TL01','NXB01',3)
 INSERT Sach VALUES      ('S03',N'Góc Sân Và Khoảng Trời',                            500,'2019-12-5',130000,N'Nguyên Vẹn','TG01',1,'TL01','NXB01',2)
  INSERT Sach VALUES     ('S04',N'Sống Tối Giản - Hạnh Phúc Không Đắt Như Bạn Nghĩ',  250,'2018-12-5',140000,N'Nguyên Vẹn','TG01',1,'TL06','NXB01',1)
    INSERT Sach VALUES   ('S05',N'-Đảo Chìm - Trường Sa Đảo Chìm - Trường Sa',        400,'2017-12-5',150000,N'Hỏng','TG01',1,'TL06','NXB01',2)
   INSERT Sach VALUES    ('S06',N'Góc Sân Và Khoảng Trời - Hạt Gạo Làng',             100,'2016-12-5',180000,N'Nguyên Vẹn','TG01',1,'TL06','NXB02',1)
   INSERT Sach VALUES    ('S07',N'Góc Sân Và Khoảng Trời - Mang Biển Về Quê',         180,'2015-12-5',260000,N'Nguyên Vẹn','TG02',1,'TL06','NXB02',3)
  INSERT Sach VALUES     ('S08',N'Toán Học, Một Thiên Tiểu Thuyết',                   250,'2014-12-5',106000,N'Nguyên Vẹn','TG02',2,'TL07','NXB03',2)
  INSERT Sach VALUES     ('S09',N'Những Câu Hỏi Lớn – Toán Học',                      250,'2013-12-5',180000,N'Nguyên Vẹn','TG02',2,'TL07','NXB04',2)
  INSERT Sach VALUES     ('S10',N'Vẻ Đẹp Toán Học – Những Bài Toán Gợi Mở Tư Duy',    250,'2012-12-5',300000,N'Nguyên Vẹn','TG02',2,'TL07','NXB03',1)
  INSERT Sach VALUES     ('S11',N'Toán Học và Nghệ thuật',                            250,'2011-12-5',400000,N'Nguyên Vẹn','TG03',2,'TL07','NXB04',4)
  INSERT Sach VALUES     ('S12',N'Cách Chinh Phục Toán Và Khoa Học',                  250,'2010-12-5',500000,N'Nguyên Vẹn','TG04',2,'TL07','NXB03',6)
  INSERT Sach VALUES     ('S13',N'Những Dấu Chân Toán Học',                           250,'2009-12-5',400000,N'Nguyên Vẹn','TG04',2,'TL07','NXB04',3)
  INSERT Sach VALUES     ('S14',N'Phương Trình Thay Đổi Thế Giới',                    250,'2008-12-5',400000,N'Nguyên Vẹn','TG04',2,'TL07','NXB02',4)
 INSERT Sach VALUES      ('S15',N'Sự Kì Diệu Của Toán Học',                           250,'2007-12-5',400000,N'Rách','TG04',2,'TL07','NXB01',2)
   INSERT Sach VALUES    ('S16',N'1984',                                              250,'2006-12-5',450000,N'Nguyên Vẹn','TG05',3,'TL08','NXB06',1)
   INSERT Sach VALUES    ('S17',N'Người Đua Diều',                                    250,'2006-12-5',160000,N'Nguyên Vẹn','TG05',3,'TL08','NXB06',3)
  INSERT Sach VALUES     ('S18',N'Giết Con Chim Nhại',                                250,'2007-12-5',240000,N'Hỏng','TG05',3,'TL08','NXB06',2)
    INSERT Sach VALUES   ('S19',N'Chiến Tranh Và Hòa Bình',                           250,'2017-12-5',180000,N'Nguyên Vẹn','TG05',3,'TL08','NXB06',2)
  INSERT Sach VALUES     ('S20',N'Hai Số Phận',                                       250,'2016-12-5',300000,N'Nguyên Vẹn','TG05',3,'TL08','NXB06',4)
  INSERT Sach VALUES     ('S21',N'Trăm Năm Cô Đơn',                                   250,'2018-12-5',350000,N'Nguyên Vẹn','TG06',3,'TL08','NXB06',2)
  INSERT Sach VALUES     ('S22',N'Kiêu Hãnh Và Định Kiến',                            250,'2019-12-5',400000,N'Nguyên Vẹn','TG06',3,'TL08','NXB06',3)
  INSERT Sach VALUES     ('S23',N'Hoàng Tử Bé',                                       250,'2020-12-5',190000,N'Nguyên Vẹn','TG06',3,'TL08','NXB06',2)
   INSERT Sach VALUES    ('S24',N'Đôn Kihôtê',                                        250,'2013-12-5',195000,N'Nguyên Vẹn','TG06',3,'TL08','NXB02',1)
  INSERT Sach VALUES     ('S25',N'7 Thói quen của bạn trẻ thành đạt',                 250,'2020-12-5',360000,N'Nguyên Vẹn','TG06',3,'TL05','NXB02',2)
  INSERT Sach VALUES     ('S26',N'Jeff Bezos Và Kỷ Nguyên Amazon',                    250,'2018-12-5',100000,N'Nguyên Vẹn','TG06',3,'TL05','NXB02',4)
   INSERT Sach VALUES    ('S27',N'Từ tốt đến vĩ đại',                                 250,'2017-12-5',100000,N'Nguyên Vẹn','TG06',4,'TL05','NXB02',2)
   INSERT Sach VALUES    ('S28',N'Dốc Hết Trái Tim',                                  250,'2016-12-5',100000,N'Nguyên Vẹn','TG06',4,'TL05','NXB02',3)
  INSERT Sach VALUES     ('S29',N'Để xây dựng doanh nghiệp hiệu quả',                 250,'2015-12-5',250000,N'Nguyên Vẹn','TG06',4,'TL05','NXB02',4)
  INSERT Sach VALUES     ('S30',N'Khác biệt để bứt phá',                              250,'2014-12-5',250000,N'Nguyên Vẹn','TG06',4,'TL05','NXB02',3)
  INSERT Sach VALUES     ('S31',N'Năng Đoạn Kim Cương',                               250,'2014-12-5',250000,N'Rách','TG07',4,'TL05','NXB02',2)
  INSERT Sach VALUES     ('S32',N'Tỷ Phú Bán Giày',                                   250,'2013-12-5',250000,N'Nguyên Vẹn','TG07',4,'TL05','NXB01',3)
  INSERT Sach VALUES     ('S33',N'Tuần làm việc 4 giờ',                               250,'2020-12-5',250000,N'Nguyên Vẹn','TG07',4,'TL05','NXB01',2)
   INSERT Sach VALUES    ('S34',N'Gian Nan Chồng Chất Gian Nan',                      250,'2020-12-5',250000,N'Nguyên Vẹn','TG07',4,'TL05','NXB01',1)
 INSERT Sach VALUES     ('S35',N'Tư Duy Như Một Kẻ Lập Dị',                          250,'2018-12-5',250000,N'Rách','TG07',5,'TL05','NXB01',3)
  INSERT Sach VALUES     ('S36',N'13 nguyên tắc nghĩ giàu làm giàu',                  250,'2016-12-5',250000,N'Nguyên Vẹn','TG07',5,'TL05','NXB10',2)
  INSERT Sach VALUES     ('S37',N'Honda – Sức Mạnh Của Những Giấc Mơ',                250,'2013-12-5',300000,N'Nguyên Vẹn','TG07',5,'TL05','NXB10',4)
 INSERT Sach VALUES      ('S38',N'Khởi Hành',                                         250,'2013-12-5',500000,N'Nguyên Vẹn','TG08',5,'TL05','NXB10',3)
  INSERT Sach VALUES     ('S39',N'Tự Truyện Richard Branson – Đường Ra Biển Lớn',     250,'2014-12-5',550000,N'Nguyên Vẹn','TG10',5,'TL05','NXB09',2)
  INSERT Sach VALUES     ('S40',N'Code Dạo Kí Sự',                                    250,'2015-12-5',480000,N'Nguyên Vẹn','TG11',5,'TL02','NXB09',2)
   INSERT Sach VALUES    ('S41',N'Lập Trình Viên – Phù thuỷ thế giới mạng',           250,'2016-12-5',490000,N'Rách','TG11',5,'TL02','NXB09',3)
   INSERT Sach VALUES    ('S42',N'Hacker Lược sử – Steven Levy',                      250,'2016-12-5',390000,N'Nguyên Vẹn','TG11',5,'TL02','NXB08',1)
  INSERT Sach VALUES     ('S43',N'Tớ Học Lập Trình – Làm quen với lập trình Scratch', 250,'2013-12-5',395000,N'Hỏng','TG11',5,'TL02','NXB08',2)


INSERT BoPhan
VALUES ('BP01' , N'Vệ Sinh'),
       ('BP02' , N'Quản Lý'),
       ('BP03' , N'Văn Phòng'),
       ('BP04' , N'Nhân Viên Sách');

INSERT NhanVien
VALUES
       ('NV01',N'Trần Huy',         '1990-09-11',N'Hà Nội',     'BP04'),
       ('NV02',N'Thanh Tâm',        '1986-01-12',N'Bắc Ninh',   'BP04'),
       ('NV03',N'Nguyễn Minh',      '1989-07-05',N'Hải Dương',  'BP04'),
       ('NV04',N'Phan Cao',         '1991-08-12',N'Ninh Bình',  'BP02'),
       ('NV05',N'Bùi Ngọc',         '1987-03-01',N'Thái Bình',  'BP04'),
       ('NV06',N'Nguyễn Hoàng',     '1990-04-04',N'Nam Định',   'BP04'),
       ('NV07',N'Nguyễn Hải',       '1994-05-05',N'Hà Nội',     'BP04'),
       ('NV08',N'Đỗ Thảo',          '1990-06-06',N'Hà Nội',     'BP04'),
       ('NV09',N'Đỗ Thảo Phương',   '1990-07-07',N'Hà Nội',     'BP04'),
       ('NV10',N'Đỗ Huy Long',      '1995-08-08',N'Nam Định',   'BP04');


INSERT PhanLoai
VALUES (1,N'Sinh Viên'),
       (2,N'Người Ngoài Trường');

INSERT SinhVien
VALUES (191200291,N'Tôn Linh Lung',     '0987653211',    1),
       (191200292,N'Cam Linh',          '0987653212',    1),
       (191200293,N'Tôn Hoàn',          '0987653213',    1),
       (191200294,N'Bộ Uyển',           '0987653214',    1),
       (191200295,N'Lục Tốn',           '0987653215',    1),
       (191200296,N'Trình Phổ',         '0987653216',    1),
       (191200297,N'Trương Hoành',      '0987653217',    1),
       (191200298,N'Lữ Mông',           '0987653218',    1),
       (191200299,N'Lỗ Túc',            '0987653219',    1),
       (191200300,N'Đại Kiều',          '0987653220',    1),
       (191200301,N'Ngô Phượng Hi',     '0987653221',    1),
       (191200302,N'Chu Thái',          '0987653222',    1),
       (191200303,N'Tân Hiến Anh',      '0987653223',    1),
       (191200304,N'Lữ Mộng Như',       '0987653224',    1),
       (191200305,N'Chu Du',            '0987653225',    1),
       (191200306,N'Tôn Kiên',          '0987653226',    2),
       (191200307,N'Hoàng Thái',        '0987653227',    2),
       (191200308,N'Cố Ung',            '0987653228',    2),
       (191200309,N'Linh Âm',           '0987653229',    2),
       (191200310,N'Vương Nguyên Cơ',   '0987653230',    2);

INSERT CapThe
VALUES (100001,191200291,'2020-10-11','2025-10-11','NV04'),
       (100002,191200292,'2020-10-11','2025-10-11','NV04'),
       (100003,191200293,'2020-10-11','2025-10-11','NV04'),
       (100004,191200294,'2020-10-11','2025-10-11','NV04'),
       (100005,191200295,'2020-10-11','2025-10-11','NV04'),
       (100006,191200296,'2020-10-11','2025-10-11','NV04'),
       (100007,191200297,'2020-10-11','2025-10-11','NV04'),
       (100008,191200298,'2020-10-11','2025-10-11','NV04'),
       (100009,191200299,'2020-10-11','2025-10-11','NV04'),
       (100010,191200300,'2020-10-11','2025-10-11','NV04'),
       (100011,191200301,'2020-10-11','2025-10-11','NV04'),
       (100012,191200302,'2020-10-11','2025-10-11','NV04'),
       (100013,191200303,'2020-10-11','2025-10-11','NV04'),
       (100014,191200304,'2020-10-11','2025-10-11','NV04'),
       (100015,191200305,'2020-10-11','2025-10-11','NV04'),
       (100016,191200306,'2020-10-11','2025-10-11','NV04'),
       (100017,191200307,'2020-10-11','2025-10-11','NV04'),
       (100018,191200308,'2020-10-11','2025-10-11','NV04'),
       (100019,191200309,'2020-10-11','2025-10-11','NV04'),
       (100020,191200310,'2020-10-11','2025-10-11','NV04');

INSERT MuonSach
VALUES (000001,'NV04',100001,null),
       (000002,'NV02',100002,null),
       (000003,'NV02',100003,null),
       (000004,'NV02',100004,null),
       (000005,'NV02',100005,null),
       (000006,'NV02',100006,null),
       (000007,'NV02',100007,null),
       (000008,'NV03',100008,null),
       (000009,'NV03',100009,null),
       (000010,'NV03',100010,null),
       (000011,'NV03',100011,null),
       (000012,'NV01',100012,null),
       (000013,'NV01',100019,null),
       (000014,'NV01',100020,null),
       (000015,'NV01',100012,null),
       (000016,'NV01',100015,null);
insert ChiTietMuon
values
(000001,'S01',1,'2021-12-5',N'Nguyên vẹn'),
 (000002,'S02',2,'2021-12-10',N'Nguyên vẹn'),
 (000003,'S03',1,'2021-4-5',N'Nguyên vẹn'),
 (000004,'S04',3,'2021-2-4',N'Nguyên vẹn'),
 (000005,'S02',4,'2021-1-5',N'Nguyên vẹn'),
 (000006,'S05',3,'2021-4-6',N'Nguyên vẹn'),
 (000007,'S06',2,'2021-3-8',N'Nguyên vẹn'),
 (000008,'S07',5,'2021-12-5',N'Nguyên vẹn'),
 (000009,'S10',4,'2021-3-5',N'Nguyên vẹn'),
 (000010,'S11',2,'2021-7-12',N'Nguyên vẹn'),
 (000011,'S12',3,'2021-12-21',N'Nguyên vẹn'),
 (000012,'S14',4,'2021-3-24',N'Nguyên vẹn'),
  (000013,'S15',2,'2021-1-25',N'Nguyên vẹn'),
  (000014,'S16',1,'2021-12-1',N'Nguyên vẹn'),
  (000015,'S20',3,'2021-12-5',N'Nguyên vẹn'),
  (000016,'S21',2,'2021-12-5',N'Nguyên vẹn');


insert TraSach
values 
		(000001,'NV04',100001,null),
       (000002,'NV02',100002,null),
       (000003,'NV02',100003,null),
       (000004,'NV02',100004,null),
       (000005,'NV02',100005,null),
       (000006,'NV02',100006,null),
       (000007,'NV02',100007,null),
       (000008,'NV03',100008,null),
       (000009,'NV03',100009,null),
       (000010,'NV03',100010,null),
       (000011,'NV03',100011,null),
       (000012,'NV01',100012,null),
       (000013,'NV01',100019,null),
       (000014,'NV01',100020,null),
       (000015,'NV01',100012,null),
       (000016,'NV01',100015,null);


insert ChiTietTra VALUES
(000003,'S03',1,'2021-7-5',N'Nguyên vẹn'),
(000004,'S04',2,'2021-5-5',N'Rách'),
(000005,'S02',3,'2021-12-15',N'Nguyên vẹn'),
(000006,'S05',3,'2021-12-23',N'Nguyên vẹn'),
(000007,'S06',2,'2021-11-4',N'Nguyên vẹn'),
(000009,'S10',3,'2021-10-2',N'Rách'),
(000010,'S11',2,'2021-11-1',N'Nguyên vẹn'),
(000012,'S14',3,'2021-12-30',N'Nguyên vẹn'),
(000013,'S15',2,'2021-11-21',N'Hỏng');



/*
-- View
    1. Tạo view hiện ra các thông tin của sách
    2. Tạo view các sinh viên không phải học sinh trong trường
    3. Tạo view chứa thông tin của sách và nhà xuất bản
    4. Tạo view hiện ra các học sinh trong trường được cấp thẻ
    5. Tạo view những nhân viên tham gia vào quá trình cấp thẻ
    6. Tạo view lấy ra thông tin của sách được mượn nhiều nhất
    7. Tạo view chứa thông tin của sách và tác giả
    8. Tạo view chứa thông tin của sách và vị trí của nó

-- Procedure
    1. Viết store đầu vào là mã thẻ đầu ra là các loại sách và số lượng mà sinh viên đó mượn
    2. Viết store cho ra thông tin của người mượn và số lượng sách mà người đó mượn
    3. Viết store đầu vào là mã thẻ cho ra thông tin của người mượn và các loại sách người đó mượn
    4. Viết store đầu vào là mã nhân viên đầu ra là những thẻ mà nhân viên đó đã cấp
    5. Viết store cho ra số lượng người mượn trong từng tháng của một năm 2020
    6. Viết store cho ra số lượng người mượn trong 6 tháng gần nhất
    7.Nhập nhân viên quản lý và thêm thông tin cho nhân viên quan lý
    8.Nhập đồng thời mượn sách và chi tiết mượn sách với dữ liệu về thông tin mượn được lấy ra từ 1 bảng tạm

-- Functions
    1. Viết func đầu vào là mã thẻ đầu ra là số lần mượn của thẻ đó trong tháng 5 của năm 2020
    2. Viết func đầu vào là mã thẻ đầu ra là các loại sách mà người đó từng mượn
    3. Viết func đầu vào là tên nhà xuất bản đầu ra là các đầu sách mà nhà xuất bản đó đã in
    4. Viết func đầu vào là khu để sách ra là số lượng sách ở khu đó
    5. Viết func đầu vào là tên nhân viên ra là thông tin của các sinh viên được nhân viên đó cấp thẻ
    6. Viết func đầu vào loại sinh viên ra là tổng số sinh viên loại đó

--Trigger
	1.Cập nhật số lượng loại sách khi insert,update,delete bảng ChiTietTra 
	2.Cập nhật số lượng loại sách khi insert,update,delete bảng ChiTietMuon
	3.Xóa tất cả bản ghi liên quan trên bảng sách và chitietmuon khi trong chi tiết mượn đó có mượn sách với số lượng vượt quá số lượng hiện có
	4.Ngăn việc nhập sách rách hoặc hỏng vào bảng sách
	5.update loại sinh viên khi sinh viên hết hạn thẻ sinh viên
	6.cap nhat so luong sach trong loai sach khi insert,update,delete bảng Sách
	
 */
--1
CREATE VIEW ThongTinSach AS
    SELECT * FROM Sach
GO

-- 2
CREATE VIEW SinhVienNgoaiTruong AS
    SELECT
           sv.MaSinhVien,
           sv.TenSinhVien
    FROM SinhVien sv JOIN PhanLoai PL on sv.MaPhanLoai = PL.MaPhanLoai
    WHERE PL.MaPhanLoai = 2
GO

-- 3
CREATE VIEW NhaSanXuatSach AS
    SELECT
           S.MaSach,
           S.TenSach,
           NXB.TenNXB
    FROM Sach S
        JOIN NhaXuatBan NXB ON S.MaNXB = NXB.MaNXB
GO

-- 4
CREATE VIEW SinhVienCapThe AS
SELECT
       SV.MaSinhVien,
       SV.TenSinhVien,
       CT.NgayCap
FROM SinhVien SV
    JOIN CapThe CT ON SV.MaSinhVien = CT.MaSinhVien
WHERE SV.MaPhanLoai = 1
GO

-- 5
CREATE VIEW NhanVienCapThe AS
SELECT
       NV.MaNhanVien,NV.TenNhanVien,NV.MaBoPhan
FROM NhanVien NV
    JOIN CapThe CT on NV.MaNhanVien = CT.MaNhanVien
GROUP BY NV.MaNhanVien,NV.TenNhanVien,NV.MaBoPhan
GO

-- 6
CREATE VIEW SachMuonNhieuNhat AS
    WITH SachMuon AS (
        SELECT TOP 1
             S.TenSach,S.MaSach,COUNT(CTM.MaSach) AS SoLanMuon
        FROM Sach S
            JOIN ChiTietMuon CTM on S.MaSach = CTM.MaSach
        GROUP BY S.TenSach,S.MaSach
        ORDER BY COUNT(CTM.MaSach) DESC
    )SELECT 
             S.TenSach,S.MaSach
    FROM Sach S
        JOIN ChiTietMuon CTM on S.MaSach = CTM.MaSach
    GROUP BY S.TenSach,S.MaSach
    HAVING COUNT(CTM.MaSach) = (SELECT SachMuon.SoLanMuon FROM SachMuon)
GO

-- 7
CREATE VIEW ThongTinTacGia AS
    SELECT
           S.MaSach,
           S.TenSach,
           TG.TenTacGia,
           TG.ButDanh
    FROM Sach S
        JOIN TacGia TG on S.MaTacGia = TG.MaTacGia
GO

-- 8
CREATE VIEW ViTriSach AS
    SELECT
           S.MaSach,S.TenSach,VT.Ke,VT.Khu,VT.Ngan
    FROM Sach S
        JOIN ViTri VT on S.MaViTri = VT.MaViTri
GO

-- Store Procedure
-- 1
CREATE PROCEDURE SinhVienMuon(@mathe INT)
AS
    BEGIN
        SELECT
               S.TenSach,LS.MaLoai,LS.TenLoai,SoLuongMuon
        FROM MuonSach MS
            JOIN ChiTietMuon CTM on MS.MaMuon = CTM.MaMuon
            JOIN Sach S on CTM.MaSach = S.MaSach
            JOIN LoaiSach LS on LS.MaLoai = S.MaLoai
        WHERE @mathe = MS.MaThe
        GROUP BY LS.MaLoai,LS.TenLoai,MS.MaThe,SoLuongMuon,S.TenSach

    end
GO

EXEC SinhVienMuon 100006

-- 2
CREATE PROCEDURE ThongTinNguoiMuon
AS
    BEGIN
        SELECT
            SV.MaSinhVien,
            SV.TenSinhVien,
            S.TenSach,
            CTM.SoLuongMuon
        FROM SinhVien SV
            JOIN CapThe CT on SV.MaSinhVien = CT.MaSinhVien
            JOIN MuonSach MS on CT.MaThe = MS.MaThe
            JOIN ChiTietMuon CTM on MS.MaMuon = CTM.MaMuon
            JOIN Sach S on S.MaSach = CTM.MaSach
    end
GO

EXEC ThongTinNguoiMuon;

-- 3
CREATE PROCEDURE ChiTietThongTinMuon(@mathe INT)
AS
    BEGIN
        SELECT
            SV.MaSinhVien,
            SV.TenSinhVien,
            S.TenSach,
            LS.TenLoai
        FROM SinhVien SV
            JOIN CapThe CT on SV.MaSinhVien = CT.MaSinhVien
            JOIN MuonSach MS on CT.MaThe = MS.MaThe
            JOIN ChiTietMuon CTM on MS.MaMuon = CTM.MaMuon
            JOIN Sach S on S.MaSach = CTM.MaSach
            JOIN LoaiSach LS on S.MaLoai = LS.MaLoai
        WHERE MS.MaThe = @mathe
    end
GO

EXEC ChiTietThongTinMuon 100006

-- 4
CREATE PROCEDURE SoluongTheNhanVienDaCap(@manv CHAR(10))
AS
    BEGIN
        SELECT
            NV.TenNhanVien,
            COUNT(CT.MaThe) AS SoLuonCap
        FROM NhanVien NV JOIN CapThe CT on NV.MaNhanVien = CT.MaNhanVien
        WHERE @manv = NV.MaNhanVien
        GROUP BY NV.TenNhanVien
    end
GO

EXEC SoluongTheNhanVienDaCap 'NV04'

-- 5
CREATE PROCEDURE SoLuongMuonTungThang
AS
    BEGIN
            WITH SoLuong AS (
                SELECT
                    MONTH(CTM.NgayMuon) AS Thang,
                    COUNT(MS.MaThe) AS SoNguoiMuon
                FROM MuonSach MS JOIN ChiTietMuon CTM on MS.MaMuon = CTM.MaMuon
                GROUP BY MONTH(CTM.NgayMuon)
            )SELECT
                    monthInYears.MONTH,
                    CASE
                        WHEN SoLuong.SoNguoiMuon IS NULL THEN  0
                        ELSE SoLuong.SoNguoiMuon
                    END AS SoLuongNguoiNMuon
            FROM SoLuong RIGHT JOIN
                (SELECT 1 AS MONTH
                    UNION
                SELECT 2 AS MONTH
                    UNION
                SELECT 3 AS MONTH
                    UNION
                SELECT 4 AS MONTH
                    UNION
                SELECT 5 AS MONTH
                    UNION
                SELECT 6 AS MONTH
                    UNION
                SELECT 7 AS MONTH
                    UNION
                SELECT 8 AS MONTH
                    UNION
                SELECT 9 AS MONTH
                    UNION
                SELECT 10 AS MONTH
                    UNION
                SELECT 11 AS MONTH
                    UNION
                SELECT 12 AS MONTH) AS monthInYears
            ON monthInYears.MONTH = SoLuong.Thang

    end
GO

EXEC SoLuongMuonTungThang;

-- 6
CREATE PROCEDURE SoLuongNguoiMuon6ThangGanNhat
    AS
    BEGIN
        WITH Thang AS (
        SELECT
            MONTH(CTM.NgayMuon) AS CacThang,
            COUNT(MS.MaThe) AS SoLuong
        FROM MuonSach MS JOIN ChiTietMuon CTM on MS.MaMuon = CTM.MaMuon
        GROUP BY  MONTH(CTM.NgayMuon))
        SELECT
            MONTH(CustomMount.MONTH) AS Thang,
            CASE
                WHEN Thang.SoLuong IS NULL THEN 0
                ELSE Thang.SoLuong
            END AS SoLuongMuon
        FROM Thang RIGHT JOIN(
            SELECT DATEADD(mm, DATEDIFF(month , 0, GETDATE()) - 6,0)  AS MONTH
                            UNION
            SELECT DATEADD(MM, DATEDIFF(month , 0, GETDATE()) - 5,0)  AS MONTH
                            UNION
            SELECT DATEADD(MM, DATEDIFF(month , 0, GETDATE()) - 4, 0)  AS MONTH
                            UNION
            SELECT DATEADD(MM, DATEDIFF(month , 0, GETDATE()) - 3, 0)  AS MONTH
                            UNION
            SELECT DATEADD(MM, DATEDIFF(month , 0, GETDATE()) - 2, 0)  AS MONTH
                            UNION
            SELECT DATEADD(MM, DATEDIFF(month , 0, GETDATE()) - 1, 0)  AS MONTH) AS CustomMount
            ON Thang.CacThang = MONTH(CustomMount.MONTH)
    END
GO

EXEC SoLuongNguoiMuon6ThangGanNhat;

--7
CREATE PROCEDURE NhapNhanVien (@MaNV NVARCHAR(10),@TenNV NVARCHAR(20),@NgaySinh DATETIME,@DiaChi NVARCHAR(20)
								,@MaBoPhan NVARCHAR(10),@TenBoPhan NVARCHAR(20))
AS
BEGIN
    
	IF(@MaBoPhan NOT IN (SELECT TenBoPhan FROM dbo.BoPhan))
	BEGIN
	    BEGIN TRAN
		BEGIN TRY
		    INSERT BoPhan VALUES (@MaBoPhan,@TenBoPhan)
			INSERT NhanVien VALUES (@MaNV,@TenNV,@NgaySinh,@DiaChi,@MaBoPhan)
			COMMIT TRAN
		END TRY

		BEGIN CATCH
			PRINT 'Error: ' + ERROR_MESSAGE();
			ROLLBACK TRAN
		END CATCH
	END

	ELSE
	BEGIN
	    BEGIN TRY
	        INSERT NhanVien VALUES (@MaNV,@TenNV,@NgaySinh,@DiaChi,@MaBoPhan)
	    END TRY

		BEGIN CATCH
			PRINT 'Error: ' + ERROR_MESSAGE();
			ROLLBACK TRAN
		END CATCH
	END
END



--8
CREATE TABLE #Temp
(
	MaMuon NVARCHAR(10),
	MaSach NVARCHAR(10),
	SoLuongMuon NVARCHAR(10),
	NgayTra DATETIME,
	TinhTrangKhiMuon NVARCHAR(10)
)

GO
CREATE PROCEDURE NhapThongTinMuonSach (@MaMuon NVARCHAR(10),@MaNV NVARCHAR(10),@MaThe NVARCHAR(10),@TongSoSachMuon INT)
AS
BEGIN
    BEGIN TRAN
	BEGIN TRY
		INSERT INTO MuonSach VALUES (@MaMuon,@MaNV,@MaThe,@TongSoSachMuon)
		INSERT INTO ChiTietMuon(MaMuon,MaSach,SoLuongMuon,NgayTra,TinhTrangKhiMuon)
		SELECT * FROM #Temp WHERE MaMuon = @MaMuon 
		COMMIT TRAN
	END TRY

	BEGIN CATCH
		PRINT 'Error: ' + ERROR_MESSAGE()
		ROLLBACK TRAN
	END CATCH
	    
END



-- Functions

-- 1
CREATE FUNCTION SoLanMuon (@mathe INT) RETURNS INT
AS
    BEGIN
        DECLARE @lanmuon INT;
        SELECT
               @lanmuon = COUNT(MS.MaMuon)
        FROM MuonSach MS
        WHERE MaThe = @mathe

        RETURN @lanmuon
    end
GO


DECLARE @mathe INT = 100001
DECLARE @result INT
EXEC
	@result = SoLanMuon @mathe
SELECT @result AS SoLanMuon

-- 2
CREATE FUNCTION LoaiSachTungMuon (@mathe INT) RETURNS TABLE
RETURN (
        SELECT
               S.TenSach,
               LS.TenLoai
        FROM MuonSach MS
            JOIN ChiTietMuon CTM on MS.MaMuon = CTM.MaMuon
            JOIN Sach S on S.MaSach = CTM.MaSach
            JOIN LoaiSach LS on LS.MaLoai = S.MaLoai
        WHERE @mathe = MS.MaThe
    )
GO

BEGIN
	DECLARE @mathe INT = 100001
	SELECT * FROM LoaiSachTungMuon(@mathe)
END

-- 3
CREATE FUNCTION SanXuatSach(@tennhasx NVARCHAR(50)) RETURNS TABLE
RETURN (
        SELECT
               S.MaSach,
               S.TenSach
        FROM Sach S
            JOIN NhaXuatBan NXB on S.MaNXB = NXB.MaNXB
       WHERE NXB.TenNXB LIKE N'%'+@tennhasx+'%'
    )
GO

BEGIN
	DECLARE @tennhasx NVARCHAR(50) = N'NXB ĐH GTVT'
	SELECT * FROM SanXuatSach(@tennhasx)
END
--4
 go
 create function soluongsachVT(@khu char(10))
 returns int as begin 
	DECLARE @soluong int
	select @soluong = sum(s.SoLuong)
	from Sach s, ViTri vt 
	where s.MaViTri = vt.MaViTri and  @khu = vt.Khu      
	group by vt.Khu
	return @soluong
	
 end

 print dbo.soluongsachVT('A')
 select sum(SoLuong) from Sach where MaViTri =1 or MaViTri =2 or MaViTri =3

--5
 go
 create function nhanvien_hocsinh(@tennv nvarchar(50))
 returns @table table (TenSinhVien nvarchar(50)) as begin
	insert into @table
	Select TenSinhVien from SinhVien inner join CapThe on SinhVien.MaSinhVien = CapThe.MaSinhVien 
	inner join NhanVien on CapThe.MaNhanVien = NhanVien.MaNhanVien where TenNhanVien = @tennv 
	order by SinhVien.MaSinhVien desc
	return 
 end
 drop function nhanvien_hocsinh

select * from nhanvien_hocsinh(N'Phan Cao')

--6
go
create function loaisinhvien(@loaisv nvarchar(20))
returns @table table (PhanLoai nvarchar(20),SoLuong int)  as begin
	insert into @table
	select @loaisv,COUNT(MaSinhVien)  from SinhVien inner join
	 PhanLoai  on SinhVien.MaPhanLoai = PhanLoai.MaPhanLoai
	where  PhanLoai.PhanLoai = @loaisv group by PhanLoai.PhanLoai

	return 
end
drop function loaisinhvien
select * from  loaisinhvien(N'Sinh Viên')
select * from loaisinhvien(N'Người Ngoài Trường')


--Trigger

--1
CREATE TRIGGER CapNhatSoLuongSachKhiDuocTra ON dbo.ChiTietTra
FOR INSERT,DELETE,UPDATE
AS
BEGIN
    DECLARE @Count1 INT,@Count2 INT,@MaLoai NVARCHAR(10)
	SELECT @Count1 = COUNT(*) FROM Inserted
	SELECT @Count2 = COUNT(*) FROM Deleted
	--Update
	IF (@Count1 + @Count2 = 2)
	BEGIN
	    DECLARE @x1 INT,@x2 INT,@SoLuong INT,@MaSach1 NVARCHAR(10),@MaSach2 NVARCHAR(10)
		SELECT @x1 = SoLuongTra,@MaSach1 = MaSach FROM Inserted
		SELECT @x2 = SoLuongTra,@MaSach2 = MaSach FROM Deleted

		UPDATE dbo.Sach SET SoLuong = SoLuong + @x1 WHERE MaSach = @MaSach1
		UPDATE dbo.Sach SET SoLuong = SoLuong - @x2 WHERE MaSach = @MaSach2
		
	END
	
	ELSE
	BEGIN
		DECLARE @MaSach NVARCHAR(10),@x INT
		IF (@Count1 = 1)
		BEGIN
			
			SELECT @x = SoLuongTra,@MaSach = MaSach FROM Inserted
			
			UPDATE dbo.Sach SET SoLuong = SoLuong + @x WHERE MaSach = @MaSach
		END

		ELSE
		BEGIN
		    SELECT @x = SoLuongTra,@MaSach = MaSach FROM Deleted
			
			UPDATE dbo.Sach SET SoLuong = SoLuong - @x
			WHERE MaSach = @MaSach
		END
	END
END

--2
CREATE TRIGGER CapNhatSoLuongSachKhiMuon ON dbo.ChiTietMuon
FOR INSERT,DELETE,UPDATE
AS
BEGIN
    DECLARE @Count1 INT,@Count2 INT,@MaLoai NVARCHAR(10)
	SELECT @Count1 = COUNT(*) FROM Inserted
	SELECT @Count2 = COUNT(*) FROM Deleted
	--Update
	IF (@Count1 + @Count2 = 2)
	BEGIN
	    DECLARE @x1 INT,@x2 INT,@SoLuong INT,@MaSach1 NVARCHAR(10),@MaSach2 NVARCHAR(10)
		SELECT @x1 = SoLuongMuon,@MaSach1 = MaSach FROM Inserted
		SELECT @x2 = SoLuongMuon,@MaSach2 = MaSach FROM Deleted
		UPDATE dbo.Sach SET SoLuong = SoLuong + @x2 WHERE MaSach = @MaSach2
		UPDATE dbo.Sach SET SoLuong = SoLuong - @x1 WHERE MaSach = @MaSach2
	END
	
	ELSE
	BEGIN
		DECLARE @MaSach NVARCHAR(10),@x INT
		--Insert
		IF (@Count1 = 1)
		BEGIN
			
			SELECT @x = SoLuongMuon,@MaSach = MaSach FROM Inserted
			UPDATE dbo.Sach SET SoLuong = SoLuong - @x
			WHERE MaSach = @MaSach
		END
		--Delete
		ELSE
		BEGIN
		    SELECT @x = SoLuongMuon,@MaSach = MaSach FROM Deleted
			UPDATE dbo.Sach SET SoLuong = SoLuong + @x
			WHERE MaSach = @MaSach
		END
	END
END
GO

--3
CREATE TRIGGER XoaThongTinMuonSach ON dbo.ChiTietMuon
FOR INSERT
AS
BEGIN
    DECLARE @SoLuongMuon INT,@MaSach NVARCHAR(10),@SoLuong INT
	SELECT @SoLuongMuon = SoLuongMuon,@MaSach = MaSach FROM Inserted
	SELECT @SoLuong = SoLuong FROM dbo.Sach WHERE MaSach = @MaSach
	IF(@SoLuongMuon > @SoLuong)
	BEGIN
	    ROLLBACK TRAN
		DECLARE @MaMuon NVARCHAR(10)
		SELECT @MaMuon = MaMuon FROM Inserted
		DELETE FROM dbo.ChiTietMuon WHERE MaMuon = @MaMuon
		DELETE FROM dbo.MuonSach WHERE MaMuon = @MaMuon
	END
END


--4
CREATE TRIGGER TinhTrangSach ON dbo.Sach
FOR INSERT
AS
BEGIN
	DECLARE @masach CHAR(5), @tinhtrang NVARCHAR(30)
	SELECT @masach = Inserted.MaSach FROM Inserted
	IF(@tinhtrang = N'Hỏng' OR @tinhtrang = N'Rách') ROLLBACK TRANSACTION
END
GO

--5
create trigger doiPhanLoai on SinhVien
for insert,update 
as
begin
declare @maphanloai char(3) ,@masinhvien int
select @masinhvien = MaSinhVien, @maphanloai = MaPhanLoai
from inserted
if @masinhvien <(YEAR(getdate())%100-5)*10000000 
update SinhVien set MaPhanLoai = '2' where MaSinhVien = @masinhvien
end

--6
 create trigger updateLoaiSach on Sach after insert,delete,update
 as begin

update LoaiSach set SoLuong = ISNULL(ls.SoLuong,0)+(select SoLuong from inserted where MaLoai = ls.MaLoai)
from inserted,LoaiSach ls where ls.MaLoai = inserted.MaLoai
update LoaiSach set SoLuong = ISNULL(ls.SoLuong,0)+(select SoLuong from inserted where MaTacGia = ls.MaLoai)
from deleted,LoaiSach ls where ls.MaLoai =	deleted.MaLoai

 end

