#!/bin/sh

if [ -z "$ANT" ]; then
    ANT=ant
fi

echo "JAVA_HOME: $JAVA_HOME"
echo "ANT: $ANT"
echo "ANT_OPTS: $ANT_OPTS"
echo "@: $@"
$ANT "$@"
ANT_STATUS="$?"

# Use growl to indicate build completion if available
GROWL=`which growlnotify`
if [ -e "$GROWL" ]; then
    if [ "$ANT_STATUS" -eq 0 ]; then
        TITLE="caAERS build successful"
    else
        TITLE="caAERS build failed"
    fi
    echo "
    `pwd`
    $@" | $GROWL $TITLE
fi
