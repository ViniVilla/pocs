# JavaMail Proof of concept
This is a proof of concept to use the JavaMail to send an email.

It has just one controller, wich receives a Message, that has a subject, the email it's going to be sent to, and the message itself.

With that information the API creates a MimeMessage to send the email, it puts all this information in it, and uses a HTML file as a template for the email, the message is inserted inside the HTML with Thymeleaf.

The PoC has been updated to use Spring Events to send the email, and also to use Spring Retry in case it ends up failing while trying to send the email, it retries 5 times.
