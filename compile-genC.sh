for genc in krakatoa/*.c; do
	echo "************************"
	echo "*"
	echo "* Compiling $genc"
	echo "*"
	echo "************************"
	gcc $genc -o "$genc.out"
done
