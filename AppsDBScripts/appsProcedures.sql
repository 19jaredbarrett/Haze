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