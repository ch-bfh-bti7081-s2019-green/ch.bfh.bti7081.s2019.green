#!/bin/sh
# The input for this script can be generated using
# mvn license:add-third-party > soft.txt
# It works best if you delete the first two lines of soft.txt before running.

regex="^\h*(?<license>(\([^\)]+\)\h)+)(?<libname>.+)\h\(.+-\h(?<url>[^\)]+)"
while read l; do
  license=$(echo "$l" | pcregrep -o1 "$regex")
  name=$(echo "$l" | pcregrep -o3 "$regex")
  url=$(echo "$l" | pcregrep -o4 "$regex")
  if [ "$url" = "no url defined" ]; then
          echo "this.add(new Text(\"$name $license\"));"
  else
          echo "this.add(new Anchor(\"$url\", \"$name $license\"));"
  fi
done < soft.txt
