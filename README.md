# duct-init

This repository is a pre-setup of frequently used functions in duct.

- PostgreSQL (docker)
- migration ([golang-migrate/migrate](https://github.com/golang-migrate/migrate))
- routes ([reitit](https://github.com/metosin/reitit))

## Developing

### Setup

You need to install:

- [homebrew](https://brew.sh)
- [docker](https://docs.docker.com) and docker compose

And,

```bash
./runner setup
```

### Environment

To begin developing, start with a REPL.

```sh
./runner repl
```

Run `go` to prep and initiate the system.

```clojure
dev=> (go)
:duct.server.http.jetty/starting-server {:port 3000}
:initiated
```

By default this creates a web server at <http://localhost:3000>.

When you make changes to your source files, use `reset` to reload any
modified files and reset the server.

```clojure
dev=> (reset)
:reloading (...)
:resumed
```

You can use `(auto-reset)`, `(halt)`, `(exit)`

### Testing

you can run tests through `runner`.

```sh
./runner test
```
