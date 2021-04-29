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


DROP PROCEDURE IF EXISTS buyApp
GO

/*
	buyApp - buys an app if the user doesn't already have this app.
	updates Users to decrease balance accordingly and adds the app to userApps
	returns -1 if the user already has the app
*/
CREATE PROCEDURE buyApp
	@userId		INT,
	@appId		INT,
	@comment	VARCHAR(MAX)
AS
BEGIN
	SET NOCOUNT ON
	IF EXISTS (SELECT appId FROM userApps WHERE (appId = @appId) AND (userId = @userId))
		SELECT -1
	ELSE BEGIN
		INSERT INTO userApps(userId, appId, comment)
			VALUES (@userId, @appId, @comment)

		UPDATE Users  
		SET balance = balance - (
			SELECT a.price
			FROM Apps a
			WHERE a.appId = @appId
		)
		WHERE (Users.userId = @userId)
	END
END
GO

-- EXEC buyApp 1, 2, 'this game is amazing!'
/*
	This procedure gets each of the current game's comments
*/
ALTER PROCEDURE getUserApps
		@appId		INT
AS 
BEGIN
	sET NOCOUNT ON
	SELECT	userApps.appId,
			a.appName,
			userApps.userId,
			u.username,
			userApps.comment	
	FROM userApps
		JOIN Users u ON		userApps.userId = u.userId
		JOIN Apps a		ON userApps.appId = a.appId
	WHERE userApps.appId = @appId
END
GO
-- EXEC getUserApps 6
/*
	This
*/
ALTER PROCEDURE getAllUserApps
AS
BEGIN
	SET NOCOUNT ON
	SELECT	userApps.appId,
			a.appName,
			userApps.userId,
			u.username,
			userApps.comment	
	FROM userApps
		JOIN Users u ON		userApps.userId = u.userId
		JOIN Apps a		ON userApps.appId = a.appId

END