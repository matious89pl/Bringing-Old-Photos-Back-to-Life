create index IX_5134720A on CPROPOSAL_recFormArticle (articleId, alternativeFormIds[$COLUMN_LENGTH:75$]);
create index IX_5BAB5E4E on CPROPOSAL_recFormArticle (articleReleaseSchedule[$COLUMN_LENGTH:75$]);
create index IX_92658162 on CPROPOSAL_recFormArticle (articleStatus[$COLUMN_LENGTH:75$], articleReleaseSchedule[$COLUMN_LENGTH:75$]);