INSERT INTO line (name, color)
VALUES ('1호선', '파랑'),
       ('2호선', '초록');

INSERT INTO station (name)
VALUES ('수원'),
       ('잠실나루'),
       ('의왕'),
       ('선릉');

INSERT INTO paths (line_id, up_station_id, down_station_id, distance)
VALUES (1, 1, 2, 5),
       (2, 3, 1, 5),
       (2, 1, 4, 7);


