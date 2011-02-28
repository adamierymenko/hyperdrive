Hyperdrive
==========

(c)2010-2011 Adam Ierymenko [adam.ierymenko@gmail.com]<br>
Licensed under the BSD License (http://www.opensource.org/licenses/bsd-license)

Hyperdrive is a simple Java library for creating fast arrays indexed by arbitrary numbers of dimensions
(2d, 3d, 4d, 5d, and so on). It is useful for scientific computing. Run HyperdriveTester to test and
benchmark. Documentation is in the source in the form of JavaDoc.

HyperdriveTester benchmarks Hyperdrive against native Java arrays. It is generally a bit slower for 2d
and 3d arrays but is faster for higher dimensional arrays.

Another advantage of Hyperdrive is that it provides a common interface for arrays of any dimensionality,
enabling the easy creation of algorithms that operate on arrays/matrices with arbitrary numbers of
dimensions.
