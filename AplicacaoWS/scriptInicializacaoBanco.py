# coding: utf-8

import os

os.system("curl -X PUT http://localhost:5984/trabalho")
os.system("curl -X PUT http://localhost:5984/trabalho/0 -d '{\"nome\": \"romana\", \"telefone\": \"3233671400\",\"idade\":15, \"sexo\":\"F\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/1 -d '{\"nome\": \"bruno\", \"telefone\": \"3233671401\",\"idade\":23, \"sexo\":\"M\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/2 -d '{\"nome\": \"jessica\", \"telefone\": \"3233671402\",\"idade\":20, \"sexo\":\"F\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/3 -d '{\"nome\": \"braulio\", \"telefone\": \"3233671403\",\"idade\":21, \"sexo\":\"M\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/4 -d '{\"nome\": \"guilherme\", \"telefone\": \"3233671404\",\"idade\":22, \"sexo\":\"M\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/5 -d '{\"nome\": \"gisele\", \"telefone\": \"3233671405\",\"idade\":24, \"sexo\":\"F\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/6 -d '{\"nome\": \"ana\", \"telefone\": \"3233671406\",\"idade\":19, \"sexo\":\"F\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/7 -d '{\"nome\": \"michelle\", \"telefone\": \"3233671407\",\"idade\":18, \"sexo\":\"F\"}'")
os.system("curl -X PUT http://localhost:5984/trabalho/8 -d '{\"nome\": \"rafael\", \"telefone\": \"3233671408\",\"idade\":26, \"sexo\":\"M\"}'")
