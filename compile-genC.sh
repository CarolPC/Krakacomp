for genc in krakatoa/OK-GER*.c; do
	echo "************************"
	echo "*"
	echo "* Compiling $genc"
	echo "*"
	echo "************************"
	gcc $genc -o "$genc.out"

	if [ "$?" -ne "0" ]; then
		break
	fi

done
