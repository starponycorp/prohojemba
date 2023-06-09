# Prohojemba

### Описание

REST API сервер для сервиса по отслеживанию фильмов, игр, аниме и других продуктов человеческой жизнедеятельности.

### Инфраструктура

* Nginx - проксирование, ssl, раздача фронта с медиа
* PostgreSQL - хранение данных
* Redis - хранение текущих сессий пользователей
* ElasticSearch - поисковая система
* [image-uploader](https://github.com/starponycorp/image-uploader)

![Инфраструктура проекта](/infrastructure.jpg)

### База данных

![База данных проекта](/db.jpg)

### Функционал

Здесь перечислен планируемый и реализованный функционал.

- Авторизация и аутентификация
- [ ] Регистрация пользователя
- [ ] Авторизация при помощи email и пароля
- [ ] JWT Аутентификация
- [ ] Отправка кодов верификации на email
- [ ] Сброс пароля
- [ ] Смена пароля
- [ ] Смена email
- Работа с тайтлами
- [x] Создание записи тайтла
- [x] Редактирование записи тайтла
- [x] Удаление записи тайтла
- [x] Просмотр списка тайтлов, тайтлы выводятся в алфавитном порядке
- [x] Поиск по имени тайтла
- [x] Фильтр тайтлов по типам
- Пользователи
- [ ] Редактирование текущего пользователя
- [ ] Блокировка пользователей
- [ ] Редактирование разрешений пользователей
- Работа с типами
- [x] Создание новых типов
- [x] Редактирование существующего типа
- [x] Удаление типа