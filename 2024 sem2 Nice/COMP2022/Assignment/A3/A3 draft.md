
## 1
(a)
In the
$$
ab,abab,ababab,abababab,\epsilon 
$$

Not in the
$$
a,b,ba,bb,aa
$$
(b)
```SQL
0 _ _ l halt-accept
0 a _ r 1
0 b b r halt-reject

1 b _ r 0
1 a a r halt-reject
1 _ _ r halt-reject
```

## 2
```
0 _ _ * halt-reject  
0 a a r 0  
0 b b r 0  
0 b x l 1  

1 x x l 1  
1 a x r 2  
1 b x r 2  
1 _ _ r 4  

2 x x r 2  
2 a x r 3  
2 b x r 3  
2 _ _ * halt-reject  

3 x x r 3  
3 a x l 1  
3 b x l 1  
3 _ _ * halt-reject  

4 x x r 4  
4 a a * halt-reject  
4 b b * halt-reject  
4 _ _ * halt-accept  

```

$MbN$, where $2|M| = |N|$, $M,N \in \Sigma^*$ 

(a)
in:
$$
abab,b,abba,abaa,abbb
$$
not in 
$$
\epsilon,ab,a,aa,aaa
$$


(b)
```SQL
0 _ _ R halt-reject
0 a _ R 3
0 b b R 1

1 _ _ L halt-accept
1 * * L 2

2 a _ R 3
2 b _ R 3

3 * * R 3
3 _ _ L 4

4 * _ L 5
4 _ _ L halt-reject

5 * _ L 6
5 _ _ L halt-reject

6 * * L 6
6 _ _ R 0
```

## 3
### 3.1 
```SQL
0 * * r 0
0 _ _ l 1

1 _ _ l halt-reject
1 a a l A
1 b b l B
1 c c l C

;一路向左
A * * l A
A _ _ R A0

B * * l B
B _ _ R B0

C * * l C
C _ _ R C0

; 0 -> 1
A0 a a R A1
A0 b b R A0
A0 c c R A0

B0 a a R B0
B0 b b R B1
B0 c c R B0

C0 a a R C0
C0 b b R C0
C0 c c R C1

; 1 -> 2
A1 a a R A2
A1 b b R A1
A1 c c R A1
A1 _ _ R halt-accept

B1 a a R B1
B1 b b R B2
B1 c c R B1
B1 _ _ R halt-accept

C1 a a R C1
C1 b b R C1
C1 c c R C2
C1 _ _ R halt-accept

; 2 -> 3
A2 a a R A3
A2 b b R A2
A2 c c R A2
A2 _ _ R halt-accept

B2 a a R B2
B2 b b R B3
B2 c c R B2
B2 _ _ R halt-accept

C2 a a R C2
C2 b b R C2
C2 c c R C3
C2 _ _ R halt-accept

; 3 boooo~~~
A3 a a R halt-reject
A3 b b R A3
A3 c c R A3
A3 _ _ R halt-accept

B3 a a R B3
B3 b b R halt-reject
B3 c c R B3
B3 _ _ R halt-accept

C3 a a R C3
C3 b b R C3
C3 c c R halt-reject
C3 _ _ R halt-accept


```

### 3.2

```SQL
; 删a
0 _ _ * halt-reject
0 * * * T

T a X R 1
T X X R T
T _ _ R halt-accept
T b b R halt-reject
T c c R halt-reject

; 删b
1 b X R 2
1 X X R 1
1 a a R 1
1 c c R halt-reject
1 _ _ R halt-reject

; 删c
2 c X R 3;
2 X X R 2
2 b b R 2
2 a a R halt-reject
2 _ _ R halt-reject

; 删a
3 a X L 4
3 X X R 3
3 c c R 3
3 b b R halt-reject
3 _ _ R halt-reject

; 回左边
4 * * L 4
4 _ _ R T
```

### 3.3
version 1
```SQL
; initialize
0 * * * N

; N
N a X R NA0
N b X R NB0
N c X R NC0
N X X R N
N _ _ R halt-accpet

; O
O a X R OA0
O b X R OB0
O c X R OC0
O X X R O
O _ _ R halt-accpet

; T
T a X R TA0
T b X R TB0
T c X R TC0
T X X R T
T _ _ R halt-accpet

;====================<A>==================
; NA到右边
NA0 * * R NA0
NA0 X X L NA1
NA0 _ _ L NA1

NA1 a X L NL
NA1 b X L OL
NA1 c X L OL

NA1 X X L halt-accept

; OA到右边
OA0 * * R OA0
OA0 X X L OA1

OA1 a X L OL
OA1 b X L TL
OA1 c X L TL

OA1 X X L halt-accept

; TA到右边
TA0 * * R TA0
TA0 X X L TA1

TA1 a X L TL
TA1 b X L halt-reject
TA1 c X L halt-reject

TA1 X X L halt-accept

;====================<B>==================

; NB到右边
NB0 * * R NB0
NB0 X X L NB1
NB0 _ _ L NB1

NB1 a X L OL
NB1 b X L NL
NB1 c X L OL

NB1 X X L halt-accept

; OB到右边
OB0 * * R OB0
OB0 X X L OB1

OB1 a X L TL
OB1 b X L OL
OB1 c X L TL

OB1 X X L halt-accept

; TB到右边
TB0 * * R TB0
TB0 X X L TB1

TB1 a X L halt-reject
TB1 b X L TL
TB1 c X L halt-reject

TB1 X X L halt-accept

;====================<C>==================

; NC到右边
NC0 * * R NC0
NC0 X X L NC1
NC0 _ _ L NC1

NC1 a X L OL
NC1 b X L OL
NC1 c X L NL

NC1 X X L halt-accept

; OC到右边
OC0 * * R OC0
OC0 X X L OC1

OC1 a X L TL
OC1 b X L TL
OC1 c X L OL

OC1 X X L halt-accept

; TC到右边
TC0 * * R TC0
TC0 X X L TC1

TC1 a X L halt-reject
TC1 b X L halt-reject
TC1 c X L TL

TC1 X X L halt-accept



;====================<Support>==================

; NL 向左
NL * * L NL
NL X X R N

; OL 向左
OL * * L OL
OL X X R O

; TL 向左
TL * * L TL
TL X X R T
```

version 2
```SQL


```
### 3.4
```SQl
0 a a R T
0 b b R P
0 c c R P

P * * L D0
P _ _ L D0

T _ _ L halt-accept
T * * L D0

;删除A
;D0: ab都能删
;D1:能删b
;D2:能删a

D0 a X R D1
D0 b X R D2
D0 c c R D0
D0 X X R D0
D0 _ _ L L

D1 b X R D2
D1 a a R D1
D1 c c R D0
D1 X X R D0
D1 _ _ L L

D2 a X R D1
D2 b b R D2
D2 c c R D0
D2 X X R D0
D2 _ _ L L

L a a L La
L b b L Lb
L c c L L
L X X L L
L _ _ L halt-reject

La a a L La
La b b L Lab
La c c L La
La X X L La
La _ _ L halt-accept

Lb b b L Lb
Lb a a L Lab
Lb c c L Lb
Lb X X L Lb
Lb _ _ L halt-reject

Lab * * L Lab
Lab _ _ R D0


```

### 3.5

```SQL
0 * * R P0
0 _ _ L halt-reject

P0 * * L C0

P1 _ _ R T0

;C 检查是不是只包含1个c,在最后加X

C0 * * R C0
C0 _ _ R halt-reject
C0 c c R C1 ; 1c

C1 a a R C1
C1 b b R C1
C1 _ X L L0
C1 c c R halt-reject ;2c rejected

;L0, L0 -> 回到左边
L0 * * L L0
L0 _ _ R S0

;L1, 回到左边然后去判断(T-test)
L1 * * L L1
L1 _ _ R T0

;S0 check string = 'c'
S0 c c R S1 ;first letter is c
S0 a a L P1 ; P1 -> T0
S0 b b L P1 ; P1 -> T0

S1 X X R halt-accept 
S1 a a L halt-reject ;first letter is c, 但是后面不为空
S1 b b L halt-reject ;first letter is c, 但是后面不为空

; D
D0 a a R D0
D0 b b R D0
D0 c c R D1;过了c

D1 P P R D1
D1 X X R halt-reject
D1 a P R D2a;读取到P后一个
D1 b P R D2b

D2a a a R D2a
D2a b b R D2a
D2a X X R D3a ;过了分界线

D2b a a R D2b
D2b b b R D2b
D2b X X R D3b ;过了分界线

D3a _ a L L1
D3a * * R D3a

D3b _ b L L1
D3b * * R D3b

; T 检查
T0 a A R T1a
T0 b B R T1b
T0 c c R TF

;T1,读取左边字符
T1a a a R T1a
T1a b b R T1a
T1a c c R T2a

T1b a a R T1b
T1b b b R T1b
T1b c c R T2b

;T2已经过河,做判断
T2a b b L TL
T2a a A L T3
T2a P P R T2a
T2a A A R T2a
T2a B B R T2a
T2a X X R T2a

T2b b B L T3
T2b a a L TL
T2b P P R T2b
T2b A A R T2b
T2b B B R T2b
T2b X X R T2b

;T3 判断成功,回去
T3 A A L T3
T3 B B L T3
T3 P P L T3
T3 X X L T3
T3 c c L T4

;T4
T4 a a L T4
T4 b b L T4
T4 A A R T0
T4 B B R T0

;TF,左边消耗完了,看右边有没有的多,或者右边比较成功
TF A A R TF
TF B B R TF
TF P P R TF
TF X X R TF
TF a a R halt-reject;c左边比c右边少
TF b b R halt-reject;c左边比c右边少
TF _ _ R halt-accept

;TL 回退并且清理
TL B b L TL
TL A a L TL
TL a a L TL
TL b b L TL
TL c c L TL
TL P P L TL
TL X X L TL
TL _ _ R D0 ;清理完毕尝试下一个组合


```


### 3.6
```sql
0 * * * P0
0 _ _ * halt-reject

P0 * * R P0
P0 _ # L P1

P1 * * L P1
P1 _ _ R T0

;T0 can delete abc
T0 a A R T1
T0 b B R T2
T0 c C R T3
T0 * * R T0
T0 # # R halt-accept

;T1 can delete bc
T1 a a R T1
T1 b B R T6
T1 c C R T5
T1 * * R T1
T1 # # L S0

;T2 can delete ac
T2 a A R T6
T2 b b R T2
T2 c C R T4
T2 * * R T2
T2 # # L S0

;T3 can delete ab
T3 a A R T5
T3 b B R T4
T3 c c R T3
T3 * * R T3
T3 # # L S0

;T4 can delete a
T4 a A * T7
T4 # # L S0
T4 * * R T4

;T5 can delete b
T5 b B * T7
T5 # # L S0
T5 * * R T5

;T6 can delete c
T6 c C * T7
T6 * * R T6
T6 # # L S0

;T7 go left
T7 * * L T7
T7 X X R T0
T7 Y Y R T0
T7 Z Z R T0
T7 _ _ R T0

;S

;S0复原

S0 A a L S0
S0 B b L S0
S0 C c L S0

S0 a a L S0
S0 b b L S0
S0 c c L S0

S0 _ _ R S1
S0 X X R S1
S0 Y Y R S1
S0 Z Z R S1

;S1设置下一个左侧界限
S1 a X R TP0
S1 b Y R TP0
S1 c Z R TP0

TP0 # # L TP1 
TP0 * * * T0

TP1 * # L TP2

TP2 Z c L TP3
TP2 Y b L TP3
TP2 X a L TP3
TP2 _ _ R halt-reject

TP3 Z c L TP3
TP3 Y b L TP3
TP3 X a L TP3
TP3 _ _ R T0
```


### 