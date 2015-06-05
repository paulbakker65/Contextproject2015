# Common
## table
A table is represented as its table name surrounded by `[` and `]`.

#### Example
`[sometablename]`

## field
A field is represented as a table combined with a column name surrounded by `[` and `]`. The table-part and the column-part are seperated by a `.`

#### Example
`[sometablename].[somefieldname]`

## pattern
A pattern is defined as _n_ __pattern_description__'s with a number `n > 0`. Each pattern_description is surrounded by `{` and `}`.

## pattern_description
A pattern description is defined as a specification of the number of occurrences and the type of occurrence. A pattern description can follow any of the following four syntaxes.

A number of occurrences followed by a condition that has to occur:

    { number condition }

A wildcard followed by a condition:

    { * condition }

A number of occurrences, regardless of the condition:

    { number [tablename] }

Any number of occurrences, regardless of the condition:

    { wildcard [tablename] }

#### Example
    { 1 [table1] }
    { 1 [table1].[field] > 10 }
    { * [table1] }
    { * [table1].[field] > 10 }

or a combination of patterns:

    { 1 [table1] } { 2 [table2] } { * [table3].[field] == DATE(2012-01-01) }

## value
A value can be any of the following type of values:

### number
A number can be represented as a integer or a decimal number.

    10
    10.0

### date
A date is represented as a ISO standard date in a `yyyy-mm-dd` format surrounded by `DATE()`

    DATE(2012-01-01)
    DATE(2012-05-20)

### string
A string is represented as some text surrounded by quotes.

    "The quick brown fox jumps over the laxy fox."

## compare_operator
A compare operator can be any of the usual operators for comparison:

    ==
    !=
    <
    <=
    >
    >=

## calc_operator
A calc operator can be any of the usual operators for calculation:

    *
    /
    +
    -
    %

## formula
A formula is defined as a field, a calc_operator and either one of field, value and a formula.

    field calc_operator field
    field calc_operator number
    field calc_operator formula

## condition
A condition is defined as a compare_operator and a to be compared value.

    compare_operator value
    compare_operator value AND condition

## range
A range is defined as two values, a lower bound and a higher bound.

    > value AND < value

# Data analysis Operations
## Code Operation
A coding operation is defined as the `CODE` keyword combined tablename, a pattern and a name to assign to the operation.

It uses the following syntax:

    CODE [tablename] ON pattern AS string

#### Example

    CODE [sometablename] ON { 1 [sometablename] } { 5 [sometablename].[field] > 100 } AS "this is a code name"

## Compare Operation
### Between Operation
The between operation is defined as either an event field, a date column, a value and a second value to measure between. Or as an event field, a date column, a second date column, a value and a value to measure between.

    BETWEEN field(event field) field(date column 1) field(date column 2) value(event 1 value) value(event 2 value)
    BETWEEN field(event field) field(date column) value(event 1 value) value(event 2 value)

#### Example

    BETWEEN [tablename].[event] [tablename].[Date] 10 20
    BETWEEN [tablename].[event] [tablename].[Date] [tablename].[ModifiedDate] 10 20

### Lsa Operation
The Lsa operation is defined as a field that holds the event, a lower bound lag, an upper bound lag, a key event value and a target event value.

    LSA field(event field) number(lower bound) number(upper bound) value(event key) value(event where the lag is calculated between)

#### Example

    LSA [tablename].[event] 0 1000 "event 1 occurred" "event 2 occurred"

## Connect Operation
The connect operation is defined as two field's to connect.

    CONNECT field TO field

#### Example

    CONNECT [tablename].[fieldname] TO [anotherfieldname].[fieldname]

## Constraint operation
The constraint operation is defined as a field, a compare operator and any of a value or a field.

    CONSTRAINT field compare_operator field
    CONSTRAINT field compare_operator value

#### Example

    CONSTRAINT [tablename].[fieldname] > 100
    CONSTRAINT [tablename].[fieldname] < [tablename].[anotherfieldname]

## Chunk operation
The chunk operation is defined as a field and any of a range or a chunk type.

    CHUNK field USING range
    CHUNK field USING YEAR number
    CHUNK field USING MONTH number
    CHUNK field USING DAY number

#### Example

    CHUNK [tablename].[fieldname] USING MONTH 1
    CHUNK [tablename].[fieldname] USING DAY 1
    CHUNK [tablename].[fieldname] USING > 1 AND < 5
