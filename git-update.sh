#!/bin/sh
if [ ! -n "$1" ]; then
	echo "usage: git update [Reason To Update]"
	echo "You need to add a pair of quotations if your reason contains spaces."
else
	git add . && git commit -m "$1" && git push -u origin master
fi
