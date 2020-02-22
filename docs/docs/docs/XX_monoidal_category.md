---
layout: docs
title: Monoidal Category
permalink: docs/monoidal-category/
---
# Monidal Category (Tensor Category)
Category C equipped with monoidal structure:
- bifunctor ⊗: C x C -> C called _tensor product_
- object I called unit object

## Vector
__Vector__: a quantity with magnitude and direction
- Can represent area; make magnitude equal to size of area and make direction perpindicular to the area.

Imagine a 3 dimensional coordinate system. It comes equiped with basis vectors, or vectors with length 1 with direction of coordinate axes; i-hat, j-hat, k-hat.

Vector has vector components: the projection of the vector on the axis. For example, vector 5i + 6j + 0k. Can also be expressed as array [5, 6, 0] or column vector.

Can generalize vector definition to be Ax(i-hat) + Ay(j-hat) + Az(k-hat). There is one value per basis vector because there is one basis vector per component. Vector is a tensor of rank one; 1 index + 1 basis vector per component.

## Tensor
When requesting information, there are sometimes multiple components necessary for a sufficient answer. Take a square in x-y coordinate system. Imagine applying a force to it. It can be applied in a perpendicular way (normal stress) or in a parallel way (shear stress)
- How strong is force? 5lb (scalar, rank 0 tensor)
- How strong and which direction is force? 2i + 3j (vector, rank 1 tensor)
- What is the force on a give side? 1ii + 1ij + 0ji + 0jj (rank 2 tensor)

Notice that the rank corresponds to the number of basis vectors necessary to describe a component.

## Tensor Product
A tensor product is an operation that combines (multiplies) to vector spaces.

Let V be an _n_ dimensional vector space and W be an _m_ vector space. As an example, let n = 2 and m = 3.

A vector in V could be [4, 5]. A vector in W could be [6, 3, 1]. The direct product of them is [24, 12, 4, 30, 15, 5]. This product lives in a vector space of n*m dimension, or in this case 6.

We can also view the product aboveas a vector space of pairs: [(4, 6), (4 ,3), (4, 1), ...]

Now lets go back to product of two vector spaces, not two vectors. The intuition is similar, but with a twist.

In a vector space, scalar multiplication works like this: a vector [3, 4] multiplied by a scalar 2 multiples the scalar by each component: [6, 8]. With tensors, scalar multiplication applies to whichever component you want, and they are equivalent.

Scalar addition is changed to work only if one of components are the same. (v, w) + (v1, w) = (v + v1, w). Other additions are new vectors like (v, w) + (v1, w1) which would be its own vector.

Take two vector spaces of real numbers and apply the new rules:

```
3 ⊗ 5 + 1 ⊗ (-5) = 2 ⊗ 5
(3, 5) + (1, -5) = ...
(3, 5) + (-1, 5) = ...
(3 - 1, 5)       = ...
(2, 5)           = ...
2 ⊗ 5            = 2 ⊗ 5
```

## Links
[What's a tensor?](https://www.youtube.com/watch?v=f5liqUk0ZTw)

[How to conquer tensorphobia?](https://jeremykun.com/2014/01/17/how-to-conquer-tensorphobia/)

[The tensor product demystified](https://www.math3ma.com/blog/the-tensor-product-demystified)