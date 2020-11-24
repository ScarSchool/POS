CREATE TABLE Cubes
(
    id    integer auto_increment NOT NULL,
    sides integer                NOT NULL,
    material VARCHAR(20)         NOT NULL,
    corners integer              NOT NULL,
    edges float                  NOT NULL,
    PRIMARY KEY (id)
);
commit;