insert into users( username, email, password, created_at) values( 'admin', 'admin@aulab.it', '$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS','20240607' );

insert into roles( name ) values( 'ROLE_ADMIN' );
insert into roles( name ) values( 'ROLE_REVISOR' );
insert into roles( name ) values( 'ROLE_WRITER' );
insert into roles( name ) values( 'ROLE_USER' );

insert into users_roles( user_id, role_id ) values( 1, 1 );

insert into categories( name ) values( 'politica' ),('economia'),('food&drink'),('sport'),('intrattenimento'),('tech');



-- 2. Inserisci 10 utenti
INSERT INTO users (username, email, password) VALUES
('utente1', 'utente1@example.com', 'password1'),
('utente2', 'utente2@example.com', 'password2'),
('utente3', 'utente3@example.com', 'password3'),
('utente4', 'utente4@example.com', 'password4'),
('utente5', 'utente5@example.com', 'password5'),
('utente6', 'utente6@example.com', 'password6'),
('utente7', 'utente7@example.com', 'password7'),
('utente8', 'utente8@example.com', 'password8'),
('utente9', 'utente9@example.com', 'password9'),
('utente10', 'utente10@example.com', 'password10');


-- 3. Inserisci 50 articoli
-- In questo esempio, si usa una data fissa ('2025-08-01') per la colonna publish_date
-- e si associa ogni articolo a un utente ciclico (1,2,...,10, poi di nuovo 1,2,...).
INSERT INTO articles (title, subtitle, body, publish_date, user_id, category_id) VALUES
('Titolo Articolo 1', 'Sottotitolo Articolo 1', 'Contenuto dell''articolo 1', '2025-08-01', 1, 1),
('Titolo Articolo 2', 'Sottotitolo Articolo 2', 'Contenuto dell''articolo 2', '2025-08-01', 2, 1),
('Titolo Articolo 3', 'Sottotitolo Articolo 3', 'Contenuto dell''articolo 3', '2025-08-01', 3, 1),
('Titolo Articolo 4', 'Sottotitolo Articolo 4', 'Contenuto dell''articolo 4', '2025-08-01', 4, 1),
('Titolo Articolo 5', 'Sottotitolo Articolo 5', 'Contenuto dell''articolo 5', '2025-08-01', 5, 1),
('Titolo Articolo 6', 'Sottotitolo Articolo 6', 'Contenuto dell''articolo 6', '2025-08-01', 6, 1),
('Titolo Articolo 7', 'Sottotitolo Articolo 7', 'Contenuto dell''articolo 7', '2025-08-01', 7, 1),
('Titolo Articolo 8', 'Sottotitolo Articolo 8', 'Contenuto dell''articolo 8', '2025-08-01', 8, 1),
('Titolo Articolo 9', 'Sottotitolo Articolo 9', 'Contenuto dell''articolo 9', '2025-08-01', 9, 1),
('Titolo Articolo 10', 'Sottotitolo Articolo 10', 'Contenuto dell''articolo 10', '2025-08-01', 10, 1),
('Titolo Articolo 11', 'Sottotitolo Articolo 11', 'Contenuto dell''articolo 11', '2025-08-01', 1, 1),
('Titolo Articolo 12', 'Sottotitolo Articolo 12', 'Contenuto dell''articolo 12', '2025-08-01', 2, 1),
('Titolo Articolo 13', 'Sottotitolo Articolo 13', 'Contenuto dell''articolo 13', '2025-08-01', 3, 1),
('Titolo Articolo 14', 'Sottotitolo Articolo 14', 'Contenuto dell''articolo 14', '2025-08-01', 4, 1),
('Titolo Articolo 15', 'Sottotitolo Articolo 15', 'Contenuto dell''articolo 15', '2025-08-01', 5, 1),
('Titolo Articolo 16', 'Sottotitolo Articolo 16', 'Contenuto dell''articolo 16', '2025-08-01', 6, 1),
('Titolo Articolo 17', 'Sottotitolo Articolo 17', 'Contenuto dell''articolo 17', '2025-08-01', 7, 1),
('Titolo Articolo 18', 'Sottotitolo Articolo 18', 'Contenuto dell''articolo 18', '2025-08-01', 8, 1),
('Titolo Articolo 19', 'Sottotitolo Articolo 19', 'Contenuto dell''articolo 19', '2025-08-01', 9, 1),
('Titolo Articolo 20', 'Sottotitolo Articolo 20', 'Contenuto dell''articolo 20', '2025-08-01', 10, 1),
('Titolo Articolo 21', 'Sottotitolo Articolo 21', 'Contenuto dell''articolo 21', '2025-08-01', 1, 1),
('Titolo Articolo 22', 'Sottotitolo Articolo 22', 'Contenuto dell''articolo 22', '2025-08-01', 2, 1),
('Titolo Articolo 23', 'Sottotitolo Articolo 23', 'Contenuto dell''articolo 23', '2025-08-01', 3, 1),
('Titolo Articolo 24', 'Sottotitolo Articolo 24', 'Contenuto dell''articolo 24', '2025-08-01', 4, 1),
('Titolo Articolo 25', 'Sottotitolo Articolo 25', 'Contenuto dell''articolo 25', '2025-08-01', 5, 1),
('Titolo Articolo 26', 'Sottotitolo Articolo 26', 'Contenuto dell''articolo 26', '2025-08-01', 6, 1),
('Titolo Articolo 27', 'Sottotitolo Articolo 27', 'Contenuto dell''articolo 27', '2025-08-01', 7, 1),
('Titolo Articolo 28', 'Sottotitolo Articolo 28', 'Contenuto dell''articolo 28', '2025-08-01', 8, 1),
('Titolo Articolo 29', 'Sottotitolo Articolo 29', 'Contenuto dell''articolo 29', '2025-08-01', 9, 1),
('Titolo Articolo 30', 'Sottotitolo Articolo 30', 'Contenuto dell''articolo 30', '2025-08-01', 10, 1),
('Titolo Articolo 31', 'Sottotitolo Articolo 31', 'Contenuto dell''articolo 31', '2025-08-01', 1, 1),
('Titolo Articolo 32', 'Sottotitolo Articolo 32', 'Contenuto dell''articolo 32', '2025-08-01', 2, 1),
('Titolo Articolo 33', 'Sottotitolo Articolo 33', 'Contenuto dell''articolo 33', '2025-08-01', 3, 1),
('Titolo Articolo 34', 'Sottotitolo Articolo 34', 'Contenuto dell''articolo 34', '2025-08-01', 4, 1),
('Titolo Articolo 35', 'Sottotitolo Articolo 35', 'Contenuto dell''articolo 35', '2025-08-01', 5, 1),
('Titolo Articolo 36', 'Sottotitolo Articolo 36', 'Contenuto dell''articolo 36', '2025-08-01', 6, 1),
('Titolo Articolo 37', 'Sottotitolo Articolo 37', 'Contenuto dell''articolo 37', '2025-08-01', 7, 1),
('Titolo Articolo 38', 'Sottotitolo Articolo 38', 'Contenuto dell''articolo 38', '2025-08-01', 8, 1),
('Titolo Articolo 39', 'Sottotitolo Articolo 39', 'Contenuto dell''articolo 39', '2025-08-01', 9, 1),
('Titolo Articolo 40', 'Sottotitolo Articolo 40', 'Contenuto dell''articolo 40', '2025-08-01', 10, 1),
('Titolo Articolo 41', 'Sottotitolo Articolo 41', 'Contenuto dell''articolo 41', '2025-08-01', 1, 1),
('Titolo Articolo 42', 'Sottotitolo Articolo 42', 'Contenuto dell''articolo 42', '2025-08-01', 2, 1),
('Titolo Articolo 43', 'Sottotitolo Articolo 43', 'Contenuto dell''articolo 43', '2025-08-01', 3, 1),
('Titolo Articolo 44', 'Sottotitolo Articolo 44', 'Contenuto dell''articolo 44', '2025-08-01', 4, 1),
('Titolo Articolo 45', 'Sottotitolo Articolo 45', 'Contenuto dell''articolo 45', '2025-08-01', 5, 1),
('Titolo Articolo 46', 'Sottotitolo Articolo 46', 'Contenuto dell''articolo 46', '2025-08-01', 6, 1),
('Titolo Articolo 47', 'Sottotitolo Articolo 47', 'Contenuto dell''articolo 47', '2025-08-01', 7, 1),
('Titolo Articolo 48', 'Sottotitolo Articolo 48', 'Contenuto dell''articolo 48', '2025-08-01', 8, 1),
('Titolo Articolo 49', 'Sottotitolo Articolo 49', 'Contenuto dell''articolo 49', '2025-08-01', 9, 1),
('Titolo Articolo 50', 'Sottotitolo Articolo 50', 'Contenuto dell''articolo 50', '2025-08-01', 10, 1);
