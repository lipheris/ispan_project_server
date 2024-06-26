-- database
create database if not exists project;
use project;

-- picture
create table if not exists project.picture (
    pic_id int auto_increment primary key not null,
    pic_created_at timestamp default current_timestamp,
    pic_loc varchar(50),
    pic_path varchar(50),
    x1 double,
    y1 double,
    x2 double,
    y2 double,
	x3 double,
    y3 double,
    x4 double,
    y4 double,
    x5 double,
    y5 double,
    x6 double,
    y6 double,
	x7 double,
    y7 double,
    x8 double,
    y8 double,
    x9 double,
    y9 double,
    x10 double,
    y10 double,
	x11 double,
    y11 double,
    x12 double,
    y12 double,
    x13 double,
    y13 double,
    x14 double,
    y14 double,
	x15 double,
    y15 double,
    x16 double,
    y16 double,
    x17 double,
    y17 double,
    index idx_pic_loc (pic_loc)
);
show create table project.picture;