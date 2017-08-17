create sequence oauth_configuration_seq START 101;
create table oauth_configuration ( 
        id integer default nextval('oauth_configuration_seq'), 
        code varchar(200), 
        issued_at varchar(200), 
        refresh_token varchar(200), 
        instance_url varchar(200), 
        signature varchar(200), 
        access_token varchar(200), 
        created timestamp default now() 
);


select * from oauth_configuration

insert into oauth_configuration ( code ) values ( ' test1 ' );