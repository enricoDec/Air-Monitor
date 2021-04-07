DROP TABLE IF EXISTS co CASCADE;

CREATE TABLE co
(
    co_id     serial           not null,
    ppm       double precision not null,
    timestamp timestamp        not null,
    sensorId  integer          not null,

    PRIMARY KEY (co_id)
);

COMMENT ON TABLE co IS 'carbon monoxide';

ALTER TABLE co
    OWNER TO kbe;