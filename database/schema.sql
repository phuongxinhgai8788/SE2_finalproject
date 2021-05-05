CREATE DATABASE IF NOT EXISTS scm;

CREATE TABLE IF NOT EXISTS inventories
(
    id           int AUTO_INCREMENT
        PRIMARY KEY,
    name         varchar(255)     NOT NULL,
    price        double DEFAULT 0 NULL,
    source       varchar(1024)    NULL,
    thumbnailUrl varchar(1024)    NULL,
    tags         varchar(1024)    NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    id   int AUTO_INCREMENT
        PRIMARY KEY,
    name varchar(255) NOT NULL,
    CONSTRAINT roles_name_uindex
        UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS transportingUnits
(
    id   int AUTO_INCREMENT
        PRIMARY KEY,
    name varchar(256) NOT NULL,
    CONSTRAINT transportingUnits_name_uindex
        UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS labors
(
    id                 int AUTO_INCREMENT
        PRIMARY KEY,
    name               varchar(256)             NOT NULL,
    dateOfBirth        date                     NOT NULL,
    phoneNumber        varchar(20)              NOT NULL,
    roleId             int                      NULL,
    transportingUnitId int                      NULL,
    password           varchar(256) DEFAULT '1' NULL,
    email              varchar(256)             NOT NULL,
    CONSTRAINT labors_email_uindex
        UNIQUE (email),
    CONSTRAINT labors_phoneNumber_uindex
        UNIQUE (phoneNumber),
    CONSTRAINT role___fk
        FOREIGN KEY (roleId) REFERENCES roles (id)
            ON DELETE SET NULL,
    CONSTRAINT transportingUnits___fk
        FOREIGN KEY (transportingUnitId) REFERENCES transportingUnits (id)
            ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id                 int AUTO_INCREMENT
        PRIMARY KEY,
    customerName       varchar(256)                          NOT NULL,
    address            varchar(1024)                         NOT NULL,
    phoneNumber        varchar(20)                           NOT NULL,
    transportingUnitId int                                   NULL,
    transporterId      int                                   NULL,
    notes              varchar(256)                          NULL,
    createdDate        timestamp   DEFAULT CURRENT_TIMESTAMP NULL,
    status             varchar(10) DEFAULT 'PENDING'         NULL,
    CONSTRAINT orders_transportingUnits___fk
        FOREIGN KEY (transportingUnitId) REFERENCES transportingUnits (id)
            ON DELETE SET NULL,
    CONSTRAINT transporters___fk
        FOREIGN KEY (transporterId) REFERENCES labors (id)
            ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS orderDeliveryDetails
(
    orderId     int                                 NOT NULL,
    updatedDate timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    notes       varchar(1024)                       NOT NULL,
    PRIMARY KEY (updatedDate,
                 orderId),
    CONSTRAINT orderDeliveryDetails_orders_id_fk
        FOREIGN KEY (orderId) REFERENCES orders (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS orderDetails
(
    orderId  int    NOT NULL,
    itemId   int    NOT NULL,
    price    double NOT NULL,
    quantity int    NOT NULL,
    PRIMARY KEY (orderId,
                 itemId),
    CONSTRAINT orderDetails_inventories_id_fk
        FOREIGN KEY (itemId) REFERENCES inventories (id)
            ON DELETE CASCADE,
    CONSTRAINT orderDetails_orders_id_fk
        FOREIGN KEY (orderId) REFERENCES orders (id)
            ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS warehouses
(
    id        int AUTO_INCREMENT
        PRIMARY KEY,
    address   varchar(1024) NOT NULL,
    managerId int           NULL,
    CONSTRAINT manager___fk
        FOREIGN KEY (managerId) REFERENCES labors (id)
            ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS warehouseItems
(
    inventoryId int NOT NULL,
    warehouseId int NOT NULL,
    quantity    int NOT NULL,
    PRIMARY KEY (warehouseId,
                 inventoryId),
    CONSTRAINT warehouseItems_inventories_id_fk
        FOREIGN KEY (inventoryId) REFERENCES inventories (id)
            ON DELETE CASCADE,
    CONSTRAINT warehouseItems_warehouses_id_fk
        FOREIGN KEY (warehouseId) REFERENCES warehouses (id)
            ON DELETE CASCADE
);
