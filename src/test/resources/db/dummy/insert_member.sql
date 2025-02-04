-- Member 데이터 삽입
INSERT INTO member (`id`, `nickname`, `profile_image`, `social_id`)
VALUES (1, 'member1', 'image1.png', '1'),
       (2, 'member2', 'image2.png', '2'),
       (3, 'member3', 'image3.png', '3'),
       (4, 'member4', 'image4.png', '4'),
       (5, 'member5', 'image5.png', '5'),
       (6, 'member6', 'image6.png', '6'),
       (7, 'member7', 'image7.png', '7'),
       (8, 'member8', 'image8.png', '8'),
       (9, 'member9', 'image9.png', '9'),
       (10, 'member10', 'image10.png', '10');

-- Room 데이터 삽입
INSERT INTO room (`id`, `name`, `description`)
VALUES (1, 'Room1', 'Description1'),
       (2, 'Room2', 'Description2'),
       (3, 'Room3', 'Description3');

-- RoomMembers 데이터 삽입
INSERT INTO room_member (`id`, `member_id`, `room_id`, `is_leader`)
VALUES
    -- 1번 룸에 속한 회원들
    (1, 1, 1, 1),
    (2, 2, 1, 0),
    (3, 3, 1, 0),
    (4, 4, 1, 0),

    -- 2번 룸에 속한 회원들
    (5, 5, 2, 1),
    (6, 6, 2, 0),
    (7, 7, 2, 0),
    (8, 8, 2, 0),

    -- 3번 룸에 속한 회원들
    (9, 1, 3, 0),
    (10, 2, 3, 0),
    (11, 6, 3, 0),
    (12, 7, 3, 0),
    (13, 9, 3, 0),
    (14, 10, 3, 1);

-- MemberSchedule 데이터 삽입
INSERT INTO member_schedule (id, room_id, start_date, end_date)
VALUES (1, 1, '2024-08-24 21:30:00', '2024-08-26 14:30:00'),
       (2, 2, '2024-08-10 13:00:00', '2024-08-16 02:00:00'),
       (3, 3, '2024-08-27 18:00:00', '2024-08-29 17:30:00'),
       (4, 1, '2024-08-27 08:30:00', '2024-08-28 23:30:00'),
       (5, 2, '2024-08-24 22:30:00', '2024-08-28 21:00:00'),
       (6, 3, '2024-08-22 17:00:00', '2024-08-26 12:30:00'),
       (7, 1, '2024-08-14 02:00:00', '2024-08-16 00:00:00'),
       (8, 2, '2024-08-18 02:30:00', '2024-08-24 12:30:00'),
       (9, 3, '2024-08-17 20:00:00', '2024-08-22 00:30:00');

-- MemberScheduleRecord 데이터 삽입
INSERT INTO member_schedule_record (id, member_id, member_schedule_id, room_id, start_date, end_date)
VALUES (1, 1, 1, 1, '2024-08-25 13:30:00', '2024-08-25 22:30:00'),
       (2, 2, 1, 1, '2024-08-25 23:30:00', '2024-08-26 01:00:00'),
       (3, 3, 1, 1, '2024-08-26 00:30:00', '2024-08-26 14:00:00'),
       (4, 4, 1, 1, '2024-08-26 08:00:00', '2024-08-26 10:00:00'),
       (5, 1, 1, 1, '2024-08-25 06:00:00', '2024-08-26 13:30:00'),
       (6, 2, 1, 1, '2024-08-25 08:30:00', '2024-08-25 21:30:00'),
       (7, 3, 1, 1, '2024-08-25 18:00:00', '2024-08-25 20:00:00'),
       (8, 4, 1, 1, '2024-08-24 22:30:00', '2024-08-26 07:00:00'),
       (9, 1, 1, 1, '2024-08-26 14:00:00', '2024-08-26 14:00:00'),
       (10, 2, 1, 1, '2024-08-26 02:00:00', '2024-08-26 13:00:00'),
       (11, 1, 2, 2, '2024-08-12 22:00:00', '2024-08-14 06:30:00'),
       (12, 2, 2, 2, '2024-08-15 05:00:00', '2024-08-16 00:30:00'),
       (13, 3, 2, 2, '2024-08-11 10:30:00', '2024-08-11 11:00:00'),
       (14, 4, 2, 2, '2024-08-13 11:30:00', '2024-08-15 03:00:00'),
       (15, 1, 2, 2, '2024-08-10 17:30:00', '2024-08-15 22:00:00'),
       (16, 2, 2, 2, '2024-08-12 15:30:00', '2024-08-12 18:00:00'),
       (17, 3, 2, 2, '2024-08-10 16:30:00', '2024-08-10 21:30:00'),
       (18, 4, 2, 2, '2024-08-11 03:30:00', '2024-08-13 07:00:00'),
       (19, 1, 2, 2, '2024-08-16 00:30:00', '2024-08-16 01:30:00'),
       (20, 2, 2, 2, '2024-08-13 16:30:00', '2024-08-14 12:00:00'),
       (21, 1, 3, 3, '2024-08-27 19:00:00', '2024-08-27 23:00:00'),
       (22, 2, 3, 3, '2024-08-28 12:30:00', '2024-08-29 08:30:00'),
       (23, 3, 3, 3, '2024-08-28 07:30:00', '2024-08-28 20:00:00'),
       (24, 4, 3, 3, '2024-08-27 18:00:00', '2024-08-29 12:00:00'),
       (25, 1, 3, 3, '2024-08-28 14:30:00', '2024-08-29 04:00:00'),
       (26, 2, 3, 3, '2024-08-29 11:30:00', '2024-08-29 16:00:00'),
       (27, 3, 3, 3, '2024-08-29 03:30:00', '2024-08-29 03:30:00'),
       (28, 4, 3, 3, '2024-08-29 13:00:00', '2024-08-29 14:30:00'),
       (29, 1, 3, 3, '2024-08-28 05:30:00', '2024-08-29 10:30:00'),
       (30, 2, 3, 3, '2024-08-28 04:00:00', '2024-08-28 08:00:00'),
       (31, 5, 4, 1, '2024-08-28 20:00:00', '2024-08-28 20:30:00'),
       (32, 6, 4, 1, '2024-08-28 04:00:00', '2024-08-28 19:00:00'),
       (33, 7, 4, 1, '2024-08-27 21:30:00', '2024-08-28 06:30:00'),
       (34, 8, 4, 1, '2024-08-28 15:30:00', '2024-08-28 22:30:00'),
       (35, 5, 4, 1, '2024-08-27 16:00:00', '2024-08-28 00:00:00'),
       (36, 6, 4, 1, '2024-08-27 11:00:00', '2024-08-28 03:30:00'),
       (37, 7, 4, 1, '2024-08-27 12:30:00', '2024-08-27 18:00:00'),
       (38, 8, 4, 1, '2024-08-27 10:30:00', '2024-08-28 08:00:00'),
       (39, 5, 4, 1, '2024-08-28 14:30:00', '2024-08-28 15:00:00'),
       (40, 6, 4, 1, '2024-08-28 20:30:00', '2024-08-28 22:30:00'),
       (41, 5, 5, 2, '2024-08-26 02:30:00', '2024-08-26 05:00:00'),
       (42, 6, 5, 2, '2024-08-26 04:00:00', '2024-08-27 08:30:00'),
       (43, 7, 5, 2, '2024-08-26 01:00:00', '2024-08-26 06:00:00'),
       (44, 8, 5, 2, '2024-08-27 01:00:00', '2024-08-27 21:00:00'),
       (45, 5, 5, 2, '2024-08-27 16:00:00', '2024-08-27 18:30:00'),
       (46, 6, 5, 2, '2024-08-27 15:30:00', '2024-08-27 18:00:00'),
       (47, 7, 5, 2, '2024-08-26 08:00:00', '2024-08-27 16:00:00'),
       (48, 8, 5, 2, '2024-08-27 22:30:00', '2024-08-28 19:30:00'),
       (49, 5, 5, 2, '2024-08-26 09:00:00', '2024-08-26 11:30:00'),
       (50, 6, 5, 2, '2024-08-27 15:00:00', '2024-08-27 21:30:00'),
       (51, 5, 6, 3, '2024-08-22 23:30:00', '2024-08-25 09:00:00'),
       (52, 6, 6, 3, '2024-08-23 09:30:00', '2024-08-26 03:30:00'),
       (53, 7, 6, 3, '2024-08-25 22:30:00', '2024-08-26 07:00:00'),
       (54, 8, 6, 3, '2024-08-24 17:30:00', '2024-08-26 04:30:00'),
       (55, 5, 6, 3, '2024-08-26 01:00:00', '2024-08-26 08:30:00'),
       (56, 6, 6, 3, '2024-08-22 20:30:00', '2024-08-23 04:00:00'),
       (57, 7, 6, 3, '2024-08-23 22:00:00', '2024-08-24 18:00:00'),
       (58, 8, 6, 3, '2024-08-26 08:00:00', '2024-08-26 08:30:00'),
       (59, 5, 6, 3, '2024-08-25 17:00:00', '2024-08-25 20:30:00'),
       (60, 6, 6, 3, '2024-08-26 12:00:00', '2024-08-26 12:00:00'),
       (61, 9, 7, 1, '2024-08-14 16:30:00', '2024-08-15 12:30:00'),
       (62, 10, 7, 1, '2024-08-14 11:30:00', '2024-08-14 17:00:00'),
       (63, 10, 7, 1, '2024-08-14 09:00:00', '2024-08-14 21:00:00'),
       (64, 10, 7, 1, '2024-08-14 06:30:00', '2024-08-15 05:00:00'),
       (65, 9, 7, 1, '2024-08-15 20:00:00', '2024-08-15 20:00:00'),
       (66, 10, 7, 1, '2024-08-15 11:00:00', '2024-08-15 15:00:00'),
       (67, 10, 7, 1, '2024-08-14 03:30:00', '2024-08-14 04:00:00'),
       (68, 10, 7, 1, '2024-08-15 22:00:00', '2024-08-15 23:30:00'),
       (69, 9, 7, 1, '2024-08-15 21:30:00', '2024-08-15 22:30:00'),
       (70, 10, 7, 1, '2024-08-15 06:00:00', '2024-08-15 16:00:00'),
       (71, 9, 8, 2, '2024-08-18 12:30:00', '2024-08-22 01:30:00'),
       (72, 10, 8, 2, '2024-08-22 23:00:00', '2024-08-22 23:30:00'),
       (73, 10, 8, 2, '2024-08-23 06:30:00', '2024-08-24 11:30:00'),
       (74, 10, 8, 2, '2024-08-21 04:30:00', '2024-08-22 17:30:00'),
       (75, 9, 8, 2, '2024-08-23 18:30:00', '2024-08-23 18:30:00'),
       (76, 10, 8, 2, '2024-08-19 19:30:00', '2024-08-20 06:30:00'),
       (77, 10, 8, 2, '2024-08-19 10:00:00', '2024-08-20 12:30:00'),
       (78, 10, 8, 2, '2024-08-18 20:30:00', '2024-08-20 15:00:00'),
       (79, 9, 8, 2, '2024-08-22 13:00:00', '2024-08-23 12:00:00'),
       (80, 10, 8, 2, '2024-08-20 18:00:00', '2024-08-21 04:00:00'),
       (81, 9, 9, 3, '2024-08-19 04:00:00', '2024-08-19 22:30:00'),
       (82, 10, 9, 3, '2024-08-19 11:00:00', '2024-08-19 19:30:00'),
       (83, 10, 9, 3, '2024-08-20 01:00:00', '2024-08-20 12:00:00'),
       (84, 10, 9, 3, '2024-08-18 20:00:00', '2024-08-20 18:30:00'),
       (85, 9, 9, 3, '2024-08-18 22:30:00', '2024-08-20 09:30:00'),
       (86, 10, 9, 3, '2024-08-21 19:30:00', '2024-08-21 23:00:00'),
       (87, 10, 9, 3, '2024-08-21 11:30:00', '2024-08-21 13:00:00'),
       (88, 10, 9, 3, '2024-08-21 00:30:00', '2024-08-21 15:00:00'),
       (89, 9, 9, 3, '2024-08-20 23:00:00', '2024-08-21 22:00:00'),
       (90, 10, 9, 3, '2024-08-18 15:00:00', '2024-08-18 17:00:00');
