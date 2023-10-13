#!/bin/bash

#This script extracts the users that we use in the framework.
#Run in from the root of the project

ARRAY=(qa1 qa2 qa3 stage prod)

echo
echo
echo ' _   _                     _                        '
echo '| | | |                   (_)                       '
echo '| | | |___  ___ _ __ ___   _ _ __    _   _ ___  ___ '
echo '| | | / __|/ _ \  __/ __| | |  _ \  | | | / __|/ _ \'
echo '| |_| \__ \  __/ |  \__ \ | | | | | | |_| \__ \  __/'
echo ' \___/|___/\___|_|  |___/ |_|_| |_|  \__,_|___/\___|'
                                                    
                                                    


echo `date`
echo
for i in ${ARRAY[@]}; do
	echo
    echo ______ ${i}_________
    grep "[A-z1-9]@" src/test/resources/ui/config/*/*${i}*properties| grep -v PASS| awk -F "=" '{print $NF}' |sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//'|sort|uniq
done
echo
echo ' _____          _ '
echo '|  ___|        | |'
echo '| |__ _ __   __| |'
echo '|  __|  _ \ / _` |'
echo '| |__| | | | (_| |'
echo '\____/_| |_|\__,_|'
exit 0


