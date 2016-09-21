

CREATE TABLE episode
(
  epId serial PRIMARY KEY NOT NULL,
  name character varying(500),

  season int,
  episodeNumber int,
  description text
);


copy episode from 'episode.csv' delimiter ',' csv;