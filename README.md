# Практическрая работа №6
### Вариант №15: Посуда

## Инструкция по сборке и запуску
Убедитесь, что на вашем компьютере присутствует [JDK](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)

```
git clone https://github.com/waffflezz/rkis-6.git
cd rkis-6
psql -U postgres -h localhost -f create_db.sql
sh mvnw package
java -jar target/rkis-6-0.0.1-SNAPSHOT.jar
```

Открываем в браузере [страницу localhost](http://127.0.0.1:8080)

Так как мы шифруем пароли, пользователя _admin_ необходимо создавать вручную. Сделать это можно присвоив _username_ значение _admin_ (`username = admin`)

_Для сборки необходимо иметь [Maven](https://maven.apache.org/download.cgi) на компьютере_

## Примеры работы авторизации
Страница входа:

![image](https://github.com/waffflezz/rkis-5/assets/56751225/220525f1-d053-4fc9-8407-8b2fca0b2fe7)


Страница регистрации:

![image](https://github.com/waffflezz/rkis-5/assets/56751225/e54efd86-fb22-497b-bf2a-f6cb87af88d2)
