# Feign proof of concept
This is a proof of concept for using Feign.

The endpoints it hits are from a different project I was working on when making this, I do plan to release that project soon on GitHub, and I'll post a link to it here.

There are implementations for two POST endpoint, one of them has a JWT protection, so a token needs to be sent in the header, to get the token there's feign makes a requisition and gets the token to be sent later.

There's also a GET endpoint with JWT protection.
