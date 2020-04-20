WHAT PROBLEM DOES NORMALIZATION SOLVE?

# 1NF
1. cells are single value
2. column data types are enforced
3. UID exists - eg customer id

# 2NF
1. all attributeds are key dependent
- what columns are tied to the UID
    - eg customer id has dependencies:
        - customer name
        - customer address
- use `foreign key` if primary key can only have 1 value
- if relationship is 1 - N, then use join table or second table, look up all instances of uid in table
    - eg if customer can have many items, or many subscriptions
- if relationship is N - 1, use foreign key
    - eg if customer table has membership_type and membership_price
        - there can be 10 instances of "free_trial" and "price" of $0
        - if you change price, you have to change all intances

# 3NF
1. no columns determine other column values (except key columns)
- if customer table has `state` and `state_abbreviation`, they are always tied together
    - if you needed to update `state_abbreviation`, you'd need to update every single record
- create `foreign key` to fix this
    - foreign key restricts the value which can be input


# 4NF
1. remove `multi-value dependency`
- can the primary key exist twice?
- if it can exist twice bc two different row values exist, there is multi-value dependency
- eg customer table has contact_method field
    - you can have 2 rows bc email and sms can be used
    - again, if you need to update, you'll have to update twice