USE se22021;

insert into se22021.inventories (id, name, price, source, thumbnailUrl, tags)
values  (1, 'Anchor fastener (plated) 4', 20, null, null, 'hiltop,qualified'),
        (2, 'Pendant switch', 40, null, null, 'anchor,new'),
        (3, 'Batten nail', 50, null, null, 'ck,qualified'),
        (4, '6 sq mm 3 core Copper armoured cable', 24, null, null, 'ecko,new'),
        (5, '6M Modular Plate', 53, null, null, 'anchor roma,new'),
        (6, 'Pendant Bulb holder bakelite', 34, null, null, 'anchor,classic'),
        (7, 'Lamp 150 W HPSV screw type', 88, null, null, 'crompton greaves,new,qualified');

insert into se22021.roles (id, name)
values  (1, 'Director'),
        (2, 'Manager'),
        (3, 'Transporter');

insert into se22021.transportingUnits (id, name)
values  (1, 'FastExpress'),
        (2, 'J&T Express');        

insert into se22021.labors (id, name, dateOfBirth, phoneNumber, roleId, transportingUnitId, password, email)
values  (1, 'Karen Quinn', '1968-01-15', '01632 960506', 3, 2, '1', 'super@cannabisresoulution.net'),
        (2, 'Rachel Turner', '1992-08-19', '202-555-0141', 1, 1, '1', '1gjenerali@oormi.com'),
        (3, 'Irene Fisher', '1914-02-19', '202-555-0116', 2, 1, '1', 'babdelha@taluabushop.com'),
        (4, 'Ella MacLeod', '1939-06-27', '202-555-0150', 3, 1, '1', '3atair@noisemails.com'),
        (5, 'Carl Hudson', '1931-11-10', '202-555-0176', 2, 2, '1', 'vsoufain@betrallydk.com'),
        (6, 'Wendy Clarkson', '1907-03-21', '202-555-0191', 3, 2, '1', '3welingtongoldboz@how2t.site');

insert into scm.orders (id, customerName, address, phoneNumber, transportingUnitId, transporterId, notes, createdDate, status)
values  (1, 'Mr. Christopher F Swartz', '655 Poe Lane, Fort Worth, Kansas', '913-570-6050', 1, 6, 'Home-time: from 5.00pm', '2021-04-29 06:03:35', 'SHIPPING'),
        (2, 'Ms. Margaret R Key', '4550 Fannie Street, Clute, Texas', '979-265-7112', 2, 1, 'Home-time: from 5.00pm', '2021-04-30 06:03:35', 'SHIPPING'); 

insert into se22021.orderDeliveryDetails (orderId, updatedDate, notes)
values  (1, '2021-04-30 06:07:31', 'Goods packaged & prepared to deliver'),
        (1, '2021-04-30 06:07:52', 'Package is on going'),
        (2, '2021-04-30 06:07:57', 'Goods packaged & prepared to deliver'),
        (2, '2021-04-30 06:22:52', 'Packaging is not qualified, returned to repack'),
        (2, '2021-04-30 07:04:44', 'Package is on going, transporter confirmed');

insert into se22021.orderDetails (orderId, itemId, price, quantity)
values  (1, 1, 20, 5),
        (1, 4, 24, 6),
        (1, 7, 88, 2),
        (2, 3, 50, 1),
        (2, 5, 53, 4);

insert into scm.warehouses (id, address, managerId)
values  (4, '3687 Viking Drive, Dublin, Ohio
740-717-3284', 6),
        (5, '1458 Ridge Road, Dodge City, Kansas
620-789-4485', 7);
delete from scm.warehouses where id=4;