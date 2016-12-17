echo 'Segundo trabalho.'
echo 'testa a geração de código'

rm OK-*.txt
rm ER-*.txt
rm *.c
rm z.txt
rm r.txt
rm OK-*.out
rm ER-*.out

echo 'rm ..\..\t\ok-*.txt'
echo 'rm ..\..\t\er-*.txt'
echo 'rm ..\..\t\ok-*.out'
echo 'rm ..\..\t\er-*.out'
echo 'rm ..\..\..\tests\ok-*.txt'
echo 'rm ..\..\..\tests\er-*.txtcd'
echo 'rm ..\..\..\tests\ok-*.out'
echo 'rm ..\..\..\tests\er-*.out'
echo 'rm ..\..\..\tests\ok-*.c'



#java -cp . comp.Comp ..\..\..\tests\OK-GER01.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER02.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER03.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER04.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER05.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER06.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER07.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER08.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER09.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER10.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER11.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER12.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER14.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER15.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER16.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER17.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER18.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER19.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER20.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER21.KRA
#java -cp . comp.Comp ..\..\..\tests\OK-GER22.KRA

read -p "Press [Enter] to continue..."

echo 'mv ..\..\t\ok-*.txt .'
echo 'mv ..\..\t\er-*.txt .'
echo 'mv ..\..\t\ok-*.out .'
echo 'mv ..\..\..\tests\ok-*.txt .'
echo 'mv ..\..\..\tests\er-*.txt .'
echo 'mv ..\..\..\tests\ok-*.c .'
echo 'mv ..\..\..\tests\ok-*.out .'


rm z.txt

gcc -o OK-GER01.out ../krakatoa/OK-GER01.c
chmod +x OK-GER01.out
./OK-GER01.out  < 30-enters.txt > OK-Out01.txt
gcc -o OK-GER02.out ../krakatoa/OK-GER02.c
chmod +x OK-GER02.out
./OK-GER02.out  < 30-enters.txt > OK-Out02.txt
gcc -o OK-GER03.out ../krakatoa/OK-GER03.c
chmod +x OK-GER03.out
./OK-GER03.out  < 30-enters.txt > OK-Out03.txt
gcc -o OK-GER04.out ../krakatoa/OK-GER04.c
chmod +x OK-GER04.out
./OK-GER04.out  < 30-enters.txt > OK-Out04.txt
gcc -o OK-GER06.out ../krakatoa/OK-GER06.c
chmod +x OK-GER06.out
./OK-GER06.out  < 30-enters.txt > OK-Out06.txt
gcc -o OK-GER07.out ../krakatoa/OK-GER07.c
chmod +x OK-GER07.out
./OK-GER07.out  < 30-enters.txt > OK-Out07.txt
gcc -o OK-GER08.out ../krakatoa/OK-GER08.c
chmod +x OK-GER08.out
./OK-GER08.out  < 30-enters.txt > OK-Out08.txt
gcc -o OK-GER09.out ../krakatoa/OK-GER09.c
chmod +x OK-GER09.out
./OK-GER09.out  < 30-enters.txt > OK-Out09.txt
gcc -o OK-GER10.out ../krakatoa/OK-GER10.c
chmod +x OK-GER10.out
./OK-GER10.out  < 30-enters.txt > OK-Out10.txt
gcc -o OK-GER12.out ../krakatoa/OK-GER12.c
chmod +x OK-GER12.out
./OK-GER12.out  < 30-enters.txt > OK-Out12.txt
gcc -o OK-GER14.out ../krakatoa/OK-GER14.c
chmod +x OK-GER14.out
./OK-GER14.out  < 30-enters.txt > OK-Out14.txt
gcc -o OK-GER15.out ../krakatoa/OK-GER15.c
chmod +x OK-GER15.out
./OK-GER15.out  < 30-enters.txt > OK-Out15.txt
gcc -o OK-GER16.out ../krakatoa/OK-GER16.c
chmod +x OK-GER16.out
./OK-GER16.out  < 30-enters.txt > OK-Out16.txt
gcc -o OK-GER21.out ../krakatoa/OK-GER21.c
chmod +x OK-GER21.out
./OK-GER21.out  < 30-enters.txt > OK-Out21.txt
gcc -o OK-GER23.out ../krakatoa/OK-GER23.c
chmod +x OK-GER23.out
./OK-GER23.out  < 30-enters.txt > OK-Out23.txt


#type OK-Out05.txt >> z.txt
#type OK-Out11.txt >> z.txt


#rm *.tds
#rm *.obj
#rm *.bak

for txtreport in OK-Out*.txt; do
  cat $txtreport >> z.txt
done

less z.txt
