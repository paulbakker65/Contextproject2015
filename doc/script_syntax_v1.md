# Common
## field

Fields are column names surrounded by [ .. ]

The column date would be represented as [date]

# number

A number is defined as either a _decimal number_ or a _integer_

## compareoperator

__compareoperator__ can be any of: ==, !=, <=, <, >=, >

## calcoperator

__calcoperator__ can be any of: ``*, /, +, -, %``

## formula

A formula can be any of the following formats:

[field] __calcoperator__ [field]  
[field] __calcoperator__ number  
[field] __calcoperator__ formula  

## condition

A condition is defined similar to a formula but with the main difference that you cannot use another field in your calculation. It can have any of the following formats:

_compareoperator_ number  
_compareoperator_ AND _compareoperator_  

Thus giving the ability to use nested conditions like so:

< 10 AND > 5 AND <= 7

## range

A range is defined to specify a range between two values:

\> number AND < number

The distance between the two numbers is used to calculate the range width. The range width is used to create appropiate ranges for all records.

# CHUNK operation
CHUNK [field] USING __range__

# CODE operation
CODE [field] ON __condition__

# CONNECT operation
CONNECT [field] TO [anotherField]

# COMPARE operation
__COMPARE__ [field] _compareoperator_ [anotherField]

# CONSTRAINT operation
__CONSTRAINT__ [field] _compareoperator_ [anotherField]  
__CONSTRIANT__ [field] _compareoperator_ value  

# CONVERT operation
__CONVERT__ [field] TO _formula_
