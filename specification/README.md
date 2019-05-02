# Specification Proof of concept
This is a proof of concept of how to use specifications on Spring.

It has one controller, that's used to find data from the repository, one of the endpoints uses a manual implementation of specifications from the request parameters that I created, but it's not that good, and the other endpoint uses SpecificationArgResolver, which does most of the work through annotations.

The main point of this PoC was to be able to use SpecificationArgResolver, so I didn't put much time to make the manual implementation that good.

All of the configuration is done in PersonSpecificationArgResolver, there you can see the name of the parameters to be used on the SpecificationArgResolver implementation.
