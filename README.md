[![GitLab CI Build Status](https://gitlab.com/severinkaderli/ch-bfh-bti7081-s2019-green/badges/develop/build.svg)](https://gitlab.com/severinkaderli/ch-bfh-bti7081-s2019-green/pipelines)
[![Coverage Status](https://coveralls.io/repos/github/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/badge.svg?branch=develop)](https://coveralls.io/github/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green?branch=develop)
[![Codebeat Badge](https://codebeat.co/badges/829114c3-bfb4-45f7-a853-d1b0c264f46d)](https://codebeat.co/projects/github-com-ch-bfh-bti7081-s2019-green-ch-bfh-bti7081-s2019-green-develop)

# Patient Management System - Team Green
## Application
The live production version of the application can be found [here](https://pms.schaer.dev/).

## Usage
This application is currently only compatible with [PostgreSQL](https://www.postgresql.org/). 

1. Install PostgreSQL
2. Create a user called `dbuser` (`createuser --interactive`)
3. Create a database called `pms` (`createdb pms -O dbuser`)
4. Run `mvn compile exec:java@seed` to seed the database
5. Run `mvn jetty:run` to start the application on `localhost:8080`

If you'd like to run PostgreSQL in a docker container, you can use this command:

```{.bash}
sudo docker run -d --rm --name pg-docker -e POSTGRES_USER=pg-docker -e POSTGRES_PASSWORD=pg-docker -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
```

## Documentation
### Diary
The current PDF of the diary can be found [here](https://gitlab.com/severinkaderli/ch-bfh-bti7081-s2019-green/builds/artifacts/develop/raw/diary.pdf?job=Doc:PDF).

### Scrum Document
The current Scrum document can be found [here](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/scrum.ods).

### Javadoc
The Javadoc of the application can be found [here](https://pms.schaer.dev/docs/).

### Tasks
* Task 01
  * [Documentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_01/task_01.pdf)
  * [Presentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_01/task_01_presentation.pdf)
* Task 02
  * [Documentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_02/task_02.pdf)
  * [Presentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_02/task_02_presentation.pdf)
* Task 03
  * [Documentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_03/task_03.pdf)
* Task 04
  * [Documentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_04/task_04.pdf)
  * [Presentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_04/task_04_presentation.pdf)
* Task 05 & 06
  * [Documentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_05_06/task_05_06.pdf)
  * [Presentation](https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green/raw/develop/doc/task_05_06/task_05_06_presentation.pdf)
* Task 08
  * [Documentation](https://gitlab.com/severinkaderli/ch-bfh-bti7081-s2019-green/builds/artifacts/develop/raw/task_08.pdf?job=Doc:PDF)
* Task 09
  * [Presentation](https://gitlab.com/severinkaderli/ch-bfh-bti7081-s2019-green/builds/artifacts/develop/raw/task_09_presentation.pdf?job=Doc:PDF)

