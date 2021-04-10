USE master
GO

IF DB_ID('Apps201') IS NOT NULL
	DROP DATABASE Apps201
GO

CREATE DATABASE Apps201
GO 

USE Apps201
GO

CREATE TABLE Users(	
	userId		int IDENTITY(1,1)				NOT NULL,
	username	varchar(100)					NOT NULL,
	password	VARCHAR(MAX)					NOT NULL,
	balance		MONEY							NOT NULL,
	-- 0-2, 2 is admin
	accessLevel	INT					DEFAULT(0)	NOT NULL
	PRIMARY KEY(userId)
)
GO

CREATE TABLE Apps (
	appId			int IDENTITY(1,1)			NOT NULL,
	appName			VARCHAR(100)				NOT NULL,
	description		VARCHAR(MAX)				NOT NULL,
	price 			SMALLMONEY					NOT NULL,
	numDownloads	INT							NOT NULL
	PRIMARY KEY(appId)
)
GO

-- A user can have many apps
CREATE TABLE userApps (
	userId	INT					NOT NULL,
	appId	INT					NOT NULL,
	comment	VARCHAR(MAX)		NOT NULL
	CONSTRAINT PK_userApps PRIMARY KEY (userId	, appId),
	CONSTRAINT FK_userId FOREIGN KEY (userId) REFERENCES [dbo].[Users] ([userId]),
    CONSTRAINT FK_appId FOREIGN KEY (appId) REFERENCES [dbo].[Apps] ([appId])
)