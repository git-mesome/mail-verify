CREATE TABLE account
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--     oauth_id           VARCHAR UNIQUE NOT NULL,
--     email              VARCHAR(254)   NOT NULL,
--     profile_image_path VARCHAR        NOT NULL,
--     nickname           VARCHAR(20)    NOT NULL,
--     phone_number       VARCHAR(15)    NOT NULL,
    grade              VARCHAR        NOT NULL DEFAULT 'BASIC',
    si_do              VARCHAR(14),
    si_gun_gu          VARCHAR(10),
    eup_myeon_dong     VARCHAR(10),
    create_date        TIMESTAMP               DEFAULT NOW(),
    update_date        TIMESTAMP
    mail_auth int default 0;
    mail_key varchar(50);
);