drop database if exists lportal;
create database lportal character set utf8;
use lportal;



create index IX_223E752F on RECsr_supportR (groupId, createDate);
create index IX_493BD2DF on RECsr_supportR (groupId, dueDate, createDate);
create index IX_A427F49F on RECsr_supportR (groupId, link(255));
create index IX_417D45D7 on RECsr_supportR (groupId, status);
create index IX_885E59F7 on RECsr_supportR (groupId, title);
create index IX_A37040C4 on RECsr_supportR (groupId, type_);
create index IX_8E9726BF on RECsr_supportR (status);
create index IX_8BFA2E8D on RECsr_supportR (uuid_, companyId);
create unique index IX_366F44F on RECsr_supportR (uuid_, groupId);



