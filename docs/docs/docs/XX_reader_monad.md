---
layout: docs
title: Reader Monad
permalink: docs/reader-monad/
---
# Reader Monad

```scala
import cats.data.Reader 

case class DatabaseConfig(
    host: String,
    username: String,
    password: String,
    port: Int
)

def validHost = Reader[DatabaseConfig, Boolean] { _.host.length > 3}
def validPort = Reader[DatabaseConfig,  Boolean] { _.port == 443 }
def validPassword = Reader[DatabaseConfig, Boolean] { _.password.length > 8}

val isValid = for {
    isValidHost <- validHost
    isValidPort <- validPort
    isValidPassword <- validPassword
} yield isValidHost && isValidPort && isValidPassword

isValid.run(DatabaseConfig("host", "username", "pass", 443)) // false
isValid.run(DatabaseConfig("host", "username", "password1", 443)) // true


def validHost(host: String): Boolean = host.length > 3
def validPort(port: Int): Boolean = port == 443
def validPassword(p: String): Boolean = p.length > 8

def isValid(db: DatabaseConfig): Boolean = validHost(db.host) && validPort(db.port) && validPassword(db.password)

isValid(DatabaseConfig("host", "username", "pass", 443)) // false
isValid(DatabaseConfig("host", "username", "password1", 443)) // true
```

## Dependency Injection
```scala
trait CrudDAO {
    def create(): String
    def read(): String
    def update(): String
    def delete(): String
}

class UserDAO extends CrudDAO {
    def create() = "create user"
    def read() = "read user"
    def update() = "update user"
    def delete() = "delete user"
}

class AdminDAO extends CrudDAO {
    def create() = "create admin user"
    def read() = "read admin user"
    def update() = "update admin user"
    def delete() = "delete admin user"
}
// Traiditional DI
class UserService(dao: CrudDAO) {
    def createAndUpdateUser(): String = dao.create() + dao.update()
}

class UseService {
    println(new UserService(new UserDAO).createAndUpdateUser())
}

// Reader Monad DI
trait UserService {
    def createAndUpdateUser = Reader[CrudDAO, String] {dao => dao.create() + dao.update()}
}

class UseService extends UserService {
    println(createAndUpdateUser.run(new UserDAO))
}
```