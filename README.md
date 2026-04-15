# comandos de teste

```
curl -X POST http://localhost:8080/api/calculus \
-H "Content-Type: application/json" \
-d '{"categoria": "INDUSTRIAL", "consumo": 35}'
```

```
curl -X POST http://localhost:8080/api/tabelas-tarifarias -H "Content-Type: application/json" -d '{
  "nome": "Tarifa Teste",
  "dataDeVigencia": "2026-01-01",
  "numFaixaConsumo": 3,
  "valorFaixaConsumo": 1,
  "consumo": {
    "id": 1
  }
}'
```

```
curl -X GET http://localhost:8080/api/tabelas-tarifarias
```

```
curl -X DELETE http://localhost:8080/api/tabelas-tarifarias/5
```