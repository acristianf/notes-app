# Challenge

<!-- TOC -->
- [Challenge](#challenge)
  - [Note-taking app](#note-taking-app)
    - [Installation](#installation)
    - [Technologies](#technologies)
      - [Backend](#backend)
      - [Backend Documentation](#backend-documentation)
      - [Frontend](#frontend)
      - [Build Technology](#build-technology)
<!-- TOC -->

## Note-taking app

### Installation

The installation depends on docker, there is a sh file in the 'install' folder called 'init.sh' 
that tries to install docker on the system(Linux Ubuntu/debian) and then run the 'docker-compose.yaml' 
file present in the folder

```bash
cd install
sudo bash init.sh
```

If you already have it installed go to the install folder and run:
```markdown
docker compose up
```

If all succeeded the app will be available on 
```markdown
http://localhost:5173/
```

and the api on
```markdown
https://localhost:9001/api/notes
https://localhost:9001/api/categories
```

### Technologies

#### Backend

- Java SDK 17
- Spring 3.1.2
    - Spring JPA
    - Spring Web
    - Springdoc OpenAPI
- PostgreSQL driver
- Maven

- PostgreSQL 15.3

#### Backend Documentation

```markdown
http://localhost:9001/swagger-ui/index.html
```

#### Frontend

- npm 9.8.1
- node 20.5.0
- React 18.2.0
- React-bootstrap 2.8.0
- Bootstrap 5
- axios 1.4.0

#### Build Technology

- Docker 24
- Docker Compose
