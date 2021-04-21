/*

	------------------------getApps
	@order 0-3
	0: we sort by name
	1: we sort by price
	2: we sort by numDownloads
	3: we sort by rating

	@isAsc : 
	0: ascending order
	1: descending order
*/

ALTER PROCEDURE getApps 
	@order	INT,
	@isAsc	BIT
	AS
	BEGIN
		IF	(@order = 0) AND 
			(@isAsc = 0)
		BEGIN
		-- order by name, descending
			SELECT	*
			FROM Apps	a
			ORDER BY a.appName DESC, a.numDownloads DESC
		END ELSE IF (@order = 0) AND
					(@isAsc = 1)
		BEGIN
		-- order by name, ascending
			SELECT	*
			FROM Apps	a
			ORDER BY a.appName ASC, a.numDownloads DESC

		END ELSE IF (@order = 1) AND
					(@isAsc = 0) 
		BEGIN
		-- order by price, descending
			SELECT	*
			FROM Apps	a
			ORDER BY a.price	DESC, a.appName DESC
		END ELSE IF (@order = 1) AND
					(@isAsc = 1) 
		BEGIN
		-- order by price, Ascending
			SELECT	*
			FROM Apps	a
			ORDER BY a.price	ASC, a.appName DESC
		END ELSE IF (@order = 2) AND
					(@isAsc = 0) 
		BEGIN
		-- order by numDownloads, Descending
			SELECT	*
			FROM Apps	a
			ORDER BY a.numDownloads DESC, a.appName DESC
		END ELSE IF (@order = 2) AND
					(@isAsc = 1) 
		BEGIN
		-- order by numDownloads, Descending
			SELECT	*
			FROM Apps	a
			ORDER BY a.numDownloads ASC, a.appName DESC
		END ELSE IF	(@order = 3) AND 
					(@isAsc = 0)
		BEGIN
		-- order by rating, descending
		SELECT	*
			FROM Apps	a
			ORDER BY a.rating DESC, a.appName DESC
		END ELSE IF	(@order = 3) AND 
					(@isAsc = 1)
		BEGIN
		-- order by rating, ascending
		SELECT	*
			FROM Apps	a
			ORDER BY a.rating ASC, a.appName DESC
		END
		

	END

	

GO
-- test
EXEC getApps 1, 1
GO

CREATE PROCEDURE registerUser
	@username		VARCHAR(100),
	@password		VARCHAR(100),
	@balance		FLOAT,
	@accessLevel	INT
AS
BEGIN
	SET NOCOUNT ON
	IF NOT EXISTS (SELECT * FROM Users WHERE username = @username) BEGIN
		-- upsert it
		INSERT INTO		Users (username, password, balance, accesslevel)
		VALUES (@username, COMPRESS(@password), @balance, @accesslevel)
		SELECT 1
	END 
	
END
GO

CREATE PROCEDURE loginUser
	@username		VARCHAR(100),
	@password		VARCHAR(100)
AS
BEGIN
	SELECT *
	FROM Users	u
	WHERE	(u.username = @username) AND
			(u.password = COMPRESS(@password))
END
GO

-- EXEC loginUser 'DogeLord'

-- Procedure searchApps returns a table with rows  that contains the given substring
ALTER PROCEDURE searchApps
	@searchString		VARCHAR(100)
AS BEGIN
	SELECT *
	FROM Apps
	WHERE UPPER(Apps.appName) LIKE UPPER(CONCAT('%',@searchString, '%'))
	ORDER BY Apps.appName
END

--exec searchApps 'mount'
/*
BuyApps transfers application information to user's application library when a user chooses to purchase an application.
@appId is the appId of the chosen application
@userId is the id of the user at the time of executing the purchase
@Comments are messages left on each application by users that have purchased them
*/
DROP PROCEDURE IF EXISTS buyApps
GO

CREATE PROCEDURE buyApps
@appId		INT,
@userId		INT,
@Comments	VARCHAR(MAX)
AS
BEGIN
INSERT INTO userApps(userId, appId, Comments)
	VALUES (@userId, @appId, @Comments)

UPDATE Users  
SET balance = balance - (
SELECT a.price
FROM Apps a
)
WHERE (Users.userId = @userId)

END

/*
------------------------getUserApps
	@order 1-3
	1: we sort by name
	2: we sort by price
	3: we sort by numDownloads
	@isAsc : 
	0: ascending orderge
	1: descending order
*/

CREATE PROCEDURE getUserApps 
	@order	INT,
	@isAsc	BIT
	AS
	BEGIN
		IF	(@order = 1) AND 
			(@isAsc = 0)
		BEGIN
		-- order by name, descending
			SELECT	ua.userId,
					ua.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads,
					ua.Comments
			FROM userApps	ua
			LEFT JOIN Apps a ON (ua.appId = a.appId)
			ORDER BY a.appName DESC, a.numDownloads DESC
		END ELSE IF (@order = 1) AND
					(@isAsc = 1)
		BEGIN
		-- order by name, ascending
			SELECT	ua.userId,
					ua.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads,
					ua.Comments
			FROM userApps	ua
			LEFT JOIN Apps a ON (ua.appId = a.appId)
			ORDER BY a.appName ASC, a.numDownloads DESC

		END ELSE IF (@order = 2) AND
					(@isAsc = 0) 
		BEGIN
		-- order by price, descending
			SELECT	ua.userId,
					ua.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads,
					ua.Comments
			FROM userApps	ua
			LEFT JOIN Apps a ON (ua.appId = a.appId)
			ORDER BY a.price	DESC, a.appName DESC
		END ELSE IF (@order = 2) AND
					(@isAsc = 1) 
		BEGIN
		-- order by price, Ascending
			SELECT	ua.userId,
					ua.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads,
					ua.Comments
			FROM userApps	ua
			LEFT JOIN Apps a ON (ua.appId = a.appId)
			ORDER BY a.price	ASC, a.appName DESC
		END ELSE IF (@order = 3) AND
					(@isAsc = 0) 
		BEGIN
		-- order by numDownloads, Descending
			SELECT	ua.userId,
					ua.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads,
					ua.Comments
			FROM userApps	ua
			LEFT JOIN Apps a ON (ua.appId = a.appId)
			ORDER BY a.numDownloads DESC, a.appName DESC
		END ELSE IF (@order = 3) AND
					(@isAsc = 1) 
		BEGIN
		-- order by numDownloads, Descending
			SELECT	ua.userId,
					ua.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads,
					ua.Comments
			FROM userApps	ua
			LEFT JOIN Apps a ON (ua.appId = a.appId)
			ORDER BY a.numDownloads ASC, a.appName DESC
		END
	END

