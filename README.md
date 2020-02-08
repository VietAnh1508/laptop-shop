## Backend
- Language: Java
- Framework: Spring boot
- ORM: Hibernate
- API management library: Swagger

**Building and starting API server**
- From Intellij: just use Run/ Debug button to build and start project
- Using command line (make sure you've installed Maven): `mvn spring-boot:run`
- REST API page: `localhost:8080/`, it will automatically redirect you to Swagger API page: `localhost:8080/swagger-ui.html`

## Frontend
- Framework: [`VueJs`](https://vuejs.org/v2/guide/)
- Material design framework: [`Vuetify`](https://vuetifyjs.com/en/), version: `2.1.1`
- Install Vue CLI: [link](https://cli.vuejs.org/guide/installation.html)

**Setup and run application**
- Install dependencies: `npm install`
- Run application: `npm run serve`
- Web app page: `localhost:8090/`

## Database
- Schema name: `laptop_shop`
- Username: `root`
- Password: `root`

_Note:_ No need to create tables, tables will be generated when backend server is built