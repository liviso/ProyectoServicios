## Ejecutar Cypress

### Modo grafico 

Ejecutar en la carpeta del proyecto

```sh
$(npm bin)/cypress open
```

o

```sh
./node_modules/.bin/cypress open
```

Crear alias 

En la seccion **scripts** del archivo **package.json** agregar

```sh
"cy": "$(npm bin)/cypress open",
"cy:*": "$(npm bin)/cypress run",
"cy:*:file": "$(npm bin)/cypress run > cypress/fileResult/$(date +'%Y%m%d%I%M%S').txt"
```

Ejecutar alias 
```sh
npm run cy
npm run cy:*
npm run cy:*:file
```

### Modo consola

*(Todos los comandos se deben ejecutar en la carpeta del proyecto)*

**Ejecutar todas las pruebas del proyecto**

```sh
$(npm bin)/cypress run
```

## DOCKER

Imagenes disponibles en [Docker Hub](https://hub.docker.com/u/cypress).

**Ejecución por consola**

```sh
docker run -it -v $PWD:/e2e -w /e2e -e Cypress cypress/included:3.4.0
```


## Licencia

MIT