#!/bin/bash

NONE='\033[00m'
GREEN='\033[01;32m'
YELLOW='\033[01;33m'
CYAN='\033[01;36m'
BOLD='\033[1m'

echo -e "\n1.${CYAN} Basic Auth using https://api-fyle.herokuapp.com/fyle/getToken${NONE}\n"
echo -e "${GREEN} Getting a token ..${NONE} "
tokenJSON=$(curl -u user:011235 -X GET --silent 'https://api-fyle.herokuapp.com/fyle/getToken')
echo -e "  " $tokenJSON
echo $tokenJSON > jsawuwerh2434check234jdfsdjljaxcw.json
token=$(grep -Po '"'"token"'"\s*:\s*"\K([^"]*)' jsawuwerh2434check234jdfsdjljaxcw.json)

echo -e "\n--> ${YELLOW}Press any key to fetch bank details ...${NONE}"
read ch
echo -e "2. ${CYAN}Bank details using above token with 'ifsc=ABNA0100332'${NONE}"
echo -e "\n${GREEN} Getting a response ..${NONE} "
bank=$(curl -X GET --silent -H 'Authorization: Bearer '$token' ' 'http://api-fyle.herokuapp.com/banks?ifsc=ABNA0100332')
echo "  " $bank

echo -e "\n--> ${YELLOW}Press to fetch Branch details${NONE}"
read ch
echo -e "3. ${CYAN}Branch details using above token with 'bank_name=ALLAHABAD BANK' & 'city=KOLKOTA'${NONE}\n"
echo -e "${GREEN} Getting a response ..${NONE} "
branch=$(curl -X GET --silent -H 'Authorization: Bearer '$token'' 'http://api-fyle.herokuapp.com/branches?bank_name=ALLAHABAD%20BANK&city=KOLKATA')
echo "  " $branch

echo -e "\n--> ${YELLOW}Press to fetch Branch details with 'LIMIT' & 'OFFSET' parameter${NONE}"
read ch
echo -e "3. ${CYAN}Branch details using above token with parameters 'limit=1' & 'offset=0'${NONE}\n"
echo -e "${GREEN} Getting a response ..${NONE} "
limoff=$(curl -X GET --silent -H 'Authorization: Bearer '$token'' 'http://api-fyle.herokuapp.com/branches?bank_name=ALLAHABAD%20BANK&city=KOLKATA&limit=1&offset=0')
echo "  " $limoff

tput sgr0
rm jsawuwerh2434check234jdfsdjljaxcw.json

echo -e "\n\n${GREEN}☑${NONE} ${BOLD}Test Complete${NONE}"


