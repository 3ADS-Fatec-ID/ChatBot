USE BOT
GO

-- INSERT Palavra_Chave_Pesquisa
DECLARE @cnt INT = 1;

WHILE @cnt < 81
BEGIN
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),1,@cnt,1)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),2,@cnt,1)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),3,@cnt,2)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),4,@cnt,2)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),5,@cnt,3)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),6,@cnt,3)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),7,@cnt,4)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),8,@cnt,4)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),9,@cnt,5)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),10,@cnt,5)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),11,@cnt,6)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),12,@cnt,6)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),13,@cnt,6)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),14,@cnt,7)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),15,@cnt,7)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),16,@cnt,7)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),17,@cnt,7)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),18,@cnt,7)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),19,@cnt,8)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),20,@cnt,9)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),21,@cnt,10)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),22,@cnt,10)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),23,@cnt,11)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),24,@cnt,12)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),25,@cnt,13)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),26,@cnt,14)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),27,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),28,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),29,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),30,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),31,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),32,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),33,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),34,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),35,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),36,@cnt,15)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),37,@cnt,16)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),38,@cnt,16)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),39,@cnt,17)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),40,@cnt,18)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),41,@cnt,18)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),42,@cnt,18)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),43,@cnt,19)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),44,@cnt,20)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),45,@cnt,20)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),46,@cnt,21)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),47,@cnt,21)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),48,@cnt,22)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),49,@cnt,22)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),50,@cnt,23)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),51,@cnt,23)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),52,@cnt,23)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),53,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),54,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),55,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),56,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),57,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),58,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),59,@cnt,24)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),60,@cnt,25)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),61,@cnt,25)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),62,@cnt,26)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),63,@cnt,27)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),64,@cnt,27)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),65,@cnt,28)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),66,@cnt,28)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),67,@cnt,29)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),68,@cnt,29)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),69,@cnt,30)
	INSERT INTO Palavra_Chave_Pesquisa VALUES (GETDATE(),70,@cnt,30)

   SET @cnt = @cnt + 1;
END;

select * from Palavra_Chave_Pesquisa