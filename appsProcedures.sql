/*

	------------------------getApps
	@order 1-3
	1: we sort by name
	2: we sort by price
	3: we sort by numDownloads

	@isAsc : 
	0: ascending orderge
	1: descending order
*/

CREATE PROCEDURE getApps 
	@order	INT,
	@isAsc	BIT
	AS
	BEGIN
		IF	(@order = 1) AND 
			(@isAsc = 0)
		BEGIN
		-- order by name, descending
			SELECT	a.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads
			FROM Apps	a
			ORDER BY a.appName DESC, a.numDownloads DESC
		END ELSE IF (@order = 1) AND
					(@isAsc = 1)
		BEGIN
		-- order by name, ascending
			SELECT	a.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads
			FROM Apps	a
			ORDER BY a.appName ASC, a.numDownloads DESC

		END ELSE IF (@order = 2) AND
					(@isAsc = 0) 
		BEGIN
		-- order by price, descending
			SELECT	a.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads
			FROM Apps	a
			ORDER BY a.price	DESC, a.appName DESC
		END ELSE IF (@order = 2) AND
					(@isAsc = 1) 
		BEGIN
		-- order by price, Ascending
			SELECT	a.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads
			FROM Apps	a
			ORDER BY a.price	ASC, a.appName DESC
		END ELSE IF (@order = 3) AND
					(@isAsc = 0) 
		BEGIN
		-- order by numDownloads, Descending
			SELECT	a.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads
			FROM Apps	a
			ORDER BY a.numDownloads DESC, a.appName DESC
		END ELSE IF (@order = 3) AND
					(@isAsc = 1) 
		BEGIN
		-- order by numDownloads, Descending
			SELECT	a.appId,
					a.appName,
					a.description,
					a.price,
					a.numDownloads
			FROM Apps	a
			ORDER BY a.numDownloads ASC, a.appName DESC
		END
		

	END

	EXEC getApps 1, 1