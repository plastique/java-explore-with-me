CREATE TABLE IF NOT EXISTS users
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    email VARCHAR(254) NOT NULL UNIQUE,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS events
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE RESTRICT,
    paid BOOLEAN NOT NULL DEFAULT false,
    request_moderation BOOLEAN NOT NULL DEFAULT false,
    event_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    publish_date TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    participant_limit INTEGER NOT NULL DEFAULT 0,
    confirmed_requests INTEGER NOT NULL DEFAULT 0,
    location_lat FLOAT NOT NULL DEFAULT 0.0,
    location_lon FLOAT NOT NULL DEFAULT 0.0,
    state VARCHAR(50) NOT NULL,
    title VARCHAR(150) NOT NULL,
    annotation VARCHAR(2000),
    description TEXT
);

CREATE TABLE IF NOT EXISTS compilations
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    pinned BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS compilation_events
(
    compilation_id BIGINT NOT NULL REFERENCES compilations(id) ON DELETE CASCADE,
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    CONSTRAINT pk_compilation_events PRIMARY KEY (compilation_id, event_id)
);

CREATE TABLE IF NOT EXISTS event_requests
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    state VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS comments
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    text TEXT NOT NULL
);

-- compilations
CREATE INDEX IF NOT EXISTS idx_compilations_pinned ON compilations(pinned);

-- events
CREATE INDEX IF NOT EXISTS idx_events_user ON events(user_id);
CREATE INDEX IF NOT EXISTS idx_events_category ON events(category_id);
CREATE INDEX IF NOT EXISTS idx_events_state ON events(state);

-- event requests
CREATE INDEX IF NOT EXISTS idx_event_requests_event ON event_requests(event_id);
CREATE INDEX IF NOT EXISTS idx_event_requests_user ON event_requests(user_id);

-- comments
CREATE INDEX IF NOT EXISTS idx_comment_created ON comments(created);
