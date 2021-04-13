﻿USE Apps201
GO

-- drop constraints so we can truncate the table
BEGIN TRAN
	ALTER TABLE userApps
	DROP	CONSTRAINT FK_userId,
			CONSTRAINT FK_appId
	TRUNCATE TABLE Users
	TRUNCATE TABLE  Apps
	TRUNCATE TABLE userApps
	ALTER TABLE userApps
	ADD CONSTRAINT FK_userId FOREIGN KEY (userId) REFERENCES [dbo].[Users] ([userId]),
		CONSTRAINT FK_appId FOREIGN KEY (appId) REFERENCES [dbo].[Apps] ([appId])

COMMIT TRAN





INSERT INTO  Apps (appName, description, price, numDownloads)
VALUES 		('Counter-Strike: Global Offensive', 
				'Counter-Strike: Global Offensive is a multiplayer first-person shooter developed by
				Valve and Hidden Path Entertainment. It is the fourth game in the Counter-Strike series.', 0.00, 999999999),

			('Hades', 'Hades is a roguelike action role-playing video game developed and published by Supergiant Games.', 24.99, 1738432),

			('Mount & Blade II: Bannerlord', 
			'Mount & Blade II: Bannerlord is a strategy action 
			role-playing video game developed by Turkish company TaleWorlds Entertainment', 49.99, 992219),
			('Minecraft', 
			'Minecraft is a sandbox video game developed by Mojang. ', 24.99, 99562219),
			('Grand Theft Auto V', 
			'Grand Theft Auto V is a 2013 action-adventure game developed by Rockstar North and published by Rockstar Games.', 14.99 , 519515151),
			('Fortnite', 
			'Fortnite is an online video game developed by Epic Games and released in 2017. ', 0.00 , 129198485),
			('Red Dead Redemption 2', 
			'Red Dead Redemption 2 is a 2018 action-adventure game developed and published by Rockstar Games.', 59.99, 8196242),
			('The Elder Scrolls V: Skyrim', 
			'The Elder Scrolls V: Skyrim is an open world action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks.', 39.99, 11852141),
			('PlayerUnknown''s Battlegrounds', 
			'PlayerUnknown''s Battlegrounds is an online multiplayer battle royale game developed and published by PUBG Corporation', 29.99, 1552652),
			('Dark Souls III', 
			'Dark Souls III is an action role-playing video game developed by FromSoftware and published by Bandai Namco Entertainment', 59.99, 9852561),
			('Old School RuneScape', 
			'Old School RuneScape is a massively multiplayer online role-playing game developed and published by Jagex.', 0.00, 1203691),
			('Apex Legends', 
			'Apex Legends is a free-to-play battle royale game developed by Respawn Entertainment and published by Electronic Arts.', 0.00, 6214521),
			('Dota 2', 
			'Dota 2 is a multiplayer online battle arena video game developed and published by Valve.', 0.00, 18253963),
			('Cyberpunk 2077', 
			'Cyberpunk 2077 is a 2020 action role-playing video game developed and published by CD Projekt.', 47.99, 5695321),
			('Valheim', 
			'Valheim is an upcoming survival and sandbox video game by the Swedish developer Iron Gate Studio. Published by Coffee Stain Studios', 19.99, 1236541),
			('Horizon Zero Dawn', 
			'Horizon Zero Dawn is a 2017 action role-playing game developed by Guerrilla Games and published by Sony Interactive Entertainment. ', 29.99, 21235693),
			('Rust', 
			'Rust is a multiplayer-only survival video game developed by Facepunch Studios', 39.99, 3695821),
			('Tom Clancy''s Rainbow Six Siege', 
			'Tom Clancy''s Rainbow Six Siege is an online tactical shooter video game developed by Ubisoft Montreal and published by Ubisoft.', 9.99, 8963654),
			('Among Us', 
			'Among Us is an online multiplayer social deduction game developed and published by American game studio Innersloth.', 3.99, 25639874),
			('Ark', 
			'Ark: Survival Evolved is a 2017 action-adventure survival video game developed by Studio Wildcard, in collaboration with Instinct Games, Efecto Studios, and Virtual Basement.', 49.99, 18963258),
			('Dead by Daylight', 
			'Dead by Daylight is an asymmetric survival horror video game developed by Behaviour Interactive.', 9.99, 9872583),
			('Destiny 2', 
			'Destiny 2 is a free-to-play online-only multiplayer first-person shooter video game developed by Bungie', 0.00, 7458521),
			('Payday 2', 
			'Payday 2 is a cooperative first-person shooter video game developed by Overkill Software and published by 505 Games. ', 9.99, 12365412),
			('Monster Hunter', 
			'Monster Hunter: World is an action role-playing game developed and published by Capcom and the fifth mainline installment in the Monster Hunter series.', 29.99, 15489874),
			('Left 4 Dead 2', 
			'Left 4 Dead 2 is a 2009 multiplayer survival horror game developed and published by Valve. ', 9.99, 5638521),
			('DayZ', 
			'DayZ is a survival video game developed and published by Bohemia Interactive.', 44.99, 6398521),
			('Fallout 4', 
			'Fallout 4 is an action role-playing game developed by Bethesda Game Studios and published by Bethesda Softworks.', 29.99, 14258963),
			('Sea Of Thieves', 
			'Sea of Thieves is a 2018 action-adventure game developed by Rare and published by Microsoft Studios. ', 39.99, 11258963),
			('Assassin''s Creed Odyssey', 
			'Assassin''s Creed Odyssey is an action role-playing video game developed by Ubisoft Quebec and published by Ubisoft.', 59.99, 9632581)

	GO


-- this is our default user!
exec registerUser 'DogeLord', 'This1sMyRealPa$$word', 6969696.00, 2
-- exec loginUser 'DogeLord', 'This1sMyRealPa$$word'