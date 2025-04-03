## Expense Sharing App
### Console Input

## Build and Run

```shell
./gradlew build
```

### Run
```shell
java -jar build/lib/TODO
```

## Example Inputs

```shell
SHOW
SHOW u1
EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
SHOW u4
SHOW u1
EXPENSE u1 1250 2 u2 u3 EXACT 370 880
SHOW
EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
SHOW u1
SHOW
```