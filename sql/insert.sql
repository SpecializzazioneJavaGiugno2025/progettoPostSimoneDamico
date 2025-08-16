insert into users( username, email, password, created_at) values( 'admin', 'admin@aulab.it', '$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS','20240607' );

insert into roles( name ) values( 'ROLE_ADMIN' );
insert into roles( name ) values( 'ROLE_REVISOR' );
insert into roles( name ) values( 'ROLE_WRITER' );
insert into roles( name ) values( 'ROLE_USER' );

insert into users_roles( user_id, role_id ) values( 1, 1 );

insert into categories( name ) values( 'politica' ),('economia'),('food&drink'),('sport'),('intrattenimento'),('tech');



-- 2. Inserisci 10 utenti
INSERT INTO users (username, email, password) VALUES
('alessandro.rossi', 'alessandro.rossi@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('giulia.bianchi', 'giulia.bianchi@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('luca.ferrari', 'luca.ferrari@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('martina.moretti', 'martina.moretti@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('davide.gallo', 'davide.gallo@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('francesca.conti', 'francesca.conti@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('matteo.giordano', 'matteo.giordano@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('elisa.verdi', 'elisa.verdi@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('andrea.pellegrini', 'andrea.pellegrini@example.com','$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS'),
('sara.marini', 'sara.marini@example.com', '$2a$10$oMiUOq5ToRfUI/Zprg5nE.qt8nT9KKJZoDBu1SIWuj.UGx8aRHwxS');


-- 3. Inserisci 50 articoli
-- In questo esempio, si usa una data fissa ('2025-08-01') per la colonna publish_date
-- e si associa ogni articolo a un utente ciclico (1,2,...,10, poi di nuovo 1,2,...).
-- 50 articoli realistici distribuiti tra utenti e categorie
INSERT INTO articles (title, subtitle, body, user_id, category_id) VALUES
-- 1-5 Politica
('Crisi di governo: trattative serrate nella notte', 'Nuove alleanze per evitare le urne', 'Fonti vicine al governo confermano incontri segreti tra i leader di maggioranza e opposizione per scongiurare la crisi imminente.', 2, 1),
('Riforma elettorale: cosa cambierà per i cittadini', 'Dibattito acceso in Parlamento', 'Il testo della riforma elettorale approda in Aula con modifiche che potrebbero cambiare radicalmente il sistema di voto.', 4, 1),
('Vertice UE a Bruxelles: l’Italia chiede più fondi', 'Meloni incontra i leader europei', 'Al centro del vertice la distribuzione del bilancio comunitario e le nuove sfide energetiche.',  7, 1),
('Opposizione compatta contro la legge di bilancio', 'Manifestazioni in piazza a Roma', 'Decine di migliaia di persone hanno protestato contro la manovra finanziaria definita “iniqua” dai sindacati.',  9, 1),
('Il Presidente della Repubblica: “Serve coesione nazionale”', 'Discorso al Quirinale', 'Nel tradizionale intervento di metà anno, il Capo dello Stato richiama tutti al senso di responsabilità.',  5, 1),

-- 6-10 Economia
('Inflazione in lieve calo, ma resta l’allarme caro vita', 'Le famiglie ancora in difficoltà', 'Nonostante un rallentamento dei prezzi, il potere d’acquisto delle famiglie italiane rimane sotto pressione.',  3, 2),
('Borsa di Milano chiude in rialzo', 'Banche e tecnologia trainano il listino', 'Dopo una mattinata volatile, il FTSE MIB recupera grazie agli acquisti su titoli bancari e tecnologici.',  6, 2),
('Boom di startup green nel 2025', 'Il settore sostenibile cresce del 30%', 'Dati del Ministero dello Sviluppo mostrano un aumento record di nuove imprese a basso impatto ambientale.',  8, 2),
('Il turismo straniero spinge il PIL', 'Record di arrivi da USA e Asia', 'La stagione estiva registra numeri da primato per presenze e spesa media dei visitatori internazionali.',  10, 2),
('Crisi energetica: cala il prezzo del gas', 'Effetto accordo UE-Russia', 'L’intesa raggiunta a Bruxelles porta sollievo a imprese e famiglie, ma gli esperti restano cauti.',  1, 2),

-- 11-15 Food & Drink
('Il ritorno della pizza napoletana tradizionale', 'Maestri pizzaioli contro le mode', 'A Napoli cresce il movimento per preservare la vera ricetta certificata UNESCO.',  2, 3),
('Street food: i piatti più amati dell’estate', 'Dal panino con la milza ai tacos gourmet', 'Una panoramica delle specialità di strada che conquistano turisti e cittadini.',  4, 3),
('Birre artigianali italiane conquistano l’estero', 'Premi a Londra e Berlino', 'Microbirrifici italiani portano a casa riconoscimenti internazionali nelle fiere di settore.',  7, 3),
('Gelato: i nuovi gusti del 2025', 'Dal basilico alla lavanda', 'Creatività e ingredienti insoliti nei laboratori artigianali più innovativi.', 9, 3),
('Vino rosso o bianco? Dipende dalla musica', 'La ricerca dell’Università di Torino', 'Uno studio rivela come l’ascolto di generi musicali diversi influenzi la percezione del gusto del vino.',  5, 3),

-- 16-20 Sport
('Serie A: svelato il calendario 2025-26', 'Subito big match alla prima giornata', 'La nuova stagione si apre con partite di alto livello che promettono spettacolo.',  3, 4),
('Olimpiadi: Italia già a quota 10 medaglie', 'Trionfi nel nuoto e nell’atletica', 'Gli azzurri brillano nelle prime giornate di gare internazionali.',  6, 4),
('Tennis: Berrettini torna al successo', 'Vittoria in finale a Cincinnati', 'Il tennista romano batte in tre set il numero 4 del ranking mondiale.',  8, 4),
('Ciclismo: il Giro d’Italia parte dalla Sicilia', 'Presentato il percorso 2026', 'La corsa rosa avrà un avvio spettacolare con tappe tra Etna e mare.',  10, 4),
('Calcio femminile: record di spettatori', 'Finale scudetto davanti a 40mila persone', 'Un successo di pubblico e di qualità di gioco che segna una nuova era per il calcio femminile.', 1, 4),

-- 21-25 Intrattenimento
('Festival di Sanremo: annunciate le nuove regole', 'Più spazio ai giovani artisti', 'Il direttore artistico promette un’edizione innovativa e ricca di sorprese.',  2, 5),
('Cinema italiano: tre film in corsa agli Oscar', 'Grandi aspettative per il 2026', 'Le produzioni italiane conquistano la critica internazionale e puntano alle nomination.',  4, 5),
('Musica live: il ritorno di Vasco Rossi', 'Tour in 10 stadi italiani', 'Il rocker emiliano infiamma il pubblico con concerti sold out.',  7, 5),
('Teatro: boom di spettacoli sperimentali', 'Nuove forme di recitazione', 'Compagnie emergenti portano in scena testi innovativi e provocatori.',  9, 5),
('Mostre d’arte immersiva a Milano', 'Tecnologia e creatività unite', 'Le nuove esposizioni multimediali attirano migliaia di visitatori ogni settimana.',  5, 5),

-- 26-30 Tech
('Intelligenza artificiale e privacy', 'L’UE propone nuove regole', 'La Commissione Europea presenta un pacchetto di norme per proteggere i dati personali.', 3, 6),
('Smart city: Torino diventa modello europeo', 'Tecnologia al servizio del cittadino', 'Progetti di mobilità sostenibile e gestione intelligente delle risorse in primo piano.',  6, 6),
('Robotica agricola: raccolta automatizzata', 'Un futuro senza fatica nei campi', 'Startup italiane sviluppano macchinari per la raccolta di frutta e verdura con AI.',  8, 6),
('Cybersecurity: allarme hacker in Italia', 'Colpiti enti pubblici e aziende', 'Gli esperti invitano a potenziare le difese informatiche nazionali.',  10, 6),
('Lancio del nuovo smartphone italiano', 'Design e prestazioni di livello', 'Un’azienda lombarda entra nel mercato con un modello innovativo e competitivo.', 1, 6),

-- 31-50 Altri articoli mixati
('Politica estera: l’Italia media tra Israele e Palestina', 'Colloqui segreti a Roma', 'Diplomatici confermano incontri riservati per favorire il dialogo tra le parti.',  2, 1),
('Economia circolare: il caso virtuoso di Bologna', 'Rifiuti ridotti del 40%', 'Il capoluogo emiliano è modello per gestione sostenibile delle risorse.',  4, 2),
('Cucina vegana: nuove tendenze 2025', 'Piatti creativi e nutrienti', 'Chef italiani reinventano la tradizione in chiave vegetale.',  7, 3),
('MotoGP: Bagnaia domina a Misano', 'Gara perfetta dall’inizio alla fine', 'Il pilota torinese consolida la leadership mondiale.',  9, 4),
('Festival di musica elettronica a Firenze', 'Tre giorni di DJ set internazionali', 'Un evento che attira giovani da tutta Europa.',  5, 5),
('Startup fintech milanesi conquistano Londra', 'Investimenti record', 'Il settore bancario digitale italiano piace agli investitori stranieri.',  3, 2),
('Pane artigianale: il ritorno del lievito madre', 'Tradizione e salute', 'I panifici riscoprono tecniche antiche per prodotti più digeribili.', 6, 3),
('Europei di Atletica: oro per l’Italia nella staffetta', 'Record nazionale', 'Gli azzurri firmano una prestazione storica.',  8, 4),
('Mostra su Leonardo da Vinci a Roma', 'Opere originali e installazioni digitali', 'Un percorso che unisce arte e tecnologia.',  10, 5),
('Software open source italiano vince premio mondiale', 'Innovazione a costo zero', 'Un team di sviluppatori indipendenti porta a casa il prestigioso riconoscimento.', 1, 6),
('Politica interna: approvata legge sul salario minimo', 'Soddisfazione dei sindacati', 'Un passo storico per i diritti dei lavoratori.',  2, 1),
('Mercati azionari: settimana di forti rialzi', 'Investitori ottimisti', 'Gli indici principali segnano guadagni a due cifre.', 4, 2),
('Nuove ricette estive light', 'Fresche e veloci', 'Consigli per piatti gustosi e salutari perfetti per la stagione calda.',  7, 3),
('Calcio: Italia qualificata ai Mondiali 2026', 'Vittoria decisiva contro la Spagna', 'Un gol nei minuti finali regala la qualificazione.',  9, 4),
('Grande Fratello VIP: svelati i concorrenti', 'Colpi di scena nel cast', 'L’edizione promette momenti indimenticabili.',  5, 5),
('Startup di AI medica ottiene 10 milioni di finanziamento', 'Progetti di diagnosi precoce', 'La tecnologia promette di rivoluzionare la sanità.',  3, 6),
('Politica: mozione di sfiducia al ministro dell’interno', 'Opposizione compatta', 'Il voto in Parlamento potrebbe segnare la fine della legislatura.',  6, 1),
('Crescita economica oltre le attese', 'Italia sopra la media UE', 'Il PIL registra un +3,2% grazie a export e turismo.', 8, 2),
('Dolci siciliani: tradizione e innovazione', 'Cannoli e cassate rivisitati', 'Pasticceri giovani reinventano i classici dolci dell’isola.', 10, 3),
('Volley: Italia campione d’Europa', 'Finale vinta 3-0', 'Gli azzurri dominano contro la Francia.', 1, 4);
