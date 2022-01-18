create table board_comment(
    c_no number(5) primary key,
    b_no number(5) REFERENCES board(b_no) ON DELETE CASCADE,
    c_writer VARCHAR2(20 char),
    c_date date not null,
    c_parent number(5),
    c_ischange number(1),	--코멘트 상태 0=수정전, 1=수정후, -1=삭제
    c_comment varchar2(1000 char) not null   
);


create sequence COMMENT_SEQ;

--drop sequence COMMENT_SEQ;
--drop table board_comment;

select * from BOARD_COMMENT;

/*
코멘트 가져올때 답글에 레벨을 설정해서 보여줌
select Level, c_no, b_no, c_writer, c_date, c_parent, c_comment
from BOARD_COMMENT
where  b_no = 44
start with c_parent = 0
connect by prior c_no = c_parent;
*/
