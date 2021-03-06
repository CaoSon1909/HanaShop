USE [master]
GO
/****** Object:  Database [P0013]    Script Date: 1/20/2021 10:58:27 PM ******/
CREATE DATABASE [P0013]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'P0013', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS1\MSSQL\DATA\P0013.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'P0013_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS1\MSSQL\DATA\P0013_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [P0013] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [P0013].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [P0013] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [P0013] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [P0013] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [P0013] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [P0013] SET ARITHABORT OFF 
GO
ALTER DATABASE [P0013] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [P0013] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [P0013] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [P0013] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [P0013] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [P0013] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [P0013] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [P0013] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [P0013] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [P0013] SET  ENABLE_BROKER 
GO
ALTER DATABASE [P0013] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [P0013] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [P0013] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [P0013] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [P0013] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [P0013] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [P0013] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [P0013] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [P0013] SET  MULTI_USER 
GO
ALTER DATABASE [P0013] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [P0013] SET DB_CHAINING OFF 
GO
ALTER DATABASE [P0013] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [P0013] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [P0013] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [P0013] SET QUERY_STORE = OFF
GO
USE [P0013]
GO
/****** Object:  Table [dbo].[tbl_CartDetail]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_CartDetail](
	[ID] [uniqueidentifier] NOT NULL,
	[userEmail] [varchar](320) NOT NULL,
	[foodID] [uniqueidentifier] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
 CONSTRAINT [PK__tbl_Cart__3214EC27C4A43094] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Food]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Food](
	[ID] [uniqueidentifier] NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[description] [nvarchar](max) NULL,
	[price] [float] NOT NULL,
	[createDate] [bigint] NOT NULL,
	[currentQuantity] [int] NOT NULL,
	[categoryID] [int] NOT NULL,
	[status] [int] NOT NULL,
 CONSTRAINT [PK__tbl_Food__3214EC27E46070E4] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_FoodCategory]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_FoodCategory](
	[ID] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_FoodImage]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_FoodImage](
	[ID] [uniqueidentifier] NOT NULL,
	[link] [varchar](260) NOT NULL,
	[foodID] [uniqueidentifier] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Order]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Order](
	[ID] [uniqueidentifier] NOT NULL,
	[deliveryAddress] [nvarchar](max) NOT NULL,
	[total] [float] NOT NULL,
	[orderDate] [bigint] NOT NULL,
	[status] [int] NOT NULL,
	[userEmail] [varchar](320) NULL,
	[paymentTypeID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_OrderDetail]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_OrderDetail](
	[ID] [uniqueidentifier] NOT NULL,
	[orderID] [uniqueidentifier] NOT NULL,
	[foodID] [uniqueidentifier] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_PaymentType]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_PaymentType](
	[ID] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[ID] [int] NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
	[roleDescription] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 1/20/2021 10:58:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[email] [varchar](320) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[fullname] [nvarchar](150) NOT NULL,
	[address] [nvarchar](max) NULL,
	[phoneNum] [varchar](13) NULL,
	[dateOfBirth] [bigint] NULL,
	[gender] [int] NOT NULL,
	[status] [int] NOT NULL,
	[roleID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'42c316d5-7005-4e81-9b72-1457c259e99e', N'xoi dau den', N'xoi', 234, 1611157610996, 678, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'e05771d9-b155-47a5-ba83-1f9313bea8a1', N'ca phao', N'ca phao ngon', 249, 1611157582348, 239, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'aa082ff9-0162-4000-943c-228441ac0652', N'7up', N'nuoc ngot', 26, 1611157668814, 2, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'dfc1539c-f974-4e7b-89be-22ab63275f42', N'tom chien xu', N'tom', 468, 1611157625808, 23, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'31908070-77ff-44e9-bac6-2cf35c2b3131', N'mi cay han quoc', N'mi cay', 69, 1611157563880, 298, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'ed245d21-d49a-4631-a3c5-3a386366d460', N'dau hu chien gion', N'dau hu', 56, 1611157463522, 10, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'6d4c868a-54e8-46ba-b9e1-3bb2463443ef', N'bia sai gon', N'bia', 899, 1611157550392, 200, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'f22278d6-7b3e-444f-b3a8-47d6c5f614bb', N'gio heo ham hat sen', N'gio heo', 242, 1611157640663, 34, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'0fabb080-489c-4054-b18d-4941595cb688', N'sua chua da', N'sua chua da thom ngon', 29, 1611157434439, 201, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'75bda6a6-396c-474d-ab16-4e24924daf59', N'che thai', N'che thai thom ngon', 100, 1554829200000, 200, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'f5b3401d-5b77-4474-8b8d-5cfa78bc3aea', N'pepsi', N'nuoc ngot', 23, 1611157654393, 57, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'fd31ccda-d4cb-4c90-8e90-76d84b98ace7', N'tra sua tran chau', N'milk tea', 29, 1611157485112, 21, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'63f89007-153d-4103-a2e2-77da760cbe0c', N'xoi che', N'xoi', 357, 1611157594729, 22, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'2d1d2167-870e-4adc-99f2-95206e200fcf', N'pho', N'pho de nhat', 19, 1611157495688, 100, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'68ba08d3-41eb-4774-bdec-a457ac446025', N'cafe sua da', N'cafe', 22, 1611157531597, 56, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'60736847-e9f3-49d9-ae65-a9c8b7dd87b6', N'chuoi chien', N'chuoi chien ngon', 202, 1569862800000, 277, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'dcf83db1-2fa8-41a9-a8d7-ba6efdca6f55', N'kim chi', N'kim chi cu cai', 27, 1611157520226, 299, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'4dc65a7f-8d23-4bc2-8d87-c545851bd905', N'mi an lien', N'mi goi', 10, 1611157507425, 100, 1, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'78158d58-8316-4f4b-914b-d9830a3cf32c', N'che buoi', N'che buoi thom ngon', 522, 1510419600000, 212, 2, 1)
INSERT [dbo].[tbl_Food] ([ID], [name], [description], [price], [createDate], [currentQuantity], [categoryID], [status]) VALUES (N'4608c05f-c0e5-4bfe-912a-ddcdd6812b86', N'com ga', N'com ga chien gion', 292, 1611157447143, 1000, 1, 1)
GO
INSERT [dbo].[tbl_FoodCategory] ([ID], [name], [description]) VALUES (1, N'fast food', NULL)
INSERT [dbo].[tbl_FoodCategory] ([ID], [name], [description]) VALUES (2, N'fast drink', NULL)
GO
INSERT [dbo].[tbl_Role] ([ID], [roleName], [roleDescription]) VALUES (1, N'admin', NULL)
INSERT [dbo].[tbl_Role] ([ID], [roleName], [roleDescription]) VALUES (2, N'user', NULL)
GO
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [address], [phoneNum], [dateOfBirth], [gender], [status], [roleID]) VALUES (N'admin', N'admin', N'admin dep trai', N'fpt', N'0523787357', 1510419600000, 1, 1, 1)
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [address], [phoneNum], [dateOfBirth], [gender], [status], [roleID]) VALUES (N'user', N'user', N'user dep trai', N'fpt', N'0523787357', 1510419600000, 1, 1, 2)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tbl_Food__72E12F1B17BDAA9F]    Script Date: 1/20/2021 10:58:28 PM ******/
ALTER TABLE [dbo].[tbl_Food] ADD  CONSTRAINT [UQ__tbl_Food__72E12F1B17BDAA9F] UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tbl_Food__72E12F1BA84C28B2]    Script Date: 1/20/2021 10:58:28 PM ******/
ALTER TABLE [dbo].[tbl_FoodCategory] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tbl_Role__B1947861404C6DEE]    Script Date: 1/20/2021 10:58:28 PM ******/
ALTER TABLE [dbo].[tbl_Role] ADD UNIQUE NONCLUSTERED 
(
	[roleName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_CartDetail] ADD  CONSTRAINT [DF__tbl_CartDeta__ID__46E78A0C]  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[tbl_Food] ADD  CONSTRAINT [DF__tbl_Food__ID__2F10007B]  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[tbl_FoodImage] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[tbl_Order] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[tbl_OrderDetail] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[tbl_User] ADD  DEFAULT ((0)) FOR [gender]
GO
ALTER TABLE [dbo].[tbl_CartDetail]  WITH CHECK ADD  CONSTRAINT [FK__tbl_CartD__foodI__48CFD27E] FOREIGN KEY([foodID])
REFERENCES [dbo].[tbl_Food] ([ID])
GO
ALTER TABLE [dbo].[tbl_CartDetail] CHECK CONSTRAINT [FK__tbl_CartD__foodI__48CFD27E]
GO
ALTER TABLE [dbo].[tbl_CartDetail]  WITH CHECK ADD  CONSTRAINT [FK__tbl_CartD__userE__47DBAE45] FOREIGN KEY([userEmail])
REFERENCES [dbo].[tbl_User] ([email])
GO
ALTER TABLE [dbo].[tbl_CartDetail] CHECK CONSTRAINT [FK__tbl_CartD__userE__47DBAE45]
GO
ALTER TABLE [dbo].[tbl_Food]  WITH CHECK ADD  CONSTRAINT [FK__tbl_Food__catego__31EC6D26] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tbl_FoodCategory] ([ID])
GO
ALTER TABLE [dbo].[tbl_Food] CHECK CONSTRAINT [FK__tbl_Food__catego__31EC6D26]
GO
ALTER TABLE [dbo].[tbl_FoodImage]  WITH CHECK ADD  CONSTRAINT [FK__tbl_FoodI__foodI__35BCFE0A] FOREIGN KEY([foodID])
REFERENCES [dbo].[tbl_Food] ([ID])
GO
ALTER TABLE [dbo].[tbl_FoodImage] CHECK CONSTRAINT [FK__tbl_FoodI__foodI__35BCFE0A]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD FOREIGN KEY([paymentTypeID])
REFERENCES [dbo].[tbl_PaymentType] ([ID])
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD FOREIGN KEY([userEmail])
REFERENCES [dbo].[tbl_User] ([email])
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__tbl_Order__foodI__4222D4EF] FOREIGN KEY([foodID])
REFERENCES [dbo].[tbl_Food] ([ID])
GO
ALTER TABLE [dbo].[tbl_OrderDetail] CHECK CONSTRAINT [FK__tbl_Order__foodI__4222D4EF]
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_Order] ([ID])
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tbl_Role] ([ID])
GO
ALTER TABLE [dbo].[tbl_CartDetail]  WITH CHECK ADD  CONSTRAINT [CK__tbl_CartD__price__4AB81AF0] CHECK  (([price]>(0)))
GO
ALTER TABLE [dbo].[tbl_CartDetail] CHECK CONSTRAINT [CK__tbl_CartD__price__4AB81AF0]
GO
ALTER TABLE [dbo].[tbl_CartDetail]  WITH CHECK ADD  CONSTRAINT [CK__tbl_CartD__quant__49C3F6B7] CHECK  (([quantity]>(0)))
GO
ALTER TABLE [dbo].[tbl_CartDetail] CHECK CONSTRAINT [CK__tbl_CartD__quant__49C3F6B7]
GO
ALTER TABLE [dbo].[tbl_Food]  WITH CHECK ADD  CONSTRAINT [CK__tbl_Food__curren__30F848ED] CHECK  (([currentQuantity]>=(0)))
GO
ALTER TABLE [dbo].[tbl_Food] CHECK CONSTRAINT [CK__tbl_Food__curren__30F848ED]
GO
ALTER TABLE [dbo].[tbl_Food]  WITH CHECK ADD  CONSTRAINT [CK__tbl_Food__price__300424B4] CHECK  (([price]>=(0)))
GO
ALTER TABLE [dbo].[tbl_Food] CHECK CONSTRAINT [CK__tbl_Food__price__300424B4]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD CHECK  (([total]>(0)))
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD CHECK  (([price]>(0)))
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD CHECK  (([quantity]>(0)))
GO
USE [master]
GO
ALTER DATABASE [P0013] SET  READ_WRITE 
GO
