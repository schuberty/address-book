# !/bin/bash

shopt -s extglob

src="src"
package="gabrielschubert"
build="build/classes"
mainclass="gabrielschubert/addressbook/AddressBook"

build_all(){
  if [ ! -d "$build" ]; then
    mkdir -p $build
  fi
  cd $src && javac -d ../$build $package/*/*.java
}

run_main(){
#  [[ -z "$2" ]] && echo "Type the arguments." && exit 1
  var=$(echo "$2" | tr -d ' _-' | tr 'A-Z' 'a-z')
  cd $build && java $mainclass $var
}

show_build(){
  find $build
}

usage_help(){
  cat <<EOF
Usage: ${0##*/} [options]
  Options:
   
    --build,  -b          # Build it
    --run,    -r <vars>   # Run ir
    --show,   -s          # Show builded paths
    --help,   -h          # Show this

EOF
}

case $1 in

  "--build"|"-b") build_all ;;
  "--run"|"-r" ) run_main "$@";;
  "--show"|"-s") show_build ;;
  "--help"|"-h") usage_help ;;
  *) echo "Invalid option." && usage_help ;;

esac

exit
