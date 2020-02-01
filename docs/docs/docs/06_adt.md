---
layout: docs
title: ADT
permalink: docs/adt
---
# ADT
ADTs (algabraic data type) are types that are composed from smaller types. There is a 1-1 correspondence to algebra (also correspondence to logic as explained by Curry-Howard isomorphism):
- 0: Void
- 1: Unit
- a + b: Either[A, B]
- a * b: (A, B)
- 2 = 1 + 1:
```scala
sealed trait Bool
case object True extends Bool
case object False extends Bool
```
- 1 + a: Option[A]

Types are associate, commutative, and distributive:
```scala
// associative property
// (a + b) + c
Either[Either[A, B], C]

// a + (b + c)
Either[A, Either[B + C]]

// commutative property
// a + b
Either[A. B]

// b + a
Either[B, A]

// distributive property
// a * (b + c)
(A, Either[B, C])

// a * b + a * c
Either[(A, B), (A, C)]
```