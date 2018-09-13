# venezuela

Server implementation for Pale Blue Foundation's Bitcoin wallet

## Install

```
git submodule init
git submodule update

createuser azul
createdb --owner=azul azuldb_dev

lein migratus migrate
```

## Usage

```
gradle clean
gradle generateProto

lein repl
> (venezuela.rpc.server/start-server 8080)
```

## License

Copyright © 2018 Pale Blue Foundation
