@echo off
:: ����ѹ��JS�ļ��ĸ�Ŀ¼���ű����Զ�������β��Һ�ѹ�����е�JS
SET JSFOLDER=C:\Users\Administrator\Desktop\formini
echo ���ڲ���JS�ļ�
chdir /d %JSFOLDER%
for /r . %%a in (*.js) do (
    @echo ����ѹ�� %%~a ...
    uglifyjs %%~fa  -m -o %%~fa
)
echo ���!
pause & exit
