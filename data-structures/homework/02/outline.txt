# specs
- stack must have normal stack methods:
    - pop
    - push
    - clear
    - isFull
    - isEmpty

- list items must contain:
    - row
    - column
    - Item
    - list item name? 
        - move
        - pick
        - entity
        - location
        - in chess you make moves, so choose 'move' as list item name

# File System
- Driver.java
    - entrypoint
    - create new Driver
    - create new stack
- Stack.java
    - just a class with stack methods and single top field of type Move
    - contains top
    - `push` method: 
        - creates Move object and assigns data using move.setter()
        - top is equal to this node
- Move.java
    - class with two fields:
        - Move object named `next`
        - Location object named `locaation` which has two fields: row and column
    - class with get/set data, getNext, setNext
        - every time you push, must run setNext
    


# Outline
- Driver contains `main` function
    - build stack
    - set `success` to `false`
    - place move on stack
    - check if prior move has same row, column or diagnal. can check first if is first on stack, if so return w/o problem
    - place second move on stack, repeat check
        - this "check"

- Stack class contains:
    - `int top`, if top == 0 then is empty
    - array of moves

- Move class contains:
    - getter/setter methods
    - next object of type `Move`
    - `when pushing on to stack`:
        - set next to move at the current `top` position
        - update the `top` position
    - `when pulling off the stack`:
        - update the `top` position using move.Next()
    



## push
- push `A` onto stack first, top points to move A
- push `B` onto stack second, top points to move B
- `next` method for B should point to A
    - difference between linked list and stack linked list
    - normal linked list, `next` A would point to B
    - but stack linked list, a is placed first, b is placed on top/ahead of
- `every push must change top and change "next" method`
- does push method require passing top value? 
1. create new node
2. assign data
3. 

## pop
- point `top` to move.Next()
- linked list
    - 