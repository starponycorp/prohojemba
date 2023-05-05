-- Создание разрешений
insert into permissions values
    ('EDIT_TITLES', 'Создание, редактирование и удаление тайтлов'),
    ('EDIT_PERMISSIONS', 'Создание, редактирование и удаление разрешений'),
    ('EDIT_ACCOUNTS', 'Редактирование других пользователей');

-- Создание типов тайтлов
insert into types values
    ('games', 'Игры'),
    ('films', 'Фильмы'),
    ('series', 'Сериалы'),
    ('anime', 'Аниме');

-- Создание тайтлов
insert into titles values
    (1, 'Persona 5', '/covers/qqqqqqqqqqqqqqqqqqqqqqqqqqqq.webp', 'games'),
    (2, 'Half Life 2', '/covers/half-life.webp', 'games'),
    (3, 'Borderlands', '/covers/borderlands.webp', 'games'),
    (4, 'Diablo 3', '/covers/diablo3.webp', 'games'),
    (5, 'Danganronpa', '/covers/danganronpa.webp', 'games'),
    (6, 'Breaking Bad', '/covers/bb.webp', 'series'),
    (7, 'Wednesday', '/covers/wednesday.webp', 'series'),
    (8, 'Улицы разбитых фонарей', '/covers/streets.webp', 'series'),
    (9, 'Ментозавры', '/covers/dinopolice.webp', 'series'),
    (10, 'Star Wars: The Clone Wars', '/covers/star-wars-c.webp', 'series'),
    (11, 'Uncharted 4', '/covers/uncharted.webp', 'films'),
    (12, 'Alien', '/covers/alien.webp', 'films'),
    (13, 'The Thing', '/covers/thing.webp', 'films'),
    (14, 'Attack on titans (Season 1)', '/covers/at1.webp', 'anime'),
    (15, 'Attack on titans (Final final final season)', '/covers/atf.webp', 'anime'),
    (16, 'Death Note', '/covers/dn.webp', 'anime'),
    (17, 'Boku no Piko', '/covers/bnp.webp', 'anime');